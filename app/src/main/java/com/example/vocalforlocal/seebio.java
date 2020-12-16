package com.example.vocalforlocal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blogspot.atifsoftwares.circularimageview.CircularImageView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class seebio extends AppCompatActivity {

    CircularImageView imageView;
    FloatingActionButton  fb;

    TextView setname,setmobile,setacard,setprfo,setlocation,setsd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seebio);

        imageView = findViewById(R.id.setimage);
        setname = findViewById(R.id.setname);
        setmobile = findViewById(R.id.setmobile);
        setacard = findViewById(R.id.setacard);
        setprfo = findViewById(R.id.setpro);
        setlocation = findViewById(R.id.setloc);
        setsd = findViewById(R.id.setsdt);
        fb = findViewById(R.id.exit);



        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setname.setText("");
                setmobile.setText("");
                setacard.setText("");
                setprfo.setText("");
                setlocation.setText("");
                setsd.setText("");
                Intent i = new Intent(seebio.this,logsin.class);
                startActivity(i);
                finish();


            }
        });

        Intent mobile = getIntent();
        String name = mobile.getStringExtra("name");
       Uri uri = Uri.parse(mobile.getStringExtra("image"));
        String mob = mobile.getStringExtra("mobile");
        String acd = mobile.getStringExtra("acard");
        String pf = mobile.getStringExtra("profe");
        String lo = mobile.getStringExtra("loca");
        String st = mobile.getStringExtra("sdr");

        setname.setText(name);
        imageView.setImageURI(uri);
        setmobile.setText(mob);
        setacard.setText(acd);
        setprfo.setText(pf);
        setlocation.setText(lo);
        setsd.setText(st);


    }
}