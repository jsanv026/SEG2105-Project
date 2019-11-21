package com.example.clinicchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

public class AdminServices extends AppCompatActivity {

    private Singleton singleton = Singleton.getInstance();
    private Services services = singleton.getServices();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_services);

        for (int i = 0; i < services.getServiceArr().length; i++) {

            final TextView myTxtView = new TextView(this);
            myTxtView.setTextSize(20);
            myTxtView.setTag(i);
            myTxtView.setClickable(true);

            if (services.getService(i) != null) {

                myTxtView.append("o --- Service Name: " + services.getService(i).getServiceName());
                LinearLayout ll = (LinearLayout) findViewById(R.id.layoutServices);
                ll.addView(myTxtView);
                Space spc = new Space(this);
                spc.setMinimumHeight(30);
                ll.addView(spc);

                myTxtView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        singleton.setServicesIndex((int) myTxtView.getTag());
                        startActivity(new Intent(AdminServices.this, ServiceInfo.class));
                    }
                });

            }

        }

    }

    public void accountInfo(View v) { startActivity(new Intent(AdminServices.this, AccountInfo.class)); }
    public void accountsList(View v) { startActivity(new Intent(AdminServices.this, AdminDeleteAccounts.class)); }
    public void add(View v) { startActivity(new Intent(AdminServices.this, AddService.class)); }

}
