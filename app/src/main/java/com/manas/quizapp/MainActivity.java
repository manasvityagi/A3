package com.manas.quizapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.manas.quizapp.models.QuizDAO;
import com.manas.quizapp.models.QuizQuestionsModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "mainActivity";
    Button startQuizBtn;
    Button getMyRecord;
    Button updateQuestions;
    private final String FILE_NAME = "questions.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startQuizBtn = findViewById(R.id.start_quiz_btn);
        getMyRecord = findViewById(R.id.my_record);
        updateQuestions = findViewById(R.id.update_questions);
        //////////

        //(String username, String sessionTS, String category, Integer score, Integer quiz_length, double correct_percent) {
        startQuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateDBfromJson();
                Intent i = new Intent(MainActivity.this, ChoseCategory.class);
                startActivity(i);
            }
        });

        getMyRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent i = new Intent(MainActivity.this, PastRecord.class);
//                startActivity(i);
            }
        });

        updateQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuestionsFromCloud();


            }
        });
    }


    public void updateQuestionsFromCloud() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://static-pottery-289106.firebaseio.com/.json";
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String reply = response.toString();


                        if (reply.equals(readFromFile(nameOfLatestFile()))) {
                            //This is the read file from the firebase as well as updated for future reference
                            // We need ti pick up the latest questions file
                            // get the latest file from directory based on creation date
                            writeToFile(reply, getApplicationContext());
                            Toast.makeText(MainActivity.this, "Questions Updated" + nameOfLatestFile(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "No Updates Available", Toast.LENGTH_SHORT).show();
                        }


                        nameOfLatestFile();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //textView.setText("That didn't work!");
                Log.e("app", "That didn't work!");
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private String nameOfLatestFile() {
        Context cntxt = getApplicationContext();
        File allFiles = cntxt.getApplicationContext().getFilesDir();
        Date latest_lastModDate = new Date(0);
        String most_updated_file_name = FILE_NAME;

        for (String strFile : allFiles.list()) {
            File f = cntxt.getFileStreamPath(strFile);
            Date lastModDate_new = new Date(f.lastModified());
            if (lastModDate_new.after(latest_lastModDate)) {
                most_updated_file_name = f.getName();
            }
            Log.e("app", "most_updated_file_name : " + most_updated_file_name);
        }

        return most_updated_file_name;
    }

    private String writeToFile(String data, Context context) {

        String file_name = "questions";
        try {
            String timeStamp_post_fix = String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
            file_name = file_name + "_" + timeStamp_post_fix + ".json";
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(file_name, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();

        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }

        return file_name;
    }

    private String readFromFile(String fileName) {
        Context context = getApplicationContext();
        String ret = "";

        try {
            InputStream inputStream = context.openFileInput(fileName);

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append("\n").append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }


    JsonObject[] populateDBfromJson() {
        nameOfLatestFile();
        String jsonFileString = getJsonFromAssets(nameOfLatestFile());
        Gson gson = new Gson();
        JsonObject[] arrQuestions = gson.fromJson(jsonFileString, JsonObject[].class);

        ArrayList<QuizQuestionsModel> qArray = new ArrayList<QuizQuestionsModel>();
        ;
        QuizDAO helper = new QuizDAO(getApplicationContext());
        for (JsonObject arrQuestion : arrQuestions) {
            QuizQuestionsModel tempQuestion = new QuizQuestionsModel();

            tempQuestion.setOption1(String.valueOf(arrQuestion.get("Option1")).replace("\"", ""));
            tempQuestion.setOption2(String.valueOf(arrQuestion.get("Option2")).replace("\"", ""));
            tempQuestion.setOption3(String.valueOf(arrQuestion.get("Option3")).replace("\"", ""));
            tempQuestion.setOption4(String.valueOf(arrQuestion.get("Option4")).replace("\"", ""));
            tempQuestion.setOption5(String.valueOf(arrQuestion.get("Option5")).replace("\"", ""));
            tempQuestion.setOption6(String.valueOf(arrQuestion.get("Option6")).replace("\"", ""));
            tempQuestion.setQuestionStatement(String.valueOf(arrQuestion.get("QuestionStatement")).replace("\"", ""));
            tempQuestion.setCorrectOptionNumber(String.valueOf(arrQuestion.get("correctOptionNumber")).replace("\"", ""));
            tempQuestion.setPictureUrl(String.valueOf(arrQuestion.get("picture_url")).replace("\"", ""));
            tempQuestion.setQuestionCategory(String.valueOf(arrQuestion.get("question_category")).replace("\"", ""));
            tempQuestion.setUserRating(Double.valueOf(String.valueOf(arrQuestion.get("user_rating"))));
            qArray.add(tempQuestion);
        }

        helper.insertQuestionObject(qArray);
        return arrQuestions;
    }

    public static boolean isJson(String Json) {
        Gson gson = new Gson();
        try {
            gson.fromJson(Json, Object.class);
            Object jsonObjType = gson.fromJson(Json, Object.class).getClass();
            if (jsonObjType.equals(String.class)) {
                return false;
            }
            return true;
        } catch (com.google.gson.JsonSyntaxException ex) {
            return false;
        }
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