package com.creativeshakeeb.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(/*@Nullable*/ Context context/* @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version*/) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(name TEXT primary key , contact TEXT, dob TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Userdetails");
    }

    public boolean insertuserdata(String name, String contact , String dob){
        SQLiteDatabase  DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name );
        contentValues.put("contact",contact );
        contentValues.put("dob",dob );
        long results = DB.insert("Userdetails", null, contentValues );
        if (results==-1){
            return false;
        }else {
            return true;
        }
    }



    public boolean updateuserdata(String name, String contact , String dob) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("contact", contact);
        contentValues.put("dob", dob);
        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long results = DB.update("Userdetails", contentValues, "name=?", new String[]{name});
            if (results == -1) {
                return false;
            } else {
                return true;
            }
        }else {
            return false;
        }
    }



    public boolean deletedata(String name) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long results = DB.delete("Userdetails",  "name=?", new String[]{name});
            if (results == -1) {
                return false;
            } else {
                return true;
            }
        }else {
            return false;
        }
    }



    public Cursor gedtata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails" ,null);
        return cursor;
    }


}
