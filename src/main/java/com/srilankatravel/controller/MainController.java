package com.srilankatravel.controller;

import com.srilankatravel.model.Destination;
import com.srilankatravel.model.User;
import com.srilankatravel.service.DestinationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private DestinationService destinationService;

    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        try {
            List<Destination> destinations = destinationService.getAllDestinations();
            List<Destination> featuredDestinations = destinations.subList(0, Math.min(3, destinations.size()));
            model.addAttribute("featuredDestinations", featuredDestinations);

            // Check if user is logged in
            User user = (User) session.getAttribute("user");
            if (user != null) {
                model.addAttribute("currentUser", user);
            }

        } catch (Exception e) {
            // Handle error gracefully - show page even if there are no destinations
            model.addAttribute("error", "Unable to load destinations at this time.");
        }

        return "index";
    }

    /*@GetMapping("/destinations")
    public String destinations(Model model) {
        List<Destination> destinations = destinationService.getAllDestinations();
        model.addAttribute("destinations", destinations);
        return "destinations";
    }

    @GetMapping("/destinations/{id}")
    public String destinationDetail(@PathVariable Long id, Model model) {
        Destination destination = destinationService.getDestinationById(id);
        model.addAttribute("destination", destination);
        return "destination-detail";
    }*/

    @GetMapping("/search")
    public String searchDestinations(@RequestParam String query, Model model) {
        List<Destination> results = destinationService.searchDestinations(query);
        model.addAttribute("results", results);
        model.addAttribute("query", query);
        return "search-results";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
}