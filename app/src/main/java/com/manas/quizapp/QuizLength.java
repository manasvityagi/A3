package com.manas.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class QuizLength extends AppCompatActivity {
    Button btnMiniQuiz;
    Button btnFullQuiz;
    final static Integer FULL_QUIZ_LENGTH = 35;
    final static Integer MINI_QUIZ_LENGTH = 10;
    final static String CATEGORY = "category";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_length);
        String categoryPassedToThis = getIntent().getStringExtra(CATEGORY);
        Toast.makeText(this, categoryPassedToThis, Toast.LENGTH_LONG).show();

        btnMiniQuiz = findViewById(R.id.mini_quiz);
        btnFullQuiz = findViewById(R.id.full_quiz);

        btnMiniQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizLength.this, QuizActivity.class);
                intent.putExtra("quiz_length", MINI_QUIZ_LENGTH);
                intent.putExtra(CATEGORY, categoryPassedToThis);
                startActivity(intent);
            }
        });

        btnFullQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizLength.this, QuizActivity.class);
                intent.putExtra("quiz_length", FULL_QUIZ_LENGTH);
                intent.putExtra(CATEGORY, categoryPassedToThis);
                startActivity(intent);
            }
        });
    }
}