package com.example.tracnghiem2;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tracnghiem2.score.Score;

import java.util.ArrayList;


public class SaveScore extends AppCompatActivity {

    private SQLiteDatabase db;
    private EditText editName, editPhone;

    private ListView lvUser;
    private ArrayAdapter<Score> adapter;
    private ArrayList<Score> scoreList = new ArrayList<>();

    int idUpdate = -1;
    int HighScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_score);

        initData();
        lvUser = findViewById(R.id.lvUser);
        adapter = new ArrayAdapter<Score>(this, 0, scoreList) {

            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_list_score, null);

                TextView tvName = convertView.findViewById(R.id.tvNameStudent);
               // TextView tvMARV= convertView.findViewById(R.id.tvMSSV);
                TextView tvPhone = convertView.findViewById(R.id.tvScore);


                Score u = scoreList.get(position);
                tvName.setText(u.getName());
            //    tvMARV.setText(u.getRoom());
                tvPhone.setText(u.getScore());


                return convertView;
            }
        };
        lvUser.setAdapter(adapter);

        loadData();
    }






    private void initData() {
        db = openOrCreateDatabase("tracnghiem.db", MODE_PRIVATE, null);

        String sql = "CREATE TABLE IF NOT EXISTS tbchuno (id integer primary key autoincrement, name text, phone integer)";
        db.execSQL(sql);
    }


    private void loadData() {
        scoreList.clear();


        String sql = "SELECT * FROM tbscore";
        Cursor cursor = db.rawQuery(sql, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String phone = cursor.getString(2);
         //   String room = cursor.getString(3);


            
            Score u = new Score();
           u.setId(id);
            u.setName(name);
            u.setScore(phone);
         //   u.setRoom(room);

            scoreList.add(u);

            cursor.moveToNext();
        }

        adapter.notifyDataSetChanged();
    }

}