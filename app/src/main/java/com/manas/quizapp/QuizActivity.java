package com.manas.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class QuizActivity extends AppCompatActivity {
    SQLiteDatabase dbHandler = null;
    List<QuizQuestionsModel> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        String categoryPassedToThis = getIntent().getStringExtra("category");
        Integer quizLength = bundle.getInt("quiz_length");
        Log.e("app", String.valueOf(quizLength) + " category " + categoryPassedToThis);
        setContentView(R.layout.activity_quiz);

        questionList = getQuestionList();

        Toast.makeText(this, categoryPassedToThis + " : " + String.valueOf(quizLength), Toast.LENGTH_LONG).show();


    }

    private List<QuizQuestionsModel> getQuestionList() {
        Bundle bundle = getIntent().getExtras();
        String categoryPassedToThis = getIntent().getStringExtra("category");
        Integer quizLength = bundle.getInt("quiz_length");
        Log.e("app", String.valueOf(quizLength) + " category " + categoryPassedToThis);

        Toast.makeText(this, categoryPassedToThis + " : " + String.valueOf(quizLength), Toast.LENGTH_LONG).show();

        // Open database handler using our own specialized CustomerDatabaseHelper
        QuizDAO helper = new QuizDAO(getApplicationContext());
        List<QuizQuestionsModel> questionList = helper.getQuestions(categoryPassedToThis, quizLength);

        for (QuizQuestionsModel individualQuestions : questionList) {
            Log.e("app", "Question Statement " + individualQuestions.getQuestionStatement());
        }
        return questionList;
    }

}