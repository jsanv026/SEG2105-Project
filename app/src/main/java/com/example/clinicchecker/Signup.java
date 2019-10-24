package com.example.clinicchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class Signup extends AppCompatActivity {

    private Singleton singleton = Singleton.getInstance();
    private Account[] userAccounts = singleton.getAccounts();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void createAccount(View view) {

        String email, user, pass, role, firstName, lastName, passConfirm;
        boolean flag = false;

        EditText editEmail = (EditText) findViewById(R.id.email);
        EditText editUser = (EditText) findViewById(R.id.username);
        EditText editPass = (EditText) findViewById(R.id.password);
        EditText editPassConfirm = (EditText) findViewById(R.id.confirmPassword);
        EditText editFirstName = (EditText) findViewById(R.id.firstName);
        EditText editLastName = (EditText) findViewById(R.id.lastName);
        Spinner roles = (Spinner) findViewById(R.id.roles);

        role = roles.getSelectedItem().toString();
        email = editEmail.getText().toString();
        user = editUser.getText().toString();
        pass = editPass.getText().toString();
        passConfirm = editPassConfirm.getText().toString();
        firstName = editFirstName.getText().toString();
        lastName = editLastName.getText().toString();

        if (firstName.equals("") || pass.equals("") || lastName.equals("") || email.equals("") || passConfirm.equals("") || user.equals("")) { message("Login Failed", "Invalid login fields", "OK"); return; } // Checking if all fields are filled

        // Checking if username already exists

        for (int i = 0; i < singleton.getSize() - 1; i++) {

            if (email.equals(userAccounts[i].getEmail())) {
                message("Error", "Entered email already exists", "OK");
                return;
            } else if (user.equals(userAccounts[i].getUser())) {
                message("Error", "Entered username already exists", "OK");
                return;
            }
        }

        // Checking if name input is valid email

        for (int i = 0; i < email.length(); i++) {

            String c = Character.toString(email.charAt(i));
            if (c.equals("@")) {
                flag = true;
                break;
            }
        }

        if (flag == false) {
            message("Login failed","Invalid email","OK");
            return;
        }


        if (pass.equals(passConfirm)) {

            Account acc;
            int s = singleton.getSize();

            if (role == "Employee") {
                acc = new Employee(user, email, firstName, lastName, pass, s);
                singleton.add(acc);
                message("Success", "Welcome, " + firstName + " (Employee)", "OK");
            }
            else if (role == "Patient") {
                acc = new Patient(user, email, firstName, lastName, pass, s);
                singleton.add(acc);
                message("Success", "Welcome, " + firstName + " (Patient)", "OK");
            } else if (role == "Admin") {
                acc = new Admin(user, email, firstName, lastName, pass, s);
                singleton.add(acc);
                message("Success", "Welcome, " + firstName + " (Admin)", "OK");
            }

        } else {
            message("Error", "Passwords do not match", "OK");
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
