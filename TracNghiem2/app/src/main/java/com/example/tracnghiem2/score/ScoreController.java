package com.example.tracnghiem2.score;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import DBHELPER.DatabaseHelper;

public class ScoreController {
    private DatabaseHelper dbHelper;

    public ScoreController(Context context) {
        dbHelper= new DatabaseHelper(context);
    }

    public void insertScore(String name, String score, String room){
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put("name", name);
        values.put("score", score);
        values.put("room", room);
        db.insert("tbscore", null, values);
        db.close();
    }
    //Lấy danh sách điểm
    public Cursor getScore() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("tbscore", //ten bang
                null, //danh sach cot can lay
                null, //dieu kien where
                null, //doi so dieu kien where
                null, //bieu thuc groupby
                null, //bieu thuc having
                "_id DESC", //bieu thuc orderby
                null
        );
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
}
