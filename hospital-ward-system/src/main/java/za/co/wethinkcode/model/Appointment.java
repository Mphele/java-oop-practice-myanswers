package za.co.wethinkcode.model;

public class Appointment {

    public enum AppointmentStatus {
        SCHEDULED,
        IN_CONSULTATION,
        COMPLETED,
        CANCELLED
    }

    private int appointmentId;
    private Patient patient;
    private String doctorName;
    private AppointmentStatus status = AppointmentStatus.SCHEDULED;

    public Appointment(int id, Patient appointmentPatient, String doctor){
        appointmentId = id;
        doctorName = doctor;
        patient = appointmentPatient;
    }

    public int appointmentId() {
        return appointmentId;
    }

    public Patient patient() {
        return patient;
    }

    public String doctorName() {
        return doctorName;
    }

    public AppointmentStatus status() {
        return status;
    }

    public void updateStatus(AppointmentStatus newStatus) {
        status = newStatus;
    }

    public String toString() {
        return "Appointment{" +
                "appointmentId=" + appointmentId +
                ", patient=" + patient.fullName() +
                ", doctorName='" + doctorName + '\'' +
                ", status=" + status +
                '}';
    }
}
