package com.example.newsustainability;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;
    private EditText eEmail, ePassword;
    private Button eLogin, eSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        eEmail = findViewById(R.id.etEmailAdd);
        ePassword = findViewById(R.id.etPass);
        eLogin = findViewById(R.id.bLogin);
        eSignUp = findViewById(R.id.bSignUp);

        eSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveToSignUp = new Intent(MainActivity.this, SignUp.class);
                startActivity(moveToSignUp);
            }
        }); //Practice

        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = eEmail.getText().toString();
                String password = ePassword.getText().toString();
                Boolean chkEP = db.checkEmailPass(email, password);
                if(chkEP == true){
                    String userN = db.getUserName(email);
                    Intent moveToHome = new Intent(MainActivity.this, HomeS.class);
                    //moveToHome.putExtra("userName", userN);
                    moveToHome.putExtra("userEmail",email);
                    startActivity(moveToHome);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Wrong email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
