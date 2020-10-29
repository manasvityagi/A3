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
    Integer currentQuestionPointer =0;
    TextView questionStatementField;
    RadioGroup quesRadioGroup;
    RadioButton radioOption1;
    RadioButton radioOption2;
    RadioButton radioOption3;
    RadioButton radioOption4;
    RadioButton selectedRadioButton;
    Button submitButton;
    int quizQuesLength;
    private Integer currentScore = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.questionList = getQuestionList();
        setupUI();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup~
                int selectedId = quesRadioGroup.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                selectedRadioButton = (RadioButton) findViewById(selectedId);
                Toast.makeText(QuizActivity.this,
                        selectedRadioButton.getText(), Toast.LENGTH_SHORT).show();
                Log.e("app",  selectedRadioButton.getText().toString());
                loadQuestionOnUI(questionList.get(currentQuestionPointer));
                currentQuestionPointer+=1;

                if(currentQuestionPointer == quizQuesLength){
                    Intent intent = new Intent(QuizActivity.this, FinalScore.class);
                    intent.putExtra("score", currentScore);
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

    private void loadQuestionOnUI(QuizQuestionsModel individualQuestions){
        Log.e("app", "Question Statement " + individualQuestions.getQuestionStatement());
        questionStatementField.setText(individualQuestions.getQuestionStatement());
        radioOption1.setText(individualQuestions.getOption1());
        radioOption2.setText(individualQuestions.getOption2());
        radioOption3.setText(individualQuestions.getOption3());
        radioOption4.setText(individualQuestions.getOption4());
    }

    private List<QuizQuestionsModel> getQuestionList() {
        Bundle bundle = getIntent().getExtras();
        String categoryPassedToThis = getIntent().getStringExtra("category");
        categoryPassedToThis = "courtesy";
        this.quizQuesLength = bundle.getInt("quiz_length");

        // Open database handler using our own specialized CustomerDatabaseHelper
        QuizDAO helper = new QuizDAO(getApplicationContext());
        List<QuizQuestionsModel> questionList = helper.getQuestions(categoryPassedToThis, quizLength);

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
        //Load First Question
        loadQuestionOnUI(questionList.get(0));
        currentQuestionPointer=1;
    }


}