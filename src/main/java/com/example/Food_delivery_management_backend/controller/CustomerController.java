package com.example.Food_delivery_management_backend.controller;

import com.example.Food_delivery_management_backend.dto.CustomerRegistrationRequest;
import com.example.Food_delivery_management_backend.entity.Customer;
import com.example.Food_delivery_management_backend.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@RequestBody CustomerRegistrationRequest request) {
        try {
            Customer customer = customerService.registerCustomer(
                    request.getEmail(),
                    request.getPassword(),
                    request.getPhoneNumber(),
                    request.getDeliveryAddress(),
                    request.getAlternatePhone()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(customer);
        } catch (RuntimeException e) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Registration Failed: "+ e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getCustomerByUserId(@PathVariable Long userId) {
        try {
            Customer customer = customerService.findByUserId(userId);
            return ResponseEntity.ok(customer);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Customer not fount: " + e.getMessage());
        }
    }
}
