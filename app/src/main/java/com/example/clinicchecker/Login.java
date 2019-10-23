package com.example.clinicchecker;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class Login extends AppCompatActivity {

    private Singleton singleton = Singleton.getInstance();
    private Account[] userAccounts = singleton.getAccounts();
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void getInput(View view) {

        String user, pass, role;
        boolean flag = false;
        boolean loginSuccess = false;
        boolean foundAccount = false;

        EditText editUser = (EditText) findViewById(R.id.username);
        EditText editPass = (EditText) findViewById(R.id.password);

        user = editUser.getText().toString();
        pass = editPass.getText().toString();

        if (user.equals("") || pass.equals("")) { message("Login Failed", "Invalid login fields", "OK"); return; } // Checking if all fields are filled

        // Checking if name input is valid email

        for (int i = 0; i < user.length(); i++) {

            String c = Character.toString(user.charAt(i));
            if (c.equals("@")) {
                flag = true;
                break;
            }
        }

        if (flag == false) {
            message("Login failed","Invalid email","OK");
            return;
        }

        for (int i = 0; i < userAccounts.length-1; i++) {

            if (user.equals(userAccounts[i].getName())) {
                foundAccount = true;
                index = i;
                break;
            }
        }

        if (foundAccount) {

            if (userAccounts[index].checkPassword(pass)) {

                message("Login Successful", "Welcome, " + user + " (" + userAccounts[index].getRole() + ")", "OK");

            } else { message("Login failed", "Wrong password given", "OK"); }

        } else { message("Login failed", "Unable to find this account. Please try again", "OK"); }
    }

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


}