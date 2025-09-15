package com.srilankatravel.repository;

import com.srilankatravel.model.TourPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TourPackageRepository extends JpaRepository<TourPackage, Long> {
    List<TourPackage> findByDestinationId(Long destinationId);
    List<TourPackage> findByActiveTrue();
    List<TourPackage> findByPriceBetween(Double minPrice, Double maxPrice);
    List<TourPackage> findByDuration(Integer duration);
    List<TourPackage> findByDifficulty(String difficulty);

    @Query("SELECT t FROM TourPackage t WHERE t.startDate >= :startDate AND t.endDate <= :endDate")
    List<TourPackage> findAvailableBetweenDates(LocalDate startDate, LocalDate endDate);

    @Query("SELECT t FROM TourPackage t WHERE LOWER(t.name) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(t.description) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<TourPackage> searchTourPackages(String query);
}