package com.example.clinicchecker;

public class Accounts {

    private Account[] userAccounts;
    private int size = 0;
    private int capacity;
    private Singleton singleton = Singleton.getInstance();

    public Accounts(int capacity) {

        this.capacity = capacity;
        userAccounts = new Account[capacity];
        userAccounts[size] = new Admin("admin",null, "admin", null,"5T5ptQ");
        size++;

    }

    public void add(Account acc) {

        int index = 0;

        for (int i = 0; i < userAccounts.length - 1; i++) {

            if (userAccounts[i] == null) {
                userAccounts[size++] = acc;
                return;
            }

            index = i;

        }

        increaseArraySize();
        userAccounts[index + 1] = acc;
        size++;

    }

    public boolean delete(Account acc) {

        Singleton singleton = Singleton.getInstance();
        Account[] tmpUserAccounts = singleton.getAccounts().getAccounts();

        if (acc.getRole().equals("Admin")) { return false; }

        else {

            for (int i = 0; i < tmpUserAccounts.length - 1; i++) {

                if (tmpUserAccounts[i].equals(acc)) { tmpUserAccounts[i] = null; }

            }
        }

        return true;

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
