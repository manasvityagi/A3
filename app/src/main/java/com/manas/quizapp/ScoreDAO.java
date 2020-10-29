package com.manas.quizapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class ScoreDAO extends SQLiteOpenHelper {

    public final static String DATAFILE_NAME = "quiz.db";
    public final static String TABLE = "score_table";

    public void ScoreDAO() {

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

//        CREATE TABLE `score` (
//	`session_ts` DATE DEFAULT '',
//	`category` VARCHAR DEFAULT '',
//	`quiz_length` INT,
//	`score` INT,
//	`username` VARCHAR,
//	`correct_percent` DOUBLE
//);
        String sql = "CREATE TABLE `score` (\r\n" +
                "	`session_ts` DATE DEFAULT '',\r\n" +
                "	`category` VARCHAR DEFAULT '',\r\n" +
                "	`quiz_length` INT,\r\n" +
                "	`score` INT,\r\n" +
                "	`username` VARCHAR,\r\n" +
                "	`correct_percent` DOUBLE\r\n" +
                ");\r\n";

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

            //Log.e("app", insertQuery);
            db.execSQL(insertQuery);
        }

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
