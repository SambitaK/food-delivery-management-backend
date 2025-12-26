package com.example.Food_delivery_management_backend.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "deliveries")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false, unique = true)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @Column(name = "pickup_address", nullable = false, length = 500)
    private String pickupAddress;

    @Column(name = "delivery_address", nullable = false, length = 500)
    private String deliveryAddress;

    @Column(name = "estimated_pickup_time")
    private LocalDateTime estimatedPickupTime;

    @Column(name = "actual_pickup_time")
    private LocalDateTime actualPickupTime;

    @Column(name = "estimated_delivery_time")
    private LocalDateTime estimatedDeliveryTime;

    @Column(name = "actual_delivery_time")
    private LocalDateTime actualDeliveryTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_status", nullable = false, length = 50)
    private DeliveryStatus deliveryStatus;

    @Column(name = "driver_notes", length = 1000)
    private String driverNotes;

    @CreationTimestamp
    @Column(name = "assigned_at", nullable = false, updatable = false)
    private LocalDateTime assignedAt;


    public Delivery() {
    }

    public Delivery(Long id, Order order, Driver driver, Restaurant restaurant,
                    String pickupAddress, String deliveryAddress,
                    LocalDateTime estimatedPickupTime, LocalDateTime actualPickupTime,
                    LocalDateTime estimatedDeliveryTime, LocalDateTime actualDeliveryTime,
                    DeliveryStatus deliveryStatus, String driverNotes, LocalDateTime assignedAt) {
        this.id = id;
        this.order = order;
        this.driver = driver;
        this.restaurant = restaurant;
        this.pickupAddress = pickupAddress;
        this.deliveryAddress = deliveryAddress;
        this.estimatedPickupTime = estimatedPickupTime;
        this.actualPickupTime = actualPickupTime;
        this.estimatedDeliveryTime = estimatedDeliveryTime;
        this.actualDeliveryTime = actualDeliveryTime;
        this.deliveryStatus = deliveryStatus;
        this.driverNotes = driverNotes;
        this.assignedAt = assignedAt;
    }


    public Long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public Driver getDriver() {
        return driver;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public LocalDateTime getEstimatedPickupTime() {
        return estimatedPickupTime;
    }

    public LocalDateTime getActualPickupTime() {
        return actualPickupTime;
    }

    public LocalDateTime getEstimatedDeliveryTime() {
        return estimatedDeliveryTime;
    }

    public LocalDateTime getActualDeliveryTime() {
        return actualDeliveryTime;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public String getDriverNotes() {
        return driverNotes;
    }

    public LocalDateTime getAssignedAt() {
        return assignedAt;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void setEstimatedPickupTime(LocalDateTime estimatedPickupTime) {
        this.estimatedPickupTime = estimatedPickupTime;
    }

    public void setActualPickupTime(LocalDateTime actualPickupTime) {
        this.actualPickupTime = actualPickupTime;
    }

    public void setEstimatedDeliveryTime(LocalDateTime estimatedDeliveryTime) {
        this.estimatedDeliveryTime = estimatedDeliveryTime;
    }

    public void setActualDeliveryTime(LocalDateTime actualDeliveryTime) {
        this.actualDeliveryTime = actualDeliveryTime;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public void setDriverNotes(String driverNotes) {
        this.driverNotes = driverNotes;
    }

    public void setAssignedAt(LocalDateTime assignedAt) {
        this.assignedAt = assignedAt;
    }
}
