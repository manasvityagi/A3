package com.manas.quizapp.models;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class ScoreDAO extends SQLiteOpenHelper {

    public final static String DATAFILE_NAME = "quiz.db";
    public final static String TABLE = "score";

    public ScoreDAO(Context context) {
        super(context, DATAFILE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "CREATE TABLE `score` (`session_ts` DATE, `category` VARCHAR, " +
                "`quiz_length` INT, `score` INT, `username` VARCHAR, `correct_percent` DOUBLE)";
        Log.e("app", sql);

        sqLiteDatabase.execSQL(sql);

    }

    public void createScoreTable() {


        String sql = "CREATE TABLE `score` (`session_ts` DATE, `category` VARCHAR, " +
                "`quiz_length` INT, `score` INT, `username` VARCHAR, `correct_percent` DOUBLE)";
        Log.e("app", sql);

        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.execSQL(sql);
        } catch (Exception e) {
            Log.e("fatal", "db create erroro it exists already");
        }
        db.close();

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE);
        onCreate(sqLiteDatabase);
    }


    public void insertScoreObject(ScoreRecordModel scoreObj) {
        SQLiteDatabase db = this.getWritableDatabase();
        String insertQuery = "INSERT INTO score (session_ts, category, quiz_length, score, username, correct_percent) "
                + " VALUES(\"TSX\", \"CTX\", \"LEN\", \"SRX\", \"USX\", \"CRKT\");";

        insertQuery = insertQuery.replace("TSX", scoreObj.getSessionTS());
        insertQuery = insertQuery.replace("CTX", scoreObj.getCategory());
        insertQuery = insertQuery.replace("LEN", String.valueOf(scoreObj.getQuizLength()));
        insertQuery = insertQuery.replace("SRX", String.valueOf(scoreObj.getCorrectPercent()));
        insertQuery = insertQuery.replace("USX", scoreObj.getUsername());
        insertQuery = insertQuery.replace("CRKT", String.valueOf(scoreObj.getCorrectPercent()));

        Log.e("app", insertQuery);
        db.execSQL(insertQuery);

        db.close();
    }


    // code to get all contacts in a list view
    public List<ScoreRecordModel> getScore() {
        List<ScoreRecordModel> scoreList = new ArrayList<ScoreRecordModel>();
        // Select All Query

        String selectQuery = "SELECT  * FROM " + TABLE;
        Log.e("app", selectQuery);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ScoreRecordModel scoreRow = new ScoreRecordModel();

                scoreRow.setSessionTS(cursor.getString(0).replace("\"", ""));
                scoreRow.setCategory(cursor.getString(1).replace("\"", ""));
                scoreRow.setQuizLength(Integer.valueOf(cursor.getString(2).replace("\"", "")));
                scoreRow.setCorrectPercent(Double.valueOf(cursor.getString(3).replace("\"", "")));
                scoreRow.setUsername(cursor.getString(4).replace("\"", ""));
                scoreRow.setCorrectPercent(Double.valueOf(cursor.getString(5).replace("\"", "")));

                // Adding score to list
                scoreList.add(scoreRow);
            } while (cursor.moveToNext());
        }

        return scoreList;
    }

    public void cleanDB(){
        SQLiteDatabase db = this.getWritableDatabase();
        String cleanSQL = "DELETE FROM " + TABLE;

        try {
            db.execSQL(cleanSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
