package com.example.Food_delivery_management_backend.entity;

public enum OrderStatus {
    PLACED,           // Order just created, payment completed
    ACCEPTED,         // Restaurant accepted the order
    REJECTED,         // Restaurant rejected (customer gets refund)
    PREPARING,        // Restaurant is preparing food
    READY_FOR_PICKUP, // Food ready, waiting for driver
    OUT_FOR_DELIVERY, // Driver picked up, on the way
    DELIVERED,        // Successfully delivered
    CANCELLED         // Customer or system cancelled
}
