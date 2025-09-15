package com.srilankatravel.controller;


import com.srilankatravel.model.Destination;
import com.srilankatravel.model.TourPackage;
import com.srilankatravel.service.DestinationService;
import com.srilankatravel.service.TourPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/settings")
public class SettingsController {

    @Autowired
    private DestinationService destinationService;

    @Autowired
    private TourPackageService tourPackageService;

    // Destinations Management
    @GetMapping("/destinations")
    public String destinationsManagement(Model model) {
        List<Destination> destinations = destinationService.getAllDestinations();
        model.addAttribute("destinations", destinations);
        model.addAttribute("destination", new Destination());
        return "settings/destinations";
    }

    @PostMapping("/destinations")
    public String saveDestination(@ModelAttribute Destination destination) {
        destinationService.saveDestination(destination);
        return "redirect:/settings/destinations";
    }

    @GetMapping("/destinations/edit/{id}")
    public String editDestination(@PathVariable Long id, Model model) {
        Destination destination = destinationService.getDestinationById(id);
        model.addAttribute("destination", destination);
        model.addAttribute("destinations", destinationService.getAllDestinations());
        return "settings/destinations";
    }

    @GetMapping("/destinations/delete/{id}")
    public String deleteDestination(@PathVariable Long id) {
        destinationService.deleteDestination(id);
        return "redirect:/settings/destinations";
    }

    // Tour Packages Management
    @GetMapping("/tour-packages")
    public String tourPackagesManagement(Model model) {
        List<TourPackage> tourPackages = tourPackageService.getAllTourPackages();
        List<Destination> destinations = destinationService.getAllDestinations();
        model.addAttribute("tourPackages", tourPackages);
        model.addAttribute("tourPackage", new TourPackage());
        model.addAttribute("destinations", destinations);
        return "settings/tour-packages";
    }

    @PostMapping("/tour-packages")
    public String saveTourPackage(@ModelAttribute TourPackage tourPackage) {
        tourPackageService.saveTourPackage(tourPackage);
        return "redirect:/settings/tour-packages";
    }

    @GetMapping("/tour-packages/edit/{id}")
    public String editTourPackage(@PathVariable Long id, Model model) {
        TourPackage tourPackage = tourPackageService.getTourPackageById(id);
        List<Destination> destinations = destinationService.getAllDestinations();
        model.addAttribute("tourPackage", tourPackage);
        model.addAttribute("tourPackages", tourPackageService.getAllTourPackages());
        model.addAttribute("destinations", destinations);
        return "settings/tour-packages";
    }

    @GetMapping("/tour-packages/delete/{id}")
    public String deleteTourPackage(@PathVariable Long id) {
        tourPackageService.deleteTourPackage(id);
        return "redirect:/settings/tour-packages";
    }
}
