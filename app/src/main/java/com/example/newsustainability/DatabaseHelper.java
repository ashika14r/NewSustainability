package com.example.newsustainability;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "NewSustainability.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create table user(email text primary key, password text, name text)");
        sqLiteDatabase.execSQL("Create table consumption(Consumption_ID integer primary key, email text, water double, electricity double, gas double, achieved boolean)");
        sqLiteDatabase.execSQL("Create table goals(email text primary key, water double, electricity double, gas double)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists user");
        sqLiteDatabase.execSQL("Drop table if exists consumption");
        sqLiteDatabase.execSQL("Drop table if exists goals");
        onCreate(sqLiteDatabase);
    }

    //inserting into database
    public boolean insert(String email, String password, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("name", name);
        long ins = db.insert("user", null, contentValues);
        if(ins == -1) {return false;}
        else {return true;}
    }

    public boolean insertConsumption(String email, double water, double electricity, double gas, boolean achieved){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("water", water);
        contentValues.put("electricity", electricity);
        contentValues.put("gas", gas);
        contentValues.put("achieved", achieved);

        long ins = db.insert("consumption", null, contentValues);
        if(ins == -1) {return false;}
        else {return true;}
    }

    // inserting goals
    public boolean insertGoals(String email, double water, double electricity, double gas){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("water", water);
        contentValues.put("electricity", electricity);
        contentValues.put("gas", gas);
        long ins = db.insert("goals", null, contentValues);
        if(ins == -1) {return false;}
        else {return true;}
    }

    public boolean updateGoal(String email, double water, double electricity, double gas){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);
        contentValues.put("water",water);
        contentValues.put("electricity",electricity);
        contentValues.put("gas",gas);
        db.update("goals",contentValues,"email = ?",new String[]{email});
        return true;

    }

    //checking if email already exists
    public Boolean checkEmail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=?", new String[] {email});
        if (cursor.getCount()>0){return false;}
        else {return true;}
    }

    //checking email and password to log user in
    public Boolean checkEmailPass(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=? and password=?", new String[] {email, password});
        if (cursor.getCount()>0){return true;}
        else {return false;}
    }

    public String getUserName(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select name from user where email=?", new String[] {email});
        if (cursor.moveToFirst()) {return cursor.getString(0);}
        else {return "";}
    }

    public Double get_Goals_water(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select water from goals where email=?", new String[] {email});
        if (cursor.moveToFirst()) {return cursor.getDouble(0);}
        else {return 0.0;}
    }

    public Double get_Goals_electricity(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select electricity from goals where email=?", new String[] {email});
        if (cursor.moveToFirst()) {return cursor.getDouble(0);}
        else {return 0.0;}
    }

    public Double get_Goals_gas(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select gas from goals where email=?", new String[] {email});
        if (cursor.moveToFirst()) {return cursor.getDouble(0);}
        else {return 0.0;}
    }

    public Cursor getAllConsumption(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from consumption", null );
        return result;
    }


}
