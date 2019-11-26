package com.example.clinicchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

                myTxtView.append("o --- Service Name: " + services.getService(i).getServiceName());
                LinearLayout ll = (LinearLayout) findViewById(R.id.layoutAvailableServices);
                ll.addView(myTxtView);
                Space spc = new Space(this);
                spc.setMinimumHeight(30);
                ll.addView(spc);

                myTxtView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int i = (int) myTxtView.getTag();
                        if (employee.getClinic().getServices().addService(services.getService(i))) { toastMessage("Successfully added service"); }
                        else { toastMessage("Successfully added service"); }
                    }
                });

            }

        }
    }

    public void back(View v) { startActivity(new Intent(EmployeeAddService.this, ServicesViewer.class)); }
    private void toastMessage(String message) { Toast.makeText(EmployeeAddService.this, message, Toast.LENGTH_SHORT).show(); }

}
