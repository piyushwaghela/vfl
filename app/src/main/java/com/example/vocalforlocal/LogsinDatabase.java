package com.example.vocalforlocal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class LogsinDatabase extends SQLiteOpenHelper {

    public static final String DATABSE_NAME = "logsinse.db";


    public LogsinDatabase(@Nullable Context context) {
        super(context,DATABSE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table alluser(id integer primary key autoincrement, name TEXT, email TEXT, mobile TEXT, password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS alluser");


    }

    public boolean insert(String name,String email,String mobile,String password){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put("name",name);
        v.put("email",email);
        v.put("mobile",mobile);
        v.put("password",password);

        long result = db.insert("alluser", null,v);
        if (result == -1){
            return false;

        }
        else  return true;
    }

    public Boolean checkusername(String email){

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from alluser where email=?", new String[]{email});
        if(cursor.getCount() > 0){
            return false;
        }else {
            return true;
        }
    }

    public Boolean Checklogin(String email,String password){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from alluser where email=? and password=?", new String[]{email, password});
        if (cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }


    }



}
