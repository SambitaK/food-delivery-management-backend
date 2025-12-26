package com.example.Food_delivery_management_backend.service;

import com.example.Food_delivery_management_backend.entity.*;
import com.example.Food_delivery_management_backend.repository.OrderItemRepository;
import com.example.Food_delivery_management_backend.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CustomerService customerService;
    private final RestaurantService restaurantService;
    private final MenuItemService menuItemService;
    private final PaymentService paymentService;

    private final DeliveryService deliveryService;

    public OrderService(OrderRepository orderRepository,
                        OrderItemRepository orderItemRepository,
                        CustomerService customerService,
                        RestaurantService restaurantService,
                        MenuItemService menuItemService,
                        PaymentService paymentService,
                        DeliveryService deliveryService) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.customerService = customerService;
        this.restaurantService = restaurantService;
        this.menuItemService = menuItemService;
        this.paymentService = paymentService;
        this.deliveryService = deliveryService;
    }

    @Transactional
    public Order placeOrder(Long customerId, Long restaurantId,
                            List<OrderItemRequest> items,
                            String deliveryAddress,
                            String specialInstructions,
                            PaymentMethod paymentMethod) {

        //  validating customer and restaurant
        Customer customer = customerService.findByUserId(customerId);
        Restaurant restaurant = restaurantService.findById(restaurantId);

        if (!restaurant.getIsActive()) {
            throw new RuntimeException("Restaurant is not accepting orders");
        }

        // creating order
        Order order = new Order();
        order.setCustomer(customer);
        order.setRestaurant(restaurant);
        order.setOrderStatus(OrderStatus.PLACED);
        order.setDeliveryAddress(deliveryAddress);
        order.setSpecialInstructions(specialInstructions);
        order.setTotalAmount(BigDecimal.ZERO); // Will calculate below

        // saving order first to get ID
        order = orderRepository.save(order);

        // creating order items and calculating total
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (OrderItemRequest itemRequest : items) {
            MenuItem menuItem = menuItemService.findById(itemRequest.getMenuItemId());

            // verifying menu item belongs to this restaurant
            if (!menuItem.getRestaurant().getId().equals(restaurantId)) {
                throw new RuntimeException("Menu item does not belong to this restaurant");
            }

            if (!menuItem.getIsAvailable()) {
                throw new RuntimeException("Menu item not available: " + menuItem.getItemName());
            }

            // creating order item with price snapshot
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setMenuItem(menuItem);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setItemPrice(menuItem.getPrice());

            BigDecimal subtotal = menuItem.getPrice()
                    .multiply(BigDecimal.valueOf(itemRequest.getQuantity()));
            orderItem.setSubtotal(subtotal);
            orderItem.setSpecialInstructions(itemRequest.getSpecialInstructions());

            orderItemRepository.save(orderItem);

            totalAmount = totalAmount.add(subtotal);
        }

        // updating order total
        order.setTotalAmount(totalAmount);
        order = orderRepository.save(order);

        // processing payment
        Payment payment = paymentService.processPayment(
                order, customer, totalAmount, paymentMethod
        );

        if (payment.getPaymentStatus() != PaymentStatus.COMPLETED) {
            throw new RuntimeException("Payment failed");
        }

        return order;
    }



    @Transactional
    public Order acceptOrder(Long orderId, Integer estimatedMinutes) {
        Order order = findById(orderId);

        if (order.getOrderStatus() != OrderStatus.PLACED) {
            throw new RuntimeException("Order cannot be accepted - current status: "
                    + order.getOrderStatus());
        }

        order.setOrderStatus(OrderStatus.ACCEPTED);
        order.setAcceptedAt(LocalDateTime.now());
        order.setEstimatedPickupTime(LocalDateTime.now().plusMinutes(estimatedMinutes));

        order = orderRepository.save(order);

        // auto-assigning driver and creating delivery
        try {
            deliveryService.assignDriver(order, order.getRestaurant());
        } catch (RuntimeException e) {
            // if no drivers available, order stays ACCEPTED but without delivery
            System.out.println("Warning: Could not assign driver - " + e.getMessage());
        }

        return order;
    }

    @Transactional
    public Order rejectOrder(Long orderId, String reason) {
        Order order = findById(orderId);

        if (order.getOrderStatus() != OrderStatus.PLACED) {
            throw new RuntimeException("Order cannot be rejected - current status: "
                    + order.getOrderStatus());
        }

        order.setOrderStatus(OrderStatus.REJECTED);
        order.setRejectedAt(LocalDateTime.now());
        order.setRejectionReason(reason);

        order = orderRepository.save(order);

        // processing refund
        Payment payment = paymentService.findByOrderId(orderId);
        paymentService.processRefund(payment.getId());

        return order;
    }

    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
    }

    public List<Order> getCustomerOrders(Long customerId) {
        return orderRepository.findByCustomerIdOrderByOrderDateDesc(customerId);
    }

    public List<Order> getRestaurantOrders(Long restaurantId) {
        return orderRepository.findByRestaurantId(restaurantId);
    }

    public List<OrderItem> getOrderItems(Long orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }

    // inner class for order item request
    public static class OrderItemRequest {
        private Long menuItemId;
        private Integer quantity;
        private String specialInstructions;

        public OrderItemRequest() {}

        public Long getMenuItemId() {
            return menuItemId;
        }

        public void setMenuItemId(Long menuItemId) {
            this.menuItemId = menuItemId;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public String getSpecialInstructions() {
            return specialInstructions;
        }

        public void setSpecialInstructions(String specialInstructions) {
            this.specialInstructions = specialInstructions;
        }
    }
}