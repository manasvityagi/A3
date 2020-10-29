package com.manas.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    SQLiteDatabase dbHandler = null;
    Button submitButton ;
    Integer currentQuestionNumber =0 ;
    List<QuizQuestionsModel> questionList = new ArrayList<QuizQuestionsModel>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        questionList = getQuestionList();

//        loadQuestion(currentQuestionNumber);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                saveScore();
//                loadQuestion(currentQuestionNumber);
            }
        });
    }

    private List<QuizQuestionsModel> getQuestionList() {
        Bundle bundle = getIntent().getExtras();
        String categoryPassedToThis = getIntent().getStringExtra("category");
        Integer quizLength = bundle.getInt("quiz_length");
        Log.e("app",String.valueOf(quizLength) + " category "+ categoryPassedToThis);
        setContentView(R.layout.activity_quiz);
        Toast.makeText(this, categoryPassedToThis+" : "+ String.valueOf(quizLength) , Toast.LENGTH_LONG).show();

        // Open database handler using our own specialized CustomerDatabaseHelper
       QuizDAO helper = new QuizDAO(getApplicationContext());
       List<QuizQuestionsModel> questions = helper.getQuestions(categoryPassedToThis,quizLength);
       for(QuizQuestionsModel individualQuestions: questions){
           Log.e("",individualQuestions.getQuestionStatement());
       }
      return questions;
    }
}