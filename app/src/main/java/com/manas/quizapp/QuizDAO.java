package com.manas.quizapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class QuizDAO extends SQLiteOpenHelper{

    public final static String DATAFILE_NAME = "quiz.db";

    public final static String TABLE = "CLIENT";
    public final static String CID ="CID";
    public final static String FIRSTNAME="FIRSTNAME";
    public final static String LASTNAME="LASTNAME";
    public final static String NOTES="NOTES";

    public QuizDAO( Context context) {
        super(context, DATAFILE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "create table " +TABLE +" ("+CID+" integer primary key, "+FIRSTNAME+" text, "+LASTNAME+" text, "+NOTES+" text);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+TABLE);
        onCreate(sqLiteDatabase);
    }
}
