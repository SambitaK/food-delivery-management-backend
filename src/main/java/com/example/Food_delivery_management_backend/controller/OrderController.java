package com.example.Food_delivery_management_backend.controller;


import com.example.Food_delivery_management_backend.dto.PlaceOrderRequest;
import com.example.Food_delivery_management_backend.entity.Order;
import com.example.Food_delivery_management_backend.entity.OrderItem;
import com.example.Food_delivery_management_backend.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;


import java.util.List;


@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping
    public ResponseEntity<?> placeOrder(@RequestBody PlaceOrderRequest request) {
        try {
            Order order = orderService.placeOrder(
                    request.getCustomerId(),
                    request.getRestaurantId(),
                    request.getItems(),
                    request.getDeliveryAddress(),
                    request.getSpecialInstructions(),
                    request.getPaymentMethod()
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(order);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Order placement failed: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {
        try {
            Order order = orderService.findById(id);
            return ResponseEntity.ok(order);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Order not found: " + e.getMessage());
        }
    }

    @GetMapping("/{id}/items")
    public ResponseEntity<List<OrderItem>> getOrderItems(@PathVariable Long id) {
        List<OrderItem> items = orderService.getOrderItems(id);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Order>> getCustomerOrders(@PathVariable Long customerId) {
        List<Order> orders = orderService.getCustomerOrders(customerId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Order>> getRestaurantOrders(@PathVariable Long restaurantId) {
        List<Order> orders = orderService.getRestaurantOrders(restaurantId);
        return ResponseEntity.ok(orders);
    }

    @PreAuthorize("hasRole('RESTAURANT')")
    @PatchMapping("/{id}/accept")
    public ResponseEntity<?> acceptOrder(@PathVariable Long id,
                                         @RequestParam Integer estimatedMinutes) {
        try {
            Order order = orderService.acceptOrder(id, estimatedMinutes);
            return ResponseEntity.ok(order);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Cannot accept order: " + e.getMessage());
        }
    }

    @PreAuthorize("hasRole('RESTAURANT')")
    @PatchMapping("/{id}/reject")
    public ResponseEntity<?> rejectOrder(@PathVariable Long id,
                                         @RequestParam String reason) {
        try {
            Order order = orderService.rejectOrder(id, reason);
            return ResponseEntity.ok(order);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Cannot reject order: " + e.getMessage());
        }
    }
}