package com.example.Food_delivery_management_backend.dto;


import com.example.Food_delivery_management_backend.entity.PaymentMethod;
import com.example.Food_delivery_management_backend.service.OrderService;

import java.util.List;

public class PlaceOrderRequest {
    private Long customerId;
    private Long restaurantId;
    private List<OrderService.OrderItemRequest> items;
    private String deliveryAddress;
    private String specialInstructions;
    private PaymentMethod paymentMethod;

    public PlaceOrderRequest() {
    }


    public Long getCustomerId() {
        return customerId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public List<OrderService.OrderItemRequest> getItems() {
        return items;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }


    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public void setItems(List<OrderService.OrderItemRequest> items) {
        this.items = items;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}