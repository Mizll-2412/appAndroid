package com.example.btlandroid;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ordertable extends AppCompatActivity {
    ImageButton btnHome, btnMenu, btnSetting;
    ListView lvtable;
    ArrayList<table> arrayImage;
    BanAnAdapter adapter;
    public static DatabaseQuanLy databaseQuanLy;

//    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),
//            new ActivityResultCallback<ActivityResult>() {
//                @Override
//                public void onActivityResult(ActivityResult result) {
//                    if (result.getResultCode() == 07) {
//                        Intent intent = result.getData();
//                        if (intent != null) {
//                            Bundle bundle = new Bundle();
//                            bundle = intent.getExtras();
//                            int id = bundle.getInt("ID");
//                            String ten = bundle.getString("ten");
//                            int gia = Integer.parseInt(bundle.getString("gia"));
//                            byte[] img = bundle.getByteArray("hinh");
//                            databaseQuanLy.UPDATE(id, ten, gia, img);
//                            getDataTable();
//                        }
//                        Toast.makeText(ordertable.this, "A", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//    );

    @SuppressLint({"MissingInflatedId", "WrongThread"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordertable);
       // lvtable = (ListView) findViewById(R.id.lvTable);
        arrayImage = new ArrayList<>();
        AnhXa();
        adapter = new BanAnAdapter(this, R.layout.table1, arrayImage);
        lvtable.setAdapter(adapter);

        //
//        BitmapDrawable bitmapDrawable = (BitmapDrawable) getDrawable(R.drawable.dinner);
//        Bitmap bitmap = bitmapDrawable.getBitmap();
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
//        byte[] img = byteArrayOutputStream.toByteArray();
//        //
//        databaseQuanLy.InsertBanAn(1, "Ban an 01", 0, 0, img);
//        databaseQuanLy.InsertBanAn(2, "Ban an 02", 0, 0, img);
//        databaseQuanLy.InsertBanAn(3, "Ban an 03", 0, 0, img);
//        databaseQuanLy.InsertBanAn("Ban an 04",0,0,img);
//        databaseQuanLy.InsertBanAn("Ban an 05",0,0,img);
//        databaseQuanLy.InsertBanAn("Ban an 06",0,0,img);
//        databaseQuanLy.InsertBanAn("Ban an 07",0,0,img);
//        databaseQuanLy.InsertBanAn("Ban an 08",0,0,img);
//        databaseQuanLy.InsertBanAn("Ban an 09",0,0,img);


//        lvtable.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
//                Intent intent=new Intent(menu.this,ChiTietMonAn.class);
//                Bundle bundle=new Bundle();
//                bundle.putInt("ID",listDishes.get(i).getID());
//                bundle.putString("ten",listDishes.get(i).getNamee());
//                bundle.putString("gia",String.format("%d", listDishes.get(i).getPrice()));
//                bundle.putByteArray("hinh",listDishes.get(i).getImage());
//                intent.putExtras(bundle);
//                activityResultLauncher.launch(intent);
//            }
//        });

        btnSetting = findViewById(R.id.btnSetting);
        btnHome = findViewById(R.id.btnHome);
        btnMenu = findViewById(R.id.btnMenu);
        lvtable.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent inten=new Intent(ordertable.this,Ban_Mon.class);
                        startActivity(inten);
            }
        });
        //AnhXa();
//      adapter=new BanAnAdapter(this,R.layout.table1,arrayImage);
//      lvtable.setAdapter(adapter) ;

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ordertable.this, ordertable.class);
                startActivity(intent);
            }
        });
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = getIntent();
                //myintent.getBundleExtra("mypackage");
                String username = myintent.getStringExtra("username");
                Intent intentSetting = new Intent(ordertable.this, SettingActivity.class);
                intentSetting.putExtra("username", username);
                startActivity(intentSetting);
            }
        });
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMenu = new Intent(ordertable.this, menu.class);
                startActivity(intentMenu);
            }
        });
//        getDataTable();
//        registerForContextMenu(lvtable);
//        getDataTable();
//        adapter.notifyDataSetChanged();
    }

//    private void getDataTable() {
//        Cursor cursor = databaseQuanLy.getData("SELECT * FROM BanAn");
//        arrayImage.clear();
//        while (cursor.moveToNext()) {
//            arrayImage.add(new table(
//                    cursor.getInt(1),
//                    cursor.getString(2),
//                    cursor.getInt(3),
//                    cursor.getInt(4),
//                    cursor.getBlob(5)
//            ));
//        }
//        adapter.notifyDataSetChanged();
//    }
//}

    private void AnhXa()
    {
        lvtable=(ListView) findViewById(R.id.lvTable);
        arrayImage=new ArrayList<>();
        arrayImage.add(new table(1,"Ban so 01",0,0,R.drawable.dinner));
        arrayImage.add(new table(2,"Ban so 02",0,0,R.drawable.dinner));
        arrayImage.add(new table(3,"Ban so 03",0,0,R.drawable.dinner));
        arrayImage.add(new table(4,"Ban so 04",0,0,R.drawable.dinner));
        arrayImage.add(new table(5,"Ban so 05",0,0,R.drawable.dinner));
        arrayImage.add(new table(6,"Ban so 06",0,0,R.drawable.dinner));
        arrayImage.add(new table(7,"Ban so 07",0,0,R.drawable.dinner));
        arrayImage.add(new table(8,"Ban so 08",0,0,R.drawable.dinner));
        arrayImage.add(new table(9,"Ban so 09",0,0,R.drawable.dinner));
        arrayImage.add(new table(10,"Ban so 10",0,0,R.drawable.dinner));
    }
}