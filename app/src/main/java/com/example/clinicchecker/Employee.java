package com.example.clinicchecker;

public class Employee extends Account{

    private Clinic clinic = new Clinic();
    private boolean hasCreatedProfile = false;

    public Employee(String username, String email, String firstName, String lastName, String password) {
        super(username, email, firstName, lastName, "Employee", password);

    }

    public boolean isDeletable() { return true; }
    public boolean hasCreatedProfile() { return hasCreatedProfile; }
    public void toggleCreatedProfile() { hasCreatedProfile = true; }
    public Clinic getClinic() { return clinic; }


}
