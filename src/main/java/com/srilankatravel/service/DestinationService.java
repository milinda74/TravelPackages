package com.srilankatravel.service;


import com.srilankatravel.model.Destination;
import com.srilankatravel.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DestinationService {

    @Autowired
    private DestinationRepository destinationRepository;

    public List<Destination> getAllDestinations() {
        return destinationRepository.findAll();
    }

    // Use the new methods that fetch activities
    public Destination getDestinationById(Long id) {
        return destinationRepository.findByIdWithActivities(id)
                .orElseThrow(() -> new RuntimeException("Destination not found with id: " + id));
    }

    public List<Destination> getActiveDestinations() {

        /*List<Destination> destinations = destinationRepository.findDestinationsWithTourPackages();
        destinations.forEach(destination -> {
            destination.setTourPackageCount(destination.getTourPackages() != null ?
                    destination.getTourPackages().size() : 0);
        });

        */

        return destinationRepository.findActiveDestinationsWithActivities();
    }

    public List<Destination> getDestinationsByRegion(String region) {
        return destinationRepository.findByRegionWithActivities(region);
    }

    public List<Destination> searchDestinations(String query) {
        return destinationRepository.searchDestinationsWithActivities(query);
    }

    public List<Destination> getDestinationsByActivity(String activity) {
        return destinationRepository.findByActivityContaining(activity);
    }

    public List<Destination> getFeaturedDestinations() {
        return destinationRepository.findByIsFeaturedTrueAndIsActiveTrue();
    }

    public List<Destination> getFeaturedDestinationsWithTours() {
        return destinationRepository.findFeaturedDestinationsWithTours();
    }

    public List<Destination> getPopularDestinations() {
        return destinationRepository.findByIsActiveTrueOrderByPopularityRatingDesc();
    }

    public List<Destination> getDestinationsWithTourPackages() {
        return destinationRepository.findDestinationsWithTourPackages();
    }

    public Destination saveDestination(Destination destination) {
        // Check if destination with same name already exists (for new destinations)
        if (destination.getId() == null && destinationRepository.existsByName(destination.getName())) {
            throw new RuntimeException("Destination with name '" + destination.getName() + "' already exists");
        }
        return destinationRepository.save(destination);
    }

    public void deleteDestination(Long id) {
        Destination destination = getDestinationById(id);
        destinationRepository.delete(destination);
    }

    public void deactivateDestination(Long id) {
        Destination destination = getDestinationById(id);
        destination.setActive(false);//();
        destinationRepository.save(destination);
    }

    public void activateDestination(Long id) {
        Destination destination = getDestinationById(id);
        destination.setActive(true);
        destinationRepository.save(destination);
    }

    public void featureDestination(Long id) {
        Destination destination = getDestinationById(id);
        destination.setFeatured(true);
        destinationRepository.save(destination);
    }

    public void unfeatureDestination(Long id) {
        Destination destination = getDestinationById(id);
        destination.setFeatured(false);
        destinationRepository.save(destination);
    }

    public void incrementPopularity(Long id) {
        Destination destination = getDestinationById(id);
        destination.setPopularityRating(destination.getPopularityRating() + 1);
        destinationRepository.save(destination);
    }

    public long getActiveDestinationsCount() {
        return destinationRepository.countByIsActiveTrue();
    }

    public boolean destinationExists(String name) {
        return destinationRepository.existsByName(name);
    }
}