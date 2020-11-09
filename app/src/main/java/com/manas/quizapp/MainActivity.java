package com.manas.quizapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.manas.quizapp.models.QuizDAO;
import com.manas.quizapp.models.QuizQuestionsModel;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button startQuizBtn;
    Button getMyRecord;
    Button updateQuestions;
    private  final String FIREBASE_URL = getResources().getString(R.string.firebase_database_url);
    private  final String QUESTION_LOCAL_FILE_NAME = getResources().getString(R.string.question_file_name);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startQuizBtn = findViewById(R.id.start_quiz_btn);
        getMyRecord = findViewById(R.id.my_record);
        updateQuestions = findViewById(R.id.update_questions);

        makeSureQuestionFileIsOK();

        startQuizBtn.setOnClickListener(v -> {
            // In runtime the app works on sqlite database
            populateDBfromJson();
            Intent i = new Intent(MainActivity.this, ChoseCategory.class);
            startActivity(i);
        });

        getMyRecord.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, PastRecord.class);
            startActivity(i);
        });

        updateQuestions.setOnClickListener(v -> {
            //pulls the latest question list and updates the local file
            //Updates only if there is an updated, otherwise does not overwrites the file
            updateQuestionsFromCloud();
        });
    }

    private void makeSureQuestionFileIsOK() {
        //check if file exists in context
        if (!doesFileExistsInContext()) {
            //copy file from assets to context
            String contentFromAssets = readQuestionFromAssets();
            writeFileToContext(contentFromAssets);
            Toast.makeText(MainActivity.this, "Copied", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean doesFileExistsInContext() {
        String[] fileList = getApplicationContext().fileList();
        FileInputStream fis = null;
        try {
            fis = getApplicationContext().openFileInput(QUESTION_LOCAL_FILE_NAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void updateQuestionsFromCloud() {
        //1. Make sure file exists in Assets
        //2. Make sure file exists in Context
        //3. If file does not exists in context, copy it from assets to context.
        //4. Get firebase response, if response is different from local file in context, update the local context

        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, FIREBASE_URL,
                response -> {
                    response = response.replace("\n", "").replace("\r", "");
                    String fileContent = readQuestionFromContext()
                            .replace("\n", "")
                            .replace("\r", "")
                            .replace("\t", "");

                    if (response.equals(fileContent)) {
                        Toast.makeText(MainActivity.this, "No Updates Available", Toast.LENGTH_SHORT).show();
                    } else {
                        writeFileToContext(response);
                        Toast.makeText(MainActivity.this, "Questions Updated", Toast.LENGTH_SHORT).show();
                    }
                }, error -> Log.e("app", "That didn't work!"));
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private void writeFileToContext(String data) {
        Context context = getApplicationContext();

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(QUESTION_LOCAL_FILE_NAME, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private String readQuestionFromAssets() {
        String tContents = "";

        try {
            InputStream stream = getAssets().open(QUESTION_LOCAL_FILE_NAME);
            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            tContents = new String(buffer);
        } catch (IOException e) {
            // Handle exceptions here
        }
        return tContents;
    }


    private String readQuestionFromContext() {

        String tContents;

        FileInputStream fis = null;
        try {
            fis = getApplicationContext().openFileInput(QUESTION_LOCAL_FILE_NAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader inputStreamReader =
                new InputStreamReader(fis, StandardCharsets.UTF_8);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append('\n');
                line = reader.readLine();
            }
        } catch (IOException e) {
            // Error occurred when opening raw file for reading.
        } finally {
            String contents = stringBuilder.toString();
            tContents = contents;
        }
        return tContents;
    }


    public void populateDBfromJson() {

        String jsonFileString = readQuestionFromContext();
        Gson gson = new Gson();
        JsonObject[] arrQuestions = gson.fromJson(jsonFileString, JsonObject[].class);

        ArrayList<QuizQuestionsModel> qArray = new ArrayList<>();

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
        helper.close();
    }

//    public static boolean isJson(String Json) {
//        Gson gson = new Gson();
//        try {
//            gson.fromJson(Json, Object.class);
//            Object jsonObjType = gson.fromJson(Json, Object.class).getClass();
//            return !jsonObjType.equals(String.class);
//        } catch (com.google.gson.JsonSyntaxException ex) {
//            return false;
//        }
//    }

//    private String nameOfLatestFile() {
//        Context cntxt = getApplicationContext();
//        File allFiles = cntxt.getApplicationContext().getFilesDir();
//        try {
//            cntxt.getApplicationContext().getAssets().open(QUESTION_LOCAL_FILE_NAME);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Date latestLastModDate = new Date(0);
//        String mostUpdatedFileName = "questions.json";
//
//        for (String strFile : allFiles.list()) {
//            File f = cntxt.getFileStreamPath(strFile);
//            Date lastModDateNew = new Date(f.lastModified());
//            if (lastModDateNew.after(latestLastModDate)) {
//                mostUpdatedFileName = f.getName();
//            }
//            Log.e("app", "most_updated_file_name : " + mostUpdatedFileName);
//        }
//
//        //return mostUpdatedFileName;
//        return "questions.json";
//    }


}