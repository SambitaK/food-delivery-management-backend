package com.example.Food_delivery_management_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "restaurant_name", nullable = false, length = 200)
    private String restaurantName;

    @Column(nullable = false, length = 500)
    private String address;

    @Column(name = "zip_code", nullable = false, length = 10)
    private String zipCode;

    @Column(name = "cuisine_type",length = 100)
    private String cuisineType;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    public Restaurant() {
    }

    public Restaurant(Long id, User user, String restaurantName, String address, String zipCode, String cuisineType, Boolean isActive) {
        this.id = id;
        this.user = user;
        this.restaurantName = restaurantName;
        this.address = address;
        this.zipCode = zipCode;
        this.cuisineType = cuisineType;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

}
