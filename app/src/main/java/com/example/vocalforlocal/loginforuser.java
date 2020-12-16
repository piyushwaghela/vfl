package com.example.vocalforlocal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

public class loginforuser extends AppCompatActivity {
    AwesomeValidation awesomeValidation;
    Button next;
    EditText password, email;
    LogsinDatabase db;
    ProgressDialog pd;

    SharedPreferences sharedPreferences;
    private static final String SHP = "myprf";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASS = "password";


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginforuser);


        db = new LogsinDatabase(this);
        next = findViewById(R.id.next);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        sharedPreferences = getSharedPreferences(SHP, MODE_PRIVATE);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this, R.id.password,
                ".{6,}", R.string.invalid_Password);

        awesomeValidation.addValidation(this, R.id.email,
                Patterns.EMAIL_ADDRESS, R.string.invalid_Email);


        sharedPreferences = getSharedPreferences(SHP, MODE_PRIVATE);

        String el = sharedPreferences.getString(KEY_EMAIL, null);
        if (el != null) {
            Intent i = new Intent(loginforuser.this, selectworker.class);
            startActivity(i);
            finish();


            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (awesomeValidation.validate()) {

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(KEY_EMAIL, email.getText().toString());
                        editor.putString(KEY_PASS, password.getText().toString());
                        editor.apply();


                        pd = new ProgressDialog(loginforuser.this);
                        pd.show();
                        pd.setContentView(R.layout.showdia);
                        pd.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


                        String str = email.getText().toString();
                        String pass = password.getText().toString();


                        Boolean ckloging = db.Checklogin(str, pass);


                        if (ckloging == true) {

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    pd.dismiss();
                                    Intent intent = new Intent(loginforuser.this, selectworker.class);
                                    startActivity(intent);
                                    finish();

                                }
                            }, 3000);


                        } else {
                            Toast.makeText(getApplicationContext(), "Password or Username is Wrong!!!", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            });


        }

    }
}














