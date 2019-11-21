package com.example.clinicchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ClinicInfo extends AppCompatActivity {

    private Singleton singleton = Singleton.getInstance();
    private Employee employee = (Employee) singleton.getCurrentLoggedIn();
    private TextView txtClinicInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_info);

        txtClinicInfo = (TextView) findViewById(R.id.txtClinicInfo);
        txtClinicInfo.append(employee.getClinic().toString());

    }

    public void accoutnInfo(View v) { startActivity( new Intent(ClinicInfo.this, AccountInfo.class)); }
}
