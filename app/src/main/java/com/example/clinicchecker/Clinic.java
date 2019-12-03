package com.example.clinicchecker;

import java.util.Locale;

public class Clinic {

    private Services services;
    private String workingHours;
    private String clinicName;
    private String insuranceType;
    private String address;
    private String phoneNumber;
    private String[] paymentMethods;
    private String[][] ratings; //[name, rating, comment]
    private int bookings = 0;

    public Clinic() {
        services = new Services(10);
        ratings = new String[10][3];

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

    public void book() { bookings++; }
    public int getBookings() { return bookings; }

    public void addRating(String[] str) {

        for (int i = 0; i < ratings.length; i++) {

            if (ratings[i][0] != null) { if (ratings[i][0].equals(str[0])) { ratings[i] = str; return; } }

        }

        for (int i = 0; i < ratings.length; i++) {

            if (ratings[i] == null) { ratings[i] = str; return; }

        }

        String[][] tmpStr = new String[ratings.length+1][3];

        for (int i = 0; i < ratings.length; i++) {

            tmpStr[i] = ratings[i];

        }

        ratings = tmpStr;
        ratings[ratings.length-1] = str;

    }

    public String[][] getRatings() { return ratings; }

    public String toString() {

        String workingHours;

        if (this.workingHours == null) { workingHours = ""; }
        else { workingHours = this.workingHours; }

        StringBuilder strB = new StringBuilder();
        for (int i = 0; i < paymentMethods.length; i++) {

            if (paymentMethods[i] != null) {
                strB.append(paymentMethods[i]);

                if(i != paymentMethods.length - 1) { strB.append(", "); }
            }

        }

        StringBuilder strC = new StringBuilder();
        for (int i = 0; i < services.getServiceArr().length; i++) {

            if (services.getService(i) != null) {

                strC.append(services.getService(i).toString());
                if(i != services.getServiceArr().length - 1) { strC.append("\n"); }

            }

        }

        return "Clinic Name: " + clinicName +
                "\nAddress: " + address +
                "\nPhone Number: " + phoneNumber +
                "\nPayment Methods: " + strB.toString() +
                "\nWorking Hours: " + workingHours +
                "\nApproximate Waiting Time: " + bookings*15 + " minutes" +
                "\n\nServices provided:\n" + strC;

    }

    public String reviews() {

        StringBuilder strC = new StringBuilder();
        int totalRating = 0;
        String averageRating;
        int numReviews = 0;
        for (int i = 0; i < ratings.length; i++) {

            if (ratings[i][0] != null) {

                strC.append("\n\n" + ratings[i][0]);
                strC.append("\nRating: " + ratings[i][1]);
                strC.append("\nComment: " + ratings[i][2]);
                totalRating += Integer.parseInt(ratings[i][1]);
                numReviews++;

            }

        }

        if (numReviews == 0) { averageRating = "0"; }
        else { float tmp = totalRating/numReviews; averageRating = String.format("%.2f", tmp); }

        return "\nReviews" +
                "\nAverage rating: " + averageRating + strC;

    }
}
