package com.example.clinicchecker;

public class Singleton {

    private static Singleton inst;
    private static Accounts accounts = new Accounts(10);

    private Singleton() { }

    public static Singleton getInstance() {

        if (inst == null) { inst = new Singleton(); }

        return inst;

    }

    public static Accounts getAccounts() { return accounts; }

}
