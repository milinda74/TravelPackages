package com.srilankatravel.model;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "tour_package_id")
    private TourPackage tourPackage;

    private LocalDate bookingDate;

    private LocalDate travelDate;

    private int numberOfTravelers;

    private double totalPrice;

    private String status = "PENDING";

    public Booking() {}

    public Booking(User user, TourPackage tourPackage, LocalDate travelDate, int numberOfTravelers) {
        this.user = user;
        this.tourPackage = tourPackage;
        this.bookingDate = LocalDate.now();
        this.travelDate = travelDate;
        this.numberOfTravelers = numberOfTravelers;
        this.totalPrice = tourPackage.getPrice() * numberOfTravelers;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public TourPackage getTourPackage() { return tourPackage; }
    public void setTourPackage(TourPackage tourPackage) { this.tourPackage = tourPackage; }
    public LocalDate getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDate bookingDate) { this.bookingDate = bookingDate; }
    public LocalDate getTravelDate() { return travelDate; }
    public void setTravelDate(LocalDate travelDate) { this.travelDate = travelDate; }
    public int getNumberOfTravelers() { return numberOfTravelers; }
    public void setNumberOfTravelers(int numberOfTravelers) { this.numberOfTravelers = numberOfTravelers; }
    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}