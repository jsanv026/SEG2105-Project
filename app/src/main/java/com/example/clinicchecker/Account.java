package com.example.clinicchecker;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Account {

    private String firstName, lastName, role, password, email, username;
    private boolean isDeletable;

    public Account(String username, String email, String firstName, String lastName, String role, String password) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.password = hashPassword(password);
        this.username = username;

    }

    public String toString() {

        return "Email: " + email +
                "\nUsername: " + username +
                "\nFirst Name: " + firstName +
                "\nLast Name: " + lastName +
                "\nRole: " + role;

    }
    
    public boolean equals(Account acc) {

        if (acc.getEmail().equals(email)) { return true; }

        return false;
    }

    public boolean checkPassword(String input) { return password.equals(hashPassword(input)); }

    private String hashPassword(String password) {

        String hash = null;
        try {
            MessageDigest m = MessageDigest.getInstance("SHA-256");
            byte[] digest = m.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < digest.length; i++) {
                sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
            }
            hash = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getRole() { return role;}
    public String getUser() { return username; }
    public String getEmail() { return email; }
    public boolean isDeletable() { return isDeletable; }

}
