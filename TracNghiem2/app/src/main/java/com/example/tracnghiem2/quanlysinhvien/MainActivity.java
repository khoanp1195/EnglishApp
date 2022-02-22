package com.example.tracnghiem2.quanlysinhvien;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tracnghiem2.R;
import com.example.tracnghiem2.adapter.SinhVienAdapter;
import com.example.tracnghiem2.model.SinhVien;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<SinhVien> sinhVienArrayList;
    private SinhVienAdapter adapter;
    TextView txt2;
    private EditText edtMssv, edtTen, edtScore, edtSdt;
    private Button btnThem, btnHuy, btnTroVe;

    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allert_dialog_save_sv);
        GetData();
        listView = (ListView) findViewById(R.id.listView);
        sinhVienArrayList = new ArrayList<>();
        txt2 = (TextView) findViewById(R.id.textView2);


       adapter = new SinhVienAdapter(this, R.layout.custom_listview_item, sinhVienArrayList);


        listView.setAdapter(adapter);

        addControls(); //khai báo các control trên giao diện
        addEvents();




    }
        private void GetData(){
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Student");
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //xóa dữ liệu trên listview và cập nhật lại
                    adapter.clear();

                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        //convert data qua SinhVien
                        SinhVien sinhVien = data.getValue(SinhVien.class);
                        //thêm sinh viên vào listview
                        sinhVien.setId(data.getKey());
                        adapter.add(sinhVien);
                        Log.d("MYTAG", "onDataChange: " + sinhVien.getHoTen());
                    }
                    Toast.makeText(getApplicationContext(), "Load Data Success", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(), "Load Data Failed" + databaseError.toString(), Toast.LENGTH_LONG).show();
                    Log.d("MYTAG", "onCancelled: " + databaseError.toString());
                }
            });
        }
    private void addEvents() {
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); //trở về lại màn hình trước đó
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //hủy thì sẽ xóa hết các text trên edt
                edtMssv.setText("");
                edtScore.setText("");
                edtSdt.setText("");
                edtTen.setText("");
            }
        });


        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sự kiện thêm. Thêm sinh viên vào firebase database
                //lấy mssv, ten, email, sdt trên view.
                String mssv = edtMssv.getText().toString();
                String hoten = edtTen.getText().toString();
                String Score = edtScore.getText().toString();
                String sdt = edtSdt.getText().toString();
                //ở đây không nhập ID vì Firebase database nó tự sinh cho mình một cái Id
                SinhVien sinhVien = new SinhVien(mssv, hoten, Score,sdt);

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Student");
                //tạo một Id ngẫu nhiên trên firebase database/DbSinhVien/
                String id = myRef.push().getKey();
                //dựa vào Id này, mình sẽ thêm dữ liệu sinh viên vào
                myRef.child(id).setValue(sinhVien).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //nếu thêm thành công sẽ nhảy vào đây
                        Toast.makeText(getApplicationContext(),"Thêm thành công!", Toast.LENGTH_LONG).show();
                       //thoát màn hình thêm, trở về màn hình danh sách sv
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //nếu thêm thất bại sẽ nhảy vào đây
                        Toast.makeText(getApplicationContext(),"Thêm thất bại! " + e.toString(), Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }

    private void addControls() {

        edtMssv = findViewById(R.id.edtMssv);
        edtTen = findViewById(R.id.edtHoTen);
        edtScore = findViewById(R.id.edtEmail);
        edtSdt = findViewById(R.id.edtSoDienThoai);

        btnHuy = findViewById(R.id.btnHuy);
        btnThem = findViewById(R.id.btnThem);
        btnTroVe = findViewById(R.id.btnTroVe);


    }





}