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
    public void addService(Service service) { services.addService(service); }

    public String toString() {

        return "Clinic Name: " + clinicName +
                "\nAddress: " + address +
                "\nPhone Number: " + phoneNumber +
                "\n Payment Methods: " + paymentMethods.toString();

    }
}
