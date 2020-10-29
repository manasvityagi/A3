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
import java.util.ArrayList;
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
                populateDBfromJson();
                Intent i = new Intent(MainActivity.this, ChoseCategory.class);
                startActivity(i);
            }
        });
    }

    JsonObject[] populateDBfromJson() {
        String jsonFileString = getJsonFromAssets("questions.json");
        Gson gson = new Gson();
        JsonObject[] arrQuestions = gson.fromJson(jsonFileString, JsonObject[].class);

        ArrayList<QuizQuestionsModel> qArray =  new ArrayList<QuizQuestionsModel>();;
        QuizDAO helper = new QuizDAO(getApplicationContext());
        for (JsonObject arrQuestion : arrQuestions) {
            QuizQuestionsModel tempQuestion = new QuizQuestionsModel();

            tempQuestion.setOption1(String.valueOf(arrQuestion.get("Option1")).replace("\"",""));
            tempQuestion.setOption2(String.valueOf(arrQuestion.get("Option2")).replace("\"",""));
            tempQuestion.setOption3(String.valueOf(arrQuestion.get("Option3")).replace("\"",""));
            tempQuestion.setOption4(String.valueOf(arrQuestion.get("Option4")).replace("\"",""));
            tempQuestion.setOption5(String.valueOf(arrQuestion.get("Option5")).replace("\"",""));
            tempQuestion.setOption6(String.valueOf(arrQuestion.get("Option6")).replace("\"",""));
            tempQuestion.setQuestionStatement(String.valueOf(arrQuestion.get("QuestionStatement")).replace("\"",""));
            tempQuestion.setCorrectOptionNumber(String.valueOf(arrQuestion.get("correctOptionNumber")).replace("\"",""));
            tempQuestion.setPictureUrl(String.valueOf(arrQuestion.get("picture_url")).replace("\"",""));
            tempQuestion.setQuestionCategory(String.valueOf(arrQuestion.get("question_category")).replace("\"",""));
            tempQuestion.setUserRating(Double.valueOf(String.valueOf(arrQuestion.get("user_rating"))));
            qArray.add(tempQuestion);
        }

        helper.insertQuestionObject(qArray);
        return arrQuestions;
    }

    String getJsonFromCloud() {
        return " ";
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


}