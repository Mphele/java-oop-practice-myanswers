package za.co.wethinkcode.service;

import java.util.ArrayList;
import java.util.List;

public class GuestAccount {

    private String accountId;
    private List<Chargeable> charges;

    public GuestAccount(String accountId){
        if(accountId==null || accountId.isEmpty()){
            throw new IllegalArgumentException();
        }
        this.accountId = accountId;
        charges = new ArrayList<>();

    }

    public String getAccountId() {
        return accountId;
    }

    public List<Chargeable> getCharges() {
        return new ArrayList<>(charges);
    }

    public void addCharge(Chargeable charge){
        if(charge==null){
            throw new IllegalArgumentException();
        }

        charges.add(charge);
    }

    public double getTotalBalance(){
        double total = 0;
        for(Chargeable charge:charges){
            total+= charge.calculateCharge();
        }

        return total;
    }
}
