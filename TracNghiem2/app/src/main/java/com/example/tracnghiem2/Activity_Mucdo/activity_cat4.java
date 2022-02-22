package com.example.tracnghiem2.Activity_Mucdo;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tracnghiem2.Start.EasyStart;
import com.example.tracnghiem2.Start.HardStart;
import com.example.tracnghiem2.R;
import com.example.tracnghiem2.Start.VeryHardStart;


public class activity_cat4 extends Activity {
    Button De,Kho, ratkho,back;
    MediaPlayer SoundBackground = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);


        De = (Button)findViewById(R.id.BtnDe);
        De.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_cat4.this, com.example.tracnghiem2.Start4.EasyStart.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
            }
        });


        Kho = (Button)findViewById(R.id.BtnKho);
        Kho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity_cat4.this, com.example.tracnghiem2.Start4.HardStart.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);



                startActivity(intent);
            }
        });

        ratkho = (Button)findViewById(R.id.Btnratkho);
        ratkho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_cat4.this, com.example.tracnghiem2.Start4.VeryHardStart.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);

            }
        });
        back = (Button) findViewById(R.id.BtnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Intent intent = new Intent(activity_test2.this,TracNghiem.class);
                finish();
                overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);

            }
        });





    }
}