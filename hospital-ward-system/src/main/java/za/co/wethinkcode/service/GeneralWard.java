package za.co.wethinkcode.service;

import za.co.wethinkcode.model.Appointment;

public class GeneralWard extends Ward implements Billable{

    private String wardName;
    private int capacity;

    public GeneralWard(String wardName, int capacity){
        super(wardName,capacity);
        this.wardName = wardName;
        this.capacity = capacity;

    }

    @Override
    protected void treat(Appointment appointment) {
        System.out.printf("%s treating %s with Dr %s using standard general care protocols.", wardName, appointment.patient().fullName(),appointment.doctorName());
    }


    @Override
    public double calculateBill(int durationMinutes) {
        return 500*durationMinutes/60.0;
    }
}
