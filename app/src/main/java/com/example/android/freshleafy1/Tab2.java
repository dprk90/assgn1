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

        return rootView;
    }

}

