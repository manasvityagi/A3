package com.manas.quizapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.manas.quizapp.models.QuizDAO;
import com.manas.quizapp.models.QuizQuestionsModel;
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
    ProgressBar progressBar;
    int quizQuesLength;
    int last_question_index;
    int currentScore = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.quizQuesLength = getIntent().getExtras().getInt("quiz_length");
        this.questionList = getQuestionList();
        if (this.questionList.size() <= quizQuesLength)
            last_question_index = this.questionList.size() - 1;
        setupUI();


        submitButton.setOnClickListener(v -> {
            // find the radiobutton by returned id
            selectedRadioButton = findViewById(quesRadioGroup.getCheckedRadioButtonId());
            String selectedAnswer = selectedRadioButton.getText().toString();

            checkAnswerAndScore(questionList.get(currentQuestionPointer), selectedAnswer);

            // Quiz Ends
            if (currentQuestionPointer == last_question_index) {
                Intent intent = new Intent(QuizActivity.this, FinalScore.class);
                intent.putExtra("score", String.valueOf(currentScore));
                startActivity(intent);
            } else {
                currentQuestionPointer += 1;
                loadQuestionOnUI(questionList.get(currentQuestionPointer));
                progressBar.setProgress(currentQuestionPointer * 10);
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
        questionStatementField.setText(individualQuestions.getQuestionStatement());
        radioOption1.setText(individualQuestions.getOption1());
        radioOption2.setText(individualQuestions.getOption2());
        radioOption3.setText(individualQuestions.getOption3());
        radioOption4.setText(individualQuestions.getOption4());
    }


    private void checkAnswerAndScore(QuizQuestionsModel individualQuestions, String selectedAnswer) {
        String correctAnswer = individualQuestions.getCorrectOptionNumber();
        if (correctAnswer.equals(selectedAnswer)) {
            Log.e("app", "Correct Answer Selected");
            currentScore = currentScore + 10;
            Toast.makeText(QuizActivity.this, "Correct :" + currentScore, Toast.LENGTH_SHORT).show();
        } else {
            Log.e("app", "Wrong Answer Selected");
            Toast.makeText(QuizActivity.this, "Wrong: " + currentScore, Toast.LENGTH_SHORT).show();
        }
    }


    private List<QuizQuestionsModel> getQuestionList() {

        String categoryPassedToThis = getIntent().getStringExtra("category");

        // Open database handler using our own specialized CustomerDatabaseHelper
        QuizDAO helper = new QuizDAO(getApplicationContext());
        List<QuizQuestionsModel> questionList = helper.getQuestions(categoryPassedToThis, quizQuesLength);

        return questionList;
    }


    private void setupUI() {
        setContentView(R.layout.activity_quiz);
        questionStatementField = findViewById(R.id.QuestionStatement);
        radioOption1 = findViewById(R.id.radioButton1);
        radioOption2 = findViewById(R.id.radioButton2);
        radioOption3 = findViewById(R.id.radioButton3);
        radioOption4 = findViewById(R.id.radioButton4);
        submitButton = findViewById(R.id.submit_answer);
        quesRadioGroup = findViewById(R.id.radioGroup);
        progressBar = findViewById(R.id.progressBar1);
        progressBar.setMax(100);
        progressBar.setProgress(0);
        progressBar.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        //Load First Question
        loadQuestionOnUI(questionList.get(currentQuestionPointer));
    }


}