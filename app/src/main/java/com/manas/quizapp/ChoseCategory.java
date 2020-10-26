package com.manas.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ChoseCategory extends AppCompatActivity {

    private TextView mTextView;
    Button btnCat1;
    Button btnCat2;
    Button btnCat3;
    Button btnCat4;
    Button btnCat5;
    private String ChoseCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);
        setRecycler();

    }

    private void setRecycler() {
        CategoryItemModel[] myListData = new CategoryItemModel[] {
                new CategoryItemModel("Core", android.R.drawable.ic_dialog_map),
                new CategoryItemModel("Behaviour", android.R.drawable.ic_dialog_alert),
                new CategoryItemModel("Courtesy Rules", android.R.drawable.ic_dialog_info),
                new CategoryItemModel("Signage", android.R.drawable.ic_dialog_map),
                new CategoryItemModel("Parking", android.R.drawable.ic_media_pause),
                new CategoryItemModel("Theory", android.R.drawable.ic_menu_agenda),
        };

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.categoryRecyclerList);
        ListAdapter adapter = new ListAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void navigate(String choseCategory) {
        Intent i = new Intent(ChoseCategory.this, QuizLength.class);
        startActivity(i);
    }
}