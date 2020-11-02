package com.manas.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.manas.quizapp.models.ScoreDAO;
import com.manas.quizapp.models.ScoreRecordModel;

import java.util.List;

public class PastRecord extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_record);

        ScoreDAO score = new ScoreDAO(getApplicationContext());
        score.createScoreTable();
        score.cleanDB();
        List<ScoreRecordModel> scoreObjectsArrayList = score.getScore();


    }
}