package com.manas.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class QuizActivity extends AppCompatActivity {
    SQLiteDatabase dbHandler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initiateDatabase();
        Bundle bundle = getIntent().getExtras();
        Integer quizLength = bundle.getInt("quiz_length");
        Log.e("app",String.valueOf(quizLength));
        setContentView(R.layout.activity_quiz);
    }

    private void initiateDatabase() {

        // Open database handler using our own specialized CustomerDatabaseHelper
        QuizDAO helper = new QuizDAO(getApplicationContext());
        List<QuizQuestionsModel> questions = helper.getAllQuestions();
        //helper.populateRandomQuestions();
    }
}