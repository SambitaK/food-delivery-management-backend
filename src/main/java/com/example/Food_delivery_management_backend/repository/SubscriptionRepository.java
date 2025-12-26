package com.example.Food_delivery_management_backend.repository;

import com.example.Food_delivery_management_backend.entity.Subscription;
import com.example.Food_delivery_management_backend.entity.SubscriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    List<Subscription> findByUserId(Long userId);

    List<Subscription> findByStatus(SubscriptionStatus status);

    List<Subscription> findByUserIdAndStatus(Long userId, SubscriptionStatus status);
}