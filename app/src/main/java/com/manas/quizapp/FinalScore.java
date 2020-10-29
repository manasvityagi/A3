package com.manas.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class FinalScore extends AppCompatActivity {
    TextView scoreField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_score);
        scoreField = findViewById(R.id.finalScore);

        String score = getIntent().getStringExtra("score");
        scoreField.setText(score);
    }
}