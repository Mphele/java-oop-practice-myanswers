package za.co.wethinkcode.service;

import za.co.wethinkcode.model.Booking;
import za.co.wethinkcode.model.Booking.BookingStatus;
import za.co.wethinkcode.model.Vehicle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RentalAgency {
    private final String agencyName;
    private final List<Vehicle> fleet;
    private final List<Booking> bookings;
    private int bookingCounter;

    public RentalAgency(String agencyName) {
        this.agencyName = agencyName;
        this.fleet = new ArrayList<>();
        this.bookings = new ArrayList<>();
        this.bookingCounter = 0;
    }

    public void addVehicle(Vehicle vehicle) {
        fleet.add(vehicle);
    }

    public List<Vehicle> fleet() {
        return Collections.unmodifiableList(fleet);
    }

    public List<Vehicle> availableVehicles() {
        List<Vehicle> available = new ArrayList<>();
        for (Vehicle v : fleet) {
            if (v.available()) {
                available.add(v);
            }
        }
        return available;
    }

    public Booking book(String registrationNumber, String customerName, int rentalDays) {
        for (Vehicle v : fleet) {
            if (v.registration().equalsIgnoreCase(registrationNumber)) {
                if (!v.available()) {
                    throw new IllegalArgumentException("Vehicle is not available.");
                }
                bookingCounter++;
                Booking booking = new Booking(bookingCounter, customerName, v, rentalDays);
                v.makeAvailable(false);
                bookings.add(booking);
                return booking;
            }
        }
        throw new IllegalArgumentException("Vehicle not found.");
    }

    public Booking completeBooking(int bookingId) {
        for (Booking b : bookings) {
            if (b.bookingId() == bookingId) {
                b.updateBookingStatus(BookingStatus.RETURNED);
                b.vehicle().makeAvailable(true);
                return b;
            }
        }
        throw new IllegalArgumentException("Booking not found.");
    }

    public Booking cancelBooking(int bookingId) {
        for (Booking b : bookings) {
            if (b.bookingId() == bookingId) {
                b.updateBookingStatus(BookingStatus.CANCELLED);
                b.vehicle().makeAvailable(true);
                return b;
            }
        }
        throw new IllegalArgumentException("Booking not found.");
    }

    public List<Booking> bookings() {
        return Collections.unmodifiableList(bookings);
    }

    public String agency() {
        return agencyName;
    }
}