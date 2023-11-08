package com.example.btlandroid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddDishActivity extends AppCompatActivity {
    EditText edName, edtPrice;

    ImageView imgHinh;
    ImageButton btnCam, btnLib, btnSave, btnCancel;
    int REQUEST_CODE_CAMERA = 123;
    int REQUEST_CODE_FOLDER = 456;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dish);
        edName=findViewById(R.id.edtName);
        edtPrice=findViewById(R.id.edtGia);
        imgHinh=findViewById(R.id.dialogImgHinh);
        btnCam=findViewById(R.id.dialogCam);
        btnLib=findViewById(R.id.dialogLib);
        btnSave=findViewById(R.id.dialogSave);
        btnCancel=findViewById(R.id.dialogHuy);

        btnCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCam= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intentCam,REQUEST_CODE_CAMERA);
            }
        });
        btnLib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intentLib= new Intent(Intent.ACTION_PICK);
                    intentLib.setType("image/*");
                    startActivityForResult(intentLib,REQUEST_CODE_FOLDER);
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chuyen data cua imageView sang dang byte
                BitmapDrawable bitmapDrawable= (BitmapDrawable) imgHinh.getDrawable();
                Bitmap bitmap=bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArrayOutputStream= new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100, byteArrayOutputStream);
                byte[] img=byteArrayOutputStream.toByteArray();

//                Intent myInten=new Intent();
//                Bundle mybundle=new Bundle();
//                mybundle.putInt("ID",id);
//                mybundle.putString("ten",tenMoi.trim());
//                mybundle.putDouble("gia",priceGia);
//                mybundle.putByteArray("hinh",img);
//                myInten.putExtras(mybundle);
//                setResult(06,myInten);
//                finish();
//                AddDishActivity.super.onBackPressed();
                menu.databaseQuanLy.InsertMonAn(
                        edName.getText().toString().trim(),
                        Integer.parseInt((edtPrice.getText().toString().trim())),
                        img
                );
               // menu.databaseQuanLy.updateImageProduct(id,img);
                Toast.makeText(AddDishActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AddDishActivity.this,menu.class));
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void getId()
    {

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
            } catch (FileNotFoundException exception) {
                exception.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}

