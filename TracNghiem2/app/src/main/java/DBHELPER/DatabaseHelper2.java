package DBHELPER;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.tracnghiem2.Question;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper2 extends SQLiteOpenHelper {

    public static final String TRAC_NGHIEM_TABLE = "TRAC_NGHIEM";
    public static final String COLUMN_question= "question";
    public static final String COLUMN_id = "_id";
    public static final String COLUMN_result = "result";
    public static final String COLUMN_ans_A = "ans_A";
    public static final String COLUMN_ans_B = "ans_B";
    public static final String COLUMN_ans_C = "ans_C";
    public static final String COLUMN_ans_D = "ans_D";
    public static final String COLUMN_muc = "muc";
    public static final String COLUMN_image = "image";

    public DatabaseHelper2(@Nullable Context context) {
        super(context,"tracnghiem.db", null, 1); //tạo database tên database.db
    }

    //TỰ ĐỘNG GỌI KHI DATABASE ĐƯỢC TẠO LẦN ĐẦU TIÊN
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //CÂU LỆNH TẠO BẢNG VỚI ID LÀ KHÓA CHÍNH , BẮT ĐẦU VỚI ID LÀ FIELD ĐẦU TIÊN , SAU ĐÓ TỚI QUESTION LÀ FIELD THỨ HAI , ....
        // duplicate column name: ANSWER
        String sqlStatement = "CREATE TABLE IF NOT EXISTS " + TRAC_NGHIEM_TABLE + " (" + COLUMN_id + " INTEGER PRIMARY KEY AUTOINCREMENT , " + COLUMN_question + " TEXT , "
                +COLUMN_ans_A + " TEXT , " + COLUMN_ans_B + " TEXT , " + COLUMN_ans_C + " TEXT , " + COLUMN_ans_D + " TEXT , " + COLUMN_result + " TEXT, "+COLUMN_muc+" TEXT," + COLUMN_image +"TEXT )";
        sqLiteDatabase.execSQL(sqlStatement); //THỰC THI CÂU LỆNH SQL
    }

    //ĐƯỢC GỌI KHI CÓ BẢN NÂNG CẤP , THAY ĐỔI VERSION TRÊN DATABASE
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    //LẤY CÂU HỎI TRONG DATABASE ĐỔ VÀO LIST
    public List<Question> getQuestion(String muc){
        List<Question> returnQuestion = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase(); //getReadableDatabase ĐỂ ĐỌC DỮ LIỆU TRÊN DATABASE

        // SU DUNG WHERE DE PHAN LOAI CAU HOI
        // DE RANDOM CAU HOI SUNG ORDER BY RANDOM()
        // where muc = 'De'
        String sqlQuerry = "SELECT * FROM "+TRAC_NGHIEM_TABLE +" WHERE muc = "+"'"+muc+"'"+" ORDER BY RANDOM() LIMIT 7";
        Cursor cursor = database.rawQuery(sqlQuerry,null); //CURSOR LÀ MỘT BẢNG KẾT QUẢ LẤY ĐƯỢC TỪ CÂU TRUY VẤN ĐÃ TRUYỀN VÀO Ở ĐÂY LÀ SELECT * FROM QUETSTION_TABLE , THAM SỐ THỨ HAI LIÊN QUAN TỚI MỆNH ĐỀ WHERE , DO KHÔNG SỬ DỤNG NÊN ĐỂ NULL
        //cursor.moveToFirst() SẼ CHẠY HÀNG ĐẦU TIÊN CỦA BẢNG CURSOR
        if(cursor.moveToFirst()){
            /*
            do {
                int ID = cursor.getInt(0); //FILED ĐẦU TIÊN CỦA BẢNG BẮT ĐẦU TỪ 0 , FIELD ĐẦU TIÊN LÀ ID
                String Question = cursor.getString(1);
                String AnswerA = cursor.getString(2);
                String AnswerB = cursor.getString(3);
                String AnswerC = cursor.getString(4);
                String AnswerD = cursor.getString(5);
                String Answer = cursor.getString(6);
                Question question = new Question(ID,Question,AnswerA,AnswerB,AnswerC,AnswerD,Answer);
                returnQuestion.add(question);

             */

            do {
                Question item;
                item= new Question(cursor.getInt(0), cursor.getString(1),cursor.getString(2),cursor.getString(3),
                        cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8));
                returnQuestion.add(item);
            }while (cursor.moveToNext()); //CHẠY HÀNG TIẾP TỤC CHO TỚI HẾT BẢNG

        }
        cursor.close();
        database.close(); //ĐÓNG BẢNG VÀ CURSOR
        return returnQuestion;
    }
}
