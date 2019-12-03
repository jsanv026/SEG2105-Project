package com.example.clinicchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;


public class PatientServicesSearch extends AppCompatActivity {

    private Singleton singleton = Singleton.getInstance();
    private Services services = singleton.getServices();
    private Service service = singleton.getServiceToSearch();
    private Clinic[] clinics = singleton.getClinics();
    private LinearLayout llClinics;
    private TextView txtServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_services_search);

        llClinics = (LinearLayout) findViewById(R.id.layoutClinics);
        txtServices = (TextView) findViewById(R.id.txtServices);

        txtServices.append(service.getServiceName());

        for (int i = 0; i < services.getServiceArr().length; i++) {

            if (services.getServiceArr()[i] != null && services.getServiceArr()[i].equals(service)) {

                for (int j = 0; j < clinics.length; j++) {

                    if (clinics[j] != null) {

                        for (int k = 0; k < clinics[j].getServices().getServiceArr().length; k++) {

                            if (clinics[j].getServices().getServiceArr()[k] != null && service.getServiceName().equals(clinics[j].getServices().getServiceArr()[k])) {

                                final TextView tmpTxtView = new TextView(this);
                                tmpTxtView.setClickable(true);
                                tmpTxtView.append("o --- " + clinics[j].getClinicName());
                                llClinics.addView(tmpTxtView);
                                Space spc = new Space(this);
                                spc.setMinimumHeight(30);
                                llClinics.addView(spc);
                                tmpTxtView.setTag(j);

                                tmpTxtView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        singleton.setClinicIndex((int) tmpTxtView.getTag());
                                        singleton.setNum(1);
                                        startActivity(new Intent(PatientServicesSearch.this, PatientSelectedClinic.class));
                                    }
                                });
                            }
                        }
                    }
                }
            }
        }
    }

    public void back(View v) { startActivity(new Intent(PatientServicesSearch.this, PatientSearch.class)); }
}
