package com.manas.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class QuizActivity extends AppCompatActivity {
    SQLiteDatabase dbHandler = null;
    List<QuizQuestionsModel> questionList;
    TextView questionStatementField;
    RadioButton radioOption1;
    RadioButton radioOption2;
    RadioButton radioOption3;
    RadioButton radioOption4;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        String categoryPassedToThis = getIntent().getStringExtra("category");
        Integer quizLength = bundle.getInt("quiz_length");


        Log.e("app", String.valueOf(quizLength) + " category " + categoryPassedToThis);
        setContentView(R.layout.activity_quiz);

        questionStatementField = (TextView) findViewById(R.id.QuestionStatement);
        radioOption1 = (RadioButton) findViewById(R.id.radioButton1);
        radioOption2 = (RadioButton) findViewById(R.id.radioButton2);
        radioOption3 = (RadioButton) findViewById(R.id.radioButton3);
        radioOption4 = (RadioButton) findViewById(R.id.radioButton4);
        submitButton = (Button) findViewById(R.id.submit_answer);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("app", "Answer submitted");
            }
        });


        questionList = getQuestionList();


        for (QuizQuestionsModel individualQuestions : questionList) {
            Log.e("app", "Question Statement " + individualQuestions.getQuestionStatement());

            questionStatementField.setText("Hey, one more TextView");
        }




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