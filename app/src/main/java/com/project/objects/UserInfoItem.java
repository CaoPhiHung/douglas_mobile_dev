package com.project.objects;

import android.content.Intent;
import android.view.View;

import com.project.activities.PortActivity;
import com.project.activities.UserInfoActivity;

public class UserInfoItem extends ListItem {

    public UserInfoItem(int id, String title, int image) {
        super(id, title, image);
    }

    @Override
    public View.OnClickListener getListener() {
        final int id = this.id;

        return new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), UserInfoActivity.class);
                i.putExtra("id", id);
                v.getContext().startActivity(i);
            }
        };
    }

}
