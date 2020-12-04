package com.example.newsustainability;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InputConsumption extends AppCompatActivity {

    private String email;
    DatabaseHelper db;

    EditText WaterInput, ElectricityInput, GasInput, MonthInput;
    double water, electricity, gas;
    String monthRaw, month, monthString;
    double Water_Goal, Electricity_Goal, Gas_Goal;
    Button BtnHomeS, BtnSubmit, BtnView;
    String[] monthList = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_consumption);

        db = new DatabaseHelper(this);

        Intent getUserN = getIntent();
        Bundle b = getUserN.getExtras();

        if(b != null)
            email = (String) b.get("userEmail");

        Water_Goal = db.get_Goals_water(email);
        Electricity_Goal = db.get_Goals_electricity(email);
        Gas_Goal = db.get_Goals_gas(email);

        WaterInput = findViewById(R.id.editTextNumberDecimal);
        ElectricityInput = findViewById(R.id.editTextNumberDecimal2);
        GasInput = findViewById(R.id.editTextNumberDecimal3);
        MonthInput = findViewById(R.id.editTextDate3);

        BtnSubmit = findViewById(R.id.BtnSubmit);

        BtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                water = Double.valueOf(WaterInput.getText().toString());
                electricity = Double.valueOf(ElectricityInput.getText().toString());
                gas = Double.valueOf(GasInput.getText().toString());
                monthRaw = MonthInput.getText().toString();
                month = monthList[Integer.valueOf(monthRaw.substring(4,6)) - 1];
                monthString = monthRaw.substring(0,4) + " " + month;

                boolean Achieve = true;

                if(water > Water_Goal || electricity > Electricity_Goal || gas > Gas_Goal )
                    Achieve = false;

                boolean isInserted = db.insertConsumption(email,water,electricity,gas,Achieve,monthString);

                if(isInserted == true){
                    if(Achieve == true)
                        Toast.makeText(InputConsumption.this, "Data Inserted, and you achieved the goal of this month!",
                                Toast.LENGTH_LONG).show();
                    else{
                        Toast.makeText(InputConsumption.this, "Data Inserted, you didn't achieved the goal of this month",
                                Toast.LENGTH_LONG).show();
                    }
                }
                else
                    Toast.makeText(InputConsumption.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
            }
        });

        BtnView = findViewById(R.id.ViewBtn);
        BtnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor result = db.getAllConsumption();
                Boolean achieved;

                StringBuffer buffer = new StringBuffer();
                while(result.moveToNext()) {
                    buffer.append("Month: " + result.getString(6)+"\n");
                    buffer.append("Email: " + result.getString(1)+"\n");
                    buffer.append("Water: " + result.getDouble(2)+"\n");
                    buffer.append("Electricity: " + result.getDouble(3)+"\n");
                    buffer.append("Gas: " + result.getDouble(4)+"\n");
                    achieved = result.getInt(5) > 0;
                    buffer.append("Achieved Goal? " + achieved.toString() +"\n");
                    buffer.append("" + ""+"\n");
                }

                showMessage("Data",buffer.toString());
            }
        });

        BtnHomeS = findViewById(R.id.HomeButton);
        BtnHomeS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToHomeS = new Intent(InputConsumption.this, HomeS.class);
                moveToHomeS.putExtra("userEmail", email);
                startActivity(moveToHomeS);
            }
        });

    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
