package com.example.clinicchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ServiceInfo extends AppCompatActivity {

    EditText editText;
    private Singleton singleton = Singleton.getInstance();
    private Services services = singleton.getServices();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_info);

        editText = (EditText) findViewById(R.id.edtServiceName);

        editText.setText(services.getService(singleton.getServiceIndex()).getServiceName());
    }

    public void confirm(View v) {

        editText = (EditText) findViewById(R.id.edtServiceName);


        if (editText.length() == 0) {
            toastMessage("Cannot change service to empty name");
            return;
        }

        services.editService(services.getService(singleton.getServiceIndex()), editText.getText().toString());
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
