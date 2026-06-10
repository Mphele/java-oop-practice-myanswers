package za.co.wethinkcode.model;

import za.co.wethinkcode.service.Chargeable;

public class SpaService implements Chargeable {

    private String serviceName;
    private boolean isPremium;

    public SpaService(String serviceName, boolean isPremium){
        if (serviceName==null||serviceName.isEmpty()){
            throw new IllegalArgumentException();
        }
        this.serviceName=serviceName;
        this.isPremium = isPremium;
    }

    public String getServiceName() {
        return serviceName;
    }

    public boolean isPremium() {
        return isPremium;
    }


    @Override
    public double calculateCharge() {
        return isPremium ? 150.0:75 ;
    }

    @Override
    public String getChargeDescription() {
        String type = isPremium ? "Premium":"Standard";
        return String.format("%s Spa Service: %s", type, serviceName);
    }
}
