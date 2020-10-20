package com.manas.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ChoseCategory extends AppCompatActivity {

    private TextView mTextView;
    Button btnCat1;
    Button btnCat2;
    Button btnCat3;
    Button btnCat4;
    Button btnCat5;
    Button btnCat6;
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
        btnCat6 = findViewById(R.id.cat_6);

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


        btnCat6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChoseCategory = "6";
                navigate(ChoseCategory);
            }
        });

    }

    private void navigate(String choseCategory) {
        Intent i = new Intent(ChoseCategory.this, QuizLength.class);
        startActivity(i);
    }
}