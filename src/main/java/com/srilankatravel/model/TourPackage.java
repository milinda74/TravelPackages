package com.srilankatravel.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tour_packages")
public class TourPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    @Column(length = 2000)
    private String description;

    @NotNull(message = "Price is required")
    private Double price;

    private Integer duration;

    private String difficulty;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> included = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> notIncluded = new ArrayList<>();

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Destination destination;

    @OneToMany(mappedBy = "tourPackage", cascade = CascadeType.ALL)
    private List<Booking> bookings = new ArrayList<>();

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer maxTravelers;

    private Integer currentBookings = 0;

    private Boolean active = true;

    public TourPackage() {}

    public TourPackage(String name, String description, Double price, Integer duration, Destination destination) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.destination = destination;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public Integer getDuration() { return duration; }
    public void setDuration(Integer duration) { this.duration = duration; }
    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }
    public List<String> getIncluded() { return included; }
    public void setIncluded(List<String> included) { this.included = included; }
    public List<String> getNotIncluded() { return notIncluded; }
    public void setNotIncluded(List<String> notIncluded) { this.notIncluded = notIncluded; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public Destination getDestination() { return destination; }
    public void setDestination(Destination destination) { this.destination = destination; }
    public List<Booking> getBookings() { return bookings; }
    public void setBookings(List<Booking> bookings) { this.bookings = bookings; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public Integer getMaxTravelers() { return maxTravelers; }
    public void setMaxTravelers(Integer maxTravelers) { this.maxTravelers = maxTravelers; }
    public Integer getCurrentBookings() { return currentBookings; }
    public void setCurrentBookings(Integer currentBookings) { this.currentBookings = currentBookings; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public boolean isAvailable() {
        if (!Boolean.TRUE.equals(active)) return false;
        if (maxTravelers == null) return true;
        // Handle null currentBookings
        int current = (currentBookings != null) ? currentBookings : 0;
        return current < maxTravelers;
    }

    public Integer getAvailableSpots() {
        if (maxTravelers == null) return null;
        // Handle null currentBookings
        int current = (currentBookings != null) ? currentBookings : 0;
        return maxTravelers - current;
    }
}