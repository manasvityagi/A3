package com.manas.quizapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class QuizDAO extends SQLiteOpenHelper {

    public final static String DATAFILE_NAME = "quiz.db";

    public final static String TABLE = "quiz";
    public final static String QUESTION_STATEMENT = "QUESTION_STATEMENT";
    public final static String OP1 = "OPTION1";
    public final static String OP2 = "OPTION2";
    public final static String OP3 = "OPTION3";
    public final static String OP4 = "OPTION4";
    public final static String OP5 = "OPTION5";
    public final static String OP6 = "OPTION6";

    public final static String correctOptionNumber = "correctOptionNumber";


    public QuizDAO(Context context) {
        super(context, DATAFILE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.e("app", "Hello World db");
        String sql = "CREATE TABLE " + TABLE + " (" + QUESTION_STATEMENT + " TEXT, "
                + OP1 + " text, "
                + OP2 + " text, "
                + OP3 + " text, "
                + OP4 + " text, "
                + OP5 + " text, "
                + OP6 + " text, "
                + "isPicture INTEGER, "
                + correctOptionNumber + " text);";

        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE);
        onCreate(sqLiteDatabase);
    }


    public void populateRandomQuestions() {

        SQLiteDatabase db = this.getWritableDatabase();
        for (int i = 1; i <= 10; i++) {
            String insertQuery = "INSERT INTO quiz (QUESTION_STATEMENT, OPTION1, OPTION2, OPTION3, OPTION4, OPTION5, OPTION6, isPicture, correctOptionNumber)\n" +
                    "VALUES (\"Question XXX\", \"Option 1\", \"Option 2\", \"Option 3\", \"Option 4\", \"Option 5\", \"Option 6\", 1, \"4\");";

            insertQuery = insertQuery.replace("XXX", String.valueOf(i));
            db.execSQL(insertQuery);
            Log.e("app",insertQuery);
        }
        db.close();
    }

    // code to get all contacts in a list view
    public List<QuizQuestionsModel> getQuestions(String category, Integer limit) {
        List<QuizQuestionsModel> questionList = new ArrayList<QuizQuestionsModel>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                QuizQuestionsModel questions = new QuizQuestionsModel();
                questions.setQuestionStatement(cursor.getString(0));
                questions.setOption1(cursor.getString(1));
                questions.setOption2(cursor.getString(2));
                questions.setOption3(cursor.getString(3));
                questions.setOption4(cursor.getString(4));
                questions.setOption5(cursor.getString(5));
                questions.setOption6(cursor.getString(6));
                //questions.setIsPicture(Integer.parseInt(cursor.getString(7)));
                questions.setCorrectOptionNumber(cursor.getString(8));

                // Adding questions to list
                questionList.add(questions);
            } while (cursor.moveToNext());
        }

        // return contact list
        return questionList;
    }
}
