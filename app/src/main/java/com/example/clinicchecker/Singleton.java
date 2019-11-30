package com.example.clinicchecker;

public class Singleton {

    private static Singleton inst;
    private static Accounts accounts = new Accounts(10);
    private static Account currentLoggedIn;
    private static Services services;
    private static int serviceIndex;

    private Singleton() {

        services = new Services(10);
        services.addService((new Service("Walk in")));
        services.addService((new Service("CPR Training")));
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
}
