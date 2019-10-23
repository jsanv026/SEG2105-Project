package com.example.clinicchecker;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Account {

    private String name, role, password, email;
    private int index;

    public Account(String email, String name, String role, String password, int index) {

        this.name = name;
        this.email = email;
        this.role = role;
        this.password = hashPassword(password);
        this.index = index;

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

    public String getName() { return name; }
    public String getRole() { return role;}
    public int getIndex() { return index; }
    public String getEmail() { return email; }

}
