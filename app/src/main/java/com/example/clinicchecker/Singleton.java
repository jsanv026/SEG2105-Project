package com.example.clinicchecker;

public class Singleton {

    private static Singleton inst;
    private static Accounts accounts = new Accounts(10);
    private static Account currentLoggedIn;
    private static Services services = new Services(10);

    private Singleton() { }

    public static Singleton getInstance() {

        if (inst == null) { inst = new Singleton(); }

        return inst;

    }

    public static Accounts getAccounts() { return accounts; }
    public static void setCurrentLoggedIn(Account s) { currentLoggedIn = s; }
    public static Account getCurrentLoggedIn() { return currentLoggedIn; }
    public static Services getServices() { return services; }
}
