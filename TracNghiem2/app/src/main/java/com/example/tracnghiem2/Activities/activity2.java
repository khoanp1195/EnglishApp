package com.example.tracnghiem2.Activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.tracnghiem2.Question;
import com.example.tracnghiem2.R;
import com.example.tracnghiem2.Result.activity_result;
import com.example.tracnghiem2.Result.activity_result2;
import com.example.tracnghiem2.check_answer_adapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import DBHELPER.DatabaseHelper2;


public class activity2 extends AppCompatActivity {

    public static final String ARG_CHECKANSWER = "checkAnswer";
    int HighScore = 0;
    public int Checkanswer = 0;
    int pos = 0;//vị trí câu hỏi trong danh sách
    public static int kq = 0; //lưu số câu trả lời đúng

    MediaPlayer SoundBackground = new MediaPlayer();
    private int position = 0;
    private TextView Cauhoi, Ketqua, minute, xem, score, Kiemtra;
    private Button BT;
    private RadioGroup RG;
    private RadioButton A, B, C, D;
    ArrayList<Question> Questions = new ArrayList();
    String muc;
    ImageView imgIcon;
    CheckBox SoundTest;

    activity2.CounterClass timer;
    String id;
    int totalTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_test);

        Intent myIntent = getIntent();
        muc = myIntent.getStringExtra("muc");
        minute = (TextView) findViewById(R.id.minute);
        Kiemtra = (TextView) findViewById(R.id.tvKiemtra);
        score = (TextView) findViewById(R.id.tvScore);
        id = myIntent.getStringExtra("_id");

        LoadHighScore();
        SoundBackground = MediaPlayer.create(activity2.this, R.raw.purple1);
        SoundBackground.start();

        SoundTest = findViewById(R.id.bgSpeaker);
        SoundTest.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean kt) {
                if (kt) {
                    SoundBackground.stop();
                } else try {
                    SoundBackground.prepare();
                    SoundBackground.start();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        totalTimer = 10;
        timer = new activity2.CounterClass(totalTimer * 60 * 1000, 1000);
        minute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        timer.start();
        SaveHighScore();


        Kiemtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkanswer();
            }
        });


        ReadDataFromSQL();
        findViewById();
        Display(position);
        BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int idCheck = RG.getCheckedRadioButtonId();

                switch (idCheck) {
                    case R.id.RdbA:
                        if (Questions.get(position).Answer.compareTo("A") == 0)
                            kq = kq + 1;
                        break;

                    case R.id.RdbB:
                        if (Questions.get(position).Answer.compareTo("B") == 0)
                            kq = kq + 1;
                        break;

                    case R.id.RdbC:
                        if (Questions.get(position).Answer.compareTo("C") == 0)
                            kq = kq + 1;
                        break;

                    case R.id.RdbD:
                        if (Questions.get(position).Answer.compareTo("D") == 0)
                            kq = kq + 1;
                        break;

                }
                position++;
                if (position >= Questions.size()) {
                    Intent intent = new Intent(activity2.this, activity_result2.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("KQ", kq);
                    bundle.putInt("Socau", position);
                    intent.putExtra("MyPackage", bundle);

                    startActivity(intent);
                    if (kq > HighScore) {
                        HighScore = kq;
                        SaveHighScore();

                    }

                    SoundBackground.stop();
                    finish();

                } else
                    Display(position);

            }
        });

    }

    void Display(final int index) {
        try {
            Question question = Questions.get(index);
            Cauhoi.setText(getItem(position).Question);
            A.setText(getItem(position).AnswerA);
            B.setText(getItem(position).AnswerB);
            C.setText(getItem(position).AnswerC);
            D.setText(getItem(position).AnswerD);
            RG.clearCheck();
            imgIcon.setImageResource(getResources().getIdentifier(getItem(position).getImage() + "", "drawable", "com.example.tracnghiem2"));
            Ketqua.setText("Score : " + kq);


        } catch (Exception exception) {
            String msg = "Khong co cau hoi.";
            Cauhoi.setText(msg);
            A.setVisibility(View.GONE);
            B.setVisibility(View.GONE);
            C.setVisibility(View.GONE);
            D.setVisibility(View.GONE);
            Toast.makeText(this, "Error occur", Toast.LENGTH_SHORT).show();
            Log.d("xxx", "Loi ne: " + exception.getMessage());

        }
        if (Checkanswer != 0) {
            A.setClickable(false);
            B.setClickable(false);
            C.setClickable(false);
            D.setClickable(false);
            getCheckAns();


        }

        RG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                getItem(position).ChoiceId = checkedId;
                getItem(position).setTraloi(getChoiceFromID(checkedId));
            }
        });

    }

    public String getChoiceFromID(int ID) {
        if (ID == R.id.RdbA) {
            return "A";
        } else if (ID == R.id.RdbB) {
            return "B";
        } else if (ID == R.id.RdbC) {
            return "C";
        } else if (ID == R.id.RdbD) {
            return "D";
        } else return "";
    }

    public Question getItem(int position) {
        return Questions.get(position);
    }

    public void findViewById() {
        Cauhoi = findViewById(R.id.TxtCauhoi);
        Ketqua = findViewById(R.id.TxtKetqua);
        BT = findViewById(R.id.BtnTraloi);
        RG = findViewById(R.id.RadioGroupTraloi);
        A = findViewById(R.id.RdbA);
        B = findViewById(R.id.RdbB);
        C = findViewById(R.id.RdbC);
        D = findViewById(R.id.RdbD);
        imgIcon = findViewById(R.id.ivIcon);
    }



    public class CounterClass extends CountDownTimer {


        public CounterClass(long millisInfuture, long countDownInterval) {
            super(millisInfuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinshed) {
            String countTime = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinshed), TimeUnit.MILLISECONDS.toSeconds(millisUntilFinshed) - TimeUnit.MINUTES.toSeconds(
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinshed)));
            minute.setText(countTime);

        }

        @Override
        public void onFinish() {
            minute.setText("00:00");
            Intent intent = new Intent(activity2.this, activity_result.class);
            Bundle bundle = new Bundle();
            bundle.putInt("KQ", kq);
            bundle.putInt("Socau", pos);
            intent.putExtra("MyPackage", bundle);

            startActivity(intent);
            if (kq > HighScore) {
                HighScore = kq;
                finish();
                SoundBackground.stop();

            }


        }
    }

    public void ReadDataFromSQL() {
        DatabaseHelper2 databaseHelper = new DatabaseHelper2(this);
        List<Question> Q1 = databaseHelper.getQuestion(muc);
        for (int i = 0; i < Q1.size(); i++) {
            Question question = new Question();
            question.ID = Q1.get(i).ID;
            question.Question = Q1.get(i).Question;
            question.AnswerA = Q1.get(i).AnswerA;
            question.AnswerB = Q1.get(i).AnswerB;
            question.AnswerC = Q1.get(i).AnswerC;
            question.AnswerD = Q1.get(i).AnswerD;
            question.Answer = Q1.get(i).Answer;
            //   question.image = Q1.get(i).image;
            question.image = Q1.get(i).getImage();
            Questions.add(question);
        }
    }

    public void checkanswer() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.check_answer);
        dialog.setTitle("Danh sach cac cau tra loi");


        check_answer_adapter answer_adapter = new check_answer_adapter(Questions, this);
        GridView checkanswer = (GridView) dialog.findViewById(R.id.checkanswer);
        checkanswer.setAdapter(answer_adapter);


        checkanswer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                dialog.dismiss();
            }
        });
        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity2.this, activity_result2.class);
                Bundle bundle = new Bundle();
                bundle.putInt("KQ", kq);
                bundle.putInt("Socau", pos);
                intent.putExtra("MyPackage", bundle);

                startActivity(intent);
                if (kq > HighScore) {
                    HighScore = kq;
                    SaveHighScore();
                    finish();
                }

            }
        });

        Button btnfinish, btnclose;
        btnclose = (Button) dialog.findViewById(R.id.btnclose);
        btnfinish = (Button) dialog.findViewById(R.id.btnFinish);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /////
                timer.cancel();
                result();
                dialog.dismiss();

            }
        });

        dialog.show();


    }

    void LoadHighScore() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyData",
                Context.MODE_PRIVATE);
        if (sharedPreferences != null) {
            HighScore = sharedPreferences.getInt("H", 0);
        }
    }

    void SaveHighScore() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("H", HighScore);
        editor.apply();
    }

    public void result() {
        Checkanswer = 1;
        // if (mPager.getCurrentItem() >= 4) mPager.setCurrentItem(mPager.getCurrentItem() - 4);
        //else if (mPager.getCurrentItem() <= 4) mPager.setCurrentItem(mPager.getCurrentItem() + 4);
        score.setVisibility(View.VISIBLE);
        Kiemtra.setVisibility(View.GONE);
    }
    /*
    public void onBackPressed() {
        if (RG.getCurrentItem() == 0) {
            dialogExit();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

     */

    private void getCheckAns() {
        //(ans.equals("A")==true)
        if (Questions.get(position).Answer.compareTo("A") == 0) {
            A.setBackgroundColor(Color.GREEN);
            // A.getId();

        } else //if(ans.equals("B")==true)
            if (Questions.get(position).Answer.compareTo("B") == 0) {
                B.setBackgroundColor(Color.GREEN);

                //  B.clearAnimation();
                //RG.getId();
                //   B.getId();
            } else //if(ans.equals("C")==true)
                if (Questions.get(position).Answer.compareTo("C") == 0) {
                    C.setBackgroundColor(Color.GREEN);

                    //   RG.getId();
                    //   C.getId();

                } else if (Questions.get(position).Answer.compareTo("B") == 0)//(ans.equals("D")==true)
                {
                    D.setBackgroundColor(Color.GREEN);

                    //  RG.getId();
                    // D.getId();

                } else {
                    RG.clearCheck();
                }
    }

}




