package com.example.clinicchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

public class ServicesViewer extends AppCompatActivity {

    private Singleton singleton = Singleton.getInstance();
    private Employee employee = (Employee) singleton.getCurrentLoggedIn();
    private Clinic clinic = employee.getClinic();
    private Services services = clinic.getServices();

    private TextView tmpTxtView;
    private boolean confirm = false;
    private Service confirmName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_viewer);

        if (services.getServiceArr().length != 0) { System.out.println("pepegas " + services.getServiceArr().length + " pepegas"); }
        for (int i = 0; i < services.getServiceArr().length; i++) {

            final TextView myTxtView = new TextView(this);
            myTxtView.setTextSize(20);
            myTxtView.setTag(i);
            myTxtView.setClickable(true);

            if (services.getService(i) != null) {

                myTxtView.append("o --- Service Name: " + services.getService(i).getServiceName());
                LinearLayout ll = (LinearLayout) findViewById(R.id.layoutServicesView);
                ll.addView(myTxtView);
                Space spc = new Space(this);
                spc.setMinimumHeight(30);
                ll.addView(spc);

                myTxtView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int i = (int) myTxtView.getTag();
                        if (tmpTxtView != null) {
                            tmpTxtView.setBackgroundColor(Color.WHITE);
                        }

                        if (confirm && confirmName.equals(services.getService(i))) {
                            confirm = false;
                            if (services.getService(i) != null) {
                                toastMessage("Successfully deleted " + services.getService(i).getServiceName() + " service");
                                services.deleteService(i);
                                finish();
                                startActivity(getIntent());
                            }
                        } else {
                            toastMessage("Tap again to confirm");
                            confirmName = services.getService(i);
                            confirm = true;
                            v.setBackgroundColor(Color.RED);
                            tmpTxtView = (TextView) v;
                        }
                    }
                });

            }

        }

    }

    public void back(View v) { startActivity(new Intent(ServicesViewer.this, EmployeeServices.class)); }
    private void toastMessage(String message) { Toast.makeText(ServicesViewer.this, message, Toast.LENGTH_SHORT).show(); }


}
