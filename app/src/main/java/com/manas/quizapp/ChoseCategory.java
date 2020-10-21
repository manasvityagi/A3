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

        //Multi,div,add, sub
        btnCat1 = findViewById(R.id.cat_1);
        btnCat2 = findViewById(R.id.cat_2);
        btnCat3 = findViewById(R.id.cat_3);
        btnCat4 = findViewById(R.id.cat_4);
        btnCat5 = findViewById(R.id.cat_5);

        btnCat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChoseCategory = "mixed";
                navigate(ChoseCategory);
            }
        });

        btnCat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChoseCategory = "2";
                navigate(ChoseCategory);
            }
        });

        btnCat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChoseCategory = "3";
                navigate(ChoseCategory);
            }
        });

        btnCat4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChoseCategory = "4";
                navigate(ChoseCategory);
            }
        });

        btnCat5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChoseCategory = "5";
                navigate(ChoseCategory);
            }
        });

        setRecycler();

    }

    private void setRecycler() {
        CategoryItemModel[] myListData = new CategoryItemModel[] {
                new CategoryItemModel("Email Address", android.R.drawable.ic_dialog_email),
                new CategoryItemModel("Info", android.R.drawable.ic_dialog_info),
                new CategoryItemModel("Delete", android.R.drawable.ic_delete),
                new CategoryItemModel("Dialer", android.R.drawable.ic_dialog_dialer),
                new CategoryItemModel("Alert", android.R.drawable.ic_dialog_alert),
                new CategoryItemModel("Map", android.R.drawable.ic_dialog_map),
                new CategoryItemModel("Email", android.R.drawable.ic_dialog_email),
                new CategoryItemModel("Info", android.R.drawable.ic_dialog_info),
                new CategoryItemModel("Delete", android.R.drawable.ic_delete),
                new CategoryItemModel("Dialer", android.R.drawable.ic_dialog_dialer),
                new CategoryItemModel("Alert", android.R.drawable.ic_dialog_alert),
                new CategoryItemModel("Map", android.R.drawable.ic_dialog_map),
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