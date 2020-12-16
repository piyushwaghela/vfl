package com.example.vocalforlocal;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.ArrayRow;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class selectworker extends AppCompatActivity {

    SearchView search;
    ProgressDialog pd;
    Button go;
    TextView stv;
    ArrayList<String> arrayList;
    Dialog dialog;
    MydbHelper db;
    String p;
    String l;

    MydbHelper mydb;
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectworker2);


        go = findViewById(R.id.go);
        search = findViewById(R.id.search);




        mydb = new MydbHelper(this);
        stv = findViewById(R.id.stv);
        db = new MydbHelper(this);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this,R.id.search,
                RegexTemplate.NOT_EMPTY,R.string.invalid_location);

        arrayList = new ArrayList<>();
        arrayList.add("Electriction");
          arrayList.add("Plumber");
          arrayList.add("Maid");
          arrayList.add("Baby-Sitter");
          arrayList.add("Housekeeping Cleaners");
          arrayList.add("WatchMan");
          arrayList.add("Beautician");
          arrayList.add("Bicyle-Repair");
        arrayList.add("Cable Tv operation");
        arrayList.add("Carpentar");
        arrayList.add("Catering");
        arrayList.add("Coaching Service");
        arrayList.add("Pottery");
        arrayList.add("Security Service");
        arrayList.add("Laundry");
        arrayList.add("welding");
        arrayList.add("Technician");
        arrayList.add("Choreographer");
        arrayList.add("Dancer");
        arrayList.add("Photographer");






          stv.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  dialog = new Dialog(selectworker.this);
                  dialog.setContentView(R.layout.dialog_searchable_spinner);
                  //set custom height and width
                  dialog.getWindow().setLayout(650,800);

                  //set transpernt background

                  dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                  //show dialog

                  dialog.show();

                  //Initialize and assign variable

                  EditText editText = dialog.findViewById(R.id.edittext);

                  ListView listView = dialog.findViewById(R.id.list);

                  final ArrayAdapter<String> adapter =  new ArrayAdapter<>(selectworker.this
                  ,android.R.layout.simple_list_item_1,arrayList);

                  listView.setAdapter(adapter);

                  editText.addTextChangedListener(new TextWatcher() {
                      @Override
                      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                      }

                      @Override
                      public void onTextChanged(CharSequence s, int start, int before, int count) {
                                //filter array list
                          adapter.getFilter().filter(s);

                      }

                      @Override
                      public void afterTextChanged(Editable s) {

                      }
                  });

                  listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                      @Override
                      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                          stv.setText(adapter.getItem(position));
                          dialog.dismiss();
                      }
                  });

              }
          });




        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                pd = new ProgressDialog(selectworker.this);
                pd.show();
                pd.setContentView(R.layout.showdia);
                pd.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


                final String set = stv.getText().toString();
                final String ser = search.getQuery().toString();

                Cursor res = db.checksearch(set,ser);
                while (res.moveToNext()){

                    p = res.getString(5);
                    l = res.getString(6);

                }
                if(set.equals(p) && ser.equals(l)){
                   new Handler().postDelayed(new Runnable() {
                       @Override
                       public void run() {
                           pd.dismiss();

                           Intent i = new Intent(selectworker.this,showworkerlist.class);
                           i.putExtra("set",set);
                           i.putExtra("ser",ser);
                           startActivity(i);
                           finish();

                       }
                   },5000);
                }else {
                    ViewGroup vg = findViewById(android.R.id.content);

                    Button ok;


                    AlertDialog.Builder  builder =new AlertDialog.Builder(selectworker.this);
                    View view = LayoutInflater.from(selectworker.this).inflate(R.layout.lose_layout,vg,false);
                    builder.setCancelable(false);
                    builder.setView(view);

                    ok = view.findViewById(R.id.ok);


                    final AlertDialog alertDialog = builder.create();

                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            alertDialog.dismiss();
                            pd.dismiss();

                        }
                    });
                    alertDialog.show();


                }
            }



        });

    }



}





