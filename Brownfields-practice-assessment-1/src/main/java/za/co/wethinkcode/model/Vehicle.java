package za.co.wethinkcode.model;

import java.time.Year;

public abstract class Vehicle {
    private final String registrationNumber;
    private final String make;
    private final String model;
    private int year;
    private boolean available;

    public Vehicle(String registrationNumber, String make, String model, int year) {
        this.registrationNumber = registrationNumber;
        this.make = make;
        this.model = model;
        defineYear(year);
        this.available = true;
    }

    public String registration() {
        return registrationNumber;
    }

    public String make() {
        return make;
    }

    public String model() {
        return model;
    }

    public int year() {
        return year;
    }

    public boolean available() {
        return available;
    }

    public void defineYear(int year) {
        if (year < 1900 || year > Year.now().getValue()) {
            throw new IllegalArgumentException("Year must be between 1900 and the current year.");
        }
        this.year = year;
    }

    public void makeAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return registrationNumber + " - " + make + " " + model + " (" + year + ")";
    }

    public abstract double calculateRentalCost(int days);
}