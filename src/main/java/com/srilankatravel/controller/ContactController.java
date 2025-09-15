package com.srilankatravel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactController {

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @PostMapping("/contact")
    public String handleContactForm(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String subject,
            @RequestParam String message,
            @RequestParam(required = false) Boolean newsletter,
            Model model) {

        try {
            // Here you would typically:
            // 1. Save the contact form submission to database
            // 2. Send email notifications
            // 3. Process any other business logic

            // For now, we'll just simulate success
            System.out.println("Contact form submission:");
            System.out.println("Name: " + name);
            System.out.println("Email: " + email);
            System.out.println("Subject: " + subject);
            System.out.println("Message: " + message);
            System.out.println("Newsletter: " + (newsletter != null && newsletter ? "Yes" : "No"));

            model.addAttribute("success", "Thank you for your message! We'll get back to you within 24 hours.");

        } catch (Exception e) {
            model.addAttribute("error", "Sorry, there was an error sending your message. Please try again later.");
        }

        return "contact";
    }
}