package com.example.clinicchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

public class PatientSearch extends AppCompatActivity {

    private Singleton singleton = Singleton.getInstance();
    private Services services = singleton.getServices();
    private Clinic[] clinics = singleton.getClinics();
    private LinearLayout llClinics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_search);

        llClinics = (LinearLayout) findViewById(R.id.layoutClinics);
    }

    public void address(View v) {

        llClinics.removeAllViews();

        for (int i = 0; i < clinics.length; i++) {

            if (clinics[i] != null) {

                final TextView tmpTxtView = new TextView(this);
                tmpTxtView.setClickable(true);
                tmpTxtView.setTextSize(20);
                tmpTxtView.append("o --- " + clinics[i].getAddress());
                llClinics.addView(tmpTxtView);
                Space spc = new Space(this);
                spc.setMinimumHeight(30);
                llClinics.addView(spc);
                tmpTxtView.setTag(i);

                tmpTxtView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        singleton.setClinicIndex((int) tmpTxtView.getTag());
                        singleton.setNum(1);
                        startActivity(new Intent(PatientSearch.this, PatientSelectedClinic.class));
                    }
                });

            }

        }

    }

    public void workingHours(View v) {

        llClinics.removeAllViews();

        for (int i = 0; i < clinics.length; i++) {

            if (clinics[i] != null) {

                final TextView tmpTxtView = new TextView(this);
                tmpTxtView.setClickable(true);
                tmpTxtView.setTextSize(20);
                tmpTxtView.append("o --- " + clinics[i].getWorkingHours());
                llClinics.addView(tmpTxtView);
                Space spc = new Space(this);
                spc.setMinimumHeight(30);
                llClinics.addView(spc);
                tmpTxtView.setTag(i);

                tmpTxtView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        singleton.setClinicIndex((int) tmpTxtView.getTag());
                        singleton.setNum(1);
                        startActivity(new Intent(PatientSearch.this, PatientSelectedClinic.class));
                    }
                });

            }

        }

    }

    public void services(View v) {

        llClinics.removeAllViews();

        for (int i = 0; i < services.getServiceArr().length; i++) {
            if (services.getServiceArr()[i] != null) {

                final TextView tmpTxtView = new TextView(this);
                tmpTxtView.setClickable(true);
                tmpTxtView.setTextSize(20);
                tmpTxtView.append("o --- " + services.getService(i).getServiceName());
                llClinics.addView(tmpTxtView);
                Space spc = new Space(this);
                spc.setMinimumHeight(30);
                llClinics.addView(spc);
                tmpTxtView.setTag(i);

                tmpTxtView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int i = (int) tmpTxtView.getTag();
                        singleton.setServiceToSearch(services.getService(i));
                        startActivity(new Intent(PatientSearch.this, PatientServicesSearch.class));
                    }
                });

            }
        }

    }

    public void accountInfo(View v) { startActivity(new Intent(PatientSearch.this, AccountInfo.class)); }
    public void availableClinics(View v) { startActivity(new Intent(PatientSearch.this, PatientAvailableClinics.class)); }

}
