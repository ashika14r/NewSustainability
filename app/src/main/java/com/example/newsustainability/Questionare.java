package com.example.newsustainability;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Questionare extends AppCompatActivity {

    private String email;
    private ProfileQ profile = new ProfileQ();
    private TextView Questions, UserName;
    private Button firstButton, secondButton, thirdButton, FinishBtn;
    private double score = 0;
    private int QuestionNumber = 0;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionare);

        Questions = findViewById(R.id.QuestionText);
        firstButton = findViewById(R.id.FirstButton);
        secondButton = findViewById(R.id.SecondButton);
        thirdButton = findViewById(R.id.ThirdButton);
        FinishBtn = findViewById(R.id.finishBtn);
        UserName = findViewById(R.id.Username);
        db = new DatabaseHelper(this);

        Intent getUserN = getIntent();
        Bundle b = getUserN.getExtras();

        if(b != null){
            email = (String) b.get("userEmail");
            String userName = db.getUserName(email);
            UserName.setText(userName);
        }

        updateQuestion();

        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestionNumber += 1;
                if(QuestionNumber == 4)
                    showResult();
                else {
                    score += 50;
                    updateQuestion();
                }
            }
        });

        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestionNumber += 1;
                if(QuestionNumber == 4)
                    showResult();
                else {
                    score += 30;
                    updateQuestion();
                }
            }
        });

        thirdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestionNumber += 1;
                if(QuestionNumber == 4)
                    showResult();
                else {
                    score += 10;
                    updateQuestion();
                }
            }
        });

    }

    private void updateQuestion(){
        Questions.setText(profile.getQuestions(QuestionNumber));
        firstButton.setText(profile.getAnswers1(QuestionNumber));
        secondButton.setText(profile.getAnswers2(QuestionNumber));
        thirdButton.setText(profile.getAnswers3(QuestionNumber));
    }

    private void showResult(){
        double water, electricity, gas;

        water = score / 50;
        electricity = score / 10;
        gas = score / 30;

        String water_ = String.valueOf(water);
        String electricity_ = String.valueOf(electricity);
        String gas_ = String.valueOf(gas);

        Questions.setText("Your suggested monthly consumption: ");
        firstButton.setText(water_ + " Tons of water");
        secondButton.setText(electricity_ +  "Watts of electricity");
        thirdButton.setText(gas_ + " Liter of gas");

        firstButton.setClickable(false);
        secondButton.setClickable(false);
        thirdButton.setClickable(false);

        FinishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Quiz Finished", Toast.LENGTH_SHORT).show();
                db.insertGoals(email, water, electricity, gas);
                Intent moveToHomeS = new Intent(Questionare.this, HomeS.class);
                moveToHomeS.putExtra("userEmail", email);
                startActivity(moveToHomeS);
                db.insertGoals(email, water, electricity, gas);
            }
        });

    }

}