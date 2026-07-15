package za.co.wethinkcode.model;

public class Booking {
    public enum BookingStatus { PENDING, ACTIVE, RETURNED, CANCELLED }

    private final int bookingId;
    private final String customerName;
    private final Vehicle vehicle;
    private int rentalDays;
    private BookingStatus status;

    public Booking(int bookingId, String customerName, Vehicle vehicle, int rentalDays) {
        if (rentalDays < 1) {
            throw new IllegalArgumentException("Rental days must be at least 1.");
        }
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.vehicle = vehicle;
        this.rentalDays = rentalDays;
        this.status = BookingStatus.PENDING;
    }

    public int bookingId() {
        return bookingId;
    }

    public String customer() {
        return customerName;
    }

    public Vehicle vehicle() {
        return vehicle;
    }

    public int rentalDays() {
        return rentalDays;
    }

    public BookingStatus status() {
        return status;
    }

    public void updateBookingStatus(BookingStatus status) {
        this.status = status;
    }

    public void updateRentalDays(int days) {
        if (days < 1) {
            throw new IllegalArgumentException("Rental days must be at least 1.");
        }
        this.rentalDays = days;
    }

    public double totalCost() {
        return vehicle.calculateRentalCost(rentalDays);
    }

    @Override
    public String toString() {
        return "Booking #" + bookingId + " - " + customerName + " - " + vehicle.registration()
                + " - " + rentalDays + " day(s) - " + status + " - R" + totalCost();
    }
}