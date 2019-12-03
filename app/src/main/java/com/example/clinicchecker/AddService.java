package com.example.clinicchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddService extends AppCompatActivity {

    private EditText edtServiceName, edtOperator;
    private Singleton singleton = Singleton.getInstance();
    private Services services = singleton.getServices();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);



    }

    public void add(View v) {

        edtServiceName = (EditText) findViewById(R.id.edtServiceName);
        edtOperator = (EditText) findViewById(R.id.edtOperator);

        if (!edtServiceName.getText().toString().equals("") || !edtOperator.getText().toString().equals("")) {
            services.addService(new Service(edtServiceName.getText().toString(), edtOperator.getText().toString()));
            toastMessage("Added service");
            startActivity(new Intent(AddService.this, AdminServices.class));
        } else { toastMessage("Can't add service without name or operator"); }
    }

    public void back(View v) { startActivity(new Intent(AddService.this, AdminServices.class)); }

    private void toastMessage(String message) { Toast.makeText(AddService.this, message, Toast.LENGTH_SHORT).show(); }
}
