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

    private Button btnTEST;

    public RoomFragment()
    {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.booking2_layout,container,false);
//        Spinner roomType = (Spinner) view.findViewById(R.id.roomTypeSpinner);
        ListView roomList = (ListView) view.findViewById(R.id.roomList);

//        View listView = inflater.inflate(R.layout.custom_room_book, null);
        ArrayList<Room>  rooms= new ArrayList<Room>();
        rooms.add(new Room());
        rooms.add(new Room());

        BookRoomAdapter roomAdapter = new BookRoomAdapter(view.getContext(), 0, rooms);
        roomList.setAdapter(roomAdapter);
        return view;

    }
}
