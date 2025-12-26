package com.example.Food_delivery_management_backend.repository;


import com.example.Food_delivery_management_backend.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    Optional<Driver> findByUserId(Long userId);

    List<Driver> findByIsAvailable(Boolean isAvailable);

    Optional<Driver> findByLicenseNumber(String licenseNumber);
}