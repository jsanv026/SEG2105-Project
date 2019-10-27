package com.example.clinicchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.clinicchecker.ui.home.HomeFragment;

public class Login extends AppCompatActivity {

    private Singleton singleton = Singleton.getInstance();
    private Account[] userAccounts = singleton.getAccounts().getAccounts();
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void getInput(View view) {

        String user, pass;
        boolean flag = false;
        boolean foundAccount = false;

        EditText editUser = (EditText) findViewById(R.id.username);
        EditText editPass = (EditText) findViewById(R.id.password);

        user = editUser.getText().toString();
        pass = editPass.getText().toString();

        if (user.equals("") || pass.equals("")) { toastMessage("Invalid login fields"); return; } // Checking if all fields are filled

        for (int i = 0; i < singleton.getAccounts().getSize(); i++) {

            if (user.equals(userAccounts[i].getUser())) {
                foundAccount = true;
                index = i;
                break;
            }
        }

        if (foundAccount) {

            if (userAccounts[index].checkPassword(pass)) {

                if (userAccounts[index].getRole().equals("Employee")) {
                    toastMessage("Welcome, " + userAccounts[index].getFirstName() + ". You are logged in as an Employee");
                    startActivity(new Intent(Login.this, WelcomeScreen.class));
                } else if (userAccounts[index].getRole().equals("Patient")) {
                    toastMessage("Welcome, " + userAccounts[index].getFirstName() + ". You are logged in as an Patient");
                    startActivity(new Intent(Login.this, WelcomeScreen.class));
                } else if (userAccounts[index].getRole().equals("Admin")) {
                    toastMessage("Welcome, " + userAccounts[index].getFirstName() + ". You are logged in as an Admin");
                    startActivity(new Intent(Login.this, AdminMain.class));
                }

                singleton.setCurrentLoggedIn(userAccounts[index].toString());

            } else { toastMessage("Wrong password given"); }

        } else { toastMessage("Unable to find this account. Please try again"); toastMessage(userAccounts[index].toString()); }
    }

    public void openWelcomeScreen(View view) { startActivity(new Intent(Login.this, WelcomeScreen.class)); }

    private void toastMessage(String message) { Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show(); }

}
