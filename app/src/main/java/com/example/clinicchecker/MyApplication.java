package com.example.clinicchecker;

import android.app.Application;

public class MyApplication extends Application {

    private static final MyApplication inst = new MyApplication();
    private Account[] userAccounts = new Account[9];
    private int size = 0;

    private MyApplication() { userAccounts[size++] = new Admin(); }
    public Account[] getAccounts() { return userAccounts;}
    public void sizeInc() { size++; }
    public int getSize() { return size; }

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
