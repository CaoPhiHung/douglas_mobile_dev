package com.project.groupproject;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;

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
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionAdapter adapter = new SectionAdapter(getSupportFragmentManager());
        adapter.addFragment(new CruiseFragment(), "Cruise");
        adapter.addFragment(new DestinationsFragment(), "Destination");
        adapter.addFragment(new OnboardFragment(), "Activities");
        adapter.addFragment(new UserFragment(), "User");
        viewPager.setAdapter(adapter);
    }
}
