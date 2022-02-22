package com.example.tracnghiem2;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tracnghiem2.model.SinhVien;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class Danhsachsinhvien extends AppCompatActivity {
    private HashMap<String, SinhVien> hashmap = new HashMap<String, SinhVien>();
    private ArrayList<String> list = new ArrayList<String>();
    private ArrayList<String> major = new ArrayList<String>();

    DatabaseReference mData;
    TextView txtKhoaHoc;
    ListView listView;
    ArrayList<String> mangsinhvien;
    ArrayAdapter adapter = null;
    Spinner spin;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danhsach);

      //  txtKhoaHoc = (TextView) findViewById(R.id.txtKhoaHoc);
        listView = (ListView) findViewById(R.id.listView1);
        mangsinhvien = new ArrayList<String>();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,mangsinhvien);
        listView.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
       // spin = (Spinner) findViewById(R.id.spinnerMajor);
     //   button = (Button) findViewById(R.id.button);

/*

        spin.setAdapter(adapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Print(spin.getSelectedItem().toString());
            }
        });

 */
    mData = FirebaseDatabase.getInstance().getReference();

    // final SinhVien sinhVien = new SinhVien("Phủ định: S + am/is/are not + V_ing + …","Thì hiện tại tiếp diễn – Present continuous tense: Khẳng định: S + am/is/are + V_ing + … \n Ex: I am doing homework. (Tôi đang làm bài tập về nhà.)","Ex: She is not playing soccer with her brother. (Cô ấy đang không chơi bóng đá với em trai cô ấy)","Nghi vấn: Am/Is/Are + S + V_ing + …? \n Ex: Are you working? (Bạn đang làm việc à?)");

      //  mData.child("Student2").push().setValue(sinhVien);

        mData.child("Student").addChildEventListener((new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
               SinhVien sinhVien1 = snapshot.getValue(SinhVien.class);
              mangsinhvien.add(sinhVien1.getHoTen() + "\n" + sinhVien1.getMssv() + "\n" + sinhVien1.getEmail() + "\n"+ sinhVien1.getSoDienThoai());
              //  mangsinhvien.add(snapshot.getValue(String.class));
                adapter.notifyDataSetChanged();

                /*
                try {
                    String splitBy = ",";
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    major.add(sinhVien1.major+ sinhVien1.id +sinhVien1.name);
                    while (br != null) {
                        String line = null;
                        try {
                            line = br.readLine();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        String[] value = line.split(splitBy);
//Lưu tất cả danh sách SV vào Hashmap

                        hashmap.put(value[0], new SinhVien(value[0], value[1], value[2]));
//Kiểm tra nếu chưa có tên ngành trong danh sách thì thêm vào

                        if (!major.contains(value[2])) {
                            major.add(value[2]);
                        }
                    }
                    br.close();
                } catch (Exception e) {
                    System.out.println("" + e.getMessage());
                }

                 */
            }


            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }));


        //    mData.child("KhoaHoc").push().setValue("Day boi");
        //   mData.child("KhoaHoc").addChildEventListener(new ChildEventListener() {


    }

/*

    public void Print(String s) {
        list.clear(); //Mỗi lần hiển thị mới phải xóa list trước
        if (s.equals("All")) {
            for (SinhVien x: hashmap.values()) {
                list.add(x.toString());
            }
        }
        else {
            for (SinhVien x : hashmap.values()) {
                if (s.equals(x.getMajor())) {
                    list.add(x.toString());
                }
            }
        }
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter1);
    }

 */


    }



