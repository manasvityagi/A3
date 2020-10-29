package com.manas.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class QuizActivity extends AppCompatActivity {
    SQLiteDatabase dbHandler = null;
    List<QuizQuestionsModel> questionList;
    Integer currentQuestionPointer = 0;
    TextView questionStatementField;
    RadioGroup quesRadioGroup;
    RadioButton radioOption1;
    RadioButton radioOption2;
    RadioButton radioOption3;
    RadioButton radioOption4;
    RadioButton selectedRadioButton;
    Button submitButton;
    int quizQuesLength;
    int currentScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.questionList = getQuestionList();
        setupUI();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // find the radiobutton by returned id
                selectedRadioButton = (RadioButton) findViewById(quesRadioGroup.getCheckedRadioButtonId());
                String selectedAnswer = selectedRadioButton.getText().toString();
                Toast.makeText(QuizActivity.this, selectedAnswer, Toast.LENGTH_SHORT).show();

                checkAnswerAndScore(questionList.get(currentQuestionPointer), selectedAnswer);
                loadQuestionOnUI(questionList.get(currentQuestionPointer));
                currentQuestionPointer += 1;

                if (currentQuestionPointer == quizQuesLength) {
                    Intent intent = new Intent(QuizActivity.this, FinalScore.class);
                    intent.putExtra("score", String.valueOf(currentScore));
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    protected void onPostResume() {
        Toast.makeText(QuizActivity.this,
                "Resumed!!!!!!!!!!", Toast.LENGTH_SHORT).show();
        super.onPostResume();
    }

    private void loadQuestionOnUI(QuizQuestionsModel individualQuestions) {
        Log.e("app", "Question Statement " + individualQuestions.getQuestionStatement());

        questionStatementField.setText(individualQuestions.getQuestionStatement());
        radioOption1.setText(individualQuestions.getOption1());
        radioOption2.setText(individualQuestions.getOption2());
        radioOption3.setText(individualQuestions.getOption3());
        radioOption4.setText(individualQuestions.getOption4());
    }

    private void checkAnswerAndScore(QuizQuestionsModel individualQuestions, String selectedAnswer) {
        if (individualQuestions.getCorrectOptionNumber().equals(selectedAnswer)) {
            Log.e("app", "Correct Answer Selected");
            currentScore = currentScore + 10;
        } else {
            Log.e("app", "Wrong Answer Selected");
        }
    }

    private List<QuizQuestionsModel> getQuestionList() {
        Bundle bundle = getIntent().getExtras();
        String categoryPassedToThis = getIntent().getStringExtra("category");
        categoryPassedToThis = "courtesy";
        this.quizQuesLength = bundle.getInt("quiz_length");

        // Open database handler using our own specialized CustomerDatabaseHelper
        QuizDAO helper = new QuizDAO(getApplicationContext());
        List<QuizQuestionsModel> questionList = helper.getQuestions(categoryPassedToThis, quizQuesLength);

        return questionList;
    }

    private void setupUI() {
        setContentView(R.layout.activity_quiz);
        questionStatementField = (TextView) findViewById(R.id.QuestionStatement);
        radioOption1 = (RadioButton) findViewById(R.id.radioButton1);
        radioOption2 = (RadioButton) findViewById(R.id.radioButton2);
        radioOption3 = (RadioButton) findViewById(R.id.radioButton3);
        radioOption4 = (RadioButton) findViewById(R.id.radioButton4);
        submitButton = (Button) findViewById(R.id.submit_answer);
        quesRadioGroup = findViewById(R.id.radioGroup);
        radioOption1.setSelected(true);
        //Load First Question
        loadQuestionOnUI(questionList.get(0));
        currentQuestionPointer = 1;
    }


}