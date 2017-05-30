package com.example.android.freshleafy1;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;


public class ItemLoader extends AsyncTaskLoader<ArrayList<AnItem>> {
    public ItemLoader(Context context) {
        super(context);
    }

    @Override
    public ArrayList<AnItem> loadInBackground() {
        ArrayList<AnItem> list = Utils.fetchData("http://ec2-54-212-231-194.us-west-2.compute.amazonaws.com/internGetQuery");
        return list ;
    }
}
