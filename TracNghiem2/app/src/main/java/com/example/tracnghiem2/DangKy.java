package com.example.tracnghiem2;

import android.content.Intent;
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

public class DangKy extends AppCompatActivity {

    Button btnDangKy, btnThoat1;
    EditText edtEmail, edtPassword;
    FirebaseAuth mAuthencation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangky);

        mAuthencation = FirebaseAuth.getInstance();

        //   ControlButton();
        Anhxa();

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangKy();
            }
        });
/*
        btnThoat1 = (Button) findViewById(R.id.BtnThoat1);
        btnThoat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

 */
    }

    private void DangKy() {
        String email = edtEmail.getText().toString();
        String pass = edtPassword.getText().toString();

        mAuthencation.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(DangKy.this, "Đăng Ký Thành Công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(DangKy.this, activity_Sanh.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(DangKy.this, "Đăng Ký Thất Bại",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }


    private void Anhxa () {
        btnDangKy = (Button) findViewById(R.id.buttonDangKy);
        edtEmail = (EditText) findViewById(R.id.editTextEmail);
        edtPassword = (EditText) findViewById(R.id.editTextPassword);
   //     btnThoat1 = (Button) findViewById(R.id.BtnThoat1);

    }
}
