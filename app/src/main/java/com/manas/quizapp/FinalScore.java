package com.manas.quizapp;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.manas.quizapp.models.ScoreDAO;
import com.manas.quizapp.models.ScoreRecordModel;

import java.util.Calendar;
import java.util.Date;

public class FinalScore extends AppCompatActivity implements SensorEventListener {
    TextView scoreField;
    TextView cpuTempratureField;
    Button navigate_home;
    private SensorManager sensorManager;
    private Sensor tempSensor;
    private boolean isSensorAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_score);
        scoreField = findViewById(R.id.finalScore);
        cpuTempratureField = findViewById(R.id.cpuTemprature);

        Log.e("app", String.valueOf(scoreField));
        String score = getIntent().getStringExtra("score");
        scoreField.setText(score);
        get_cpu_temprature();
        insertScore(score);

        navigate_home = findViewById(R.id.home_button);
        navigate_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FinalScore.this, MainActivity.class);
                startActivity(i);
            }
        });


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
        ScoreRecordModel obj = new ScoreRecordModel("usr", timeStamp, category, score, quizLength, correctPercent);
        scoreDB.insertScoreObject(obj);

    }


    private void get_cpu_temprature() {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null) {
            tempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            isSensorAvailable = true;
        } else {
            cpuTempratureField.setText("Temprature Sensor not available");
            isSensorAvailable = false;
        }

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        cpuTempratureField.setText(event.values[0]+" °C");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this,tempSensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(isSensorAvailable){
            sensorManager.unregisterListener(this);
        }
    }
}