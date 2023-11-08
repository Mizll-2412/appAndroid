package com.example.btlandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn,btnLogin;
    EditText edtName,edtpass;
    DBHelper DB;
    DatabaseQuanLy databaseQuanLy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.btnSignUp);
        btnLogin=findViewById(R.id.btnLogIn);
        edtName=findViewById(R.id.edtName);
        edtpass=findViewById(R.id.edtPass);
        DB=new DBHelper(this);
//
//        //khởi tạo database quản lý
//        databaseQuanLy= new DatabaseQuanLy(this,"quanlynhahang.sqlite",null,1);
//
//        //tạo bảng
//        databaseQuanLy.QueryData("CREATE TABLE monAn(ID INTEGER PRIMARY KEY AUTOINCREMENT,tenMonAn VARCHAR(50),");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this,dangKy.class);
                startActivity(myIntent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user= edtName.getText().toString();
                String pass=edtpass.getText().toString();
                if(TextUtils.isEmpty(user)||TextUtils.isEmpty(pass))
                {
                    Toast.makeText(MainActivity.this, "All fields required", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean checkuserpass=DB.checkUserNamePass(user,pass);
                    if(checkuserpass==true)
                    {
                        Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        Intent intentLogin= new Intent(MainActivity.this,ordertable.class);
                        intentLogin.putExtra("username",user);
                        startActivity(intentLogin);
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}