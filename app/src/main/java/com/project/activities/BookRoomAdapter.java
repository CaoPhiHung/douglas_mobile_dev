package com.project.activities;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.project.db.Room;
import com.project.db.RoomBooking;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BookRoomAdapter extends ArrayAdapter<RoomBooking> {

    private ArrayList<RoomBooking> items;
    private Context context;

    public BookRoomAdapter(Context context, int id, ArrayList<RoomBooking> items)
    {
        super(context, id, items);
        this.items= items;
        this.context = context;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Room room = Room.findRoom(items.get(i).room_id);
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.custom_room_book, null);

        TextView roomNo = (TextView) v.findViewById(R.id.txtRoomNo);
        String roomName = roomNo.getText() + room.name;
        roomNo.setText(roomName);

        final EditText adults1 = (EditText) v.findViewById(R.id.editTextAdultName1);
        final EditText adults2 = (EditText) v.findViewById(R.id.editTextAdultName2);
        final EditText children1 = (EditText) v.findViewById(R.id.editTextChildrenName1);
        final EditText children2 = (EditText) v.findViewById(R.id.editTextChildrenName2);
        final int index = i;

        adults1.setOnFocusChangeListener(new NameListenerOnFocusChange(1, adults1, index));
        adults2.setOnFocusChangeListener(new NameListenerOnFocusChange(2, adults2, index));
        children1.setOnFocusChangeListener(new NameListenerOnFocusChange(3, children1, index));
        children2.setOnFocusChangeListener(new NameListenerOnFocusChange(4, children2, index));

        return v;
    }


    public class NameListenerOnFocusChange implements View.OnFocusChangeListener {
        private int type;
        private EditText edt;
        private int index;

        NameListenerOnFocusChange(int type, EditText edt, int index){
            this.type = type;
            this.edt = edt;
            this.index = index;
        }

        @Override
        public void onFocusChange(View view, boolean b) {
            switch (type){
                case 1:
                    items.get(index).adult_names = edt.getText().toString();

                    break;
                case 2:
                    items.get(index).adult_names2 = edt.getText().toString();

                    break;
                case 3:
                    items.get(index).children_names = edt.getText().toString();

                    break;
                case 4:
                    items.get(index).children_names2 = edt.getText().toString();

                    break;
            }
        }
    }

}
