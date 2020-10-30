package com.manas.quizapp;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.manas.quizapp.databinding.ActivityPastRecordBinding;
import com.manas.quizapp.models.ScoreDAO;
import com.manas.quizapp.models.ScoreRecordModel;

import java.util.List;

public class PastRecord extends AppCompatActivity {

    private ActivityPastRecordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScoreDAO score = new ScoreDAO(getApplicationContext());
        score.createScoreTable();
        score.cleanDB();
        List<ScoreRecordModel> scoreObjectsArrayList = score.getScore();

        binding = ActivityPastRecordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_past_record);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

}