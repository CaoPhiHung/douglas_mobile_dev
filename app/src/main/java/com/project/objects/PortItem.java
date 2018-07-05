package com.project.objects;

import android.content.Intent;
import android.view.View;

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
                Intent i = new Intent(v.getContext(), PortActivity.class);
                i.putExtra("id", id);
                v.getContext().startActivity(i);
            }
        };
    }

}
