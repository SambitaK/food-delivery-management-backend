package com.example.Food_delivery_management_backend.service;

import com.example.Food_delivery_management_backend.entity.Driver;
import com.example.Food_delivery_management_backend.entity.User;
import com.example.Food_delivery_management_backend.entity.UserRole;
import com.example.Food_delivery_management_backend.repository.DriverRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DriverService {

    private final DriverRepository driverRepository;
    private final UserService userService;

    public DriverService(DriverRepository driverRepository, UserService userService) {
        this.driverRepository = driverRepository;
        this.userService = userService;
    }

    @Transactional
    public Driver registerDriver(String email, String password, String phoneNumber,
                                 String licenseNumber, String vehicleInfo,
                                 Double latitude, Double longitude) {
        // creating user account with DRIVER role
        User user = userService.createUser(email, password, phoneNumber, UserRole.DRIVER);

        // creating driver profile
        Driver driver = new Driver();
        driver.setUser(user);
        driver.setLicenseNumber(licenseNumber);
        driver.setVehicleInfo(vehicleInfo);
        driver.setIsAvailable(true);
        driver.setCurrentLatitude(latitude);
        driver.setCurrentLongitude(longitude);

        return driverRepository.save(driver);
    }

    public List<Driver> getAvailableDrivers() {
        return driverRepository.findByIsAvailable(true);
    }

    public Driver findById(Long id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found with ID: " + id));
    }

    public Driver findByUserId(Long userId) {
        return driverRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Driver not found for user ID: " + userId));
    }

    @Transactional
    public Driver updateAvailability(Long driverId, Boolean isAvailable) {
        Driver driver = findById(driverId);
        driver.setIsAvailable(isAvailable);
        return driverRepository.save(driver);
    }

    @Transactional
    public Driver updateLocation(Long driverId, Double latitude, Double longitude) {
        Driver driver = findById(driverId);
        driver.setCurrentLatitude(latitude);
        driver.setCurrentLongitude(longitude);
        return driverRepository.save(driver);
    }


    public Driver findAvailableDriver() {
        List<Driver> availableDrivers = getAvailableDrivers();
        if (availableDrivers.isEmpty()) {
            throw new RuntimeException("No drivers available");
        }
        return availableDrivers.get(0);
    }
}