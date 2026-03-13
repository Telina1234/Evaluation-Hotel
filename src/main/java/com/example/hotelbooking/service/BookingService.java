package com.example.hotelbooking.service;

import com.example.hotelbooking.model.Booking;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {

    private final List<Booking> bookings = new ArrayList<>();

    public List<Booking> getAllBookings() {
        return bookings;
    }

    public String addBooking(Booking newBooking) {

        if (newBooking.getRoomNumber() < 1 || newBooking.getRoomNumber() > 9) {
            return "Erreur : le numéro de chambre doit être compris entre 1 et 9.";
        }
        for (Booking booking : bookings) {
            if (booking.getRoomNumber() == newBooking.getRoomNumber() &&
                    booking.getBookingDate().equals(newBooking.getBookingDate())) {
                return "Chambre " + newBooking.getRoomNumber() + " non disponible à cette date.";
            }
        }

        bookings.add(newBooking);
        return "Réservation ajoutée avec succès.";
    }
}