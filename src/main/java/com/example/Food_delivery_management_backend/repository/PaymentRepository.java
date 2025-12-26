package com.example.Food_delivery_management_backend.repository;


import com.example.Food_delivery_management_backend.entity.Payment;
import com.example.Food_delivery_management_backend.entity.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByOrderId(Long orderId);

    List<Payment> findByCustomerId(Long customerId);

    List<Payment> findByPaymentStatus(PaymentStatus paymentStatus);
}