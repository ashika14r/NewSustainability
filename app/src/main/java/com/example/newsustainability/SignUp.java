package com.example.newsustainability;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    DatabaseHelper db;
    private EditText eName, eEmail, ePassword, ePassConfirm;
    private Button eLogin, eSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        db = new DatabaseHelper(this);
        eName = findViewById(R.id.etName);
        eEmail = findViewById(R.id.etEmailAdd2);
        ePassword = findViewById(R.id.etPass2);
        ePassConfirm = findViewById(R.id.etPassConfirm);
        eSignUp = findViewById(R.id.bSignUp2);
        eLogin = findViewById(R.id.bLogin2);

        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveToLogin = new Intent(SignUp.this, MainActivity.class);
                startActivity(moveToLogin);
            }
        });

        eSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = eName.getText().toString();
                String email = eEmail.getText().toString();
                String password = ePassword.getText().toString();
                String passwordC = ePassConfirm.getText().toString();
                if(name.equals("") || email.equals("") || password.equals("") || passwordC.equals("")){
                    Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(password.equals(passwordC)){
                        Boolean chkEmail = db.checkEmail(email);
                        if(chkEmail == true){
                            Boolean insert = db.insert(email, password, name);
                            if(insert == true){
                                Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Email already exists", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
}