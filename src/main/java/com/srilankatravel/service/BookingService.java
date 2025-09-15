package com.srilankatravel.service;

import com.srilankatravel.model.Booking;
import com.srilankatravel.model.TourPackage;
import com.srilankatravel.model.User;
import com.srilankatravel.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TourPackageService tourPackageService;

    public Booking createBooking(User user, TourPackage tourPackage, LocalDate travelDate, int numberOfTravelers) {
        if (!tourPackage.isAvailable()) {
            throw new RuntimeException("Tour package is not available");
        }

        if (tourPackage.getAvailableSpots() != null && numberOfTravelers > tourPackage.getAvailableSpots()) {
            throw new RuntimeException("Not enough available spots");
        }

        Booking booking = new Booking(user, tourPackage, travelDate, numberOfTravelers);
        Booking savedBooking = bookingRepository.save(booking);

        for (int i = 0; i < numberOfTravelers; i++) {
            tourPackageService.incrementBookings(tourPackage.getId());
        }

        return savedBooking;
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    public List<Booking> getUserBookings(User user) {
        return bookingRepository.findByUser(user);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking updateBookingStatus(Long id, String status) {
        Booking booking = getBookingById(id);
        booking.setStatus(status);
        return bookingRepository.save(booking);
    }

    public void cancelBooking(Long id) {
        Booking booking = getBookingById(id);

        if ("CANCELLED".equals(booking.getStatus())) {
            throw new RuntimeException("Booking is already cancelled");
        }

        booking.setStatus("CANCELLED");
        bookingRepository.save(booking);

        for (int i = 0; i < booking.getNumberOfTravelers(); i++) {
            tourPackageService.decrementBookings(booking.getTourPackage().getId());
        }
    }
}