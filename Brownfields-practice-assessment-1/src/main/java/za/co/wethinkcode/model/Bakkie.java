package za.co.wethinkcode.model;

public class Bakkie extends Vehicle {
    public static final double DAILY_RATE = 650.00;
    private final boolean isDoubleCab;

    public Bakkie(String registrationNumber, String make, String model, int year, boolean isDoubleCab) {
        super(registrationNumber, make, model, year);
        this.isDoubleCab = isDoubleCab;
    }

    public boolean isDoubleCab() {
        return isDoubleCab;
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
        return super.toString() + " - " + (isDoubleCab ? "Double Cab" : "Single Cab");
    }
}