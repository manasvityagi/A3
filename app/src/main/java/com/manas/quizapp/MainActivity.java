package com.manas.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.lang.reflect.Type;
import java.util.List;

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
                getJsonFromFile();
                Intent i = new Intent(MainActivity.this, ChoseCategory.class);
                startActivity(i);
            }
        });
    }

    void print_json() {
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
        Log.e("quiz", jsonInString);

    }

    String getJsonFromCloud() {

        return " ";
    }

    String getJsonFromAssets(String fileName) {
        String jsonString;
        try {

            InputStream is = MainActivity.this.getAssets().open(fileName);

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            jsonString = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return jsonString;
    }


    String getJsonFromFile() {
        String jsonFileString = getJsonFromAssets("questions.json");
        Gson gson = new Gson();
        QuizQuestionsModel[] arrQuestions = gson.fromJson(jsonFileString, QuizQuestionsModel[].class);
        Log.e("quiz", "------------");
        for (int i = 0; i < arrQuestions.length; i++) {
            Log.e("quiz", arrQuestions[i].toString());
        }
        return jsonFileString.toString();
    }

    // TODO: Populate a database, via a json file.
    void readJsonDB() {

    }

    // TODO: Create an array of objects
    void createQuestionArray() {

    }

    // TODO: Insert the objects in a db
    void insertArrayToDB() {

    }

    // TODO: Read the db, create the array of objects again
    void getListOfQuestionObjects() {

    }


}