package za.co.wethinkcode.service;

import java.util.ArrayList;

public class StandardTask extends DeliveryTask {

    public StandardTask(String trackingId, double distanceKm){
        super(trackingId, distanceKm);
    }

    @Override
    public double calculateCost() {
        return getDinstanceKm() * 15.0;
    }


}
