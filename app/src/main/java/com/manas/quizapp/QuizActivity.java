package com.manas.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class QuizActivity extends AppCompatActivity {
    SQLiteDatabase dbHandler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initiateDatabase();
        
        setContentView(R.layout.activity_quiz);
    }

    private void initiateDatabase() {

        // Open database handler using our own specialized CustomerDatabaseHelper
        QuizDAO helper = new QuizDAO(getApplicationContext());
        dbHandler = helper.getWritableDatabase();
    }
}