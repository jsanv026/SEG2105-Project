package com.example.clinicchecker;

public class Singleton {

    private static Singleton inst;
    private static Accounts accounts = new Accounts(10);
    private static String currentLoggedIn;

    private Singleton() { }

    public static Singleton getInstance() {

        if (inst == null) { inst = new Singleton(); }

        return inst;

    }

    public static Accounts getAccounts() { return accounts; }
    public static void setCurrentLoggedIn(String s) { currentLoggedIn = s; }
    public static String getCurrentLoggedIn() { return currentLoggedIn; }

}
