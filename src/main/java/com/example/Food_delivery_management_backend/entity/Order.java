package com.example.Food_delivery_management_backend.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false, length = 30)
    private OrderStatus orderStatus;

    @Column(name = "total_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @CreationTimestamp
    @Column(name = "order_date", nullable = false, updatable = false)
    private LocalDateTime orderDate;

    @Column(name = "accepted_at")
    private LocalDateTime acceptedAt;

    @Column(name = "rejected_at")
    private LocalDateTime rejectedAt;

    @Column(name = "estimated_pickup_time")
    private LocalDateTime estimatedPickupTime;

    @Column(name = "delivered_at")
    private LocalDateTime deliveredAt;

    @Column(name = "delivery_address", nullable = false, length = 500)
    private String deliveryAddress;

    @Column(name = "rejection_reason", length = 500)
    private String rejectionReason;

    @Column(name = "special_instructions", length = 1000)
    private String specialInstructions;



    public Order(Long id, Customer customer, Restaurant restaurant, OrderStatus orderStatus,
                 BigDecimal totalAmount, LocalDateTime orderDate, LocalDateTime acceptedAt,
                 LocalDateTime rejectedAt, LocalDateTime estimatedPickupTime, LocalDateTime deliveredAt,
                 String deliveryAddress, String rejectionReason, String specialInstructions) {
        this.id = id;
        this.customer = customer;
        this.restaurant = restaurant;
        this.orderStatus = orderStatus;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.acceptedAt = acceptedAt;
        this.rejectedAt = rejectedAt;
        this.estimatedPickupTime = estimatedPickupTime;
        this.deliveredAt = deliveredAt;
        this.deliveryAddress = deliveryAddress;
        this.rejectionReason = rejectionReason;
        this.specialInstructions = specialInstructions;
    }

    public Order() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDateTime getAcceptedAt() {
        return acceptedAt;
    }

    public void setAcceptedAt(LocalDateTime acceptedAt) {
        this.acceptedAt = acceptedAt;
    }

    public LocalDateTime getRejectedAt() {
        return rejectedAt;
    }

    public void setRejectedAt(LocalDateTime rejectedAt) {
        this.rejectedAt = rejectedAt;
    }

    public LocalDateTime getEstimatedPickupTime() {
        return estimatedPickupTime;
    }

    public void setEstimatedPickupTime(LocalDateTime estimatedPickupTime) {
        this.estimatedPickupTime = estimatedPickupTime;
    }

    public LocalDateTime getDeliveredAt() {
        return deliveredAt;
    }

    public void setDeliveredAt(LocalDateTime deliveredAt) {
        this.deliveredAt = deliveredAt;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }
}
