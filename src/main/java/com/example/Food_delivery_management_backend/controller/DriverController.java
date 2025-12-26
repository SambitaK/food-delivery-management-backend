package com.example.Food_delivery_management_backend.controller;

import com.example.Food_delivery_management_backend.dto.DriverRegistrationRequest;
import com.example.Food_delivery_management_backend.entity.Driver;
import com.example.Food_delivery_management_backend.service.DriverService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerDriver(@RequestBody DriverRegistrationRequest request) {
        try {
            Driver driver = driverService.registerDriver(
                    request.getEmail(),
                    request.getPassword(),
                    request.getPhoneNumber(),
                    request.getLicenseNumber(),
                    request.getVehicleInfo(),
                    request.getLatitude(),
                    request.getLongitude()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(driver);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Registration failed: " + e.getMessage());
        }
    }

    @GetMapping("/available")
    public ResponseEntity<List<Driver>> getAvailableDrivers() {
        List<Driver> drivers = driverService.getAvailableDrivers();
        return ResponseEntity.ok(drivers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDriverById(@PathVariable Long id) {
        try {
            Driver driver = driverService.findById(id);
            return ResponseEntity.ok(driver);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Driver not found: " + e.getMessage());
        }
    }

    @PatchMapping("/{id}/availability")
    public ResponseEntity<?> updateAvailability(@PathVariable Long id,
                                                @RequestParam Boolean isAvailable) {
        try {
            Driver driver = driverService.updateAvailability(id, isAvailable);
            return ResponseEntity.ok(driver);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Update failed: " + e.getMessage());
        }
    }

    @PatchMapping("/{id}/location")
    public ResponseEntity<?> updateLocation(@PathVariable Long id,
                                            @RequestParam Double latitude,
                                            @RequestParam Double longitude) {
        try {
            Driver driver = driverService.updateLocation(id, latitude, longitude);
            return ResponseEntity.ok(driver);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Update failed: " + e.getMessage());
        }
    }
}