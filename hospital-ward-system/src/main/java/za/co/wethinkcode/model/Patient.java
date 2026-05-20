package za.co.wethinkcode.model;

public class Patient {

    private String patientId;
    private String fullName;
    private int age;

    public Patient(String id, String name, int patientAge){
        if(patientAge<0 || patientAge>150){
            throw new IllegalArgumentException("Age cannot be smaller than 0 or greater than 150");
        }

        patientId = id;
        fullName = name;
        age = patientAge;
    }

    public String patientId() {
        return patientId;
    }

    public String fullName() {
        return fullName;
    }

    public int age() {
        return age;
    }

    public void updateAge(int newAge) {
        if(newAge<0 || newAge>150){
            throw new IllegalArgumentException("Age cannot be smaller than 0 or greater than 150");
        }
        this.age = newAge;
    }

    public String toString(){
        return "Patient ID: "+patientId() + " Fullname: " + fullName() + " Patient Age: " + age();
    }
}
