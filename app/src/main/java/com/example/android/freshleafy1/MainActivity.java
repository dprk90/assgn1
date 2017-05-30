package com.example.android.freshleafy1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.freshleafy1.Database.OrderContract;
import com.example.android.freshleafy1.Database.OrderDbHelper;

import java.util.ArrayList;
import java.util.List;



import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        LoaderManager.LoaderCallbacks<ArrayList<AnItem>> {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    ListView lv1,lv2,mDrawerList ;
    ArrayList<DisplayItem> list1,list2 ;
    MyListAdapter adapter1,adapter2 ;
    OrderDbHelper mydb ;
    SQLiteDatabase db ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConnectivityManager cm = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,PlaceOrderActivity.class);
                startActivity(i);
            }
        });

        if (isConnected)
            getSupportLoaderManager().initLoader(1,null,this).forceLoad();
        else{
            Toast.makeText(this, "No Internet Connection !",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_activity_1) {
            startActivity(new Intent(getApplicationContext(),Activity_1.class));

        } else if (id == R.id.nav_activity_2) {
            startActivity(new Intent(getApplicationContext(),Activity_2.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public Loader<ArrayList<AnItem>> onCreateLoader(int id, Bundle args) {
        return new ItemLoader(MainActivity.this) ;
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<AnItem>> loader, ArrayList<AnItem> data) {

        mydb = new OrderDbHelper(this);
        db = mydb.getWritableDatabase();
        db.delete(OrderContract.OrderEntry.TABLE_NAME, null, null);

        lv1 = (ListView)findViewById(R.id.list1);
        lv2 = (ListView)findViewById(R.id.list2);
        list1 = new ArrayList<DisplayItem>();
        list2 = new ArrayList<DisplayItem>();
        for (int i=0;i<data.size();++i)
        {

            AnItem item = data.get(i);
            mydb.insertItem(db,item.getName(),item.getPrice(),item.getCat_id(),item.getItem_id());
            byte[] decodeString = Base64.decode(item.getImage(),Base64.DEFAULT);
            Bitmap decoded = BitmapFactory.decodeByteArray(decodeString,0,decodeString.length);
            if (item.getCat_id()==1)
                list1.add(new DisplayItem(item.getNameHindi(),item.getName(),decoded,item.getPrice(),item.getCat_id(),item.getItem_id()));
            else
                list2.add(new DisplayItem(item.getNameHindi(),item.getName(),decoded,item.getPrice(),item.getCat_id(),item.getItem_id()));
        }
        adapter1 = new MyListAdapter(this,list1);
        adapter2 = new MyListAdapter(this,list2);

        lv1.setAdapter(adapter1);
        lv2.setAdapter(adapter2);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<AnItem>> loader) {

    }


    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Tab1 tab1 = new Tab1();
                    return tab1;
                case 1:
                    Tab2 tab2= new Tab2();
                    return tab2;

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Vegetables(सब्जियां)";
                case 1:
                    return "Fruits(फल)";
            }
            return null;
        }
    }

}
