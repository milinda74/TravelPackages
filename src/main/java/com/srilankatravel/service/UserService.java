package com.srilankatravel.service;

import com.srilankatravel.model.User;
import com.srilankatravel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private String encodePassword(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }

    private boolean checkPassword(String rawPassword, String encodedPassword) {
        String encodedRaw = encodePassword(rawPassword);
        return encodedRaw.equals(encodedPassword);
    }

    public User registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public boolean authenticate(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return checkPassword(password, user.getPassword());
        }
        return false;
    }
}