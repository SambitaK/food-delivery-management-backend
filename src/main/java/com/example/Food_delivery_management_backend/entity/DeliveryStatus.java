package com.example.Food_delivery_management_backend.entity;

public enum DeliveryStatus {
    ASSIGNED,                      // Driver assigned to delivery
    DRIVER_EN_ROUTE_TO_RESTAURANT, // Driver heading to restaurant
    PICKED_UP,                     // Food picked up from restaurant
    OUT_FOR_DELIVERY,              // Driver heading to customer
    DELIVERED                      // Successfully delivered
}
