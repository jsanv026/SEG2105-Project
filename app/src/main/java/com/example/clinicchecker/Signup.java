package com.example.clinicchecker;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    private Singleton singleton = Singleton.getInstance();
    private Account[] userAccounts = singleton.getAccounts().getAccounts();

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

        if (firstName.equals("") || pass.equals("") || lastName.equals("") || email.equals("") || passConfirm.equals("") || user.equals("")) { toastMessage("Invalid login fields"); return; } // Checking if all fields are filled

        // Checking if username already exists

        for (int i = 0; i < singleton.getAccounts().getSize(); i++) {

            try {
                if (email.equals(userAccounts[i].getEmail())) {
                    toastMessage("Entered email already exists");
                    return;
                } else if (user.equals(userAccounts[i].getUser())) {
                    toastMessage("Entered username already exists");
                    return;
                }

            } catch (NullPointerException e) {



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
            toastMessage("Invalid email");
            return;
        }


        if (pass.equals(passConfirm)) {

            Account acc = null;

            if (role.equals("Employee")) {
                acc = new Employee(user, email, firstName, lastName, pass);
                singleton.getAccounts().add(acc);
                toastMessage("Welcome, " + firstName + ". You are logged in as an employee");
                startActivity(new Intent(Signup.this, WelcomeScreen.class));
            }
            else if (role.equals("Patient")) {
                acc = new Patient(user, email, firstName, lastName, pass);
                singleton.getAccounts().add(acc);
                toastMessage("Welcome, " + firstName + ". You are logged in as a patient");
                startActivity(new Intent(Signup.this, WelcomeScreen.class));
            } else if (role.equals("Admin")) {
                acc = new Admin(user, email, firstName, lastName, pass);
                singleton.getAccounts().add(acc);
                toastMessage("Welcome, " + firstName + ". You are logged in as an admin");
                startActivity(new Intent(Signup.this, AdminMain.class));
            }

            singleton.setCurrentLoggedIn(acc.toString());
        } else {
            toastMessage("Passwords do not match");
        }

    }

    public void openWelcomeScreen(View view) { startActivity(new Intent(Signup.this, WelcomeScreen.class)); }

    private void toastMessage(String message) { Toast.makeText(Signup.this, message, Toast.LENGTH_SHORT).show(); }

}
