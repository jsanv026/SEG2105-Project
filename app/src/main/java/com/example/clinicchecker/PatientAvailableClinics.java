package com.example.clinicchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

public class PatientAvailableClinics extends AppCompatActivity {

    private Singleton singleton = Singleton.getInstance();
    private Clinic[] clinics = singleton.getClinics();
    private LinearLayout llClinics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_available_clinics);

        llClinics = (LinearLayout) findViewById(R.id.layoutClinics);

        for (int i = 0; i < clinics.length; i++) {

            if (clinics[i] != null && clinics[i].getClinicName() != null) {
                final TextView tmpTxtView = new TextView(this);
                tmpTxtView.setClickable(true);
                tmpTxtView.append("o --- " + clinics[i].getClinicName());
                llClinics.addView(tmpTxtView);
                Space spc = new Space(this);
                spc.setMinimumHeight(30);
                llClinics.addView(spc);
                tmpTxtView.setTag(i);

                tmpTxtView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        singleton.setClinicIndex((int) tmpTxtView.getTag());
                        singleton.setNum(0);
                        startActivity(new Intent(PatientAvailableClinics.this, PatientSelectedClinic.class));
                    }
                });
            }
        }
    }

    public void accountInfo(View v) { startActivity(new Intent(PatientAvailableClinics.this, AccountInfo.class)); }
    public void search(View v) { startActivity(new Intent(PatientAvailableClinics.this, PatientSearch.class)); }
}
