package com.project.activities;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.project.db.Room;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BookRoomAdapter extends ArrayAdapter<Room> {

    private ArrayList<Room> items;
    private Context context;

    public BookRoomAdapter(Context context, int id, ArrayList<Room> items)
    {
        super(context, id, items);
        this.items= items;
        this.context = context;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.custom_room_book, null);
        TextView name2 = (TextView) v.findViewById(R.id.txtAldultName2);
        name2.setVisibility(View.GONE);
        EditText edt2 = (EditText) v.findViewById(R.id.editTextAdultName2);
        edt2.setVisibility(View.GONE);

//        ImageView img = (ImageView)this.view.findViewById(R.id.pictureId);
//        img.setImageResource(imgSource[i]);

 //       TextView roomNo = (TextView)v.findViewById(R.id.txtTitle);
  //      roomNo.setText(roomNoText[i]);

   //     TextView roomDesc = (TextView)v.findViewById(R.id.txtDesc);
    //    roomDesc.setText(descText[i]);

//        TextView roomPrice = (TextView)this.view.findViewById(R.id.txtPrice);
//        roomPrice.setText("Description");
//
//        Button bookButton = (Button)this.view.findViewById(R.id.btnBook);
//        view = this.view;
        return v;
    }
}
