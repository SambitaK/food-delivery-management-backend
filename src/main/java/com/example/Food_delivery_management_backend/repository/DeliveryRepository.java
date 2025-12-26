package com.example.Food_delivery_management_backend.repository;

import com.example.Food_delivery_management_backend.entity.Delivery;
import com.example.Food_delivery_management_backend.entity.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    Optional<Delivery> findByOrderId(Long orderId);

    List<Delivery> findByDriverId(Long driverId);

    List<Delivery> findByDeliveryStatus(DeliveryStatus deliveryStatus);
}