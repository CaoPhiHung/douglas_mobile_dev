package com.project.activities;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.project.db.DBHelper;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private SectionAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d(TAG, "onCreate: Starting.");
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("My Cruise");
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

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        toolbar.setTitle("My Cruise");
                        break;
                    case 1:
                        toolbar.setTitle("Ports Of Call");
                        break;
                    case 2:
                        toolbar.setTitle("Onboard Activities");
                        break;
                    case 3:
                        toolbar.setTitle("Room & Services");
                        break;
                    case 4:
                        toolbar.setTitle("My Account");
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setupViewPager(final ViewPager viewPager) {
        final SectionAdapter adapter = new SectionAdapter(getSupportFragmentManager());
        adapter.addFragment(new CruiseFragment(), "Cruise");
        adapter.addFragment(new DestinationsFragment(), "Ports");
        adapter.addFragment(new OnboardFragment(), "Onboard");
        adapter.addFragment(new RoomFragment(), "Room");

        final UserFragment userFragment = new UserFragment();
        adapter.addFragment(userFragment, "User");

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                Toast.makeText(HomeActivity.this, String.valueOf(position), Toast.LENGTH_LONG).show();
                // refresh here
                if (position == 4){
                    userFragment.initItems();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
