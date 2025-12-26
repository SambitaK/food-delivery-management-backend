package com.example.Food_delivery_management_backend.controller;

import com.example.Food_delivery_management_backend.dto.RestaurantRegistrationRequest;
import com.example.Food_delivery_management_backend.entity.Restaurant;
import com.example.Food_delivery_management_backend.service.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerRestaurant(@RequestBody RestaurantRegistrationRequest request) {
        try {
            Restaurant restaurant = restaurantService.registerRestaurant(
                    request.getEmail(),
                    request.getPassword(),
                    request.getPhoneNumber(),
                    request.getRestaurantName(),
                    request.getAddress(),
                    request.getZipCode(),
                    request.getCuisineType()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed: " + e.getMessage());
        }
    }
    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurants(@RequestParam String zipCode) {
        List<Restaurant> restaurants = restaurantService.searchByZipCode(zipCode);
        return ResponseEntity.ok(restaurants);
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllActiveRestaurants() {
        List<Restaurant> restaurants = restaurantService.getActiveRestaurants();
        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRestaurantById(@PathVariable Long id) {
        try {
            Restaurant restaurant = restaurantService.findById(id);
            return ResponseEntity.ok(restaurant);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Restaurant not found: " + e.getMessage());
        }
    }
}


