package com.example.clinicchecker;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;

import android.os.Bundle;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void openLogin(View view) { startActivity(new Intent(WelcomeScreen.this, Login.class)); }

    public void openSignup(View view) { startActivity(new Intent(WelcomeScreen.this, Signup.class)); }

}
