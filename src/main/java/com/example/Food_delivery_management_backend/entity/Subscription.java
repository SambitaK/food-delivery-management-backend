package com.example.Food_delivery_management_backend.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "subscription_type", nullable = false, length = 20)
    private SubscriptionType subscriptionType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private SubscriptionPlan plan;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;        //This amount field stores the actual charge based on plan selected.

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private SubscriptionStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false, length = 20)
    private PaymentMethod paymentMethod;

    @Column(name = "auto_renew", nullable = false)
    private Boolean autoRenew = true;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;


    public Subscription() {
    }

    public Subscription(Long id, User user, SubscriptionType subscriptionType,
                        SubscriptionPlan plan, BigDecimal amount,
                        LocalDateTime startDate, LocalDateTime endDate,
                        SubscriptionStatus status, PaymentMethod paymentMethod,
                        Boolean autoRenew, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.subscriptionType = subscriptionType;
        this.plan = plan;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.autoRenew = autoRenew;
        this.createdAt = createdAt;
    }


    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public SubscriptionPlan getPlan() {
        return plan;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public SubscriptionStatus getStatus() {
        return status;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public Boolean getAutoRenew() {
        return autoRenew;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setSubscriptionType(SubscriptionType subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public void setPlan(SubscriptionPlan plan) {
        this.plan = plan;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setStatus(SubscriptionStatus status) {
        this.status = status;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setAutoRenew(Boolean autoRenew) {
        this.autoRenew = autoRenew;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

