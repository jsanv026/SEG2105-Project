package com.example.clinicchecker;

public class Singleton {

    private static Singleton inst;
    private static Accounts accounts = new Accounts(10);
    private static Account currentLoggedIn;
    private static Services services;
    private static int serviceIndex;
    private static Clinics clinics;
    private static int clinicIndex;
    private static int num;
    private static Service serviceToSearch;

    private Singleton() {

        services = new Services(10);
        services.addService((new Service("Walk in", "Staff")));
        services.addService((new Service("CPR Training", "Nurse")));
        services.addService((new Service("Flu Shots", "Nurse")));
        services.addService((new Service("Blood Donations", "Nurse")));
    }

    public static Singleton getInstance() {

        if (inst == null) {
            inst = new Singleton();
        }

        return inst;

    }

    public static Accounts getAccounts() { return accounts; }
    public static void setCurrentLoggedIn(Account s) { currentLoggedIn = s; }
    public static Account getCurrentLoggedIn() { return currentLoggedIn; }
    public static Services getServices() { return services; }
    public static void setServicesIndex(int i) { serviceIndex = i; }
    public static int getServiceIndex() { return serviceIndex; }
    public static Clinic[] getClinics() {

        if (clinics == null) {

            clinics = new Clinics();
        }
        return clinics.getClinics();
    }
    public static void setClinicIndex(int i) { clinicIndex = i;}
    public static int getClinicIndex() { return clinicIndex;}
    public static void setNum(int i) { num = i; }
    public static int getNum() { return num; }
    public static void setServiceToSearch(Service s) { serviceToSearch = s; }
    public static Service getServiceToSearch() { return serviceToSearch; }
}
