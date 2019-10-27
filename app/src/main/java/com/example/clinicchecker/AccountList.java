package com.example.clinicchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.text.method.ScrollingMovementMethod;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.Button;
import android.widget.LinearLayout;

public class AccountList extends AppCompatActivity {

    private Singleton singleton = Singleton.getInstance();
    private Accounts accClass = singleton.getAccounts();
    private Account[] userAccounts = singleton.getAccounts().getAccounts();
    private int index;
    public TextView txtAccounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_list);

        txtAccounts = (TextView) findViewById(R.id.txtAccounts);



        txtAccounts.setMovementMethod(new ScrollingMovementMethod());

        for(int i = 0; i < singleton.getAccounts().getSize(); i++) {

            index = i;
            txtAccounts.append(userAccounts[i].toString());
            Button myButton = new Button(this);
            myButton.setText("Delete " + userAccounts[i].getFirstName() + "'s account");
            LinearLayout ll = (LinearLayout)findViewById(R.id.layout);
            ll.addView(myButton);
            myButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (userAccounts[index].isDeletable()) {

                        accClass.delete(userAccounts[index]);
                        finish();
                        startActivity(getIntent());

                    } else { toastMessage("Admin accounts cannot be deleted"); }
                }
            });
            txtAccounts.append("\n\n");

        }

        Button myButton = new Button(this);
        myButton.setText("Logout");
        LinearLayout ll = (LinearLayout)findViewById(R.id.layout);
        ll.addView(myButton);
        myButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(AccountList.this, WelcomeScreen.class));
            }
        });
        txtAccounts.append("\n\n");

    }

    public void openWelcomeScreen(View view) { startActivity(new Intent(AccountList.this, WelcomeScreen.class)); }

    private void toastMessage(String message) { Toast.makeText(AccountList.this, message, Toast.LENGTH_SHORT).show(); }

}
