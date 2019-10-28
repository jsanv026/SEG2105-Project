package com.example.clinicchecker;

public class Services {

    String[] services;
    String[] category;
    int sizeCategory, sizeServices = 0;

    public Services(int capacity) {

        services = new String[capacity];
        category = new String[capacity];

    }

    public boolean addCategory(String categoryElem) {

        for (int i = 0; i < category.length; i++) {

            if (category[i].equals(categoryElem)) {
                return false;
            }

        }

        for (int i = 0; i < category.length; i++) {

            if (category[i] == null) {
                category[i] = categoryElem;
                sizeCategory++;
                return true;
            }

        }

        increaseArraySize(0);
        category[sizeCategory++] = categoryElem;
        return true;
    }

    public boolean addService(String serviceElem) {

        for (int i = 0; i < services.length; i++) {

            if (services[i].equals(serviceElem)) {
                return false;
            }

        }

        for (int i = 0; i < services.length; i++) {

            if (services[i] == null) {
                services[i] = serviceElem;
                sizeServices++;
                return true;
            }

        }

        increaseArraySize(1);
        services[sizeServices++] = serviceElem;
        return true;
    }

    public String[] getServices() { return services; }
    public String[] getCategory() { return category; }

    private void increaseArraySize(int sel) {

        // Increases category size if sel == 0
        if (sel == 0) {

            String[] tmpArray = new String[category.length + 1];

            for (int i = 0; i < category.length; i++) {
                tmpArray[i] = category[i];
            }

        }

        // Increases services size if sel == 1
        if (sel == 1) {

            String[] tmpArray = new String[services.length + 1];

            for (int i = 0; i < services.length; i++) {
                tmpArray[i] = services[i];
            }

        }

    }

}
