package com.project.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.project.db.Room;
import com.project.db.RoomBooking;
import com.project.db.User;

import java.util.ArrayList;

public class BookingActivity extends AppCompatActivity {

    private static final String TAG = "Booking Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_booking_layout);

        ArrayList<Room> rooms= Room.getAllAvailabelRoom();
        final ArrayList<RoomBooking>  roomBookings= RoomBooking.findByUserId(User.getCurrentUser().id);
        RoomAdapter roomAdapter = new RoomAdapter(this, 0, rooms, roomBookings);
        ListView roomList = (ListView) findViewById(R.id.available_room);
        roomList.setAdapter(roomAdapter);
        final Context context = this;
        Button btnBookNext = (Button) findViewById(R.id.btnBookNext);

        btnBookNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, BookingDetailActivity.class);
                Bundle b = new Bundle();
                b.putParcelableArrayList("roomBookings", roomBookings);
                i.putExtras(b);
                startActivity(i);
            }
        });
    }

}
