package za.co.wethinkcode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.co.wethinkcode.model.Appointment;
import za.co.wethinkcode.model.Patient;
import za.co.wethinkcode.service.Billable;
import za.co.wethinkcode.service.GeneralWard;
import za.co.wethinkcode.service.ICU;
import za.co.wethinkcode.service.Ward;

import static org.junit.jupiter.api.Assertions.*;

class TestWard {

    private GeneralWard generalWard;
    private ICU icu;
    private Patient alice;
    private Patient bob;

    @BeforeEach
    void setUp() {
        generalWard = new GeneralWard("Ward A", 5);
        icu         = new ICU("ICU North", 3);
        alice       = new Patient("P001", "Alice Dlamini", 34);
        bob         = new Patient("P002", "Bob Mokoena",   52);
    }

    // -------------------------------------------------------
    // admitPatient
    // -------------------------------------------------------

    @Test
    void admitPatient_addsPatientToWard() {
        generalWard.admitPatient(alice);
        assertNotNull(generalWard.getPatient("P001"),
                "getPatient() should return the patient after they are admitted");
        assertEquals("Alice Dlamini", generalWard.getPatient("P001").fullName());
    }

    @Test
    void admitPatient_throwsIllegalStateException_whenWardIsFull() {
        GeneralWard tiny = new GeneralWard("Tiny Ward", 1);
        tiny.admitPatient(alice);
        assertThrows(IllegalStateException.class,
                () -> tiny.admitPatient(bob),
                "Should throw IllegalStateException when admitting a patient to a full ward");
    }

    @Test
    void admitPatient_throwsIllegalArgumentException_whenPatientAlreadyAdmitted() {
        generalWard.admitPatient(alice);
        assertThrows(IllegalArgumentException.class,
                () -> generalWard.admitPatient(alice),
                "Should throw IllegalArgumentException when admitting a patient already in the ward");
    }

    // -------------------------------------------------------
    // dischargePatient
    // -------------------------------------------------------

    @Test
    void dischargePatient_removesPatientFromWard() {
        generalWard.admitPatient(alice);
        generalWard.dischargePatient("P001");
        assertNull(generalWard.getPatient("P001"),
                "getPatient() should return null after the patient is discharged");
    }

    @Test
    void dischargePatient_throwsWhenPatientNotFound() {
        assertThrows(IllegalArgumentException.class,
                () -> generalWard.dischargePatient("UNKNOWN"),
                "Should throw when discharging a patient not currently admitted");
    }

    // -------------------------------------------------------
    // isFull
    // -------------------------------------------------------

    @Test
    void isFull_returnsFalse_whenBelowCapacity() {
        generalWard.admitPatient(alice);
        assertFalse(generalWard.isFull(),
                "isFull() should be false when admitted patients are below capacity");
    }

    @Test
    void isFull_returnsTrue_atCapacity() {
        GeneralWard tiny = new GeneralWard("Tiny", 1);
        tiny.admitPatient(alice);
        assertTrue(tiny.isFull(),
                "isFull() should be true when admitted patients equal capacity");
    }

    // -------------------------------------------------------
    // getAllPatients
    // -------------------------------------------------------

    @Test
    void getAllPatients_returnsAllAdmittedPatients() {
        generalWard.admitPatient(alice);
        generalWard.admitPatient(bob);
        assertEquals(2, generalWard.getAllPatients().size());
    }

    @Test
    void getAllPatients_returnsUnmodifiableView() {
        generalWard.admitPatient(alice);
        assertThrows(UnsupportedOperationException.class,
                () -> generalWard.getAllPatients().put("HACK", alice),
                "getAllPatients() should return an unmodifiable map");
    }

    @Test
    void getPatient_returnsNull_forUnknownId() {
        assertNull(generalWard.getPatient("NOBODY"),
                "getPatient() should return null for a patient ID that was never admitted");
    }

    // -------------------------------------------------------
    // scheduleAppointment
    // -------------------------------------------------------

    @Test
    void scheduleAppointment_createsAppointmentWithCorrectDetails() {
        generalWard.admitPatient(alice);
        Appointment appt = generalWard.scheduleAppointment("P001", "Dr Nkosi");
        assertNotNull(appt);
        assertEquals("Alice Dlamini", appt.patient().fullName());
        assertEquals("Dr Nkosi", appt.doctorName());
        assertEquals(Appointment.AppointmentStatus.SCHEDULED, appt.status());
    }

    @Test
    void scheduleAppointment_assignsUniqueIncrementingIds() {
        generalWard.admitPatient(alice);
        generalWard.admitPatient(bob);
        Appointment a1 = generalWard.scheduleAppointment("P001", "Dr Nkosi");
        Appointment a2 = generalWard.scheduleAppointment("P002", "Dr Dube");
        assertNotEquals(a1.appointmentId(), a2.appointmentId(),
                "Each appointment should receive a unique ID");
        assertEquals(a1.appointmentId() + 1, a2.appointmentId(),
                "Appointment IDs should increment by 1");
    }

    @Test
    void scheduleAppointment_throwsWhenPatientNotAdmitted() {
        assertThrows(IllegalArgumentException.class,
                () -> generalWard.scheduleAppointment("UNKNOWN", "Dr Nkosi"),
                "Should throw when scheduling an appointment for a patient not admitted to the ward");
    }

    @Test
    void scheduleAppointment_addsAppointmentToQueue() {
        generalWard.admitPatient(alice);
        generalWard.scheduleAppointment("P001", "Dr Nkosi");
        assertEquals(1, generalWard.appointmentQueue().size());
    }

    // -------------------------------------------------------
    // processNextAppointment
    // -------------------------------------------------------

    @Test
    void processNextAppointment_returnsCompletedAppointment() {
        generalWard.admitPatient(alice);
        generalWard.scheduleAppointment("P001", "Dr Nkosi");
        Appointment completed = generalWard.processNextAppointment();
        assertNotNull(completed);
        assertEquals(Appointment.AppointmentStatus.COMPLETED, completed.status());
    }

    @Test
    void processNextAppointment_processesFifoOrder() {
        generalWard.admitPatient(alice);
        generalWard.admitPatient(bob);
        generalWard.scheduleAppointment("P001", "Dr Nkosi");
        generalWard.scheduleAppointment("P002", "Dr Dube");
        Appointment first = generalWard.processNextAppointment();
        assertEquals("Alice Dlamini", first.patient().fullName(),
                "First appointment scheduled should be processed first (FIFO)");
    }

    @Test
    void processNextAppointment_returnsNull_whenNoScheduledAppointments() {
        assertNull(generalWard.processNextAppointment(),
                "processNextAppointment() should return null when the queue has no pending appointments");
    }

    // -------------------------------------------------------
    // appointmentQueue — unmodifiable
    // -------------------------------------------------------

    @Test
    void appointmentQueue_returnsUnmodifiableView() {
        generalWard.admitPatient(alice);
        generalWard.scheduleAppointment("P001", "Dr Nkosi");
        assertThrows(UnsupportedOperationException.class,
                () -> generalWard.appointmentQueue().clear(),
                "appointmentQueue() should return an unmodifiable list");
    }

    // -------------------------------------------------------
    // Inheritance
    // -------------------------------------------------------

    @Test
    void generalWard_isSubclassOfWard() {
        assertTrue(generalWard instanceof Ward,
                "GeneralWard must extend Ward");
    }

    @Test
    void icu_isSubclassOfWard() {
        assertTrue(icu instanceof Ward,
                "ICU must extend Ward");
    }

    // -------------------------------------------------------
    // wardName and capacity
    // -------------------------------------------------------

    @Test
    void wardName_returnsCorrectName() {
        assertEquals("Ward A",    generalWard.wardName());
        assertEquals("ICU North", icu.wardName());
    }

    @Test
    void capacity_returnsCorrectCapacity() {
        assertEquals(5, generalWard.capacity());
        assertEquals(3, icu.capacity());
    }

    // -------------------------------------------------------
    // ICU end-to-end
    // -------------------------------------------------------

    @Test
    void icu_canProcessAppointments() {
        icu.admitPatient(alice);
        icu.scheduleAppointment("P001", "Dr Sithole");
        Appointment completed = icu.processNextAppointment();
        assertNotNull(completed);
        assertEquals(Appointment.AppointmentStatus.COMPLETED, completed.status());
        assertEquals("Alice Dlamini", completed.patient().fullName());
    }

    // -------------------------------------------------------
    // Billable interface
    // -------------------------------------------------------

    @Test
    void generalWard_implementsBillable() {
        assertTrue(generalWard instanceof Billable,
                "GeneralWard must implement the Billable interface");
    }

    @Test
    void icu_implementsBillable() {
        assertTrue(icu instanceof Billable,
                "ICU must implement the Billable interface");
    }

    @Test
    void generalWard_calculateBill_returnsCorrectAmountForOneHour() {
        // GeneralWard charges R500 per hour
        double bill = generalWard.calculateBill(60);
        assertEquals(500.0, bill, 0.01,
                "GeneralWard should charge R500.00 for a 60-minute appointment");
    }

    @Test
    void generalWard_calculateBill_scalesWithDuration() {
        // 30 minutes should be half of a full hour
        double bill = generalWard.calculateBill(30);
        assertEquals(250.0, bill, 0.01,
                "GeneralWard bill should scale proportionally with duration");
    }

    @Test
    void icu_calculateBill_returnsCorrectAmountForOneHour() {
        // ICU charges R2500 per hour
        double bill = icu.calculateBill(60);
        assertEquals(2500.0, bill, 0.01,
                "ICU should charge R2500.00 for a 60-minute appointment");
    }

    @Test
    void icu_calculateBill_isHigherThanGeneralWard_forSameDuration() {
        double generalBill = generalWard.calculateBill(60);
        double icuBill     = icu.calculateBill(60);
        assertTrue(icuBill > generalBill,
                "ICU billing should be higher than GeneralWard billing for the same duration");
    }

    @Test
    void billable_canBeReferencedViaInterface() {
        // Both wards can be used polymorphically through the Billable interface
        Billable billableGeneral = generalWard;
        Billable billableIcu    = icu;
        assertEquals(500.0,  billableGeneral.calculateBill(60), 0.01);
        assertEquals(2500.0, billableIcu.calculateBill(60),     0.01);
    }
}
