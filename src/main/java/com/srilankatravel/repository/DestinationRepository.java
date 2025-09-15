package com.srilankatravel.repository;



import com.srilankatravel.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {

    // Find destinations by region
    List<Destination> findByRegion(String region);

    List<Destination> findByRegionAndIsActiveTrue(String region);

    // Find active destinations
    List<Destination> findByIsActiveTrue();

    // Find featured destinations
    List<Destination> findByIsFeaturedTrueAndIsActiveTrue();

    // Search destinations by name or description
    @Query("SELECT d FROM Destination d WHERE (LOWER(d.name) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(d.description) LIKE LOWER(CONCAT('%', :query, '%'))) AND d.isActive = true")
    List<Destination> searchDestinations(@Param("query") String query);

    // Find destinations by activity
    @Query("SELECT DISTINCT d FROM Destination d JOIN d.activities a WHERE LOWER(a) LIKE LOWER(CONCAT('%', :activity, '%')) AND d.isActive = true")
    List<Destination> findByActivityContaining(@Param("activity") String activity);

    // Find featured destinations with tour packages
    @Query("SELECT DISTINCT d FROM Destination d LEFT JOIN FETCH d.tourPackages WHERE d.isFeatured = true AND d.isActive = true")
    List<Destination> findFeaturedDestinationsWithTours();

    // Find destinations by popularity
    List<Destination> findByIsActiveTrueOrderByPopularityRatingDesc();

    // Check if destination exists by name
    boolean existsByName(String name);

    // Find destination by name
    Optional<Destination> findByName(String name);

    // Count active destinations
    long countByIsActiveTrue();

    // Find destinations with tour packages
    @Query("SELECT DISTINCT d FROM Destination d LEFT JOIN FETCH d.tourPackages WHERE d.isActive = true")
    List<Destination> findDestinationsWithTourPackages();

    // Add this method for fetching with activities
    @Query("SELECT d FROM Destination d LEFT JOIN FETCH d.activities WHERE d.id = :id")
    Optional<Destination> findByIdWithActivities(@Param("id") Long id);

    @Query("SELECT d FROM Destination d LEFT JOIN FETCH d.activities WHERE d.isActive = true")
    List<Destination> findActiveDestinationsWithActivities();

    @Query("SELECT d FROM Destination d LEFT JOIN FETCH d.activities WHERE d.region = :region AND d.isActive = true")
    List<Destination> findByRegionWithActivities(@Param("region") String region);

    @Query("SELECT DISTINCT d FROM Destination d LEFT JOIN FETCH d.activities WHERE (LOWER(d.name) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(d.description) LIKE LOWER(CONCAT('%', :query, '%'))) AND d.isActive = true")
    List<Destination> searchDestinationsWithActivities(@Param("query") String query);

    @Query("SELECT DISTINCT d FROM Destination d LEFT JOIN FETCH d.tourPackages WHERE d.isActive = true")
    List<Destination> findAllActiveWithPackages();
}