package com.example.clinicchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void getInput(View view) {

        String user, pass, role;
        boolean flag = false;
        boolean loginSuccess = false;
        boolean foundAccount = false;

        EditText editUser = (EditText) findViewById(R.id.username);
        EditText editPass = (EditText) findViewById(R.id.password);
        Spinner roles = (Spinner) findViewById(R.id.roles);

        role = roles.getSelectedItem().toString();
        user = editUser.getText().toString();
        pass = editPass.getText().toString();

        if (user == null || role == null || pass == null) { return; } // Checking if all fields are filled

        // Checking if name input is valid email

        for (int i = 0; i < user.length(); i++) {

            if (String.valueOf(user.charAt(i)).equals("@")) {
                flag = true;
                message("Nice","Invalid email","OK");
                break;
            }
        }

        if (flag == false) {
            message("Login failed","Invalid email","OK");
            return;
        }

        for (int i = 0; i < userAccounts.length; i++) {

            if (user.equals(userAccounts[i].getName())) {
                foundAccount = true;
                index = i;
                break;
            }
        }

        if (foundAccount) {

            if (userAccounts[index].checkPassword(pass)) {

                loginSuccess = true;
                message("Login Successful", "Welcome, " + user, "OK");

            }

        } else { message("Login failed", "Unable to find this account. Please try again", "OK"); }
    }
    // Private methods

    private void message(String title, String message, String button) {

        AlertDialog alertDialog = new AlertDialog.Builder(Login.this).create();
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

        Account[] tmpArray = new Account[userAccounts.length + 1];

        for (int i = 0; i < userAccounts.length; i++) {

            tmpArray[i] = userAccounts[i];

        }

        userAccounts = tmpArray;

    }
}
