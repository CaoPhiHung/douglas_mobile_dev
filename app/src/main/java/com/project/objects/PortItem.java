package com.project.objects;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.project.activities.PortActivity;

public class PortItem extends ListItem {

    public PortItem(int id, String title, int image) {
        super(id, title, image);
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
                    Intent i = new Intent(v.getContext(), PortActivity.class);
                    i.putExtra("id", id);
                    v.getContext().startActivity(i);
                }
            }
        };
    }

}
