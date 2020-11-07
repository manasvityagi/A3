package com.manas.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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

        //String username, String sessionTS, String category, Integer score, Integer quiz_length, double correct_percent
        ScoreRecordModel obj = new ScoreRecordModel("usr", "10.4", "cat", 80, 10, 89.9);
        ScoreRecordModel obj1 = new ScoreRecordModel("usr", "10.4", "cat", 80, 10, 89.9);
        ScoreRecordModel obj2 = new ScoreRecordModel("usr", "10.4", "cat", 80, 10, 89.9);
        ScoreRecordModel obj3 = new ScoreRecordModel("usr", "10.4", "cat", 80, 10, 89.9);
        ScoreRecordModel obj4 = new ScoreRecordModel("usr", "10.4", "cat", 80, 10, 89.9);
        ScoreRecordModel obj5 = new ScoreRecordModel("usr", "10.4", "cat", 80, 10, 89.9);

        score.insertScoreObject(obj);
        score.insertScoreObject(obj1);
        score.insertScoreObject(obj2);
        score.insertScoreObject(obj3);
        score.insertScoreObject(obj4);
        score.insertScoreObject(obj5);

        List<ScoreRecordModel> scoreList = score.getScore();

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        PastRecordAdapter adapter = new PastRecordAdapter(scoreList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }
}
