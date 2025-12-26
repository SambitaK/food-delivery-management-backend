package com.example.Food_delivery_management_backend.service;


import com.example.Food_delivery_management_backend.entity.*;
import com.example.Food_delivery_management_backend.repository.DeliveryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final DriverService driverService;

    public DeliveryService(DeliveryRepository deliveryRepository, DriverService driverService) {
        this.deliveryRepository = deliveryRepository;
        this.driverService = driverService;
    }

    @Transactional
    public Delivery assignDriver(Order order, Restaurant restaurant) {
        // finding available driver
        Driver driver = driverService.findAvailableDriver();

        // creating delivery record
        Delivery delivery = new Delivery();
        delivery.setOrder(order);
        delivery.setDriver(driver);
        delivery.setRestaurant(restaurant);
        delivery.setPickupAddress(restaurant.getAddress());
        delivery.setDeliveryAddress(order.getDeliveryAddress());
        delivery.setEstimatedPickupTime(order.getEstimatedPickupTime());

        // estimating delivery time (30 mins after pickup)
        if (order.getEstimatedPickupTime() != null) {
            delivery.setEstimatedDeliveryTime(
                    order.getEstimatedPickupTime().plusMinutes(30)
            );
        }

        delivery.setDeliveryStatus(DeliveryStatus.ASSIGNED);

        // marking driver as unavailable
        driverService.updateAvailability(driver.getId(), false);

        return deliveryRepository.save(delivery);
    }

    @Transactional
    public Delivery updateStatus(Long deliveryId, DeliveryStatus status) {
        Delivery delivery = findById(deliveryId);
        delivery.setDeliveryStatus(status);

        // updating timestamps based on status
        switch (status) {
            case PICKED_UP:
                delivery.setActualPickupTime(LocalDateTime.now());
                break;
            case DELIVERED:
                delivery.setActualDeliveryTime(LocalDateTime.now());
                // marking driver as available again
                driverService.updateAvailability(delivery.getDriver().getId(), true);
                break;
        }

        return deliveryRepository.save(delivery);
    }

    public Delivery findById(Long id) {
        return deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery not found with ID: " + id));
    }

    public Delivery findByOrderId(Long orderId) {
        return deliveryRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Delivery not found for order ID: " + orderId));
    }

    public List<Delivery> getDriverDeliveries(Long driverId) {
        return deliveryRepository.findByDriverId(driverId);
    }
}