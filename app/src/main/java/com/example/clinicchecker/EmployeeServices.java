package com.example.clinicchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EmployeeServices extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_services);
    }

    public void clinicInfo(View v) { startActivity(new Intent(EmployeeServices.this, ClinicInfo.class)); }
    public void accountInfo(View v) { startActivity(new Intent(EmployeeServices.this, AccountInfo.class)); }
    public void addService(View v) { startActivity(new Intent(EmployeeServices.this, EmployeeAddService.class)); }
    public void openService(View v) { startActivity(new Intent(EmployeeServices.this, ServicesViewer.class)); }

}
