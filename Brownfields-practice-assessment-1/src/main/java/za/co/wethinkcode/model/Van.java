package za.co.wethinkcode.model;

public class Van extends Vehicle {
    public static final double DAILY_RATE = 800.00;
    private double payloadCapacityKg;

    public Van(String registrationNumber, String make, String model, int year, double payloadCapacityKg) {
        super(registrationNumber, make, model, year);
        definePayloadCapacityKg(payloadCapacityKg);
    }

    public double payloadCapacityKg() {
        return payloadCapacityKg;
    }

    public void definePayloadCapacityKg(double payloadCapacityKg) {
        if (payloadCapacityKg <= 0) {
            throw new IllegalArgumentException("Payload capacity must be greater than 0.");
        }
        this.payloadCapacityKg = payloadCapacityKg;
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
        return super.toString() + " - " + payloadCapacityKg + "kg payload";
    }
}