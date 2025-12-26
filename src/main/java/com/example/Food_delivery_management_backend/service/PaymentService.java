package com.example.Food_delivery_management_backend.service;

import com.example.Food_delivery_management_backend.entity.*;
import com.example.Food_delivery_management_backend.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Transactional
    public Payment processPayment(Order order, Customer customer, BigDecimal amount,
                                  PaymentMethod paymentMethod) {
        // simulating payment gateway call
        String transactionId = simulatePaymentGateway(amount, paymentMethod);

        // creating payment record
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setCustomer(customer);
        payment.setAmount(amount);
        payment.setPaymentMethod(paymentMethod);
        payment.setPaymentStatus(PaymentStatus.COMPLETED);
        payment.setTransactionId(transactionId);

        return paymentRepository.save(payment);
    }

    @Transactional
    public Payment processRefund(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        if (payment.getPaymentStatus() != PaymentStatus.COMPLETED) {
            throw new RuntimeException("Cannot refund - payment not completed");
        }

        // simulating refund to payment gateway
        payment.setPaymentStatus(PaymentStatus.REFUNDED);
        payment.setRefundDate(LocalDateTime.now());
        payment.setRefundAmount(payment.getAmount());

        return paymentRepository.save(payment);
    }

    public Payment findByOrderId(Long orderId) {
        return paymentRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Payment not found for order ID: " + orderId));
    }

    // simulating payment gateway
    private String simulatePaymentGateway(BigDecimal amount, PaymentMethod method) {

        // for now, generating fake transaction ID
        return "TXN_" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
