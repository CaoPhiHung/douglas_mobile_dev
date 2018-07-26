package com.project.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.project.db.Room;
import com.project.db.RoomBooking;
import com.project.db.User;

import java.util.ArrayList;
import java.util.Date;

public class BookingDetailActivity extends AppCompatActivity {

    private static final String TAG = "Booking Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking2_layout);

        Bundle b = this.getIntent().getExtras();
        ArrayList<String> roomsId = b.getStringArrayList("roomsId");
        final ArrayList<RoomBooking> bookedRooms = new ArrayList<RoomBooking>();
        for(int i = 0; i < roomsId.size(); i++){
            RoomBooking roomBooking = new RoomBooking();

            roomBooking.room_id = Long.parseLong(roomsId.get(i));
            Room room = Room.findRoom(roomBooking.room_id);
            roomBooking.number_adult = room.max_adult;
            roomBooking.number_children = room.max_children;
            roomBooking.user_id = User.getCurrentUser().id;
            roomBooking.price = room.price;

            bookedRooms.add(roomBooking);
        }

        BookRoomAdapter bookRoomAdapter = new BookRoomAdapter(this, 0, bookedRooms);
        ListView roomList = (ListView) findViewById(R.id.roomList);
        roomList.setAdapter(bookRoomAdapter);

        final Context context = this;
        Button btnFinish = (Button) findViewById(R.id.btnFinish);

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                  for(int i = 0; i < bookedRooms.size(); i++){
                      String adult_names = bookedRooms.get(i).adult_names + "," + bookedRooms.get(i).adult_names2;
                      String children_names = bookedRooms.get(i).children_names + "," + bookedRooms.get(i).children_names2;
                      if(bookedRooms.get(i).adult_names != null && !bookedRooms.get(i).adult_names.equals("")){
                          bookedRooms.get(i).number_adult += 1;
                      }
                      if(bookedRooms.get(i).adult_names2 != null && !bookedRooms.get(i).adult_names2.equals("")){
                          bookedRooms.get(i).number_adult += 1;
                      }
                      if(bookedRooms.get(i).children_names != null && !bookedRooms.get(i).children_names.equals("")){
                          bookedRooms.get(i).number_children += 1;
                      }
                      if(bookedRooms.get(i).children_names2 != null && !bookedRooms.get(i).children_names2.equals("")){
                          bookedRooms.get(i).number_children += 1;
                      }
                      bookedRooms.get(i).booking_date = new Date().getTime();
                      bookedRooms.get(i).save();
                  }

                Intent i = new Intent(context, HomeActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

}
