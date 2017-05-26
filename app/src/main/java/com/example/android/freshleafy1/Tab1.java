package com.example.android.freshleafy1;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.android.freshleafy1.R.layout.inflater_layout;

public class Tab1 extends Fragment {
    ArrayList<AnItem> list ;
    ListView lv ;
    MyListAdapter adapter ;

    public Tab1(ArrayList<AnItem> list){
        this.list = list ;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1, container, false);

//        list = new ArrayList<String>();
//        list.add("A");
//        list.add("B");
//        list.add("C");
//        list.add("D");
//        list.add("E");
//        list.add("F");
//        list.add("G");
//        list.add("H");
//        list.add("I");
//        list.add("J");

        lv = (ListView)rootView.findViewById(R.id.list);
        adapter = new MyListAdapter(getActivity(), list);
        lv.setAdapter(adapter);

        return rootView;
    }

}

