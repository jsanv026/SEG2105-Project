package com.example.clinicchecker;

public class Accounts {

    private Account[] userAccounts;
    private int size = 0;
    private int capacity;
    private Singleton singleton = Singleton.getInstance();

    public Accounts(int capacity) {

        this.capacity = capacity;
        userAccounts = new Account[capacity];

        Employee employee1 = new Employee("A","employee1@uottawa.ca", "John", "Doe","a");
        Employee employee2 = new Employee("B","employee2@uottawa.ca", "Jane", "Doe","b");
        this.add(new Admin("admin","admin@uottawa.ca", "admin", "admin","5T5ptQ"));
        this.add(employee1);
        this.add(employee2);
        this.add(new Patient("C","patient1@uottawa.ca", "Albert", "Speer","c"));

        employee1.getClinic().setWorkingHours("8:00-18:00");
        employee2.getClinic().setWorkingHours("10:00-20:00");

        employee1.getClinic().setClinicName("uOttawa Clinic");
        employee2.getClinic().setClinicName("Gatineau Clinic");

        employee1.getClinic().setAddress("99 Rideau St.");
        employee2.getClinic().setAddress("101 King Edward Blvd.");

        employee1.getClinic().setInsuranceType("OHIP");
        employee2.getClinic().setInsuranceType("OHIP");

        employee1.getClinic().setPhoneNumber("613-111-9123");
        employee2.getClinic().setPhoneNumber("613-111-2412");

        employee1.getClinic().getServices().addService(new Service("CPR Training"));
        employee1.getClinic().getServices().addService(new Service("Flu Shots"));

        employee2.getClinic().getServices().addService(new Service("Flu Shots"));
        employee2.getClinic().getServices().addService(new Service("Blood Donations"));

        String[] strPaymentMethods = {"Debit", "Credit"};
        employee1.getClinic().setPaymentMethods(strPaymentMethods);
        employee2.getClinic().setPaymentMethods(strPaymentMethods);

        employee1.toggleCreatedProfile();
        employee2.toggleCreatedProfile();

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

                if (tmpUserAccounts[i] != null) {
                    if (tmpUserAccounts[i].equals(acc)) { tmpUserAccounts[i] = null; }
                }

            }
        }

        return true;

    }

    public boolean forceDelete(Account acc) {

        Singleton singleton = Singleton.getInstance();
        Account[] tmpUserAccounts = singleton.getAccounts().getAccounts();
        for (int i = 0; i < tmpUserAccounts.length - 1; i++) {

            if (tmpUserAccounts[i] != null) {
                if (tmpUserAccounts[i].equals(acc)) { tmpUserAccounts[i] = null; }
            }

        }

        return true;

    }

    public boolean exists(Account acc) {

        Singleton singleton = Singleton.getInstance();
        Account[] tmpUserAccounts = singleton.getAccounts().getAccounts();
        for (int i = 0; i < tmpUserAccounts.length - 1; i++) {

            if (acc.equals(tmpUserAccounts[i])) { return true; }

        }

        return false;
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
