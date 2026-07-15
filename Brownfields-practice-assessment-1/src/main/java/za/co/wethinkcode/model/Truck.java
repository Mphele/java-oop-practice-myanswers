package za.co.wethinkcode.model;

public class Truck extends Vehicle {
    public static final double DAILY_RATE = 1500.00;
    private double grossVehicleMassTons;

    public Truck(String registrationNumber, String make, String model, int year, double grossVehicleMassTons) {
        super(registrationNumber, make, model, year);
        defineGrossVehicleMassTons(grossVehicleMassTons);
    }

    public double grossVehicleMassTons() {
        return grossVehicleMassTons;
    }

    public void defineGrossVehicleMassTons(double grossVehicleMassTons) {
        if (grossVehicleMassTons <= 0) {
            throw new IllegalArgumentException("GVM must be greater than 0.");
        }
        this.grossVehicleMassTons = grossVehicleMassTons;
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
        return super.toString() + " - " + grossVehicleMassTons + "t GVM";
    }
}