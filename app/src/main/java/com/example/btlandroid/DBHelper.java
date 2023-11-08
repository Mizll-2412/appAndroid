package com.example.btlandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME="Login.db";
    public DBHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table users(username TEXT primary key,pass TEXT,pos TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists users");
    }

    public Boolean insertData(String username, String pass, String pos)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("username",username);
        values.put("pass",pass);
        values.put("pos",pos);

        long result=db.insert("users",null,values);
        if(result==-1) return false;
        else
            return true;
    }

    public boolean checkUsername(String username)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor =db.rawQuery("Select * from users where username=?",new String[] {username});
        if(cursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean checkUserNamePass(String username,String pass)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor =db.rawQuery("Select * from users where username=? and pass=?",new String[]{username,pass});
        if(cursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}