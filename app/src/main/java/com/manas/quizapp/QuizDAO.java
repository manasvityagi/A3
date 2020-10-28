package com.manas.quizapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;


public class QuizDAO extends SQLiteOpenHelper {

    public final static String DATAFILE_NAME = "quiz.db";
    public final static String TABLE = "quiz_questions";

    public final static String OP1 = "option1";
    public final static String OP2 = "option2";
    public final static String OP3 = "option3";
    public final static String OP4 = "option4";
    public final static String OP5 = "option5";
    public final static String OP6 = "option6";
    public final static String QUESTION_STATEMENT = "questionStatement";
    public final static String CORRECT_OPTION = "correctOptionNumber";
    public final static String PICTURE_URL = "pictureUrl";
    public final static String QUESTION_CATEGORY = "questionCategory";
    public final static String RATING = "rating";
    public final static String values_base = " ("

            + OP1 + " text, "
            + OP2 + " text, "
            + OP3 + " text, "
            + OP4 + " text, "
            + OP5 + " text, "
            + OP6 + " text, "
            + QUESTION_STATEMENT + " TEXT, "
            + CORRECT_OPTION + " TEXT, "
            + PICTURE_URL + " text, "
            + QUESTION_CATEGORY + " text, "
            + RATING + " INTEGER );";

    public final static String insert_base = " ("
            + OP1 + " , "
            + OP2 + " , "
            + OP3 + " , "
            + OP4 + " , "
            + OP5 + " , "
            + OP6 + " , "
            + QUESTION_STATEMENT + " , "
            + CORRECT_OPTION + " , "
            + PICTURE_URL + " , "
            + QUESTION_CATEGORY + " , "
            + RATING + ") ";


    public QuizDAO(Context context) {
        super(context, DATAFILE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.e("app", "Created Quiz DB");
        String sql = "CREATE TABLE " + TABLE + values_base;
        Log.e("sql", sql);
        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE);
        onCreate(sqLiteDatabase);
    }


    public void insertQuestionObject(ArrayList<QuizQuestionsModel> questionArr) {
        SQLiteDatabase db = this.getWritableDatabase();
        String cleanSQL = "DELETE FROM " + TABLE;
        db.execSQL(cleanSQL);

        for (QuizQuestionsModel q : questionArr) {
            String insertQuery = "INSERT INTO " + TABLE + " " + insert_base +
                    " VALUES(\"OPX1\", \"OPX2\", \"OPX3\", \"OPX4\", \"OPX5\", \"OPX6\", \"QSX\", \"CONX\", \"URL\", \"QCX\", 4);";

            insertQuery = insertQuery.replace("OPX1", q.getOption1());
            insertQuery = insertQuery.replace("OPX2", q.getOption2());
            insertQuery = insertQuery.replace("OPX3", q.getOption3());
            insertQuery = insertQuery.replace("OPX4", q.getOption4());
            insertQuery = insertQuery.replace("OPX5", q.getOption5());
            insertQuery = insertQuery.replace("OPX6", q.getOption6());
            insertQuery = insertQuery.replace("QSX", q.getQuestionStatement());
            insertQuery = insertQuery.replace("CONX", q.getCorrectOptionNumber());
            insertQuery = insertQuery.replace("URL", q.getPictureUrl());
            insertQuery = insertQuery.replace("QCX", q.getQuestionCategory());
            Log.e("app", insertQuery);
            db.execSQL(insertQuery);
        }

        db.close();
    }


    // code to get all contacts in a list view
    public List<QuizQuestionsModel> getQuestions(String category, Integer limit) {
        List<QuizQuestionsModel> questionList = new ArrayList<QuizQuestionsModel>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE + " LIMIT " + String.valueOf(limit) +
                " WHERE " + QUESTION_CATEGORY + "=" + category;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                QuizQuestionsModel tempQuestion = new QuizQuestionsModel();
                tempQuestion.setOption1(cursor.getString(1).replace("\"", ""));
                tempQuestion.setOption2(cursor.getString(2).replace("\"", ""));
                tempQuestion.setOption3(cursor.getString(3).replace("\"", ""));
                tempQuestion.setOption4(cursor.getString(4).replace("\"", ""));
                tempQuestion.setOption5(cursor.getString(5).replace("\"", ""));
                tempQuestion.setOption6(cursor.getString(6).replace("\"", ""));
                tempQuestion.setQuestionStatement(cursor.getString(7).replace("\"", ""));
                tempQuestion.setCorrectOptionNumber(cursor.getString(8).replace("\"", ""));
                tempQuestion.setPictureUrl(cursor.getString(9).replace("\"", ""));
                tempQuestion.setQuestionCategory(cursor.getString(10).replace("\"", ""));
                tempQuestion.setUserRating(Double.valueOf(String.valueOf(cursor.getString(11))));

                // Adding questions to list
                questionList.add(tempQuestion);
            } while (cursor.moveToNext());
        }

        // return contact list
        return questionList;
    }
}
