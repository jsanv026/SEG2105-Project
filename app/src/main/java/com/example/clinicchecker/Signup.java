package com.example.clinicchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class Signup extends AppCompatActivity {

    private MyApplication app = (MyApplication) getApplicationContext();
    private Account[] userAccounts = app.getAccounts();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void getInput(View view) {

        String email, pass, role, name, passConfirm;
        boolean flag = false;
        boolean foundAccount = false;
        int index = 0;

        EditText editEmail = (EditText) findViewById(R.id.username);
        EditText editPass = (EditText) findViewById(R.id.password);
        EditText editPassConfirm = (EditText) findViewById(R.id.confirmPassword);
        EditText editName = (EditText) findViewById(R.id.firstName);
        Spinner roles = (Spinner) findViewById(R.id.roles);

        role = roles.getSelectedItem().toString();
        email = editEmail.getText().toString();
        pass = editPass.getText().toString();
        passConfirm = editPass.getText().toString();
        name = editName.getText().toString();

        if (email == null || role == null || pass == null || name == null || passConfirm == null) { return; } // Checking if all fields are filled

        // Checking if username already exists

        for (int i = 0; i < userAccounts.length; i++) {

            if (email.equals(userAccounts[i].getName())) {
                foundAccount = true;
                message("Error", "Entered username already exists", "OK");
                return;
            }
        }

        // Checking if name input is valid email

        for (int i = 0; i < email.length(); i++) {

            if (String.valueOf(email.charAt(i)).equals("@")) {
                flag = true;
                break;
            }
        }

        if (flag == false) {
            message("Login failed","Invalid email","OK");
            return;
        }

        if (!foundAccount) {

            if (pass.equals(passConfirm)) {

                Account acc;
                int s = app.getSize();

                if (role == "Employee") {
                    acc = new Employee(email, name, pass, s);
                    app.add(acc);
                    message("Success", "Welcome, " + name + " (Employee)", "OK");
                }
                else if (role == "Patient") {
                    acc = new Patient(email, name, pass, s);
                    app.add(acc);
                    message("Success", "Welcome, " + name + " (Patient)", "OK");
                }

            }

        }
    }
    // Private methods

    private void message(String title, String message, String button) {

        AlertDialog alertDialog = new AlertDialog.Builder(Signup.this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, button,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();

    }
}
