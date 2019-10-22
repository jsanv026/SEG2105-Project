package com.example.clinicchecker;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Account {

    private String name, role, password;
    private int index = 0;

    public Account(String name, String role, String password) {

        this.name = name;
        this.role = role;
        this.password = hashPassword(password);

    }

    public boolean checkPassword(String input) { return password.equals(hashPassword(input)); }

    private String hashPassword(String password) {

        String hash = null;
        try {
            MessageDigest m = MessageDigest.getInstance("SHA-256");
            m.update(password.getBytes(),0, password.length());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash;
    }

    public String getName() { return name; }
    public String getRole() { return role;}

}
