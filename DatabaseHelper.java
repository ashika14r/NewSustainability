package com.example.newsustainability;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.ResultSet;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "NewSustainability.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create table user(email text primary key, password text, name text)");
        sqLiteDatabase.execSQL("Create table consumption(consumptionID integer primary key, electricity real, water real, gas real, email text)");
        sqLiteDatabase.execSQL("Create table goals(goalID integer primary key, electricity real, water real, gas real, email text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists user");
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
    // inserting consumption
    public boolean insertConsumption(int consumptionID, double electricity, double water, double gas, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("consumptionID", consumptionID);
        contentValues.put("electricity", electricity);
        contentValues.put("water", water);
        contentValues.put("gas", gas);
        contentValues.put("email", email);
        long ins = db.insert("consumption", null, contentValues);
        if(ins == -1) {return false;}
        else {return true;}
    }

    // inserting goals
    public boolean insertGoals(int goalID, double electricity, double water, double gas, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("goalID", goalID);
        contentValues.put("electricity", electricity);
        contentValues.put("water", water);
        contentValues.put("gas", gas);
        contentValues.put("email", email);
        long ins = db.insert("goals", null, contentValues);
        if(ins == -1) {return false;}
        else {return true;}
    }

    // check current consumption
    public double checkElectricity(String email) {
        ResultSet electricityTotal = sqLiteDatabase.execSQL("Select sum(electricity) from consumption where email = " + email);
        double electricityResult = electricityTotal.getDouble("electricity");
        electricityTotal.close();
        return electricityResult;
    }
    public double checkWater(String email) {
        ResultSet waterTotal = sqLiteDatabase.execSQL("Select sum(water) from consumption where email = " + email);
        double waterResult = waterTotal.getDouble("water");
        waterTotal.close();
        return waterResult;
    }
    public double checkGas(String email) {
        ResultSet gasTotal = sqLiteDatabase.execSQL("Select sum(gas) from consumption where email = " + email);
        double gasResult = gasTotal.getDouble("gas");
        gasTotal.close();
        return gasResult;
    }


    public Boolean checkEmail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=?", new String[] {email});
        if (cursor.getCount()>0){return false;}
        else {return true;}
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
}
