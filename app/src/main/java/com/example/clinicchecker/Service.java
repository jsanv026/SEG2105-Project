package com.example.clinicchecker;

public class Service {

    private String serviceName;
    private String role;

    public Service(String serviceName, String role) {

        this.serviceName = serviceName;
        this.role = role;

    }

    public String getServiceName() { return serviceName; }
    public void setServiceName(String name) { serviceName = name; }
    public void setRole(String s) { role = s; }
    public String getRole() { return role; }

    public String toString() { return serviceName + " | " + role; }

}
