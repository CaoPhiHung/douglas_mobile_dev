package com.project.activities;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;

import com.project.db.DBHelper;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private SectionAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d(TAG, "onCreate: Starting.");

        mSectionsPageAdapter = new SectionAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        //Add icons
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_cruise);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_ports);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_onboard);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_room);
        tabLayout.getTabAt(4).setIcon(R.drawable.ic_user);

        //
        DBHelper.initInstance(this);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionAdapter adapter = new SectionAdapter(getSupportFragmentManager());
        adapter.addFragment(new CruiseFragment(), "Cruise");
        adapter.addFragment(new DestinationsFragment(), "Ports");
        adapter.addFragment(new OnboardFragment(), "Onboard");
        adapter.addFragment(new RoomFragment(), "Room");
        adapter.addFragment(new UserFragment(), "User");
        viewPager.setAdapter(adapter);
    }
}
