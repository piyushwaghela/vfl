package com.example.vocalforlocal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MydbHelper extends SQLiteOpenHelper {
    public MydbHelper(@Nullable Context context) {
        super(context, Constants.DB_NAME,null,Constants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Constants.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ Constants.TABLE_NAME);

        onCreate(db);

    }

    public long insertrecord(String name, String image, String mobile, String acard, String profe, String location, String sd){

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.C_NAME,name);
        values.put(Constants.C_IMAGE,image);
        values.put(Constants.C_MOBILE,mobile);
        values.put(Constants.C_ACARD,acard);
        values.put(Constants.C_PROFE,profe);
        values.put(Constants.C_LOCATION,location);
        values.put(Constants.C_SD,sd);
      long id =  database.insert(Constants.TABLE_NAME,null,values);
        database.close();
        return id;

    }

    public  ArrayList<ModelRecord> getalldata(String profes, String loc){

        ArrayList<ModelRecord> arrayList = new ArrayList<>();

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from " +Constants.TABLE_NAME + " where PROFE=? and LOCATION=?",new String[]{profes,loc});

        if(cursor.moveToNext()){
            do {
                ModelRecord modelRecord =new ModelRecord(
                        ""+cursor.getInt(cursor.getColumnIndex(Constants.C_ID)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_NAME)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_IMAGE)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_MOBILE)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_PROFE)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_SD)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_LOCATION))
                );
                arrayList.add(modelRecord);
            }while (cursor.moveToNext());
        }
        database.close();
        return arrayList;


    }

    public Cursor viewdata(String mobile){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from MY_WORKER_TABLE  where MOBILE=?", new String[]{mobile});
        return cursor;
    }

    public Cursor getforworker(String mobile){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from MY_WORKER_TABLE  where MOBILE=?", new String[]{mobile});
        return cursor;
    }

    public Cursor getinfo(String mo){

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from " +Constants.TABLE_NAME + " where  MOBILE=?",new String[]{mo});
        return cursor;








    }

    public Cursor checksearch(String type,String area){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor crs = db.rawQuery("select * from MY_WORKER_TABLE where PROFE=? and LOCATION=?", new String[]{type, area});

        return crs;
    }




}

