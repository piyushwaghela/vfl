package com.example.vocalforlocal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.io.File;

public class logsin extends AppCompatActivity {
    Button login,signup;
    TextView wform;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logsin);
        wform=findViewById(R.id.wform);
        login=findViewById(R.id.login);
        signup=findViewById(R.id.signup);

        toolbar=findViewById(R.id.toolbar);
        navigationView=findViewById(R.id.navigation);
        drawerLayout = findViewById(R.id.drwer);

        setSupportActionBar(toolbar);


        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        // to get menu item

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                drawerLayout.closeDrawer(GravityCompat.START);

                switch (item.getItemId()){

                    case R.id.work:
                        Intent in = new Intent(logsin.this,howwework.class);
                        startActivity(in);


                        break;

                    case R.id.cprofile:
                        Intent intent = new Intent(logsin.this,otp.class);
                        startActivity(intent);
                        finish();

                    case R.id.aboutus:
                        Intent i = new Intent(logsin.this,aboutus.class);
                        startActivity(i);



                        break;

                    case R.id.share:

                        Intent sharing = new Intent(Intent.ACTION_SEND);
                        sharing.setType("text/plain");
                        startActivity(Intent.createChooser(sharing,"Share Using"));
                        break;

                }
                return true;
            }
        });



        //Action for button
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(logsin.this,loginforuser.class);
                startActivity(intent);
                finish();

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(logsin.this,usersignup.class);
                startActivity(intent);
                finish();

            }
        });

        wform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(logsin.this,applicationforworker.class);
                startActivity(intent);
                finish();
            }
        });


    }
    public void onBackpressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
}