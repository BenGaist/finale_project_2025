package com.example.finale_project_2025;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import com.example.finale_project_2025.Result;

import java.util.ArrayList;


public class GameResultDatabaseHelper extends SQLiteOpenHelper {


    public static final String TABLE_NAME = "results";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_SCORE = "score";
    public static final String COLUMN_MOTIVATION = "motivation";

    public GameResultDatabaseHelper(Context context) {

        super(context, "results.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USERNAME + " TEXT, "
                + COLUMN_SCORE + " INTEGER, "
                + COLUMN_MOTIVATION + " TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertResult(String username, int score, String motivation) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("score", score);
        values.put("motivation", motivation);

        db.insert("results", null, values);
        db.close();
    }


    public ArrayList<Result> getAllResults() {
        ArrayList<Result> resultList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM results", null);

        if (cursor.moveToFirst()) {
            do {
                String username = cursor.getString(cursor.getColumnIndexOrThrow("username"));
                int score = cursor.getInt(cursor.getColumnIndexOrThrow("score"));
                String motivation = cursor.getString(cursor.getColumnIndexOrThrow("motivation"));

                resultList.add(new Result(username, score, motivation));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return resultList;
    }
}
