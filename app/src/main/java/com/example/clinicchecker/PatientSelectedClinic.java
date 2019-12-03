package com.example.clinicchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class PatientSelectedClinic extends AppCompatActivity {

    private Singleton singleton = Singleton.getInstance();
    private Clinic[] clinics = singleton.getClinics();
    private int clinicIndex = singleton.getClinicIndex();
    private Clinic clinic = clinics[clinicIndex];
    private TextView txtClinicInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_selected_clinic);

        txtClinicInfo = (TextView) findViewById(R.id.txtClinicInfo);
        txtClinicInfo.append(clinic.toString());
    }

    public void back(View v) {

        int num = singleton.getNum();

        if (num == 0) { startActivity(new Intent(PatientSelectedClinic.this, PatientAvailableClinics.class)); }
        else if (num == 1) { startActivity(new Intent(PatientSelectedClinic.this, PatientSearch.class)); }
    }

    public void book(View v) {

        clinic.book();
        toastMessage("Successfully booked");
        startActivity(new Intent(PatientSelectedClinic.this, PatientAvailableClinics.class));

    }

    private void toastMessage(String message) { Toast.makeText(PatientSelectedClinic.this, message, Toast.LENGTH_SHORT).show(); }

}
