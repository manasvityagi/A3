package com.manas.quizapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class QuizDAO extends SQLiteOpenHelper{

    public final static String DATAFILE_NAME = "quiz.db";

    public final static String TABLE = "quiz";
    public final static String QUESTION_STATEMENT ="QUESTION_STATEMENT";
    public final static String OP1="OPTION1";
    public final static String OP2="OPTION2";
    public final static String OP3="OPTION3";
    public final static String OP4="OPTION4";
    public final static String OP5="OPTION5";
    public final static String OP6="OPTION6";

    public final static String correctOptionNumber="correctOptionNumber";

    public QuizDAO( Context context) {
        super(context, DATAFILE_NAME, null, 1);
    }
//    CREATE TABLE "quiz" (
//            "QuestionStatement"	TEXT,
//            "Option1"	TEXT,
//            "Option2"	TEXT,
//            "Option3"	TEXT,
//            "Option4"	TEXT,
//            "Option5"	TEXT,
//            "Option6"	TEXT,
//            "isPicture"	INTEGER,
//            "correctOptionNumber"	TEXT
//)

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "CREATE TABLE " +TABLE +" ("+QUESTION_STATEMENT+" TEXT, "
                +OP1+" text, "
                +OP2+" text, "
                +OP3+" text, "
                +OP4+" text, "
                +OP5+" text, "
                +OP6+" text, "
                +"isPicture INTEGER, "
                +correctOptionNumber+" text);";

        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+TABLE);
        onCreate(sqLiteDatabase);
    }
}
