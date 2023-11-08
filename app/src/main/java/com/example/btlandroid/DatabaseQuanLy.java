package com.example.btlandroid;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import java.sql.Date;
import java.util.ArrayList;

public class DatabaseQuanLy extends SQLiteOpenHelper {
    final static String TABLE_NAME="monAn";
    final static String COLUMN_NAME_1="tenMonAn";
    final static String COLUMN_NAME_2="gia";
    final static String COLUMN_NAME_3="anh";

    final static String TABLE_NAME2="BanAn";
    final static String COLUMN_NAME2_2="TenBan";
    final static String COLUMN_NAME2_4="GiaTien";
    final static String COLUMN_NAME2_5="ThoiGianDat";

    final static String TABLE_NAME3="Ban_Mon";
    final static String COLUMN_NAME3_1="MaBan";
    final static String COLUMN_NAME3_2="MaMon";
    final static String COLUMN_NAME3_3="SoLuong";
    final static String COLUMN_NAME3_4="GiaTien";
    public DatabaseQuanLy(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void QueryData(String sql)
    {
        SQLiteDatabase database=getWritableDatabase();
        database.execSQL(sql);
    }

    public Cursor getData(String sql)
    {
        SQLiteDatabase database =getWritableDatabase();
        return database.rawQuery(sql,null);
    }
    //insert monAn
    public void InsertMonAn(String tenMonAn,int giaTien,byte[] img)
    {
            SQLiteDatabase database=getWritableDatabase();
            String sql="INSERT INTO monAn VALUES(null,?,?,?)";
            SQLiteStatement sqLiteStatement=database.compileStatement(sql);
            sqLiteStatement.clearBindings();
            sqLiteStatement.bindString(1,tenMonAn);
            sqLiteStatement.bindDouble(2,giaTien);
            sqLiteStatement.bindBlob(3,img);
            sqLiteStatement.executeInsert();
    }
    //insert ban
    public void InsertBanAn(int MaBan, String TenBan, int GiaTien, Date thoigian, byte[] hinh)
    {
        SQLiteDatabase database2= getWritableDatabase();
        String sql=
                "insert into BanAn values(?,?,?,?,?)";
        SQLiteStatement sqLiteStatement=database2.compileStatement(sql);
        sqLiteStatement.clearBindings();
        sqLiteStatement.bindLong(1,MaBan);
        sqLiteStatement.bindString(2,TenBan);
       // sqLiteStatement.bindLong(3,SoNguoi);
        sqLiteStatement.bindLong(3,GiaTien);
        sqLiteStatement.bindString(4, String.valueOf(thoigian));
        sqLiteStatement.bindBlob(5,hinh);
        sqLiteStatement.executeInsert();
    }
    //insert chi tiet ban
    public void InsertBan_Mon(int maBan, int maMon,int soLuong,double GiaTien,byte[] img)
    {
        SQLiteDatabase database3= getWritableDatabase();
        String sql=
                "insert into ChiTietBanAn(maBan,maMon,soLuong,GiaTien,img)"+
                        "values(?,?,?,?,?)";
        SQLiteStatement sqLiteStatement=database3.compileStatement(sql);
        sqLiteStatement.clearBindings();
        sqLiteStatement.bindLong(1,maBan);
        sqLiteStatement.bindLong(2,maMon);
        sqLiteStatement.bindLong(3,soLuong);
        sqLiteStatement.bindDouble(4, GiaTien);
        sqLiteStatement.bindBlob(5,img);
        sqLiteStatement.executeInsert();
    }

    public void updateImageProduct(int ID, byte[] img) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "UPDATE monAn SET hinhanh= ? WHERE ID=? ";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindBlob(1, img);
        statement.bindLong(2, ID);
        statement.executeUpdateDelete();
    }

    //noi 2 bang
//    private final String MY_QUERY = "SELECT * FROM BanAn a INNER JOIN monAn b ON a.maBan=b.id WHERE b.id=?";
//    {
//            SQLiteDatabase db =getReadableDatabase();
//        int id = 0;
//        db.rawQuery(MY_QUERY, new String[]{String.valueOf(id)});
//    }
    public void dropTable()
    {
        SQLiteDatabase sql = getWritableDatabase();
        sql.execSQL("Drop table monAn");
    }

    public void deleteAll()
    {
        SQLiteDatabase sql= getWritableDatabase();
        sql.execSQL("DELETE FROM monAn");
    }

    //ban an
    public void dropTableBanAn()
    {
        SQLiteDatabase sql = getWritableDatabase();
        sql.execSQL("Drop table BanAn");
    }

    public void deleteAllBanAN()
    {
        SQLiteDatabase sql= getWritableDatabase();
        sql.execSQL("DELETE FROM BanAn");
    }

    //ban_mon
    public void dropTableBanMon()
    {
        SQLiteDatabase sql = getWritableDatabase();
        sql.execSQL("Drop table Ban_Mon");
    }

    public void deleteAllBanMon()
    {
        SQLiteDatabase sql= getWritableDatabase();
        sql.execSQL("DELETE FROM Ban_Mon");
    }


    public void UPDATEmonAn (int id,String tenMonAn,int giaTien,byte[] hinh)
    {
        SQLiteDatabase database=getWritableDatabase();
        String sql="UPDATE monAn SET tenMonAn=? , giaTien=? , hinh=?   WHERE ID=?";
        SQLiteStatement sqLiteStatement =database.compileStatement(sql);
        sqLiteStatement.clearBindings();

        sqLiteStatement.bindString(1,tenMonAn);
        sqLiteStatement.bindLong(2,giaTien);
        sqLiteStatement.bindBlob(3,hinh);
        sqLiteStatement.bindLong(4,id);

        sqLiteStatement.executeUpdateDelete();
    }
    public void updateBanAn(int MaBan,String TenBan ,Date thoigian,int GiaTien,byte[] hinh)
    {
        SQLiteDatabase database =getWritableDatabase();
        String sql="UPDATE BanAn SET TenBan=?,SoNguoi=?, GiaTien=?,hinh=? WHERE MaBan=?";
        SQLiteStatement sqLiteStatement =database.compileStatement(sql);
        sqLiteStatement.clearBindings();
        sqLiteStatement.bindString(1,TenBan);
        sqLiteStatement.bindString(2, String.valueOf(thoigian));
        sqLiteStatement.bindLong(3, GiaTien);
        sqLiteStatement.bindBlob(4,hinh);
        sqLiteStatement.bindLong(5,MaBan);
        sqLiteStatement.executeUpdateDelete();
    }

    public void updateBan_Mon(int MaBan,int id,int SoLuong,double GiaTien,byte[] hinh)
    {
        SQLiteDatabase database =getWritableDatabase();
        String sql="UPDATE Ban_Mon SET SoLuong=?, GiaTien=?,hinh=? WHERE MaBan=? and id=?";
        SQLiteStatement sqLiteStatement =database.compileStatement(sql);
        sqLiteStatement.clearBindings();
        sqLiteStatement.bindLong(1,SoLuong);
        sqLiteStatement.bindDouble(2,GiaTien);
        sqLiteStatement.bindBlob(3, hinh);
        sqLiteStatement.bindLong(4,MaBan);
        sqLiteStatement.bindLong(5,id);
        sqLiteStatement.executeUpdateDelete();
    }

    public ArrayList<String> menu()
    {
        ArrayList<String> lsMonAn= new ArrayList<>();
        SQLiteDatabase db =getReadableDatabase();
        Cursor cs=db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        while(cs.moveToNext())
        {
                int id=cs.getInt(0);
                String ten=cs.getString(1);
                int gia=cs.getInt(2);
                byte[] img=cs.getBlob(3);
                lsMonAn.add(new dish(id,ten,gia,img).toString());
        }
        return lsMonAn;
    }


}
