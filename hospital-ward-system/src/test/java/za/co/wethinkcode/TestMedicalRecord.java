package za.co.wethinkcode;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.model.MedicalRecord;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestMedicalRecord {

    private MedicalRecord buildRecord() {
        List<String> diagnoses = new ArrayList<>();
        diagnoses.add("Hypertension");
        diagnoses.add("Type 2 Diabetes");
        return new MedicalRecord("P001", diagnoses);
    }

    @Test
    void constructor_setsPatientIdAndDiagnoses() {
        MedicalRecord record = buildRecord();
        assertEquals("P001", record.patientId());
        assertEquals(2, record.diagnoses().size());
    }

    @Test
    void constructor_storesMutableCopyOfDiagnoses() {
        List<String> original = new ArrayList<>();
        original.add("Asthma");
        MedicalRecord record = new MedicalRecord("P002", original);

        original.add("Broken Arm"); // mutate the list the caller still holds

        assertEquals(1, record.diagnoses().size(),
                "Mutating the original list after construction must not affect the record's diagnoses");
    }

    @Test
    void diagnoses_returnsDefensiveCopy() {
        MedicalRecord record = buildRecord();
        List<String> copy = record.diagnoses();
        copy.add("Flu"); // mutate the returned list

        assertEquals(2, record.diagnoses().size(),
                "Mutating the returned diagnoses list must not affect the record's internal list");
    }

    @Test
    void prescriptions_startsEmpty() {
        MedicalRecord record = buildRecord();
        assertEquals(0, record.prescriptions().size(),
                "A new MedicalRecord should have no prescriptions");
    }

    @Test
    void prescriptions_returnsDefensiveCopy() {
        MedicalRecord record = buildRecord();
        record.addPrescription("Metformin");

        List<String> copy = record.prescriptions();
        copy.add("Aspirin"); // mutate the returned list

        assertEquals(1, record.prescriptions().size(),
                "Mutating the returned prescriptions list must not affect the record's internal list");
    }

    @Test
    void addDiagnosis_increasesCount() {
        MedicalRecord record = buildRecord();
        record.addDiagnosis("Migraine");
        assertEquals(3, record.diagnoses().size());
    }

    @Test
    void addPrescription_increasesCount() {
        MedicalRecord record = buildRecord();
        record.addPrescription("Amlodipine");
        record.addPrescription("Metformin");
        assertEquals(2, record.prescriptions().size());
    }

    @Test
    void toString_containsPatientId() {
        MedicalRecord record = buildRecord();
        assertTrue(record.toString().contains("P001"),
                "toString() should contain the patient ID");
    }

    @Test
    void toString_containsDiagnosisDetails() {
        MedicalRecord record = buildRecord();
        String result = record.toString();
        assertTrue(result.contains("Hypertension"),
                "toString() should mention each diagnosis");
        assertTrue(result.contains("Type 2 Diabetes"),
                "toString() should mention each diagnosis");
    }
}
