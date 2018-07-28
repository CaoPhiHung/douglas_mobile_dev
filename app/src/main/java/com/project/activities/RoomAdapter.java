package com.project.activities;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.db.Room;
import com.project.db.RoomBooking;
import com.project.db.User;

import java.util.ArrayList;
import java.util.ListIterator;

public class RoomAdapter extends ArrayAdapter<Room> {

    public ArrayList<Room> items;
    private ArrayList<RoomBooking> roomBookings;
    private Context context;

    public RoomAdapter(Context context, int id, ArrayList<Room> items, ArrayList<RoomBooking> roomBookings)
    {
        super(context, id, items);
        this.items= items;
        this.roomBookings = roomBookings;
        this.context = context;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.custom_row, null);

        TextView roomNo = (TextView) v.findViewById(R.id.txtTitle);
        roomNo.setText(this.items.get(i).name);

        TextView roomDesc = (TextView) v.findViewById(R.id.txtDesc);
        roomDesc.setText(this.items.get(i).desc);

        TextView roomPrice = (TextView) v.findViewById(R.id.txtPrice);
        roomPrice.setText("$" + Double.toString(this.items.get(i).price));

        final Button bookButton = (Button) v.findViewById(R.id.btnBook);
        final int index = i;
        Room room = this.items.get(index);
        if(room.booked == 1){
            bookButton.setEnabled(false);
            bookButton.setText("Not Available");
        }

        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(bookButton.getText().toString().equals("Book")){
                    bookButton.setText("Booked");
                    RoomBooking roomBooking = new RoomBooking();
                    roomBooking.room_id = items.get(index).id;
                    roomBookings.add(roomBooking);

                }else{
                    bookButton.setText("Book");
                    for (int i = 0; i < roomBookings.size(); i++){
                        RoomBooking selectRoom = roomBookings.get(i);
                        if(selectRoom.room_id == items.get(index).id) {
                            roomBookings.remove(selectRoom);
                        }
                    }
                }

            }
        });
        return v;
    }
}
