package za.co.wethinkcode.model;

import za.co.wethinkcode.service.Chargeable;

public class RoomBooking implements Chargeable {

    private String roomNumber;
    private int nights;
    private double baseRate;

    public RoomBooking(String roomNumber, int nights, double baseRate){
        if(roomNumber==null||roomNumber.isEmpty()){
            throw new IllegalArgumentException();
        }
        this.roomNumber = roomNumber;

        if(nights<1){
            throw new IllegalArgumentException();
        }
        this.nights = nights;

        if (baseRate<0.0){
            throw new IllegalArgumentException();
        }
        this.baseRate = baseRate;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public int getNights() {
        return nights;
    }

    public double getBaseRate() {
        return baseRate;
    }


    @Override
    public double calculateCharge() {
        return nights*baseRate;
    }

    @Override
    public String getChargeDescription() {
        return String.format("Room %s for %d nights", roomNumber, nights);
    }
}
