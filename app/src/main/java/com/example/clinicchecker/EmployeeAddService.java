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

public class EmployeeAddService extends AppCompatActivity {

    private Singleton singleton = Singleton.getInstance();
    private Services services = singleton.getServices();
    private Employee employee = (Employee) singleton.getCurrentLoggedIn();

    private TextView tmpTxtView;
    private boolean confirm = false;
    private Service confirmName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_add_service);

        for (int i = 0; i < services.getServiceArr().length; i++) {

            final TextView myTxtView = new TextView(this);
            myTxtView.setTextSize(20);
            myTxtView.setTag(i);
            myTxtView.setClickable(true);

            if (services.getService(i) != null) {

                myTxtView.append("o " + services.getService(i).getServiceName() + " | " + services.getService(i).getRole());
                LinearLayout ll = (LinearLayout) findViewById(R.id.layoutAvailableServices);
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
                            if (employee.getClinic().getServices().addService(services.getService(i))) { toastMessage("Successfully added service"); }
                            else { toastMessage("Unable to add service (probably exists already)"); }
                            startActivity(new Intent(EmployeeAddService.this, EmployeeAddService.class));

                        } else {
                            toastMessage("Tap again to confirm");
                            confirmName = services.getService(i);
                            confirm = true;
                            v.setBackgroundColor(Color.GREEN);
                            tmpTxtView = (TextView) v;
                        }

                    }
                });

            }

        }
    }

    public void back(View v) { startActivity(new Intent(EmployeeAddService.this, EmployeeServices.class)); }
    private void toastMessage(String message) { Toast.makeText(EmployeeAddService.this, message, Toast.LENGTH_SHORT).show(); }

}
