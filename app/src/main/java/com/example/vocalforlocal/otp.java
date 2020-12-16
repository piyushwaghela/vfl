package com.example.vocalforlocal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import java.util.Random;

public class otp extends AppCompatActivity {

    Button getotp;
    EditText getmobile;
    ImageView gotologin;
    MydbHelper db;
    String rmobile="";

    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);


        db = new MydbHelper(this);
        gotologin = findViewById(R.id.gotologin);
        getmobile = findViewById(R.id.getmobile);
        getotp = findViewById(R.id.getotp);




        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this, R.id.getmobile,
                "[5-9]{1}[0-9]{9}$", R.string.invalid_Mobilenumber);


        getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (awesomeValidation.validate()) {

                    String checkmobile = getmobile.getText().toString().trim();

                    Cursor res = db.viewdata(checkmobile);
                    while (res.moveToNext()){

                      rmobile = res.getString(3);

                    }

                    if(rmobile.equals(checkmobile)){

                        Random ran = new Random();
                        int n=ran.nextInt(1000000)+1;
                        String val=String.valueOf(n);

                        Intent i = new Intent(otp.this,geto.class);
                        i.putExtra("val",val);
                        i.putExtra("checkmobile",checkmobile);
                        startActivity(i);
                        finish();




                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Your Number Didn't Match with your profile",Toast.LENGTH_LONG).show();
                    }






                }




            }
        });



        gotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(otp.this,logsin.class);
                startActivity(intent);
                finish();

            }
        });

    }
}