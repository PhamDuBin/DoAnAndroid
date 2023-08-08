package com.example.doanandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String databaseName = "Signup.db";
    public DatabaseHelper(@Nullable Context context){
        super(context,"Signup.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase MyDatabase){
        MyDatabase.execSQL("create Table allusers(UserName TEXT primary key, Email TEXT,Tel TEXT,Password TEXT,CheckPassword TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase,int i,int i1){
        MyDatabase.execSQL("drop Table if exists allusers");
    }
    public boolean insertData(String email,String password,String username,String Tel){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Password" ,password);
        cv.put("Email",email);
        cv.put("UserName",username);
        cv.put("Tel",Tel);
        long result = MyDatabase.insert("allusers",null,cv);

        if(result==-1){
            return false;
        }else {
            return true;
        }
    }
    public boolean checkEmail(String email){
        SQLiteDatabase mydatabase = this.getWritableDatabase();
        Cursor cursor = mydatabase.rawQuery("Select * from allusers where email = ?",new String[]{email} );
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }
    public boolean checkEmailPassword(String email,String password){
        SQLiteDatabase mydatabase = this.getWritableDatabase();
        Cursor cursor = mydatabase.rawQuery("Select * from allusers where email = ? and password=?",new String[]{email,password} );
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }
}
