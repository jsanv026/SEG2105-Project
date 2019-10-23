package com.example.clinicchecker;

public class Singleton {

    private static Singleton inst;
    private Account[] userAccounts = new Account[10];
    private int size = 0;

    private Singleton() {

        userAccounts[size] = new Admin(null, "admin", "5T5ptQ", 0);
        size++;
    }

    public Account[] getAccounts() { return userAccounts;}
    public void sizeInc() { size++; }
    public int getSize() { return size; }

    public static Singleton getInstance() {

        if (inst == null) { inst = new Singleton(); }

        return inst;

    }

    public void add(Account acc) {

        if (size == userAccounts.length - 1) { increaseArraySize(); }

        userAccounts[size++] = acc;

    }

    private void increaseArraySize() {

        Account[] tmpArray = new Account[userAccounts.length + 1];

        for (int i = 0; i < userAccounts.length; i++) {

            tmpArray[i] = userAccounts[i];

        }

        userAccounts = tmpArray;

    }

}
