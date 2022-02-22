package com.example.tracnghiem2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tracnghiem2.Activity_Mucdo.activity_test2;
import com.google.firebase.database.FirebaseDatabase;


public class TracNghiem extends AppCompatActivity {
    Button HighScore, Quit,Category,Danhsachsinhvien,tipenglish,BtnLuuDiem;
    TextView txtFullName;
    FirebaseDatabase database;
    Animation animation;
    ImageView img;
    MediaPlayer SoundBackground = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracnghiem);
        SoundBackground = MediaPlayer.create(TracNghiem.this, R.raw.purple);
        SoundBackground.start();

        database=FirebaseDatabase.getInstance();



        Quit = (Button) findViewById(R.id.BtnThoat);
        Quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                SoundBackground.stop();
            }
        });




        Category = (Button) findViewById(R.id.BtnCategory);
        Category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TracNghiem.this, choosenumenglish.class);
                SoundBackground.stop();
                startActivity(intent);
                overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);

            }
        });


/*
        btnKiemtra =(Button) findViewById(R.id.BtnCheck);
        btnKiemtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TracNghiem.this,activity.class);
                intent.putExtra("muc", "Kho");
                startActivity(intent);
            }
        });

 */





        Danhsachsinhvien =(Button) findViewById(R.id.danhsach);
        Danhsachsinhvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(TracNghiem.this, Danhsachsinhvien.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
            }
        });


        tipenglish = (Button) findViewById(R.id.tipEnglish);
        tipenglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TracNghiem.this,TipsEnglish.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
            }
        });

        BtnLuuDiem = (Button) findViewById(R.id.BtnLuuDiem);
        BtnLuuDiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TracNghiem.this,SaveScore.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
            }
        });



        Quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(TracNghiem.this);
                builder.setIcon(R.drawable.exit);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có chắc muốn thoát hay không?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent =  new Intent(TracNghiem.this, activity_Sanh.class);
                        SoundBackground.stop();

                        startActivity(intent);

                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                builder.show();
            }
        });






        ImageView imgRotate;
        imgRotate = (ImageView) findViewById(R.id.plane);

        animation = AnimationUtils.loadAnimation(this,R.anim.plane);
        imgRotate.startAnimation(animation);



    }
}