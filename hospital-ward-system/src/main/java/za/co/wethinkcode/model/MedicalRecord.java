package za.co.wethinkcode.model;

import java.util.List;
import java.util.ArrayList;

public class MedicalRecord {

    private String patientId;
    private List<String> diagnoses;
    private List<String> prescriptions;

    public MedicalRecord(String id, List<String> patientDiagnoses){
        patientId = id;
        diagnoses = new ArrayList<>(patientDiagnoses);
        prescriptions = new ArrayList<>();
    }

    public String patientId() {
        return patientId;
    }

    public List<String> diagnoses() {
        return new ArrayList<>(diagnoses);
    }

    public List<String> prescriptions() {
        return new ArrayList<>(prescriptions);
    }

    public void addDiagnosis(String diagnoses) {
        this.diagnoses.add(diagnoses);
    }
    public void addPrescription(String prescription) {
        prescriptions.add(prescription);
    }

    public String toString(){
        return "Patient ID: " + patientId() + " Diagnoses "+ diagnoses() + " Prescriptions " + prescriptions();
    }
}
