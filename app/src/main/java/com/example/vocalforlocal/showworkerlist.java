package com.example.vocalforlocal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class showworkerlist extends AppCompatActivity {



    MydbHelper mydb;
    private RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showworkerlist);










        recyclerView = findViewById(R.id.recordiv);
        Intent intent = getIntent();
       String pro =  intent.getStringExtra("set");
        String loc =intent.getStringExtra("ser");

        mydb = new MydbHelper(this);
        AdapterRecord adapterRecord = new AdapterRecord(showworkerlist.this,mydb.getalldata(pro,loc));
        recyclerView.setAdapter(adapterRecord);




    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(showworkerlist.this);
        builder.setMessage("Are you sure you want to Exit?")
                .setCancelable(true)
                .setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


}