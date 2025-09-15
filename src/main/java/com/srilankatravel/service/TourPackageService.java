package com.srilankatravel.service;

import com.srilankatravel.model.TourPackage;
import com.srilankatravel.repository.TourPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TourPackageService {
    @Autowired
    private TourPackageRepository tourPackageRepository;

    public List<TourPackage> getAllTourPackages() {
        return tourPackageRepository.findAll();
    }

    public List<TourPackage> getActiveTourPackages() {
        return tourPackageRepository.findByActiveTrue();
    }

    public TourPackage getTourPackageById(Long id) {
        return tourPackageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tour package not found"));
    }

    public TourPackage saveTourPackage(TourPackage tourPackage) {
        return tourPackageRepository.save(tourPackage);
    }

    public void deleteTourPackage(Long id) {
        tourPackageRepository.deleteById(id);
    }

    public List<TourPackage> getTourPackagesByDestination(Long destinationId) {
        return tourPackageRepository.findByDestinationId(destinationId);
    }

    public List<TourPackage> searchTourPackages(String query) {
        return tourPackageRepository.searchTourPackages(query);
    }

    public List<TourPackage> getTourPackagesByPriceRange(Double minPrice, Double maxPrice) {
        return tourPackageRepository.findByPriceBetween(minPrice, maxPrice);
    }

    public List<TourPackage> getTourPackagesByDifficulty(String difficulty) {
        return tourPackageRepository.findByDifficulty(difficulty);
    }

    public List<TourPackage> getAvailableTourPackagesBetweenDates(LocalDate startDate, LocalDate endDate) {
        return tourPackageRepository.findAvailableBetweenDates(startDate, endDate);
    }

    public void incrementBookings(Long tourPackageId) {
        Optional<TourPackage> optionalTourPackage = tourPackageRepository.findById(tourPackageId);
        if (optionalTourPackage.isPresent()) {
            TourPackage tourPackage = optionalTourPackage.get();
            tourPackage.setCurrentBookings(tourPackage.getCurrentBookings() + 1);
            tourPackageRepository.save(tourPackage);
        }
    }

    public void decrementBookings(Long tourPackageId) {
        Optional<TourPackage> optionalTourPackage = tourPackageRepository.findById(tourPackageId);
        if (optionalTourPackage.isPresent()) {
            TourPackage tourPackage = optionalTourPackage.get();
            if (tourPackage.getCurrentBookings() > 0) {
                tourPackage.setCurrentBookings(tourPackage.getCurrentBookings() - 1);
                tourPackageRepository.save(tourPackage);
            }
        }
    }
}
