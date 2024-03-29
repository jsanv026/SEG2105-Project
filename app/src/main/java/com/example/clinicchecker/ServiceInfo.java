package com.example.clinicchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ServiceInfo extends AppCompatActivity {

    EditText editText, edtOperator;
    private Singleton singleton = Singleton.getInstance();
    private Services services = singleton.getServices();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_info);

        editText = (EditText) findViewById(R.id.edtServiceName);
        edtOperator = (EditText) findViewById(R.id.edtOperator);

        editText.setText(services.getService(singleton.getServiceIndex()).getServiceName());
        edtOperator.setText(services.getService(singleton.getServiceIndex()).getRole());
    }

    public void confirm(View v) {

        editText = (EditText) findViewById(R.id.edtServiceName);


        if (editText.length() == 0) {
            toastMessage("Cannot change service to empty name");
            return;
        }

        if (services.findService(new Service(editText.getText().toString(), "")) != -1) {
            toastMessage("Cannot change service to an existing service");
            return;
        }

        if (!services.editService(services.getService(singleton.getServiceIndex()), editText.getText().toString(), edtOperator.getText().toString())) {

            throw new NullPointerException("fuck it didnt work");

        }
        toastMessage("Successfully changed service name");
        startActivity(new Intent(ServiceInfo.this, AdminServices.class));

    }

    public void delete (View v) {

        toastMessage("Successfully deleted " + services.getService(singleton.getServiceIndex()).getServiceName() + " service");
        services.deleteService(singleton.getServiceIndex());
        startActivity(new Intent(ServiceInfo.this, AdminServices.class));

    }

    public void back(View v) { startActivity(new Intent(ServiceInfo.this, AdminServices.class)); }

    private void toastMessage(String message) { Toast.makeText(ServiceInfo.this, message, Toast.LENGTH_SHORT).show(); }

}
