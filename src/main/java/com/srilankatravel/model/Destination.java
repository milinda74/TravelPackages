package com.srilankatravel.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "destinations")
@NamedEntityGraph(
        name = "Destination.withActivities",
        attributeNodes = @NamedAttributeNode("activities")
)
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Destination name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    @Column(nullable = false, length = 100)
    private String name;

    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 2000, message = "Description must be between 10 and 2000 characters")
    @Column(nullable = false, length = 2000)
    private String description;

    @NotBlank(message = "Region is required")
    @Size(min = 2, max = 50, message = "Region must be between 2 and 50 characters")
    @Column(nullable = false, length = 50)
    private String region;

    @ElementCollection(fetch = FetchType.EAGER) // Changed to EAGER
    @CollectionTable(name = "destination_activities", joinColumns = @JoinColumn(name = "destination_id"))
    @Column(name = "activity", length = 100)
    private List<String> activities = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER) // Changed to EAGER
    @CollectionTable(name = "destination_highlights", joinColumns = @JoinColumn(name = "destination_id"))
    @Column(name = "highlight", length = 200)
    private List<String> highlights = new ArrayList<>();

    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @Column(name = "best_time_to_visit", length = 100)
    private String bestTimeToVisit;

    @Column(name = "average_temperature", length = 50)
    private String averageTemperature;

    @Column(name = "travel_tips", length = 1000)
    private String travelTips;

    @Column(name = "is_featured")
    private Boolean isFeatured = false;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "altitude")
    private String altitude;

    @Column(name = "popularity_rating")
    private Integer popularityRating = 0;

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<TourPackage> tourPackages = new ArrayList<>();

    // Constructors
    public Destination() {
    }

    public Destination(String name, String description, String region) {
        this.name = name;
        this.description = description;
        this.region = region;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public List<String> getActivities() {
        if (activities == null) {
            activities = new ArrayList<>();
        }
        return activities;
    }

    public void setActivities(List<String> activities) {
        this.activities = activities;
    }

    public List<String> getHighlights() {
        if (highlights == null) {
            highlights = new ArrayList<>();
        }
        return highlights;
    }

    public void setHighlights(List<String> highlights) {
        this.highlights = highlights;
    }

    // ... other getters and setters ...

    // Helper methods with null checks
    public void addActivity(String activity) {
        getActivities().add(activity);
    }

    public void removeActivity(String activity) {
        getActivities().remove(activity);
    }

    public void addHighlight(String highlight) {
        getHighlights().add(highlight);
    }

    public void removeHighlight(String highlight) {
        getHighlights().remove(highlight);
    }

    public int getActivityCount() {
        return getActivities().size();
    }

    public int getHighlightCount() {
        return getHighlights().size();
    }

    // toString, equals, hashCode methods
    @Override
    public String toString() {
        return "Destination{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", region='" + region + '\'' +
                ", activityCount=" + getActivityCount() +
                ", isActive=" + isActive +
                '}';
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getFeatured() {
        return isFeatured;
    }

    public void setFeatured(Boolean featured) {
        isFeatured = featured;
    }

    public Integer getPopularityRating() {
        return popularityRating;
    }

    public void setPopularityRating(Integer popularityRating) {
        this.popularityRating = popularityRating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<TourPackage> getTourPackages() {
        return tourPackages;
    }

    public void setTourPackages(List<TourPackage> tourPackages) {
        this.tourPackages = tourPackages;
    }


}