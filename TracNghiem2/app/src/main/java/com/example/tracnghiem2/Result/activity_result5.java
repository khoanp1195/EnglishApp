package com.example.tracnghiem2.Result;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tracnghiem2.Question;
import com.example.tracnghiem2.R;
import com.example.tracnghiem2.TracNghiem;
import com.example.tracnghiem2.score.ScoreController;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

import static com.example.tracnghiem2.choosenumenglish.cauhoi;
import static com.example.tracnghiem2.Activities.activity5.kq;



public class activity_result5 extends Activity {
    ArrayList<Question> Questions = new ArrayList();


    PieChart pieChart;
    public int soo = cauhoi - kq;
    public int kqq = kq;

    Button btnSave;
    Button  btnagain, MainActivity,btnSaveSv;
    TextView KQ,  tvScore;
    //lưu số câu trả lời đúng

    ScoreController scoreController;
    MediaPlayer SoundBackground = new MediaPlayer();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        scoreController = new ScoreController(activity_result5.this);
        SoundBackground = MediaPlayer.create(activity_result5.this, R.raw.preview);
        SoundBackground.start();

        pieChart = findViewById(R.id.piechart);
        setData();


        final Intent intent = getIntent();
        Questions = (ArrayList<Question>) intent.getExtras().getSerializable("Questions");
        begin();


        Intent callerIntent = getIntent();
        KQ = findViewById(R.id.KQ);

        final Bundle packageFromCaller = callerIntent.getBundleExtra("MyPackage");
        KQ.setText(packageFromCaller.getInt("KQ") + "/" + packageFromCaller.getInt("Socau"));


        btnSaveSv = (Button) findViewById(R.id.btnSaveSv);
        btnSaveSv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_result5.this, com.example.tracnghiem2.quanlysinhvien.MainActivity.class);

                startActivity(intent);
            }
        });


        btnagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(activity_result5.this);
                builder.setIcon(R.drawable.exit);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có chắc muốn thoát hay không?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(activity_result5.this, TracNghiem.class);
                        kq = 0;
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
        MainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(activity_result5.this);
                LayoutInflater inflater = activity_result5.this.getLayoutInflater();
                View view = inflater.inflate(R.layout.allert_dialog_save_score, null);
                builder.setView(view);

                final EditText edtName = (EditText) view.findViewById(R.id.edtHoTen);
                //   final EditText edtMSSV= (EditText) view.findViewById(R.id.edtMssv);
                final TextView tvScore = (TextView) view.findViewById(R.id.tvScore);
                final EditText edtRoom = (EditText) view.findViewById(R.id.edtEmail);
                // tvsaveroom.setText("muc");
                tvScore.setText(packageFromCaller.getInt("KQ") + "/" + packageFromCaller.getInt("Socau"));
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = edtName.getText().toString();
                        String score = tvScore.getText().toString();
                        String room = edtRoom.getText().toString();


                        scoreController.insertScore(name, score, room);
                        Toast.makeText(activity_result5.this, "Lưu điểm thành công!", Toast.LENGTH_LONG).show();
                        finish();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog b = builder.create();
                b.show();
            }
        });
    }
    public void setData() {

        // Set the data and color to the pie chart

        pieChart.addPieSlice(
                new PieModel(

                        Integer.parseInt(String.valueOf(soo)),
                        Color.parseColor("#EF5350")));



        pieChart.addPieSlice(
                new PieModel(

                        Integer.parseInt(String.valueOf(kqq)),
                        Color.parseColor("#29B6F6")));


        // To animate the pie chart
        pieChart.startAnimation();
    }


    public void begin(){

        btnagain =  findViewById(R.id.Btnagain);
        MainActivity = findViewById(R.id.btnSaveScore);
        tvScore =  findViewById(R.id.tvScore);

    }

}