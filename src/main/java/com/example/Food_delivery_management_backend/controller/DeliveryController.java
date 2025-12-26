package com.example.Food_delivery_management_backend.controller;

import com.example.Food_delivery_management_backend.entity.Delivery;
import com.example.Food_delivery_management_backend.entity.DeliveryStatus;
import com.example.Food_delivery_management_backend.service.DeliveryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDeliveryById(@PathVariable Long id) {
        try {
            Delivery delivery = deliveryService.findById(id);
            return ResponseEntity.ok(delivery);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Delivery not found: " + e.getMessage());
        }
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getDeliveryByOrderId(@PathVariable Long orderId) {
        try {
            Delivery delivery = deliveryService.findByOrderId(orderId);
            return ResponseEntity.ok(delivery);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Delivery not found: " + e.getMessage());
        }
    }

    @GetMapping("/driver/{driverId}")
    public ResponseEntity<List<Delivery>> getDriverDeliveries(@PathVariable Long driverId) {
        List<Delivery> deliveries = deliveryService.getDriverDeliveries(driverId);
        return ResponseEntity.ok(deliveries);
    }

    @PreAuthorize("hasRole('DRIVER')")
    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateDeliveryStatus(@PathVariable Long id,
                                                  @RequestParam DeliveryStatus status) {
        try {
            Delivery delivery = deliveryService.updateStatus(id, status);
            return ResponseEntity.ok(delivery);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Update failed: " + e.getMessage());
        }
    }
}