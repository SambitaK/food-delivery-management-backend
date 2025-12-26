package com.example.Food_delivery_management_backend.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false, unique = true)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false, length = 20)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false, length = 20)
    private PaymentStatus paymentStatus;

    @Column(name = "transaction_id", length = 100)
    private String transactionId;

    @CreationTimestamp
    @Column(name = "payment_date", nullable = false, updatable = false)
    private LocalDateTime paymentDate;

    @Column(name = "refund_date")
    private LocalDateTime refundDate;

    @Column(name = "refund_amount", precision = 10, scale = 2)
    private BigDecimal refundAmount;


    public Payment() {
    }

    public Payment(Long id, Order order, Customer customer, BigDecimal amount,
                   PaymentMethod paymentMethod, PaymentStatus paymentStatus,
                   String transactionId, LocalDateTime paymentDate,
                   LocalDateTime refundDate, BigDecimal refundAmount) {
        this.id = id;
        this.order = order;
        this.customer = customer;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.transactionId = transactionId;
        this.paymentDate = paymentDate;
        this.refundDate = refundDate;
        this.refundAmount = refundAmount;
    }


    public Long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public Customer getCustomer() {
        return customer;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public LocalDateTime getRefundDate() {
        return refundDate;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setRefundDate(LocalDateTime refundDate) {
        this.refundDate = refundDate;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }
}