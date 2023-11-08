package com.example.btlandroid;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class menu extends AppCompatActivity {
    FloatingActionButton addMonAn;
    ListView lvDishes;
    ArrayList<dish> listDishes;
    MonAnAdapter adapter;
    ImageButton imgBack;
    public static DatabaseQuanLy databaseQuanLy;
    int REQUEST_CODE_LV = 789;
    int REQUEST_CODE_CAMERA = 123;
    int REQUEST_CODE_FOLDER = 456;


    ActivityResultLauncher<Intent> activityResultLauncher=registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode()==24)
                    {
                        Intent intent=result.getData();
                        if(intent!=null)
                        {
                            Bundle bundle=new Bundle();
                            bundle=intent.getExtras();
                            int id=bundle.getInt("ID");
                            String ten=bundle.getString("ten");
                            int gia=(bundle.getInt("gia"));
                            byte[] img=bundle.getByteArray("img");
                            databaseQuanLy.UPDATEmonAn(id,ten,gia,img);
                            getDataMonAn();
                        }
                        Toast.makeText(menu.this, "A", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //AnhXa();
        imgBack=findViewById(R.id.imgBack);
        addMonAn=(FloatingActionButton) findViewById(R.id.addMonAn);
        lvDishes=(ListView) findViewById(R.id.lvMenu);
        listDishes=new ArrayList<>();
        adapter=new MonAnAdapter(this,R.layout.dish,listDishes);
        lvDishes.setAdapter(adapter);
        databaseQuanLy=new DatabaseQuanLy(this,"QuanLyMonAnnm.sqlite",null,1);
//        databaseQuanLy.QueryData("Drop table BanAn");
//        databaseQuanLy.QueryData("Drop table monAn");
//        databaseQuanLy.QueryData("Drop table Ban_Mon");
        databaseQuanLy.QueryData("CREATE TABLE IF NOT EXISTS BanAn(MaBan INTEGER PRIMARY KEY , TenBan varchar(50),SoNguoi int,GiaTien int ,hinh BLOB)");
        databaseQuanLy.QueryData("CREATE TABLE IF NOT EXISTS monAn(ID INTEGER PRIMARY KEY AUTOINCREMENT, tenMonAn varchar(50),gia int,hinh BLOB)");
        //databaseQuanLy.QueryData("CREATE TABLE IF NOT EXISTS Ban_Mon(MaBan INTEGER foreign key references BanAn(MaBan),ID INTEGER foreign key references monAn(ID),SoLuong int,gia int, hinh BLOB)" );
        addMonAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(menu.this,AddDishActivity.class);
                startActivity(intent);
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(menu.this,ordertable.class);
                startActivity(intent);
            }
        });
        lvDishes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent intent=new Intent(menu.this,ChiTietMonAn.class);
                Bundle bundle=new Bundle();
                bundle.putInt("ID",listDishes.get(i).getID());
                bundle.putString("ten",listDishes.get(i).getNamee());
                bundle.putInt("price", Integer.parseInt(String.format("%d", listDishes.get(i).getPrice())));
                //bundle.putByteArray("img",listDishes.get(i).getImage());
                intent.putExtras(bundle);
               activityResultLauncher.launch(intent);
               // startActivity(intent);
            }
        });
        getDataMonAn();
        registerForContextMenu(lvDishes);
        getDataMonAn();
        adapter.notifyDataSetChanged();
    }
    private void getDataMonAn() {
        Cursor cursor=databaseQuanLy.getData("SELECT * FROM monAn");
        listDishes.clear();
        while(cursor.moveToNext())
        {
            listDishes.add(new dish(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getBlob(3)
            ));
        }
        adapter.notifyDataSetChanged();
    }

//    private void AnhXa()
//    {
//        lvDishes=(ListView) findViewById(R.id.lvMenu);
//        listDishes=new ArrayList<>();
//        listDishes.add(new dish("Bào ngư vi cá",500000,R.drawable.baonguvica));
//        listDishes.add(new dish("Bò hầm rau củ",350000,R.drawable.bohamraucu));
//        listDishes.add(new dish("Bò willington",700000,R.drawable.bohamraucu));
//        listDishes.add(new dish("Cá hồi nướng",300000,R.drawable.cahoinuong));
//        listDishes.add(new dish("Salad",50000,R.drawable.bohamraucu));
//        listDishes.add(new dish("Súp cua",250000,R.drawable.bohamraucu));
//        listDishes.add(new dish("Thăn bò sốt tiêu",350000,R.drawable.bohamraucu));
//    }
}