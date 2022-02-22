package com.example.tracnghiem2.Start2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tracnghiem2.Activities.activity;
import com.example.tracnghiem2.R;

public class EasyStart extends Activity {

  //  TextView minute;
  //  EasyStart.CounterClass timer;
    Button start;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        start = findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EasyStart.this, com.example.tracnghiem2.Activities.activity2.class);
                intent.putExtra("muc", "De");
                startActivity(intent);
            }
        });



/*
        minute = (TextView) findViewById(R.id.minute);
        timer = new EasyStart.CounterClass(3 * 1000, 1000);
        minute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        timer.start();

 */
    }
/*
    public class CounterClass extends CountDownTimer {


        public CounterClass(long millisInfuture, long countDownInterval) {
            super(millisInfuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinshed) {
            String countTime = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinshed), TimeUnit.MILLISECONDS.toSeconds(millisUntilFinshed));
            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinshed);
            minute.setText(countTime);

        }
        public void onFinish() {

            minute.setText("START");
            Intent intent = new Intent(EasyStart.this, activity.class);
            intent.putExtra("muc", "De");
            startActivity(intent);
            finish();

        }

 */
    }


