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

import com.project.db.Room;

import java.util.ArrayList;

public class RoomAdapter extends ArrayAdapter<Room> {

    int [] imgSource = {R.drawable.landscape0, R.drawable.landscape1, R.drawable.landscape2};
    String [] roomNoText = {"Room #1", "Room #2", "Room #3", "aaaaaa"};
    String [] descText = {"Room #1 desc", "Room #2 desc", "Room #3 desc", "aaaa"};
    private ArrayList<Room> items;
    private Context context;

    public RoomAdapter(Context context, int id, ArrayList<Room> items)
    {
        super(context, id, items);
        this.items= items;
        this.context = context;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.custom_row, null);

//        ImageView img = (ImageView)this.view.findViewById(R.id.pictureId);
//        img.setImageResource(imgSource[i]);

        TextView roomNo = (TextView)v.findViewById(R.id.txtTitle);
        roomNo.setText(roomNoText[i]);

        TextView roomDesc = (TextView)v.findViewById(R.id.txtDesc);
        roomDesc.setText(descText[i]);

//        TextView roomPrice = (TextView)this.view.findViewById(R.id.txtPrice);
//        roomPrice.setText("Description");
//
//        Button bookButton = (Button)this.view.findViewById(R.id.btnBook);
//        view = this.view;
        return v;
    }
}
