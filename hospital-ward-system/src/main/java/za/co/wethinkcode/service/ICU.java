package za.co.wethinkcode.service;

import za.co.wethinkcode.model.Appointment;

public class ICU extends Ward implements Billable{

    private String wardName;
    private int capacity;

    public ICU(String wardName, int capacity){
        super(wardName,capacity);
        this.wardName = wardName;
        this.capacity = capacity;

    }

    @Override
    protected void treat(Appointment appointment) {
        System.out.printf("%s treating %s with Dr %s  using intensive monitoring and life support.", wardName, appointment.patient().fullName(),appointment.doctorName());
    }


    @Override
    public double calculateBill(int durationMinutes) {
        return 2500*durationMinutes/60.0;
    }

}
