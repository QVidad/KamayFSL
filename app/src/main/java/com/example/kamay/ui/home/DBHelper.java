package com.example.kamay.ui.home;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import  androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper{
    public DBHelper(Context context){
        super(context,
                "History.db",
                null,
                1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Historydetails(id TEXT primary key, getDate TEXT, sb TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Historydetails");
    }

    public Boolean insertHistory(String id, String getDate, String sb){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("getDate", getDate);
        contentValues.put("sb", sb);
        long result = DB.insert("Historydetails", null, contentValues);
        if (result==-1) {
            return false;
        } else{
            return true;
        }
    }
    public Cursor getData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Historydetails where (id=='1')", null);
        return cursor;
    }
    public Cursor getDate(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select getDate from Historydetails where (id == '1')", null);
        return cursor;
    }
}
