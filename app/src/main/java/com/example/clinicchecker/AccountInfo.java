package com.example.clinicchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.view.*;
import android.widget.*;

import android.os.Bundle;

public class AccountInfo extends AppCompatActivity {

    private Singleton singleton = Singleton.getInstance();
    private Account acc = singleton.getCurrentLoggedIn();
    private Accounts accClass = singleton.getAccounts();
    private Account[] userAccounts = accClass.getAccounts();
    private boolean confirmLogout = false;
    private boolean confirmDelete = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_info);

        TextView txtAccInfo = (TextView) findViewById(R.id.txtAccInfo);
        final Button btnLogout = (Button) findViewById(R.id.btnLogout);
        final Button btnDeleteAccount = (Button) findViewById(R.id.btnDeleteAccount);
        Button btnLeft = (Button) findViewById(R.id.btnLeft);
        Button btnRight = (Button) findViewById(R.id.btnRight);
        btnDeleteAccount.setBackgroundColor(Color.LTGRAY);
        btnLogout.setBackgroundColor(Color.LTGRAY);
        txtAccInfo.append(acc.toString());

        if (acc.getRole().equals("Employee")) {
            btnLeft.setText("Clinic Info");
            btnLeft.setOnClickListener((new View.OnClickListener() {
                public void onClick(View v) {
                    startActivity(new Intent(AccountInfo.this, ClinicInfo.class));
                }
            }));
            btnRight.setText("Services");
            btnRight.setOnClickListener((new View.OnClickListener() {
                public void onClick(View v) {
                    startActivity(new Intent(AccountInfo.this, EmployeeServices.class));
                }
            }));
        }

        btnLogout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (confirmLogout) {
                    confirmLogout = false;
                    singleton.setCurrentLoggedIn(null);
                    Toast.makeText(AccountInfo.this, "Successfully logged out", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AccountInfo.this, WelcomeScreen.class));
                } else {
                    toastMessage("Tap again to confirm");
                    confirmLogout = true;
                    confirmDelete = false;
                    btnDeleteAccount.setBackgroundColor(Color.LTGRAY);
                    v.setBackgroundColor(Color.RED);
                }
            }
        });

        btnDeleteAccount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (confirmDelete) {
                    confirmDelete = false;
                    accClass.forceDelete(acc);
                    singleton.setCurrentLoggedIn(null);
                    Toast.makeText(AccountInfo.this, "Successfully deleted account", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AccountInfo.this, WelcomeScreen.class));
                } else {
                    toastMessage("Are you sure? This action cannot be undone");
                    confirmDelete = true;
                    confirmLogout = false;
                    btnLogout.setBackgroundColor(Color.LTGRAY);
                    v.setBackgroundColor(Color.RED);
                }
            }
        });

    }

    public void openServices(View v) { startActivity(new Intent(AccountInfo.this, AdminServices.class)); }
    public void openAccountsList(View v) { startActivity(new Intent(AccountInfo.this, AdminDeleteAccounts.class)); }
    private void toastMessage(String message) { Toast.makeText(AccountInfo.this, message, Toast.LENGTH_SHORT).show(); }


}
