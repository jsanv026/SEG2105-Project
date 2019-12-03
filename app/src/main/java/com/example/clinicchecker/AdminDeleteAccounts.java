package com.example.clinicchecker;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.widget.Space;


public class AdminDeleteAccounts extends AppCompatActivity {

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
        setContentView(R.layout.activity_admin_delete_accounts);

        txtAccounts = (TextView) findViewById(R.id.txtAccounts);

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
                myButton.setForegroundGravity(Gravity.RIGHT);

                LinearLayout llBtn = (LinearLayout) findViewById(R.id.layoutButtons);
                llBtn.addView(myButton);
                Space spc = new Space(this);
                spc.setMinimumHeight(210);
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



    }

    public void openAccountInfo(View view) { startActivity(new Intent(AdminDeleteAccounts.this, AccountInfo.class));}
    public void openServices(View view) { startActivity(new Intent(AdminDeleteAccounts.this, AdminServices.class));}
    private void toastMessage(String message) { Toast.makeText(AdminDeleteAccounts.this, message, Toast.LENGTH_SHORT).show(); }

}


