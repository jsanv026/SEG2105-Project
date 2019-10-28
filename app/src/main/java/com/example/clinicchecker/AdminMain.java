package com.example.clinicchecker;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.*;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.graphics.Color;
import android.widget.Space;
import java.*;


public class AdminMain extends AppCompatActivity {

    private Singleton singleton = Singleton.getInstance();
    private Accounts accClass = singleton.getAccounts();
    private Account[] userAccounts = singleton.getAccounts().getAccounts();
    private TextView txtAccounts;
    private boolean confirm = false;
    private String confirmName;
    private Button tmpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.home, R.id.services, R.id.accInfo).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.accInfo:
                        Toast.makeText(AdminMain.this, "Recents", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.services:
                        Toast.makeText(AdminMain.this, "Favorites", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.accManagement:
                        Toast.makeText(AdminMain.this, "Nearby", Toast.LENGTH_SHORT).show();
                        break;                }
                return true;
            }
        });

        txtAccounts = (TextView) findViewById(R.id.txtAccounts);



        txtAccounts.setMovementMethod(new ScrollingMovementMethod());

        int rank = 1;

        for(int i = 0; i < singleton.getAccounts().getSize(); i++) {

            Button myButton = new Button(this);
            myButton.setTag(i);

            if (userAccounts[i] != null) {
                txtAccounts.append("[" + rank + "]\n");
                rank++;
                txtAccounts.append(userAccounts[i].toString());

                myButton.setText("Delete " + userAccounts[i].getFirstName() + "'s account");
                myButton.setBackgroundColor(Color.LTGRAY);

                LinearLayout llBtn = (LinearLayout)findViewById(R.id.layout);
                llBtn.addView(myButton);
                Space spc = new Space(this);
                spc.setMinimumHeight(15);
                llBtn.addView(spc);

                myButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        int i = (int) v.getTag();

                        if (tmpBtn != null) {
                            tmpBtn.setBackgroundColor(Color.LTGRAY);
                        }

                        if (confirm && confirmName.equals(userAccounts[i].toString())) {
                            confirm = false;
                            if (userAccounts[i] != null) {
                                if(userAccounts[i].isDeletable()) {
                                    toastMessage("Successfully deleted " + userAccounts[i].getFirstName() + "'s account");
                                    accClass.delete(userAccounts[i]);
                                    finish();
                                    startActivity(getIntent());
                                } else { toastMessage("Admin accounts cannot be deleted");}
                            }
                        } else {
                            toastMessage("Tap again to confirm");
                            confirmName = userAccounts[i].toString();
                            confirm = true;
                            v.setBackgroundColor(Color.RED);
                            tmpBtn = (Button) v;
                        }
                    }
                });

                txtAccounts.append("\n\n");
            }


        }

        Button myButton = new Button(this);
        myButton.setText("Logout");
        myButton.setBackgroundColor(Color.LTGRAY);
        LinearLayout ll = (LinearLayout)findViewById(R.id.layout);
        ll.addView(myButton);
        myButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(AdminMain.this, WelcomeScreen.class));
            }
        });

    }

    public void openWelcomeScreen(View view) { startActivity(new Intent(AdminMain.this, WelcomeScreen.class)); toastMessage("Successfully logged out"); }

    private void toastMessage(String message) { Toast.makeText(AdminMain.this, message, Toast.LENGTH_SHORT).show(); }
}


