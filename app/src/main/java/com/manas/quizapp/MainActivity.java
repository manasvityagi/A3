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

                Log.e("quiz", "-----------+----------");
                //print_json();
                getJsonFromFile();
                Intent i = new Intent(MainActivity.this, ChoseCategory.class);
                //startActivity(i);
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


    JsonObject[] getJsonFromFile() {
        String jsonFileString = getJsonFromAssets("questions.json");
        Gson gson = new Gson();
        JsonObject[] arrQuestions = gson.fromJson(jsonFileString, JsonObject[].class);

        for (JsonObject arrQuestion : arrQuestions) {

            QuizQuestionsModel tempQuestion = new QuizQuestionsModel();
            Log.e("quiz", "------------");
            Log.e("quiz", String.valueOf(arrQuestion.get("Option1")));
            tempQuestion.setOption1(String.valueOf(arrQuestion.get("Option1")));
            tempQuestion.setOption1(String.valueOf(arrQuestion.get("Option2")));
            tempQuestion.setOption1(String.valueOf(arrQuestion.get("Option3")));
            tempQuestion.setOption1(String.valueOf(arrQuestion.get("Option4")));
            tempQuestion.setOption1(String.valueOf(arrQuestion.get("Option5")));
            tempQuestion.setOption1(String.valueOf(arrQuestion.get("Option6")));
            tempQuestion.setOption1(String.valueOf(arrQuestion.get("QuestionStatement")));
            tempQuestion.setOption1(String.valueOf(arrQuestion.get("correctOptionNumber")));
            tempQuestion.setOption1(String.valueOf(arrQuestion.get("question_category")));
            tempQuestion.setOption1(String.valueOf(arrQuestion.get("user_rating")));
            QuizDAO helper = new QuizDAO(getApplicationContext());
            helper.insertQuestionObject(tempQuestion);
            //tempQuestion.setOption1(arrQuestions[i].getOption1());

        }
        return arrQuestions;
    }


    // TODO: Populate a database, via a json file.
    void readJsonDB() {

    }

    // TODO: Create an array of objects
    void createQuestionArray() {

    }

//    // TODO: Insert the objects in a db
//    void insertArrayToDB() {
//        QuizQuestionsModel[] arr = getJsonFromFile();
//
//        for (int i = 0; i < arr.length; i++) {
//            QuizQuestionsModel tempQuestion = new QuizQuestionsModel();
//            tempQuestion.setOption1(arr[i].getOption1());
//        }
//    }

    // TODO: Read the db, create the array of objects again
    void getListOfQuestionObjects() {

    }


}