package za.co.wethinkcode.service;

import za.co.wethinkcode.model.Appointment;
import za.co.wethinkcode.model.Patient;

import java.util.*;

public abstract class Ward {

    private String wardName;
    private int capacity;
    private Map<String, Patient> patients;
    private List<Appointment> appointmentQueue;
    private int appointmentCounter =0;

    public Ward(String name, int wardCapacity){
        wardName = name;
        capacity = wardCapacity;
        patients = new HashMap<>();
        appointmentQueue = new ArrayList<>();
    }

    public boolean isFull(){
        return patients.size()>=capacity;
    }

    public void admitPatient(Patient patient){
        if(isFull()){
            throw new IllegalStateException("Ward is full");
        }
        if (patients.containsKey(patient.patientId())){
            throw new IllegalArgumentException("Patient already admitted");
        }

        patients.put(patient.patientId(),patient);

    }

    public void dischargePatient(String patientId){
        if(!patients.containsKey(patientId)){
            throw new IllegalArgumentException("Patient does not exist");
        }
        patients.remove(patientId);
    }

    public Patient getPatient(String patientId){
        if(!patients.containsKey(patientId)){
            return null;
        }
        return patients.get(patientId);
    }

    public Map<String, Patient> getAllPatients() {
        return Collections.unmodifiableMap(patients);
    }

    public Appointment scheduleAppointment(String patientId, String doctorName){
        if(!patients.containsKey(patientId)){
            throw new IllegalArgumentException("Patient not admitted");
        }
        Appointment newAppointment = new Appointment(++appointmentCounter, getPatient(patientId), doctorName);
        appointmentQueue.add(newAppointment);
        return newAppointment;
    }


    public Appointment processNextAppointment(){

        for (Appointment appointment: appointmentQueue){
            if (appointment.status()== Appointment.AppointmentStatus.SCHEDULED){
                appointment.updateStatus(Appointment.AppointmentStatus.IN_CONSULTATION);
                treat(appointment);
                appointment.updateStatus(Appointment.AppointmentStatus.COMPLETED);
                return appointment;
            }
        }
        return null;


    }

    public List<Appointment> appointmentQueue(){
        return Collections.unmodifiableList(appointmentQueue);
    }

    public String wardName() {
        return wardName;
    }

    public int capacity() {
        return capacity;
    }

    protected abstract void treat(Appointment appointment);
}
