package com.srilankatravel.controller;

import com.srilankatravel.model.TourPackage;
import com.srilankatravel.service.TourPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tour-packages")
public class TourPackageController {
    @Autowired
    private TourPackageService tourPackageService;

    @GetMapping
    public String getAllTourPackages(Model model) {
        List<TourPackage> tourPackages = tourPackageService.getAllTourPackages();//tourPackageService.getActiveTourPackages();
        model.addAttribute("tourPackages", tourPackages);
        return "tour-packages";
    }

    @GetMapping("/{id}")
    public String getTourPackageDetail(@PathVariable Long id, Model model) {
        TourPackage tourPackage = tourPackageService.getTourPackageById(id);
        model.addAttribute("tourPackage", tourPackage);
        return "tour-package-detail";
    }

    @GetMapping("/destination/{destinationId}")
    public String getTourPackagesByDestination(@PathVariable Long destinationId, Model model) {
        List<TourPackage> tourPackages = tourPackageService.getTourPackagesByDestination(destinationId);
        model.addAttribute("tourPackages", tourPackages);
        return "tour-packages";
    }

    @GetMapping("/search")
    public String searchTourPackages(@RequestParam String query, Model model) {
        List<TourPackage> results = tourPackageService.searchTourPackages(query);
        model.addAttribute("results", results);
        model.addAttribute("query", query);
        return "search-results";
    }
}