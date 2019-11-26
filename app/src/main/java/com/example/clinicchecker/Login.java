package com.example.clinicchecker;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.text.InputType;
import androidx.fragment.app.*;

public class Login extends AppCompatActivity {

    private Singleton singleton = Singleton.getInstance();
    private Account[] userAccounts = singleton.getAccounts().getAccounts();
    private int index;
    private CheckBox revealPasswords;
    private EditText editPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        revealPasswords = (CheckBox) findViewById(R.id.chkRevealPass);
        editPass = (EditText) findViewById(R.id.password);

        editPass.setInputType(129);

        revealPasswords.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(!isChecked) {
                    editPass.setInputType(129);
                    editPass.setSelection(editPass.getText().length());
                } else {
                    editPass.setInputType(1);
                    editPass.setSelection(editPass.getText().length());
                }

            }
        }
        );
    }

    public void getInput(View view) {

        String user, pass;
        boolean flag = false;
        boolean foundAccount = false;

        EditText editUser = (EditText) findViewById(R.id.username);
        editPass = (EditText) findViewById(R.id.password);

        user = editUser.getText().toString();
        pass = editPass.getText().toString();

        if (user.equals("") || pass.equals("")) { toastMessage("Invalid login fields"); return; } // Checking if all fields are filled

        for (int i = 0; i < singleton.getAccounts().getSize(); i++) {

            if (userAccounts[i] == null ){

            } else if(user.equals(userAccounts[i].getUser())) {
                foundAccount = true;
                index = i;
                break;
            }
        }

        if (foundAccount) {

            if (userAccounts[index].checkPassword(pass)) {

                singleton.setCurrentLoggedIn(userAccounts[index]);

                if (userAccounts[index].getRole().equals("Employee")) {
                    toastMessage("Welcome, " + userAccounts[index].getFirstName() + ". You are logged in as an Employee");
                    singleton.setCurrentLoggedIn(userAccounts[index]);
                    Employee employee = (Employee) userAccounts[index];
                    if (employee.hasCreatedProfile() == false ) {
                        startActivity(new Intent(Login.this, EmployeeWelcomeScreen.class));
                    } else {
                        startActivity(new Intent(Login.this, AccountInfo.class));
                    }
                } else if (userAccounts[index].getRole().equals("Patient")) {
                    toastMessage("Welcome, " + userAccounts[index].getFirstName() + ". You are logged in as an Patient");
                    singleton.setCurrentLoggedIn(userAccounts[index]);
                    startActivity(new Intent(Login.this, WelcomeScreen.class));
                } else if (userAccounts[index].getRole().equals("Admin")) {
                    toastMessage("Welcome, " + userAccounts[index].getFirstName() + ". You are logged in as an Admin");
                    singleton.setCurrentLoggedIn(userAccounts[index]);
                    startActivity(new Intent(Login.this, AdminDeleteAccounts.class));
                }

            } else { toastMessage("Wrong password given"); }

        } else { toastMessage("Unable to find this account. Please try again"); }
    }

    public void openWelcomeScreen(View view) { startActivity(new Intent(Login.this, WelcomeScreen.class)); }

    private void toastMessage(String message) { Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show(); }

}
