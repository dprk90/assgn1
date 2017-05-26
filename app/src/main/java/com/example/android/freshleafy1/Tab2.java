package com.example.android.freshleafy1;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.android.freshleafy1.R.layout.inflater_layout;

public class Tab2 extends Fragment {
    ListView lv2 ;
    ArrayList<String> list ;
    ArrayAdapter adapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2, container, false);

        list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");

        lv2 = (ListView)rootView.findViewById(R.id.lv2);
        adapter = new ArrayAdapter(getActivity(), inflater_layout,R.id.tp,list);
        lv2.setAdapter(adapter);

        return rootView;
    }

}

