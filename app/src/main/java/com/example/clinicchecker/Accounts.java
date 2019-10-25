package com.example.clinicchecker;

public class Accounts {

    private Account[] userAccounts;
    private int size = 0;
    private int capacity;

    public Accounts(int capacity) {

        this.capacity = capacity;
        userAccounts = new Account[capacity];
        userAccounts[size] = new Admin("admin",null, "admin", null,"5T5ptQ");
        size++;

    }

    public void add(Account acc) {

        if (size == capacity) { increaseArraySize(); }

        userAccounts[size] = acc;
        size++;

    }

    public Account[] getAccounts() { return userAccounts;}
    public int getSize() { return size; }

    private void increaseArraySize() {

        Account[] tmpArray = new Account[userAccounts.length + 1];

        for (int i = 0; i < userAccounts.length; i++) {

            tmpArray[i] = userAccounts[i];

        }

        userAccounts = tmpArray;

    }

}
