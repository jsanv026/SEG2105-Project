package com.example.clinicchecker;

public class Clinic {

    private Services services;
    private String workingHours;
    private String clinicName;
    private String insuranceType;
    private String address;
    private String phoneNumber;
    private String[] paymentMethods;

    public Clinic() {
        services = new Services(10);
    }

    public void setWorkingHours(String str) { workingHours = str; }
    public void setClinicName(String str) { clinicName = str; }
    public void setInsuranceType(String str) { insuranceType = str; }
    public void setAddress(String str) {address = str; }
    public void setPhoneNumber(String str) { phoneNumber = str; }
    public void setPaymentMethods(String[] strArr) { paymentMethods = strArr; }

    // Getters

    public String getWorkingHours() { return workingHours; }
    public String getClinicName() { return clinicName; }
    public String getInsuranceType() { return insuranceType; }
    public String getAddress() { return address; }
    public String getPhoneNumber() { return phoneNumber; }
    public String[] getPaymentMethods() { return paymentMethods; }
    public Services getServices() { return services; }

    public String toString() {

        StringBuilder strB = new StringBuilder();
        for (int i = 0; i < paymentMethods.length; i++) {

            if (paymentMethods[i] != null) {
                strB.append(paymentMethods[i]);
                strB.append(", ");
            }

        }

        return "Clinic Name: " + clinicName +
                "\nAddress: " + address +
                "\nPhone Number: " + phoneNumber +
                "\nPayment Methods: " + strB.toString() +
                "\nWorking Hours: " + workingHours;

    }
}
