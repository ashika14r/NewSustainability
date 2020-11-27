package com.example.newsustainability;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HomeS extends AppCompatActivity {

    DatabaseHelper db;
    private TextView userN;
    private String email;
    Button Goals_Btn, Input_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_s);


        db = new DatabaseHelper(this);
        userN = findViewById(R.id.nameID);
        Intent getUserN = getIntent();
        Bundle b = getUserN.getExtras();

        if(b != null){
            email = (String) b.get("userEmail");
            String userName = db.getUserName(email);
            userN.setText(userName);
        }



        Goals_Btn = findViewById(R.id.GoalsBtn);
        Goals_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToGoals = new Intent(HomeS.this, Goals.class);
                moveToGoals.putExtra("userEmail", email);
                startActivity(moveToGoals);
            }
        });

        Input_Btn = findViewById(R.id.InputConsumption);
        Input_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToInput = new Intent(HomeS.this, InputConsumption.class);
                moveToInput.putExtra("userEmail", email);
                startActivity(moveToInput);
            }
        });



    }
}