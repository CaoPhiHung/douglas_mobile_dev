package com.project.activities;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.project.db.DBHelper;
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
            boolean haveUser = false;
            for(int i = 0; i < bookedRooms.size(); i++){
                RoomBooking bookedRoom = bookedRooms.get(i);
                String adult_names = bookedRoom.adult_names + "," + bookedRoom.adult_names2;
                String children_names = bookedRoom.children_names + "," + bookedRoom.children_names2;
                haveUser = false;

                if(bookedRoom.adult_names != null && !bookedRoom.adult_names.equals("")){
                  bookedRoom.number_adult += 1;
                    haveUser = true;
                }

                if(bookedRoom.adult_names2 != null && !bookedRoom.adult_names2.equals("")){
                  bookedRoom.number_adult += 1;
                    haveUser = true;
                }

                if(bookedRoom.children_names != null && !bookedRoom.children_names.equals("")){
                  bookedRoom.number_children += 1;
                    haveUser = true;
                }

                if(bookedRoom.children_names2 != null && !bookedRoom.children_names2.equals("")){
                  bookedRoom.number_children += 1;
                  haveUser = true;
                }

                if(haveUser) {
                    bookedRoom.booking_date = new Date().getTime();
                    Room r = Room.findRoom(bookedRoom.room_id);
                    r.booked = 1;
                    SQLiteDatabase db = DBHelper.getDbInstance();
                    r.save(db);
                    bookedRoom.save();
                }
            }

            if(haveUser) {
                Intent i = new Intent(context, HomeActivity.class);
                startActivity(i);
                finish();
            }else{
                Toast.makeText(view.getContext(), "Please enter at least 1 name of for 1 room!", Toast.LENGTH_LONG).show();
            }
            }
        });
    }

}
