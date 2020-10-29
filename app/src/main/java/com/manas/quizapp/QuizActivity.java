package com.manas.quizapp;

import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadQuestionsFromDB();
        setupUI();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = quesRadioGroup.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                selectedRadioButton = (RadioButton) findViewById(selectedId);
                Toast.makeText(QuizActivity.this,
                        selectedRadioButton.getText(), Toast.LENGTH_SHORT).show();
                Log.e("app",  selectedRadioButton.getText().toString());
                loadNextQuestion(questionList.get(currentQuestionPointer));
                currentQuestionPointer+=1;

            }
        });

    }

    private void loadNextQuestion(QuizQuestionsModel individualQuestions){
        Log.e("app", "Question Statement " + individualQuestions.getQuestionStatement());
        loadNextQuestion(questionList.get(currentQuestionPointer));
        currentQuestionPointer+=1;

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
        loadNextQuestion(questionList.get(0));
        currentQuestionPointer=1;
    }

    private void loadQuestionsFromDB() {
        this.questionList = getQuestionList();
    }

}