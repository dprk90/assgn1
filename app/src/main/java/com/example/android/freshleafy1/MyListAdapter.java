package com.example.android.freshleafy1;


import android.app.Activity;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MyListAdapter extends ArrayAdapter<String>
{

    private final Activity context ;
    private final ArrayList<AnItem> list ;

    Cursor res ;


    public MyListAdapter(Activity context, ArrayList<AnItem> list )
    {
        super(context,R.layout.inflater_layout);
        this.context = context;
        this.list = list ;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.inflater_layout,null,true);
        AnItem item = list.get(position);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.nameEng);
        txtTitle.setText(item.getName());

        TextView price = (TextView) rowView.findViewById(R.id.price);
        price.setText("Rs. "+ item.getPrice());

//        final TextView q = (TextView)rowView.findViewById(R.id.q) ;
//        q.setText(Integer.toString(quantity[position]));

//        Button b1 = (Button)rowView.findViewById(R.id.b1);
//        Button b2 = (Button)rowView.findViewById(R.id.b2);
//
//        res = myDb.getAllData();
//
//
//
//        b1.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                if(quantity[position]>0)
//                {
//                    --quantity[position] ;
//                }
//                q.setText(Integer.toString(quantity[position]));
//                int c = 0;
//
//                res.moveToFirst();
//                while(res.moveToNext())
//                {
//                    if(res.getString(1).equals(progNames[position]))
//                    {
//                        boolean up = myDb.updateData(Integer.toString(2+c),progNames[position],  prices[position], quantity[position]);
//                    }
//                    ++c ;
//                }
//
//            }
//        });
//        b2.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                ++quantity[position] ;
//                q.setText(Integer.toString(quantity[position]));
//                res.moveToFirst();
//                int c = 0 ;
//                while(res.moveToNext() && c<100)
//                {
//                    if(res.getString(1).equals(progNames[position]))
//                    {
//                        boolean up = myDb.updateData(Integer.toString(c+2),progNames[position],  prices[position], quantity[position]);
//                    }
//                    ++c ;
//                }
//
//            }
//        });
        return rowView ;
    }
}
