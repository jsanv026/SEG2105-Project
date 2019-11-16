package com.example.clinicchecker;

public class Service {

    private String serviceName;
    private String category;

    public Service(String serviceName, String category) {

        this.serviceName = serviceName;
        this.category = category;

    }

    public String getServiceName() { return serviceName; }
    public String getCategory() { return category; }

}
