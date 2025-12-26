package com.example.Food_delivery_management_backend.service;

import com.example.Food_delivery_management_backend.entity.User;
import com.example.Food_delivery_management_backend.entity.UserRole;
import com.example.Food_delivery_management_backend.entity.UserStatus;
import com.example.Food_delivery_management_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Service
public class UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User createUser(String email, String password, String phoneNumber, UserRole role) {
        //checking if email already exists
        if(userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already exists: " + email);
        }
        //creating new user
        User user = new User();
        user.setEmail(email);
//        user.setPassword(password); // Hash password before saving
        user.setPassword(passwordEncoder.encode(password)); // Hash password with BCrypt
        user.setPhoneNumber(phoneNumber);
        user.setRole(role);
        user.setStatus(UserStatus.ACTIVE);

        return userRepository.save(user);
    }
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}
