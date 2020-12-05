package com.example.newsustainability;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ModifyGoal extends AppCompatActivity {

    Button IncraseConsumption, DecreaseConsumption;
    DatabaseHelper db;
    String email;
    double water, electricity, gas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_goal);

        Intent getUserN = getIntent();
        Bundle b = getUserN.getExtras();

        if(b != null){
            email = (String) b.get("userEmail");
        }

        db = new DatabaseHelper(this);


        DecreaseConsumption = findViewById(R.id.HardGoal);
        IncraseConsumption = findViewById(R.id.EasyGoal);



        DecreaseConsumption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                water = db.get_Goals_water(email) - 0.5;
                electricity = db.get_Goals_electricity(email) - 30.0;
                gas = db.get_Goals_gas(email) - 10.0;
                db.updateGoal(email,water,electricity,gas);
                Toast.makeText(ModifyGoal.this, "Goals updated!", Toast.LENGTH_LONG).show();
                Intent moveToGoals = new Intent(ModifyGoal.this, Goals.class);
                moveToGoals.putExtra("userEmail", email);
                startActivity(moveToGoals);
            }
        });

        IncraseConsumption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                water = db.get_Goals_water(email) + 0.5;
                electricity = db.get_Goals_electricity(email) + 30.0;
                gas = db.get_Goals_gas(email) + 10.0;
                db.updateGoal(email,water,electricity,gas);
                Toast.makeText(ModifyGoal.this, "Goals updated!", Toast.LENGTH_LONG).show();
                Intent moveToGoals = new Intent(ModifyGoal.this, Goals.class);
                moveToGoals.putExtra("userEmail", email);
                startActivity(moveToGoals);
            }
        });

    }




}