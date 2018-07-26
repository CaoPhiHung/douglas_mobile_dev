package com.project.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.project.db.Room;
import com.project.db.RoomBooking;
import com.project.db.Service;
import com.project.db.ServiceBooking;
import com.project.db.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 300284134 on 6/25/2018.
 */

public class RoomFragment extends Fragment{
    private static final String TAG = "OnboardFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.room_layout,container,false);

        final Spinner roomList = (Spinner) view.findViewById(R.id.roomList);
        final ArrayList<RoomBooking>  roombookings= RoomBooking.findByUserId(User.getCurrentUser().id);
        final List<String> list = new ArrayList<String>();
        for(int  i = 0; i < roombookings.size(); i++){
            RoomBooking rb = roombookings.get(i);
            list.add(Room.findRoom(rb.room_id).name);
        }

        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_list_item_1, list);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roomList.setAdapter(adp1);

        Spinner roomService = (Spinner) view.findViewById(R.id.serviceList);
        final ArrayList<Service>  roomServices= Service.getALl();
        final List<String> service_list = new ArrayList<String>();
        final List<String> service_desc = new ArrayList<String>();
        final List<String> service_price = new ArrayList<String>();
        for(int  i = 0; i < roomServices.size(); i++){
            service_list.add(roomServices.get(i).name);
            service_desc.add(roomServices.get(i).description);
            service_price.add(roomServices.get(i).price + "");
        }

        ArrayAdapter<String> adp2 = new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_list_item_1, service_list);
        adp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roomService.setAdapter(adp2);

        final TextView roomServiceName = (TextView) view.findViewById(R.id.txtRoomServiceTitle);
        final TextView serviceDescription = (TextView) view.findViewById(R.id.txtServiceDesc);
        final TextView servicePrice = (TextView) view.findViewById(R.id.txtPrice);
        roomService.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                // TODO Auto-generated method stub
                roomServiceName.setText(service_list.get(position));
                serviceDescription.setText(service_desc.get(position));
                servicePrice.setText("Price: $" + service_price.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });


        Button addService = (Button) view.findViewById(R.id.btnAdd);
        addService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServiceBooking b2 = new ServiceBooking();
                Service s = Service.findServiceByName(roomServices, roomServiceName.getText().toString());
                String selectedRoomName = roomList.getSelectedItem().toString();
                for(int  i = 0; i < roombookings.size(); i++){
                    RoomBooking rb = roombookings.get(i);
                    Room r = Room.findRoom(rb.room_id);
                    if(r.name.equalsIgnoreCase(selectedRoomName)){
                        b2.room_id = r.id;
                    }
                }
                b2.booking_date = new Date().getTime();
                b2.user_id = User.getCurrentUser().id;
                b2.service_id = s.id;
                b2.price = s.price;
                b2.save();
                Toast.makeText(view.getContext(), "Service has been add to your room", Toast.LENGTH_LONG).show();
                ArrayList<ServiceBooking> sb = ServiceBooking.findByUserId(User.getCurrentUser().id);
                Toast.makeText(view.getContext(), "Total Service has been add for this room: " + sb.size(), Toast.LENGTH_LONG).show();
             }
        });
        return view;

    }
}
