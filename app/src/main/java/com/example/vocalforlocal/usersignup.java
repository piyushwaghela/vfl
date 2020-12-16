package com.example.vocalforlocal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

public class usersignup extends AppCompatActivity {


    Button signup;
    EditText username, mobilenumber, uemail, password, conpass;

    LogsinDatabase db;




    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usersignup);



        db = new LogsinDatabase(this);




        signup = findViewById(R.id.signup);
        username = findViewById(R.id.name);
        mobilenumber = findViewById(R.id.mobilenumber);
        uemail = findViewById(R.id.uemail);
        password = findViewById(R.id.pass);
        conpass = findViewById(R.id.conpass);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        // validation
        awesomeValidation.addValidation(this, R.id.name,
                RegexTemplate.NOT_EMPTY, R.string.invalid_username);

        awesomeValidation.addValidation(this, R.id.mobilenumber,
                "[5-9]{1}[0-9]{9}$", R.string.invalid_mobilenumber);

        awesomeValidation.addValidation(this, R.id.uemail,
                Patterns.EMAIL_ADDRESS, R.string.invalid_Email);

        awesomeValidation.addValidation(this, R.id.pass,
                ".{6,}", R.string.invalid_Password);

        awesomeValidation.addValidation(this, R.id.conpass,
                R.id.pass, R.string.invalid_ConfirmPassword);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (awesomeValidation.validate()) {

                    String name = username.getText().toString().trim();
                    String email = uemail.getText().toString().trim();
                    String mobile = mobilenumber.getText().toString().trim();
                    String pass = password.getText().toString().trim();

                    Boolean cku = db.checkusername(email);
                    if (cku == true){
                        Boolean insert  = db.insert(name,email,mobile,pass);
                        if(insert == true){
                            Toast.makeText(getApplicationContext(),"Registered",Toast.LENGTH_SHORT).show();
                            username.setText("");
                            uemail.setText("");
                            mobilenumber.setText("");
                            password.setText("");
                            conpass.setText("");
                            Intent i = new Intent(getApplicationContext(),logsin.class);
                            startActivity(i);
                            finish();



                        }
                    }else {
                        Toast.makeText(getApplicationContext(),"Username exists...",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}

