package com.example.clinicchecker;

public class Clinics {

    private Clinic[] clinics;
    private Singleton singleton = Singleton.getInstance();
    private Account[] accounts = singleton.getAccounts().getAccounts();

    public Clinics() {

        clinics = new Clinic[10];

    }

    public Clinic[] getClinics() {

        int size = 0;

        for (int i = 0; i < accounts.length; i++) {


            try {

                if (accounts[i].getRole().equals("Employee")) {

                    Employee employee = (Employee) accounts[i];

                    if (employee.getClinic() != null) {
                        if (clinics.length == size) { increaseArraySize(); }
                        clinics[size++] = employee.getClinic();
                    }
                }


            } catch(Exception e) {

            }

        }

        return clinics;

    }

    private void increaseArraySize() {

        Clinic[] tmpArray = new Clinic[clinics.length + 1];

        for (int i = 0; i < clinics.length; i++) {

            tmpArray[i] = clinics[i];

        }

        clinics = tmpArray;

    }

}
