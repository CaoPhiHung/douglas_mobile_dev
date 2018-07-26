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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

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

        final ArrayList<Room> rooms = Room.getAllAvailabelRoom();

        final ArrayList<RoomBooking>  roomBookings= RoomBooking.findByUserId(User.getCurrentUser().id);
        ArrayList<Room> filtered_rooms = new ArrayList<Room>();
        for( Room a : rooms) {
            if (a.deck == 1) {
                filtered_rooms.add(a);
            }
        }
        final RoomAdapter roomAdapter = new RoomAdapter(this, 0, filtered_rooms, roomBookings);
        ListView roomList = (ListView) findViewById(R.id.available_room);
        roomList.setAdapter(roomAdapter);

        Spinner deck = (Spinner) findViewById(R.id.deckSpinner);
        deck.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, final int position, long id) {
                // your code here
//                Toast.makeText(selectedItemView.getContext(), "" + position, Toast.LENGTH_LONG).show();
                ArrayList<Room> filtered_rooms = new ArrayList<Room>();
                for( Room a : rooms) {
                    if (a.deck == position + 1) {
                        filtered_rooms.add(a);
                    }
                }
                roomAdapter.items = filtered_rooms;

//                Toast.makeText(selectedItemView.getContext(), "Size : " + filtered_rooms.size(), Toast.LENGTH_LONG).show();
                roomAdapter.notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        final Context context = this;

        Button btnBookNext = (Button) findViewById(R.id.btnBookNext);
        final ArrayList<String> roomsId = new ArrayList<String>();



        btnBookNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(roomBookings.size() > 0){
                    Intent i = new Intent(context, BookingDetailActivity.class);
                    Bundle b = new Bundle();
                    for(int j = 0 ; j < roomBookings.size(); j++){
                        roomsId.add(roomBookings.get(j).room_id + "" );
                    }
                    b.putStringArrayList("roomsId", roomsId);
                    i.putExtras(b);
                    startActivity(i);
                    finish();
                }else {
                    Toast.makeText(view.getContext(), "Please book room", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

}
