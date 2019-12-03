package com.example.clinicchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class PatientRating extends AppCompatActivity {

    private Singleton singleton = Singleton.getInstance();
    private Clinic[] clinics = singleton.getClinics();
    private Clinic clinic = clinics[singleton.getClinicIndex()];
    private Patient patient = (Patient) singleton.getCurrentLoggedIn();
    private TextView txtInfo;
    private EditText edtComment;
    private RadioGroup rdGrp;
    private RadioButton rdBtn;
    private Button btnSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_rating);

        txtInfo = (TextView) findViewById(R.id.txtInfo);
        edtComment = (EditText) findViewById(R.id.edtComment);

        txtInfo.append("Give a rating for " + clinic.getClinicName());

        rdGrp = (RadioGroup) findViewById(R.id.rdGrp);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int id = rdGrp.getCheckedRadioButtonId();
                rdBtn = (RadioButton) findViewById(id);

                if (edtComment.getText().toString().equals("")) { toastMessage("Enter a comment"); return; }
                if (rdBtn == null) { toastMessage("Select a rating"); return; }

                String[] str = new String[3];
                str[0] = patient.getLastName() + ", " + patient.getFirstName();
                str[1] = rdBtn.getText().toString();
                str[2] = edtComment.getText().toString();

                clinic.addRating(str);
                toastMessage("Review submitted");

                startActivity(new Intent(PatientRating.this, PatientSelectedClinic.class));

            }
        });

    }

    public void back(View v) { startActivity(new Intent(PatientRating.this, PatientSelectedClinic.class)); }
    private void toastMessage(String message) { Toast.makeText(PatientRating.this, message, Toast.LENGTH_SHORT).show(); }

}
