package com.example.btlandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {
    TextView edtusername;
    Intent myintent;
    Button btnlogout;
    ImageButton imgBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        edtusername=findViewById(R.id.txtusername);
        imgBack=findViewById(R.id.imgBack);
        myintent=getIntent();
        String username = myintent.getStringExtra("username");
        edtusername.setText("@"+username);

        btnlogout=findViewById(R.id.btnlogout);
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentback=new Intent(SettingActivity.this,MainActivity.class);
                startActivity(intentback);
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent=new Intent(SettingActivity.this,ordertable.class);
                backIntent.putExtra("username",username);
                startActivity(backIntent);
            }
        });
    }
}