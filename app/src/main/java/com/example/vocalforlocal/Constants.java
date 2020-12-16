package com.example.vocalforlocal;

public class Constants {

    public static final String DB_NAME = "MY_WORKER";

    public static final int DB_VERSION = 1;

    public static final String TABLE_NAME="MY_WORKER_TABLE";

    public static final String C_ID = "ID";
    public static final String C_NAME = "NAME";
    public static final String C_IMAGE = "IMAGE";
    public static final String C_MOBILE = "MOBILE";
    public static final String C_ACARD = "ACARD";
    public static final String C_PROFE = "PROFE";
    public static final String C_LOCATION = "LOCATION";
    public static final String C_SD = "SD";


    //table qurey

    public static final String CREATE_TABLE = " CREATE TABLE " + TABLE_NAME + "("
            + C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + C_NAME + " TEXT,"
            + C_IMAGE + " TEXT,"
            + C_MOBILE + " TEXT,"
            + C_ACARD + " TEXT,"
            + C_PROFE + " TEXT,"
            + C_LOCATION + " TEXT,"
            + C_SD + " TEXT"
            + ")";

}
