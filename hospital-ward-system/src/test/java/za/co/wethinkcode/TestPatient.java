package za.co.wethinkcode;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.model.Patient;

import static org.junit.jupiter.api.Assertions.*;

class TestPatient {

    @Test
    void constructor_setsAllFields() {
        Patient patient = new Patient("P001", "Alice Dlamini", 34);
        assertEquals("P001", patient.patientId());
        assertEquals("Alice Dlamini", patient.fullName());
        assertEquals(34, patient.age());
    }

    @Test
    void constructor_throwsOnNegativeAge() {
        assertThrows(IllegalArgumentException.class,
                () -> new Patient("P002", "Bob Mokoena", -1),
                "Should throw when age is negative");
    }

    @Test
    void constructor_throwsWhenAgeExceedsMax() {
        assertThrows(IllegalArgumentException.class,
                () -> new Patient("P003", "Carol Ndlovu", 151),
                "Should throw when age exceeds 150");
    }

    @Test
    void constructor_allowsZeroAge() {
        assertDoesNotThrow(() -> new Patient("P004", "Baby Sithole", 0),
                "Age of 0 should be a valid age");
    }

    @Test
    void constructor_allows150Age() {
        assertDoesNotThrow(() -> new Patient("P005", "Elder Dube", 150),
                "Age of 150 should be the valid upper bound");
    }

    @Test
    void updateAge_updatesValueCorrectly() {
        Patient patient = new Patient("P006", "Dave Nkosi", 25);
        patient.updateAge(30);
        assertEquals(30, patient.age());
    }

    @Test
    void updateAge_throwsOnNegativeValue() {
        Patient patient = new Patient("P007", "Eve Zulu", 40);
        assertThrows(IllegalArgumentException.class,
                () -> patient.updateAge(-5),
                "Should throw when update age is negative");
    }

    @Test
    void updateAge_throwsWhenAgeExceedsMax() {
        Patient patient = new Patient("P008", "Frank Cele", 40);
        assertThrows(IllegalArgumentException.class,
                () -> patient.updateAge(200),
                "Should throw when update age exceeds 150");
    }

    @Test
    void toString_containsPatientIdFullNameAndAge() {
        Patient patient = new Patient("P009", "Grace Mthembu", 55);
        String result = patient.toString();
        assertTrue(result.contains("P009"),     "toString() should contain the patient ID");
        assertTrue(result.contains("Grace Mthembu"), "toString() should contain the full name");
        assertTrue(result.contains("55"),       "toString() should contain the age");
    }
}
