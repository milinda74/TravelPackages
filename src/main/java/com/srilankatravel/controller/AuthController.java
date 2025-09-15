package com.srilankatravel.controller;

import com.srilankatravel.model.User;
import com.srilankatravel.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.Optional;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/authenticate")
    public String authenticate(@RequestParam String email,
                               @RequestParam String password,
                               HttpSession session) {

        Optional<User> userOptional = userService.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Simple password check (in real application, use password encoding)
            if (user.getPassword().equals(password)) {
                // Store user in session
                session.setAttribute("user", user);
                return "redirect:/?login=success";
            }
        }

        // Authentication failed
        return "redirect:/login?error=true";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/login?logout=true";
    }
}