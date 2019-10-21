package com.example.johns.seg2105project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Spinner;
import android.app.AlertDialog;
import android.content.DialogInterface;


public class LoginActivity extends AppCompatActivity {

    private Spinner roles;
    private String[][] userAccounts = new String[1][3]; // 2D array [[USER, PASSWORD, ROLE],...]
    private final String adminPass = "5T5ptQ";
    private final String adminUser = "admin";
    private Button login;
    private int size = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void getInput(View view) {

        String user, pass, role;
        EditText editUser = (EditText)findViewById(R.id.username);
        EditText editPass = (EditText)findViewById(R.id.password);
        Spinner roles = (Spinner) findViewById(R.id.roles);

        role = roles.getSelectedItem().toString();
        user = editUser.getText().toString();
        pass = editPass.getText().toString();

        if (role == "Admin") {
            if (user == adminUser && pass == adminPass) {

                message("Login Successful", "Welcome, " + user + "! You are logged in as a: " + role, "OK");
                return;
            } else {
                message("Login Failed", "Invalid Admin Credentials", "OK");
            }
        }

        for (int i = 0; i < userAccounts.length; i++) {

            if (user == userAccounts[i][0] && pass == userAccounts[i][1] && role == userAccounts[i][2]) {

                message("Login Successful", "Welcome, " + user + "! You are logged in as a: " + role, "OK");
                return;
            }

        }
        userAccounts[size][0] = user;
        userAccounts[size][1] = pass;
        userAccounts[size][2] = role;
        size++;

        if (size == userAccounts.length - 1) { increaseArraySize(); }
    }

    // Private methods

    private void message(String title, String message, String button) {

        AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
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

    private void increaseArraySize() {

        String[][] tmpArray = new String[userAccounts.length + 1][3];

        for (int i = 0; i < userAccounts.length; i++) {

            tmpArray[i] = userAccounts[i];

        }

        userAccounts = tmpArray;

    }
}
