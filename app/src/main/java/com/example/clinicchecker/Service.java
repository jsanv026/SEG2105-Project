package com.example.clinicchecker;

public class Service {

    private String serviceName;
    private String role;

    public Service(String serviceName) {

        this.serviceName = serviceName;

    }

    public String getServiceName() { return serviceName; }
    public void setServiceName(String name) { serviceName = name; }

}
