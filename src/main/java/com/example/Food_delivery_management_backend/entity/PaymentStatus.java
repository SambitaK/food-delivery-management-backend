package com.example.Food_delivery_management_backend.entity;

public enum PaymentStatus {
    PENDING,    // Payment initiated
    COMPLETED,  // Payment successful
    FAILED,     // Payment failed
    REFUNDED    // Money returned to customer
}
