package com.example.Food_delivery_management_backend.service;

import com.example.Food_delivery_management_backend.entity.Customer;
import com.example.Food_delivery_management_backend.entity.User;
import com.example.Food_delivery_management_backend.entity.UserRole;
import com.example.Food_delivery_management_backend.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final UserService userService;


    public CustomerService(CustomerRepository customerRepository, UserService userService) {
        this.customerRepository = customerRepository;
        this.userService = userService;
    }

    @Transactional
    public Customer registerCustomer(String email, String password, String phoneNumber,
                                     String deliveryAddress, String alternatePhone) {
        // first, creating the user account
        User user = userService.createUser(email, password, phoneNumber, UserRole.CUSTOMER);

        // Then, creating the customer profile
        Customer customer = new Customer();
        customer.setUser(user);
        customer.setDeliveryAddress(deliveryAddress);
        customer.setAlternatePhone(alternatePhone);

        return customerRepository.save(customer);
    }

    public Customer findByUserId(Long userId) {
        return customerRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Customer not found for user ID: " + userId));
    }
}