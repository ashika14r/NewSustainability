package com.example.newsustainability;

import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.DefaultTaskExecutor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Goals extends AppCompatActivity {

    private String email;
    DatabaseHelper db;

    private TextView WaterText, ElectricityText, GasText;
    private double water, electricity, gas;
    private Button BtnHome,BtnModify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);

        db = new DatabaseHelper(this);

        WaterText = findViewById(R.id.Water);
        ElectricityText = findViewById(R.id.Electricity);
        GasText = findViewById(R.id.Gas);

        Intent getUserN = getIntent();
        Bundle b = getUserN.getExtras();

        if(b != null){
            email = (String) b.get("userEmail");
        }

        water = db.get_Goals_water(email);
        electricity = db.get_Goals_electricity(email);
        gas = db.get_Goals_gas(email);

        String water_ = String.valueOf(water);
        String electricity_ = String.valueOf(electricity);
        String gas_ = String.valueOf(gas);

        WaterText.setText(water_ + " tons of water");
        ElectricityText.setText(electricity_ + " watts of electricity");
        GasText.setText(gas_ + " L of gasoline");

        BtnModify = findViewById(R.id.ModifyGoal);
        BtnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToModifyGoal = new Intent(Goals.this, ModifyGoal.class);
                moveToModifyGoal.putExtra("userEmail", email);
                startActivity(moveToModifyGoal);
            }
        });

        BtnHome = findViewById(R.id.HomeButton);
        BtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToHomeS = new Intent(Goals.this, HomeS.class);
                moveToHomeS.putExtra("userEmail", email);
                startActivity(moveToHomeS);
            }
        });
    }
}