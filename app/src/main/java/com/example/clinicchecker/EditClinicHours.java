package com.example.clinicchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditClinicHours extends AppCompatActivity {

    private Singleton singleton = Singleton.getInstance();
    private Employee employee = (Employee) singleton.getCurrentLoggedIn();
    private Clinic clinic = employee.getClinic();

    private EditText edtHours;
    private Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_clinic_hours);

        edtHours = (EditText) findViewById(R.id.edtHours);
        btnConfirm = (Button) findViewById(R.id.btnConfirm);

        if (clinic.getWorkingHours() != null) { edtHours.setText(clinic.getWorkingHours()); }

    }

    public void confirm(View v) {

        clinic.setWorkingHours(edtHours.getText().toString());
        toastMessage("Changes saved");
        startActivity(new Intent(EditClinicHours.this, ClinicInfo.class));

    }

    public void back(View v) { startActivity(new Intent(EditClinicHours.this, ClinicInfo.class)); }
    private void toastMessage(String message) { Toast.makeText(EditClinicHours.this, message, Toast.LENGTH_SHORT).show(); }


}
