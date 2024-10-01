package com.example.myprojects;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String  DBName="Login.db";

    public DatabaseHelper( Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table users(email TEXT primary key,password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists users");

    }
    public Boolean insertData(String email,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("email",email);
        cv.put("password",password);
        long res=db.insert("users",null,cv);
        if(res==-1)
            return false;
        else
            return true;
    }
    public Boolean checkusername(String email)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c=db.rawQuery("select * from users where email=?",new String[]{email});
        if(c.getCount()>0)
            return true;
        else
            return false;
    }
    public Boolean checkusernamepassword(String email, String password){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c=db.rawQuery("select * from users where email=? and password=?",new String[] {email,password});
        if(c.getCount()>0)
            return true;
        else
            return false;
    }
}