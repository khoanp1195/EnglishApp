package com.example.tracnghiem2.Start4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tracnghiem2.Activities.activity;
import com.example.tracnghiem2.R;

public class HardStart extends Activity {

    //  TextView minute;
    //  EasyStart.CounterClass timer;
    Button start;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start1);

        start = findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HardStart.this, com.example.tracnghiem2.Activities.activity4.class);
                intent.putExtra("muc", "Kho");
                startActivity(intent);
            }
        });
    }
}
