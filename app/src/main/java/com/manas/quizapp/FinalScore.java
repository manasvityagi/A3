package com.manas.quizapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.manas.quizapp.models.ScoreDAO;
import com.manas.quizapp.models.ScoreRecordModel;
import java.util.Calendar;
import java.util.Date;

public class FinalScore extends AppCompatActivity {
    TextView scoreField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_score);
        scoreField = findViewById(R.id.finalScore);
        Log.e("app", String.valueOf(scoreField));
        String score = getIntent().getStringExtra("score");
        scoreField.setText(score);
    }

    private void insertScore(String scoreObtained) {
        ScoreDAO scoreDB = new ScoreDAO(getApplicationContext());
        //scoreDB.createScoreTable();
        // score.cleanDB();
        Date currentTime = Calendar.getInstance().getTime();
        String timeStamp = currentTime.toString();
        String category = getIntent().getStringExtra("category");
        Integer score = Integer.valueOf(scoreObtained);
        Integer quizLength = 10;
        double correctPercent = (score / quizLength) * 10;
        //String username, String sessionTS, String category, Integer score, Integer quiz_length, double correct_percent
        ScoreRecordModel obj = new ScoreRecordModel("usr", timeStamp,category , score, quizLength, correctPercent);
        scoreDB.insertScoreObject(obj);

    }
}