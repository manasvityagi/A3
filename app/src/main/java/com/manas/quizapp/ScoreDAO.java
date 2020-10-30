package com.manas.quizapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class ScoreDAO extends SQLiteOpenHelper {

    public final static String DATAFILE_NAME = "quiz.db";
    public final static String TABLE = "score_table";
//    public final static String insert_base = " ("
//            + OP1 + " , "
//            + OP2 + " , "
//            + OP3 + " , "
//            + OP4 + " , "
//            + QUESTION_STATEMENT + " , "
//            + CORRECT_OPTION + " , "
//            + PICTURE_URL + " , "
//            + QUESTION_CATEGORY + " , "
//            + RATING + ") ";

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
       // VALUES (123321, 'courtesy', 10, 80, 'manas', 80.0)
        +" VALUES(\"TSX\", \"CTX\", \"LEN\", \"SRX\", \"USX\", \"CRKT\");";

            insertQuery = insertQuery.replace("TSX", scoreObj.getSessionTS());
            insertQuery = insertQuery.replace("CTX", scoreObj.getCategory());
            insertQuery = insertQuery.replace("LEN", String.valueOf(scoreObj.getQuiz_length()));
            insertQuery = insertQuery.replace("SRX",  String.valueOf(scoreObj.getCorrect_percent()));
            insertQuery = insertQuery.replace("USX",  scoreObj.getUsername());
            insertQuery = insertQuery.replace("CRKT",  scoreObj.getUsername());

            Log.e("app", insertQuery);
            //db.execSQL(insertQuery);


        db.close();
    }


    // code to get all contacts in a list view
    public List<ScoreRecordModel> getScore(Integer limit) {
        List<ScoreRecordModel> scoreList = new ArrayList<ScoreRecordModel>();
        // Select All Query

        String selectQuery = "SELECT  * FROM " + TABLE + " LIMIT " + limit;
        Log.e("", selectQuery);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

//                Log.e("app",cursor.getString(0).replace("\"", ""));
//                Log.e("app",cursor.getString(1).replace("\"", ""));
//                Log.e("app",cursor.getString(2).replace("\"", ""));
//                Log.e("app",cursor.getString(3).replace("\"", ""));
//                Log.e("app",cursor.getString(4).replace("\"", ""));

                ScoreRecordModel scoreRow = new ScoreRecordModel();
                scoreRow.setSessionTS(cursor.getString(0).replace("\"", ""));
                scoreRow.setCategory(cursor.getString(1).replace("\"", ""));
                scoreRow.setQuiz_length(Integer.valueOf(cursor.getString(2).replace("\"", "")));
                scoreRow.setScore(Integer.valueOf(cursor.getString(3).replace("\"", "")));
                scoreRow.setUsername(cursor.getString(4).replace("\"", ""));
                scoreRow.setCorrect_percent(Double.valueOf(cursor.getString(5).replace("\"", "")));

                // Adding score to list
                scoreList.add(scoreRow);
            } while (cursor.moveToNext());
        }

        return scoreList;
    }
}
