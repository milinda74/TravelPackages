package com.srilankatravel.controller;

import com.srilankatravel.model.Destination;
import com.srilankatravel.model.User;
import com.srilankatravel.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.transaction.annotation.Transactional;



import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class DestinationsController {

    @Autowired
    private DestinationService destinationService;

    @GetMapping("/destinations")
    @Transactional(readOnly = true) // Add this annotation
    public String destinations(Model model, HttpSession session,
                               @RequestParam(value = "query", required = false) String query) {
        try {
            List<Destination> destinations;

            if (query != null && !query.trim().isEmpty()) {
                // Search destinations
                destinations = destinationService.searchDestinations(query.trim());
                model.addAttribute("query", query.trim());
            } else {
                // Get all active destinations
                destinations = destinationService.getActiveDestinations();
            }

            model.addAttribute("destinations", destinations);

            // Get unique regions for filter
            List<String> regions = destinationService.getActiveDestinations().stream()
                    .map(Destination::getRegion)
                    .distinct()
                    .collect(Collectors.toList());
            model.addAttribute("regions", regions);

            // Check if user is logged in
            User user = (User) session.getAttribute("user");
            if (user != null) {
                model.addAttribute("currentUser", user);
            }

        } catch (Exception e) {
            model.addAttribute("error", "Unable to load destinations. Please try again later.");
            // Log the error
            System.err.println("Error loading destinations: " + e.getMessage());
        }

        return "destinations";
    }

    @GetMapping("/destinations/region/{region}")
    public String destinationsByRegion(@PathVariable String region, Model model, HttpSession session) {
        try {
            List<Destination> destinations = destinationService.getDestinationsByRegion(region);
            model.addAttribute("destinations", destinations);
            model.addAttribute("currentRegion", region);

            // Get unique regions for filter
            List<String> regions = destinationService.getActiveDestinations().stream()
                    .map(Destination::getRegion)
                    .distinct()
                    .collect(Collectors.toList());
            model.addAttribute("regions", regions);

            // Check if user is logged in
            User user = (User) session.getAttribute("user");
            if (user != null) {
                model.addAttribute("currentUser", user);
            }

        } catch (Exception e) {
            model.addAttribute("error", "Unable to load destinations for region: " + region);
            return "redirect:/destinations";
        }

        return "destinations";
    }

    @GetMapping("/destinations/search")
    public String searchDestinations(@RequestParam String query, Model model, HttpSession session) {
        return destinations(model, session, query);
    }

    @GetMapping("/destinations/{id}")
    @Transactional(readOnly = true)
    public String destinationDetail(@PathVariable Long id, Model model, HttpSession session) {
        try {
            Destination destination = destinationService.getDestinationById(id);
            model.addAttribute("destination", destination);

            // Check if user is logged in
            User user = (User) session.getAttribute("user");
            if (user != null) {
                model.addAttribute("currentUser", user);
            }

        } catch (Exception e) {
            model.addAttribute("error", "Destination not found.");
            return "redirect:/destinations";
        }

        return "destination-detail";
    }
}