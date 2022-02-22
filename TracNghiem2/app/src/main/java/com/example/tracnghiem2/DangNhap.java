package com.example.tracnghiem2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.internal.service.Common;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class DangNhap extends AppCompatActivity {

    Button  btnDangnhap, btnThoat1;
    EditText edtEmail1, edtPassword1;
    FirebaseAuth mAuthencation;
    MediaPlayer SoundBackground = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuthencation = FirebaseAuth.getInstance();

        //   ControlButton();
        Anhxa();

        btnDangnhap.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Dangnhap();
                //  Intent intent = new Intent(DangNhap.this, TracNghiem.class);
                //startActivity(intent);
            }
        });

      /*  btnThoat1 = (Button) findViewById(R.id.BtnThoat1);
        btnThoat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

       */
    }
       /* private void ControlButton() {
            btnThoat1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(DangNhap.this, android.R.style.Theme_DeviceDefault_Light_Dialog);
                    builder.setTitle("do you sure to want to exit this app");
                    builder.setMessage("Please, choose to define");
                    builder.setIcon(android.R.drawable.ic_dialog_alert);
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            onBackPressed();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();
                }
            });
/*
            btnDangnhap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (edtEmail.getText().length() != 0 && edtPassword.getText().length() !=0){
                        if (edtEmail.getText().toString().equals(edtEmail) && edtPassword.getText().length() !=0){
                            Toast.makeText(DangNhap.this, "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();

                        }
                    }
                }
            });
        }
*/

    private void Dangnhap(){
        String email1 = edtEmail1.getText().toString();
        String pass1 = edtPassword1.getText().toString();
        mAuthencation.signInWithEmailAndPassword(email1, pass1)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(DangNhap.this, "Đăng Nhập Thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(DangNhap.this, TracNghiem.class);
                            startActivity(intent);


                            // Sign in success, update UI with the signed-in user's information

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(DangNhap.this, "Sai mật khẩu rồi thằng cu", Toast.LENGTH_SHORT).show();
                            // ...
                        }

                        // ...
                    }
                });

    }

    private void Anhxa () {

            btnDangnhap = (Button) findViewById(R.id.buttonDangnhap);
            edtEmail1 = (EditText) findViewById(R.id.editTextEmail1);
            edtPassword1 = (EditText) findViewById(R.id.editTextPassword1);
        //    btnThoat1 = (Button) findViewById(R.id.BtnThoat1);

        }
    }
