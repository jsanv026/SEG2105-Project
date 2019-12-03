package com.example.clinicchecker;

public class Services {

    Service[] services;
    int sizeServices;

    public Services(int capacity) {

        services = new Service[capacity];
        sizeServices = 0;

    }

    public boolean addService(Service serviceElem) {

        int index = 0;

        if (findService(serviceElem) != -1) {
            return false;
        }

        for (int i = 0; i < services.length; i++) {

            if (services[i] == null) {
                services[i] = serviceElem;
                sizeServices++;
                return true;
            }

            index = i;

        }

        increaseArraySize();
        services[index + 1] = serviceElem;
        sizeServices++;
        return true;
    }

    public boolean editService(Service service, String name, String role) {

        int index = findService(service);
        if (index == -1) {return false;}

        services[index].setServiceName(name);
        services[index].setRole(role);
        return true;

    }

    public void deleteService(int i) {
        services[i] = null;
        sizeServices--;
    }

    public int getSize() { return sizeServices; }
    public Service getService(int i) { return services[i]; }
    public Service[] getServiceArr() { return services; }

    public int findService(Service serviceElem) {

        if (sizeServices == 0) { return -1; }

        for (int i = 0; i < services.length; i++) {

            if (services[i] != null) {

                if (services[i].getServiceName() != null && serviceElem.getServiceName().equals(services[i].getServiceName())) {
                    return i;
                }
            }
        }

        return -1;

    }


    private void increaseArraySize() {

        Service[] tmpArray = new Service[sizeServices + 1];

        for (int i = 0; i < services.length - 1; i++) {
             tmpArray[i] = services[i];
         }

    }

}
