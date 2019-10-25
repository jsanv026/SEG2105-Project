package com.example.clinicchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.text.method.ScrollingMovementMethod;
import android.os.Bundle;
import android.widget.Toast;

public class AccountList extends AppCompatActivity {

    private Singleton singleton = Singleton.getInstance();
    private Account[] userAccounts = singleton.getAccounts().getAccounts();
    public TextView txtAccounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_list);

        txtAccounts = (TextView) findViewById(R.id.txtAccounts);
        txtAccounts.setMovementMethod(new ScrollingMovementMethod());

        for(int i = 0; i < singleton.getAccounts().getSize(); i++) {

            txtAccounts.append(userAccounts[i].toString());
            txtAccounts.append("\n\n");

        }

    }

    public void openWelcomeScreen(View view) { startActivity(new Intent(AccountList.this, WelcomeScreen.class)); }

    private void toastMessage(String message) { Toast.makeText(AccountList.this, message, Toast.LENGTH_SHORT).show(); }

}