package za.co.wethinkcode.service;

public class ExpressTask extends DeliveryTask {

    private boolean requiresRefrigeration;

    public ExpressTask(String trackingId, double distanceKm, boolean requiresRefrigeration){
        super(trackingId, distanceKm);
        this.requiresRefrigeration = requiresRefrigeration;
    }


    @Override
    public double calculateCost() {
        double cost = getDinstanceKm() * 25.0;
        return requiresRefrigeration ? cost+100: cost;
    }
}
