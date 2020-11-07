package com.manas.quizapp.models;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
    public static final String TEXT = " text, ";
    public final static String VALUES_BASE = " ("

            + OP1 + TEXT
            + OP2 + TEXT
            + OP3 + TEXT
            + OP4 + TEXT
            + OP5 + TEXT
            + OP6 + TEXT
            + QUESTION_STATEMENT + TEXT
            + CORRECT_OPTION + TEXT
            + PICTURE_URL + TEXT
            + QUESTION_CATEGORY + TEXT
            + RATING + " INTEGER );";

    public final static String INSERT_BASE = " ("
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

        String sql = "CREATE TABLE " + TABLE + VALUES_BASE;
        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE);
        onCreate(sqLiteDatabase);
    }


    public void insertQuestionObject(ArrayList<QuizQuestionsModel> questionArr) {
        makeSureDBexists();
        SQLiteDatabase db = this.getWritableDatabase();
        String cleanSQL = "DELETE FROM " + TABLE;
        db.execSQL(cleanSQL);

        for (QuizQuestionsModel q : questionArr) {
            String insertQuery = "INSERT INTO " + TABLE + " " + INSERT_BASE +
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

            db.execSQL(insertQuery);
        }

        db.close();
    }

    private void makeSureDBexists() {
        String sql = "CREATE TABLE " + TABLE + VALUES_BASE;

        try {
            this.getWritableDatabase().execSQL(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // code to get all contacts in a list view
    public List<QuizQuestionsModel> getQuestions(String category, Integer limit) {
        List<QuizQuestionsModel> questionList = new ArrayList<>();
        // Select All Query
        category = category.toLowerCase();
        String selectQuery = "SELECT  * FROM " + TABLE +
                " WHERE " + QUESTION_CATEGORY + "=" + "\'" + category + "\'" + " LIMIT " + limit;
        Log.e("", selectQuery);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                QuizQuestionsModel tempQuestion = new QuizQuestionsModel();
                tempQuestion.setOption1(cursor.getString(0).replace("\"", ""));
                tempQuestion.setOption2(cursor.getString(1).replace("\"", ""));
                tempQuestion.setOption3(cursor.getString(2).replace("\"", ""));
                tempQuestion.setOption4(cursor.getString(3).replace("\"", ""));
                tempQuestion.setOption5(cursor.getString(4).replace("\"", ""));
                tempQuestion.setOption6(cursor.getString(5).replace("\"", ""));
                tempQuestion.setQuestionStatement(cursor.getString(6).replace("\"", ""));
                tempQuestion.setCorrectOptionNumber(cursor.getString(7).replace("\"", ""));
                tempQuestion.setPictureUrl(cursor.getString(8).replace("\"", ""));
                tempQuestion.setQuestionCategory(cursor.getString(9).replace("\"", ""));
                tempQuestion.setUserRating(Double.valueOf(String.valueOf(cursor.getString(10))));

                // Adding questions to list
                questionList.add(tempQuestion);
            } while (cursor.moveToNext());
        }

        // return contact list
        return questionList;
    }
}
