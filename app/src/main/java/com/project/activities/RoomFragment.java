package com.project.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.project.db.Room;

import java.util.ArrayList;

/**
 * Created by 300284134 on 6/25/2018.
 */

public class RoomFragment extends Fragment{
    private static final String TAG = "OnboardFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
<<<<<<< HEAD
        View view = inflater.inflate(R.layout.room_booking_layout,container,false);

        ListView roomList = (ListView) view.findViewById(R.id.available_room);

        View listView = inflater.inflate(R.layout.custom_row, null);
        ArrayList<Room>  rooms= Room.getAllAvailabelRoom();
=======
        View view = inflater.inflate(R.layout.booking2_layout,container,false);
//        Spinner roomType = (Spinner) view.findViewById(R.id.roomTypeSpinner);
        ListView roomList = (ListView) view.findViewById(R.id.roomList);

//        View listView = inflater.inflate(R.layout.custom_room_book, null);
        ArrayList<Room>  rooms= new ArrayList<Room>();
        rooms.add(new Room());
        rooms.add(new Room());
>>>>>>> b30fb34e6f5689192380184d6169a8888bd12b81

        BookRoomAdapter roomAdapter = new BookRoomAdapter(view.getContext(), 0, rooms);
        roomList.setAdapter(roomAdapter);

        Button btnBookNext = (Button)view.findViewById(R.id.btnBookNext);
        btnBookNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;

    }
}
