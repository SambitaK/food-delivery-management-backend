package com.example.Food_delivery_management_backend.repository;

import com.example.Food_delivery_management_backend.entity.Order;
import com.example.Food_delivery_management_backend.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomerId(Long customerId);

    List<Order> findByRestaurantId(Long restaurantId);

    List<Order> findByOrderStatus(OrderStatus orderStatus);

    List<Order> findByCustomerIdOrderByOrderDateDesc(Long customerId);
}
