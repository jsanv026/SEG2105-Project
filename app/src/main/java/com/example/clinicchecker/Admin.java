package com.example.clinicchecker;

public class Admin extends Account{

    public Admin(String username, String email, String firstName, String lastName, String password) { super(username, email, firstName, lastName,"Admin", password); }

    public boolean isDeletable() { return false; }
}
