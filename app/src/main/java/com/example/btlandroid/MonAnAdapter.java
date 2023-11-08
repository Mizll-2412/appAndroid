package com.example.btlandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MonAnAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<dish> Listdish;

    public MonAnAdapter(Context context, int layout, List<dish> Listdish) {
        this.context = context;
        this.layout = layout;
        this.Listdish = Listdish;
    }

    @Override
    public int getCount() {
        return Listdish.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public class ViewHolder
    {
        ImageView img;
        TextView txtName,txtprice;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view ==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            viewHolder = new ViewHolder();

            //ánh xạ view
            viewHolder.txtName = (TextView) view.findViewById(R.id.txtnamee);
            viewHolder.txtprice = view.findViewById(R.id.txtpricee);
            viewHolder.img = (ImageView) view.findViewById(R.id.imgView);
            view.setTag(viewHolder);
        }
        else
            {
                viewHolder= (ViewHolder) view.getTag();
            }
        //gán giá trị
        dish ds=Listdish.get(i);
        viewHolder.txtName.setText(ds.getNamee());
        viewHolder.txtprice.setText(String.format("%d", ds.getPrice()));
        // chuyển mảng byte qua bitmap
        byte[] imgAnh = ds.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(imgAnh,0,imgAnh.length);
        viewHolder.img.setImageBitmap(bitmap);
        return view;
    }
}
