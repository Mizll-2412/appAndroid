package com.example.btlandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ChiTietMonAn extends AppCompatActivity {
    EditText edName, edtPrice;
    String a;
    ImageView imgHinh;
    ImageButton btnCam, btnLib, btnSave, btnCancel,btnDelete;
    int REQUEST_CODE_CAMERA = 123;
    int REQUEST_CODE_FOLDER = 456;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_mon_an);
        anhXa();
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        intent.getBundleExtra("bundle");

        id=bundle.getInt("ID");
        edName.setText(bundle.getString("ten"));
        edtPrice.setText(String.valueOf(bundle.getInt("price")));

        //chuyển mảng byte qua bitmap
        byte[] img=bundle.getByteArray("img");
        Bitmap bitmap= BitmapFactory.decodeByteArray(img,0,img.length);
        imgHinh.setImageBitmap(bitmap);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,REQUEST_CODE_CAMERA);
            }
        });
        btnLib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_FOLDER);
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tenmoi = edName.getText().toString().trim();
                int giaMoi = Integer.parseInt((edtPrice.getText().toString()));

                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgHinh.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG , 100 , byteArrayOutputStream );
                byte[] hinh = byteArrayOutputStream.toByteArray();

                Intent intent = new Intent();
                Bundle bundle = new Bundle();

                bundle.putInt("ID", id);
                bundle.putString("ten",tenmoi.trim());
                bundle.putInt("gia",giaMoi);
                bundle.putByteArray("img",hinh);

                intent.putExtras(bundle);
                setResult(24,intent);
                finish();
                ChiTietMonAn.super.onBackPressed();
            }
        });
    }

    private void anhXa()
    {
        edName=findViewById(R.id.edtName);
        edtPrice=findViewById(R.id.edtGia);
        imgHinh=findViewById(R.id.dialogImgHinh);
        btnCam=findViewById(R.id.dialogCam);
        btnLib=findViewById(R.id.dialogLib);
        btnSave=findViewById(R.id.dialogSave);
        btnCancel=findViewById(R.id.dialogHuy);
        btnDelete=findViewById(R.id.btnDelete);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgHinh.setImageBitmap(bitmap);
        }
        if (requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgHinh.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
