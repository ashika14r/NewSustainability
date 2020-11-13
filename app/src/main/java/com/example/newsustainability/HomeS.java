package com.example.newsustainability;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class HomeS extends AppCompatActivity {

    DatabaseHelper db;
    private TextView userN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_s);

        db = new DatabaseHelper(this);
        userN = findViewById(R.id.nameID);
        Intent getUserN = getIntent();
        Bundle b = getUserN.getExtras();

        if(b != null){
            String userName = (String) b.get("userName");
            userN.setText(userName);
        }
    }
}