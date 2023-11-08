package com.example.btlandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BanAnAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<table> listTables;

    public BanAnAdapter(Context context, int layout, ArrayList<table> listTables) {
        this.context = context;
        this.layout = layout;
        this.listTables = listTables;
    }

    @Override
    public int getCount() {
        return listTables.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

//    public class ViewHolder
//    {
//        ImageView img;
//        TextView txtName,txtprice,txtSl;
//    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
//        ViewHolder viewHolder;
//        if(view ==null) {
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            view = inflater.inflate(layout, null);
//            viewHolder = new ViewHolder();
//
//            //ánh xạ view
//            viewHolder.txtName = (TextView) view.findViewById(R.id.txtnamee);
//            viewHolder.txtprice = view.findViewById(R.id.txtpricee);
//            viewHolder.txtSl=view.findViewById(R.id.txtprice2);
//            viewHolder.img = (ImageView) view.findViewById(R.id.imgView);
//            view.setTag(viewHolder);
//        }
//        else
//        {
//            viewHolder= (ViewHolder) view.getTag();
//        }
//        //gán giá trị
//        table tb=listTables.get(i);
//        viewHolder.txtName.setText(tb.getTenban());
//        viewHolder.txtprice.setText(String.format("%d", tb.getTongTien()));
//        viewHolder.txtSl.setText(String.format("%d", tb.getTongTien()));
//        // chuyển mảng byte qua bitmap
//        byte[] imgAnh = dish.getImage();
//        Bitmap bitmap = BitmapFactory.decodeByteArray(imgAnh,0,imgAnh.length);
//        viewHolder.img.setImageBitmap(bitmap);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(layout,null);

        ImageView img=view.findViewById(R.id.imgView);
        TextView txtName=view.findViewById(R.id.txtnamee);
       // TextView txtSl=view.findViewById(R.id.txtprice2);
        TextView txtP=view.findViewById(R.id.txtpricee);

        table tb=listTables.get(i);
        txtName.setText(tb.getTenban());
        txtP.setText("Tong tien: "+String.format("%d", tb.getTongTien()));
       // txtSl.setText("SoNg:"+String.format("%d", tb.getSoNguoi()));
        img.setImageResource(tb.getHinh());
        return view;
    }

    private Bitmap getBitMapImage(String encodedImage) {
        byte[] bytes = Base64.decode(encodedImage, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}
