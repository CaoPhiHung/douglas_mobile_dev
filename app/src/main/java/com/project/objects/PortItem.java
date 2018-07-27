package com.project.objects;

import android.view.View;
import android.widget.Toast;

import com.project.events.StartActivityForResultListener;

public class PortItem extends ListItem {

    StartActivityForResultListener listener;

    public PortItem(int id, String title, int image) {
        super(id, title, image);
    }

    public void setListener(StartActivityForResultListener listener) {
        this.listener = listener;
    }

    @Override
    public View.OnClickListener getListener() {
        final int id = this.id;

        return new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (!enabled){
                    Toast.makeText(v.getContext(), "You have already booked this event!", Toast.LENGTH_SHORT).show();
                } else {
                    listener.startActivity(id);
                }
            }
        };
    }

}
