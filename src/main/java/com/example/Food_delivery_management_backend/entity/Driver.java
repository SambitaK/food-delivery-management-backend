package com.example.Food_delivery_management_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "drivers")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "license_number", nullable = false, unique = true, length = 50)
    private String licenseNumber;

    @Column(name = "vehicle_info", length = 200)
    private String vehicleInfo;

    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable = true;

    @Column(name = "current_latitude")
    private Double currentLatitude;

    @Column(name = "current_longitude")
    private Double currentLongitude;

    public Driver() {
    }

    public Driver(Long id, User user, String licenseNumber, String vehicleInfo,
                  Boolean isAvailable, Double currentLatitude, Double currentLongitude) {
        this.id = id;
        this.user = user;
        this.licenseNumber = licenseNumber;
        this.vehicleInfo = vehicleInfo;
        this.isAvailable = isAvailable;
        this.currentLatitude = currentLatitude;
        this.currentLongitude = currentLongitude;
    }


    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String getVehicleInfo() {
        return vehicleInfo;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public Double getCurrentLatitude() {
        return currentLatitude;
    }

    public Double getCurrentLongitude() {
        return currentLongitude;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public void setVehicleInfo(String vehicleInfo) {
        this.vehicleInfo = vehicleInfo;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public void setCurrentLatitude(Double currentLatitude) {
        this.currentLatitude = currentLatitude;
    }

    public void setCurrentLongitude(Double currentLongitude) {
        this.currentLongitude = currentLongitude;
    }
}
