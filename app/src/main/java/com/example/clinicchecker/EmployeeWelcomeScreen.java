package com.example.clinicchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EmployeeWelcomeScreen extends AppCompatActivity {

    private Singleton singleton = Singleton.getInstance();
    private Employee employee = (Employee) singleton.getCurrentLoggedIn();
    private EditText edtClinicName, edtAddress, edtPhoneNumber, edtInsuranceType;
    private TextView txtTitle;
    private CheckBox chkCash, chkDebit, chkCredit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_welcome_screen);

        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtTitle.setText("Welcome, " + employee.getFirstName());
    }

    public void confirm(View v) {

        edtClinicName = (EditText) findViewById(R.id.edtClinicName);
        edtAddress = (EditText) findViewById(R.id.edtAddress);
        edtPhoneNumber = (EditText) findViewById(R.id.edtPhoneNumber);
        edtInsuranceType = (EditText) findViewById(R.id.edtInsuranceType);
        chkDebit = (CheckBox) findViewById(R.id.chkDebit);
        chkCash = (CheckBox) findViewById(R.id.chkCash);
        chkCredit = (CheckBox) findViewById(R.id.chkCredit);

        if (edtInsuranceType.getText().toString().equals("") ||
                edtPhoneNumber.getText().toString().equals("") ||
                edtAddress.getText().toString().equals("") ||
                edtClinicName.getText().toString().equals("") ||
                !chkDebit.isChecked() &&
                !chkCash.isChecked() &&
                !chkCredit.isChecked()) {

            toastMessage("Missing mandatory fields");
            return;

        }

        employee.getClinic().setClinicName(edtClinicName.getText().toString());
        employee.getClinic().setAddress(edtAddress.getText().toString());
        employee.getClinic().setPhoneNumber(edtPhoneNumber.getText().toString());
        employee.getClinic().setInsuranceType(edtInsuranceType.getText().toString());

        String[] paymentMethods = new String[3];

        if (chkDebit.isChecked()) { paymentMethods[0] = "Debit"; }
        if (chkCredit.isChecked()) { paymentMethods[1] = "Credit"; }
        if (chkCash.isChecked()) { paymentMethods[2] = "Cash"; }

        employee.getClinic().setPaymentMethods(paymentMethods   );

        toastMessage("Successfully created employee profile");
        startActivity(new Intent(EmployeeWelcomeScreen.this, AccountInfo.class));
        employee.toggleCreatedProfile();

    }

    public void back(View v) { startActivity(new Intent(EmployeeWelcomeScreen.this, WelcomeScreen.class)); }

    private void toastMessage(String message) { Toast.makeText(EmployeeWelcomeScreen.this, message, Toast.LENGTH_SHORT).show(); }

}
