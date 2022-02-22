package com.example.tracnghiem2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.internal.service.Common;


public class activity_Sanh extends AppCompatActivity {
    Button Signin,Login;
    MediaPlayer SoundBackground = new MediaPlayer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animatiom);

        SoundBackground = MediaPlayer.create(activity_Sanh.this, R.raw.purple3);
        SoundBackground.start();

            Signin = (Button) findViewById(R.id.BtnsSignin);
            Signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity_Sanh.this, DangKy.class);
                    startActivity(intent);

                }
            });

            Login = (Button) findViewById(R.id.BtnLogin);
            Login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity_Sanh.this, DangNhap.class);
                    startActivity(intent);
                    SoundBackground.stop();

                }
            });






    }
}