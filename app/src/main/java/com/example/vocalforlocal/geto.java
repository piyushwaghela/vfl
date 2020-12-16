package com.example.vocalforlocal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

public class geto extends AppCompatActivity {

    EditText geto;
    ImageButton getdata;
    MydbHelper db;
    ProgressDialog pd;
    AwesomeValidation awesomeValidation;
    String name,mobile1,acard,profe,loc,sd;
    String image1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geto);

        geto = findViewById(R.id.geto);
        db = new MydbHelper(this);

        getdata = findViewById(R.id.getdata);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);


        awesomeValidation.addValidation(this, R.id.getotp,
                ".{6,}", R.string.invalid_Otp);

        getdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mobile = getIntent();

                if (awesomeValidation.validate()) {


                    String mc = mobile.getStringExtra("checkmobile");

                    Cursor result = db.getinfo(mc);

                    if (result.moveToNext()) {
                        do {
                            name = result.getString(1);
                            image1 = result.getString(2);
                            mobile1 = result.getString(3);
                            acard = result.getString(4);
                            profe = result.getString(5);
                            loc = result.getString(6);
                            sd = result.getString(7);
                        } while (result.moveToNext());
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"Invlaid OTP",Toast.LENGTH_LONG).show();
                }


                Intent i = new Intent(geto.this, seebio.class);
                i.putExtra("name", name);
                i.putExtra("image", image1);
                i.putExtra("mobile", mobile1);
                i.putExtra("acard", acard);
                i.putExtra("profe", profe);
                i.putExtra("loca", loc);
                i.putExtra("sdr", sd);

                startActivity(i);
                finish();

            }

        });

        pd = new ProgressDialog(geto.this);
        pd.show();
        pd.setContentView(R.layout.showdia);
        pd.getWindow().setBackgroundDrawableResource(android.R.color.transparent);





        new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent str = getIntent();
                    String val = str.getStringExtra("val");
                    geto.setText(val);
                    pd.dismiss();




                }
            }, 3000);


    }


}