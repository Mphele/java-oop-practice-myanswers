package za.co.wethinkcode;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.model.Appointment;
import za.co.wethinkcode.model.Patient;

import static org.junit.jupiter.api.Assertions.*;

class TestAppointment {

    private Patient samplePatient() {
        return new Patient("P001", "Alice Dlamini", 34);
    }

    @Test
    void constructor_setsAllFieldsCorrectly() {
        Appointment appt = new Appointment(1, samplePatient(), "Dr Nkosi");
        assertEquals(1, appt.appointmentId());
        assertEquals("Alice Dlamini", appt.patient().fullName());
        assertEquals("Dr Nkosi", appt.doctorName());
    }

    @Test
    void constructor_defaultsStatusToScheduled() {
        Appointment appt = new Appointment(1, samplePatient(), "Dr Nkosi");
        assertEquals(Appointment.AppointmentStatus.SCHEDULED, appt.status(),
                "A newly created appointment should have SCHEDULED status");
    }

    @Test
    void updateStatus_toInConsultation() {
        Appointment appt = new Appointment(2, samplePatient(), "Dr Dube");
        appt.updateStatus(Appointment.AppointmentStatus.IN_CONSULTATION);
        assertEquals(Appointment.AppointmentStatus.IN_CONSULTATION, appt.status());
    }

    @Test
    void updateStatus_toCompleted() {
        Appointment appt = new Appointment(3, samplePatient(), "Dr Sithole");
        appt.updateStatus(Appointment.AppointmentStatus.COMPLETED);
        assertEquals(Appointment.AppointmentStatus.COMPLETED, appt.status());
    }

    @Test
    void updateStatus_toCancelled() {
        Appointment appt = new Appointment(4, samplePatient(), "Dr Mokoena");
        appt.updateStatus(Appointment.AppointmentStatus.CANCELLED);
        assertEquals(Appointment.AppointmentStatus.CANCELLED, appt.status());
    }

    @Test
    void appointmentStatus_hasExactlyFourValues() {
        Appointment.AppointmentStatus[] values = Appointment.AppointmentStatus.values();
        assertEquals(4, values.length,
                "AppointmentStatus must define exactly 4 values: SCHEDULED, IN_CONSULTATION, COMPLETED, CANCELLED");
    }

    @Test
    void toString_containsPatientNameAndDoctorName() {
        Appointment appt = new Appointment(5, samplePatient(), "Dr Nkosi");
        String result = appt.toString();
        assertTrue(result.contains("Alice Dlamini"), "toString() should include the patient's full name");
        assertTrue(result.contains("Dr Nkosi"),      "toString() should include the doctor's name");
    }
}
