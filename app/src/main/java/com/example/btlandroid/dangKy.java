package com.example.btlandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;

public class dangKy extends AppCompatActivity {
    TextView txtlogIn,edtName,edtcfPass,edtPass;
    RadioButton radioButton3,radioButton4;
    Button btnSignUp;
    DBHelper DB;
    String poss="";
    int postion=0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        txtlogIn = findViewById(R.id.txtlogIn);
        edtName = findViewById(R.id.edtName);
        edtcfPass = findViewById(R.id.edtcfpass);
        edtPass = findViewById(R.id.edtPass);
        radioButton3 = findViewById(R.id.radioButton3);
        radioButton4 = findViewById(R.id.radioButton4);
        btnSignUp = findViewById(R.id.btnSignUp);
        DB=new DBHelper(this);
        //xử lý radioButton
        if(radioButton3.isChecked())
        {
            postion=1;
            poss="Staff";
        }
        else if(radioButton4.isChecked())
        {
            postion=0;
            poss="Manager";
        }
        //tạo và mở csdl sqlite
//        mydatabaseS = openOrCreateDatabase("qlStaff.db", MODE_PRIVATE, null);
//        mydatabaseM = openOrCreateDatabase("qlManager.db", MODE_PRIVATE, null);
//        //tạo table để chứa dữ liệu Nhân Viên
//        try {
//            String sql = "CREATE TABLE tblStaff(firstNam TEXT,Dob DATE,pass TEXT,position TEXT)";
//            mydatabaseS.execSQL(sql);
//        } catch (Exception e) {
//            Log.e("Error", "Table already exists");
//        }
//        // table chứa dữ liệu Quản Lý
//        try {
//            String sql = "CREATE TABLE tblManager(firstNam TEXT ,Dob DATE,pass TEXT,position TEXT)";
//            mydatabaseM.execSQL(sql);
//        } catch (Exception e) {
//            Log.e("Error", "Table already exists");
//        }
        //xử lý đăng ký
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtName.getText().toString();
                String cfpass = edtcfPass.getText().toString();
                String pass = edtPass.getText().toString();
                String pos = String.valueOf(poss.getBytes(StandardCharsets.UTF_8));
                if(TextUtils.isEmpty(user)||TextUtils.isEmpty(cfpass)||TextUtils.isEmpty(pass)||TextUtils.isEmpty(pos))
                {
                    Toast.makeText(dangKy.this, "All fields required", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (pass.equals(cfpass)) {
                        Boolean checkuser = DB.checkUsername(user);
                        if (checkuser == false) {
                            Boolean insert = DB.insertData(user, pass, pos);
                            if (insert == true) {
                                Toast.makeText(dangKy.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent myintent = new Intent(dangKy.this, MainActivity.class);
                                startActivity(myintent);
                            } else {
                                Toast.makeText(dangKy.this, "Registrantion Failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(dangKy.this, "Username already exists", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(dangKy.this, "Passwords are not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        txtlogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}