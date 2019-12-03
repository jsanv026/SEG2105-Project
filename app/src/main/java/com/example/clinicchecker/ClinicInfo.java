package com.example.clinicchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
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
        txtClinicInfo.setTextSize(20);
        txtClinicInfo.setGravity(Gravity.CENTER);

    }

    public void accountInfo(View v) { startActivity( new Intent(ClinicInfo.this, AccountInfo.class)); }
    public void services(View v) { startActivity( new Intent(ClinicInfo.this, EmployeeServices.class)); }
    public void editClinicHours(View v) { startActivity( new Intent(ClinicInfo.this, EditClinicHours.class)); }

}
