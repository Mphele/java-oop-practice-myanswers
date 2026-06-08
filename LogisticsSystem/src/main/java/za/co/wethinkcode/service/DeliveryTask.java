package za.co.wethinkcode.service;

import java.util.ArrayList;
import java.util.List;

public abstract class DeliveryTask {

    private String trackingId;
    private double distanceKm;
    private List<String> checkpoints;

    public DeliveryTask(String trackingId, double distanceKm){
        if ( trackingId==null||trackingId.isEmpty()){
            throw new IllegalArgumentException();
        }
        if (distanceKm<=0){
            throw new IllegalArgumentException();
        }

        this.trackingId = trackingId;
        this.distanceKm = distanceKm;
        this.checkpoints = new ArrayList<>();
    }

    public String getTrackingId() {
        return trackingId;
    }

    public double getDinstanceKm() {
        return distanceKm;
    }

    public List<String> getCheckpoints() {
        return new ArrayList<>(checkpoints);
    }

    public void addCheckpoint(String checkpoint) {
        if (checkpoint==null||checkpoint.isEmpty()){
            throw new IllegalArgumentException();
        }
        this.checkpoints.add(checkpoint);
    }

    public abstract double calculateCost();
}
