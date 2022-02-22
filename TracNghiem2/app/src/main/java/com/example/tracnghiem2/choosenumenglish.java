package com.example.tracnghiem2;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.example.tracnghiem2.Activity_Mucdo.activity_test2;

public class choosenumenglish extends Activity {
    public static int cauhoi;
    EditText socauhoi;
    Button bt;
    Button tieptheo;


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chosenumber);
        bt = (Button) findViewById(R.id.BtnBack);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        socauhoi = (EditText) findViewById(R.id.Edtsocau);
        tieptheo=(Button)findViewById(R.id.BtnNext);
        socauhoi.addTextChangedListener(numberTextWathcer);
        tieptheo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cauhoi = Integer.parseInt(socauhoi.getText().toString());

                if (cauhoi < 5 || cauhoi >10 ) {
                    socauhoi.setError("Vui lòng nhập từ 5 đến 10");
                    socauhoi.requestFocus();
                    return;
                }
                if (cauhoi == 5){
                    Intent intent = new Intent(choosenumenglish.this, activity_test2.class);
                    startActivity(intent);
                    finish();
                }
                if (cauhoi == 6){

                    Intent intent = new Intent(choosenumenglish.this,  com.example.tracnghiem2.Activity_Mucdo.activity_cat1.class);
                    startActivity(intent);
                    finish();
                }
                if (cauhoi == 7){
                    Intent intent = new Intent(choosenumenglish.this, com.example.tracnghiem2.Activity_Mucdo.activity_cat2.class);
                    startActivity(intent);
                    finish();
                }
                if (cauhoi == 8){
                    Intent intent = new Intent(choosenumenglish.this, com.example.tracnghiem2.Activity_Mucdo.activity_cat3.class);
                    startActivity(intent);
                    finish();
                }
                if (cauhoi == 9){
                    Intent intent = new Intent(choosenumenglish.this, com.example.tracnghiem2.Activity_Mucdo.activity_cat4.class);
                    startActivity(intent);
                    finish();
                }
                if (cauhoi == 10){
                    Intent intent = new Intent(choosenumenglish.this, com.example.tracnghiem2.Activity_Mucdo.activity_cat5.class);
                    startActivity(intent);
                    finish();
                }
            }
        });


    }
    private TextWatcher numberTextWathcer = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String socauhoiInput = socauhoi.getText().toString().trim();
            tieptheo.setEnabled(!socauhoiInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}