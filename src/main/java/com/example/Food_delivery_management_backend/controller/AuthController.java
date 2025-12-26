package com.example.Food_delivery_management_backend.controller;

import com.example.Food_delivery_management_backend.dto.LoginRequest;
import com.example.Food_delivery_management_backend.dto.LoginResponse;
import com.example.Food_delivery_management_backend.entity.User;
import com.example.Food_delivery_management_backend.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            String token = authService.login(request.getEmail(), request.getPassword());
            User user = authService.getUserByEmail(request.getEmail());

            LoginResponse response = new LoginResponse(
                    token,
                    user.getEmail(),
                    user.getRole().name(),
                    user.getId()
            );

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Login failed: " + e.getMessage());
        }
    }
}