package za.co.wethinkcode.model;

public class Motorbike extends Vehicle{
    public static final double DAILY_RATE = 300.00;
    private final int engineCC;

    public Motorbike(String registrationNumber, String make, String model, int year, int engineCC) {
        super(registrationNumber, make, model, year);
        this.engineCC = engineCC;
    }

    public int engineCC(){
        return engineCC;
    }

    @Override
    public double calculateRentalCost(int days) {
        if(days<1){
            throw new IllegalArgumentException();

        }

        return DAILY_RATE * days;
    }
}
