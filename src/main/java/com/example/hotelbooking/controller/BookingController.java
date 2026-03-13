package com.example.hotelbooking.controller;

import com.example.hotelbooking.model.Booking;
import com.example.hotelbooking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
@CrossOrigin(origins = "*")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public List<Booking> getBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable int id) {
        Booking booking = bookingService.getAllBookings().stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElse(null);

        if (booking == null) {
            return ResponseEntity.status(404).body("Réservation introuvable");
        }
        return ResponseEntity.ok(booking);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createBooking(@RequestBody Booking newBooking) {
        String result = bookingService.addBooking(newBooking);

        if (result.startsWith("Erreur") || result.contains("non disponible")) {
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok(bookingService.getAllBookings());
    }
}