package com.example.Food_delivery_management_backend.service;

import com.example.Food_delivery_management_backend.entity.Restaurant;
import com.example.Food_delivery_management_backend.entity.User;
import com.example.Food_delivery_management_backend.entity.UserRole;
import com.example.Food_delivery_management_backend.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final UserService userService;


    public RestaurantService(RestaurantRepository restaurantRepository, UserService userService) {
        this.restaurantRepository = restaurantRepository;
        this.userService = userService;
    }

    public Restaurant registerRestaurant(String email, String password, String phoneNumber,
                                         String restaurantName, String address, String zipCode,
                                         String cuisineType) {
        User user = userService.createUser(email, password, phoneNumber, UserRole.RESTAURANT);

        Restaurant restaurant = new Restaurant();
        restaurant.setUser(user);
        restaurant.setRestaurantName(restaurantName);
        restaurant.setAddress(address);
        restaurant.setZipCode(zipCode);
        restaurant.setCuisineType(cuisineType);
        restaurant.setIsActive(true);

        return restaurantRepository.save(restaurant);
    }
    public List<Restaurant> searchByZipCode(String zipCode) {
        return restaurantRepository.findByZipCode(zipCode);
    }
    public List<Restaurant> getActiveRestaurants() {
        return restaurantRepository.findByIsActive(true);
    }
    public Restaurant findById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with ID: " + id));
    }
    public Restaurant findByUserId(Long userId) {
        return restaurantRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found for user ID: " + userId));
    }
}
