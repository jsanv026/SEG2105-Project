package com.example.clinicchecker;

public class Services {

    Service[] services;
    String[] category;
    int sizeCategory, sizeServices = 0;

    public Services(int capacity) {

        services = new Service[capacity];
        category = new String[3];

    }

    public boolean addService(Service serviceElem) {

        for (int i = 0; i < services.length; i++) {

            if (services[i].equals(serviceElem)) {
                return false;
            }

        }

        for (int i = 0; i < services.length; i++) {

            if (services[i] == null) {
                services[i] = serviceElem;
                sizeServices++;
                if (checkCategoryExists(serviceElem.getCategory())) {
                    addCategory(serviceElem.getCategory());
                }
                return true;
            }

        }

        increaseArraySize(1);
        services[sizeServices++] = serviceElem;
        if (checkCategoryExists(serviceElem.getCategory())) {
            addCategory(serviceElem.getCategory());
        }
        return true;
    }

    public Service[] getServicesArr() { return services; }
    public String[] getCategoryArr() { return category; }

    private boolean checkCategoryExists(String category) {

        for (int i = 0; i < this.category.length - 1; i++) {

            if (this.category[i].equals(category)) {
                return true;
            }

        }
        return false;
    }

    private boolean addCategory(String category) {

        for (int i = 0; i < this.category.length - 1; i++) {

            if (this.category[i] == null) {
                this.category[i] = category;
                return true;
            }

        }

        increaseArraySize(0);
        this.category[sizeCategory++] = category;
        return true;

    }

    private void increaseArraySize(int sel) {

        // Increases category size if sel == 0
        if (sel == 0) {

            String[] tmpArray = new String[category.length + 1];

            for (int i = 0; i < category.length - 1; i++) {
                tmpArray[i] = category[i];
            }

        }

        // Increases services size if sel == 1
        if (sel == 1) {

            Service[] tmpArray = new Service[services.length + 1];

            for (int i = 0; i < services.length - 1; i++) {
                tmpArray[i] = services[i];
            }

        }

    }

}
