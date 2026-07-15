package za.co.wethinkcode.model;

public class Car extends Vehicle {
    public static final double DAILY_RATE = 450.00;
    private int numberOfSeats;

    public Car(String registrationNumber, String make, String model, int year, int numberOfSeats) {
        super(registrationNumber, make, model, year);
        setNumberOfSeats(numberOfSeats);
    }

    public int numberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        if (numberOfSeats < 1) {
            throw new IllegalArgumentException("Number of seats must be at least 1.");
        }
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public double calculateRentalCost(int days) {
        if (days < 1) {
            throw new IllegalArgumentException("Days must be at least 1.");
        }
        return DAILY_RATE * days;
    }

    @Override
    public String toString() {
        return super.toString() + " - " + numberOfSeats + " seats";
    }
}