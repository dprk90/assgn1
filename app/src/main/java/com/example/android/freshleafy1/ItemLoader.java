package com.example.android.freshleafy1;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;


public class ItemLoader extends AsyncTaskLoader<List<AnItem>> {
    public ItemLoader(Context context) {
        super(context);
    }

    @Override
    public List<AnItem> loadInBackground() {
        ArrayList<AnItem> list = Utils.fetchEarthquakeData("http://ec2-54-212-231-194.us-west-2.compute.amazonaws.com/internGetQuery");
        return list ;
    }
}
