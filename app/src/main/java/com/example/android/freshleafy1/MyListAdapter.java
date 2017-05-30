package com.example.android.freshleafy1;


import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.freshleafy1.Database.OrderDbHelper;

import java.util.ArrayList;
import java.util.List;

public class MyListAdapter extends ArrayAdapter<DisplayItem>
{
    private final Activity context ;
    private final ArrayList<DisplayItem> list ;

    OrderDbHelper mydb ;
    SQLiteDatabase db ;

    public MyListAdapter(Activity context, ArrayList<DisplayItem> list )
    {
        super(context,0,list);
        this.context = context;
        this.list = list ;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.inflater_layout,null,true);
        final DisplayItem item = list.get(position);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.nameEng);
        txtTitle.setText(item.getName());

        TextView hinName = (TextView)rowView.findViewById(R.id.nameHindi);
        hinName.setText(item.getNameHindi());

        TextView price = (TextView) rowView.findViewById(R.id.price);
        price.setText("₹ "+ item.getPrice());

        ImageView icon = (ImageView)rowView.findViewById(R.id.image);
        icon.setImageBitmap(item.getImage());

        Button b1 = (Button)rowView.findViewById(R.id.minus);
        Button b2 = (Button)rowView.findViewById(R.id.plus);

        mydb = new OrderDbHelper(getContext());
        db = mydb.getWritableDatabase();

        final TextView qty = (TextView)rowView.findViewById(R.id.qty);
        int quantity = mydb.getQuantity(db,item.getItem_id()) ;
        qty.setText(""+quantity);

        final TextView qxp = (TextView)rowView.findViewById(R.id.qXp);
        qxp.setText(item.getPrice()+" X "+quantity);

        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int c = mydb.getQuantity(db,item.getItem_id());
                if (c>0)
                    --c;
                mydb.updateData(db,item.getItem_id(),c);
                qty.setText(""+c);
                qxp.setText(item.getPrice()+" X "+c);
            }
        });

        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int c = mydb.getQuantity(db,item.getItem_id());
                ++c ;
                mydb.updateData(db,item.getItem_id(),c);
                qty.setText(""+c);
                qxp.setText(item.getPrice()+" X "+c);
            }
        });

        return rowView ;
    }
}
