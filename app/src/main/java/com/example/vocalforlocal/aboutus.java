package com.example.vocalforlocal;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.annotation.SuppressLint;

import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;
import mehdi.sakout.aboutpage.Element;

import mehdi.sakout.aboutpage.AboutPage;
public class aboutus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);

        Element adsElement = new Element();
        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .isRTL(false)
                .setDescription("Vocal For Local")
                .addItem(new Element().setTitle("Version 1.0"))
                .addGroup("CONNECT WITH US")
                .addEmail("pwaghela506@gmail.com")
                .addPlayStore("com.example.vocalforlocal")   //Replace all this with your package name
                .addInstagram("piyushwaghela07")   //Your instagram id
                .addItem(createCopyright())
                .create();
        setContentView(aboutPage);
    }
    private Element createCopyright()
    {
        Element copyright = new Element();
        @SuppressLint("DefaultLocale") final String copyrightString = String.format("Copyright %d Piyush Waghela", Calendar.getInstance().get(Calendar.YEAR));
        copyright.setTitle(copyrightString);
        // copyright.setIcon(R.mipmap.ic_launcher);
        copyright.setGravity(Gravity.CENTER);
        copyright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(aboutus.this,copyrightString,Toast.LENGTH_SHORT).show();
            }
        });
        return copyright;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}