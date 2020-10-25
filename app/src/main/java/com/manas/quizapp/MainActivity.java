package com.manas.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
Button startQuizBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startQuizBtn = findViewById(R.id.start_quiz_btn);
        startQuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                print_json();
                Intent i = new Intent(MainActivity.this, ChoseCategory.class);
                startActivity(i);
            }
        });
    }

    void print_json(){
        QuizQuestionsModel temp_question_obj = new QuizQuestionsModel("This is the Question", "Wrong option 1",
                "Wrong option 2",
                "Wrong option 3",
                "Wrong option 4",
                "Correct option 5",
                "Correct option 6",
                "https://i.imgur.com/eaJOzqt.jpeg",
                "Correct option 6", "courtesy", 4.5
        );
        Gson gson = new Gson();
        String jsonInString = gson.toJson(temp_question_obj);
        Log.e("quiz",jsonInString);

    }
}