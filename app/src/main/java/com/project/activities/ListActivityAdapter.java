package com.project.activities;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.db.ActivityBooking;
import com.project.db.OnboardActivity;
import com.project.db.User;
import com.project.objects.ActivityItem;
import com.project.objects.ListItem;

import java.util.ArrayList;
import java.util.Date;

public class ListActivityAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList<ActivityItem> items;

    public ListActivityAdapter(Context context, ArrayList<ActivityItem> items) {
        super(context, 0, items);
        //super(context, R.layout.list_activity_layout, description);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.items = items;
    }


    public View getView(int position, View view, ViewGroup parent) {
        //LayoutInflater inflater=context.getLayoutInflater();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.list_activity_layout, null,false);

        final ActivityItem item = items.get(position);
        final User currentUser = User.getCurrentUser();

        ImageView imgView =  rowView.findViewById(R.id.actImg);
        TextView txtDesc = rowView.findViewById(R.id.actDesc);
        txtDesc.setText(item.desc);
        imgView.setImageResource(item.image);

        Button btnReserve = rowView.findViewById(R.id.btnReserve);
        btnReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnboardActivity activity = OnboardActivity.get(item.id);
                if (ActivityBooking.getCountByActivityId(item.id) < activity.max_people){
                    try{
                        ActivityBooking actBooking = new ActivityBooking();
                        actBooking.activity_id = item.id;
                        actBooking.user_id = currentUser.id;
                        actBooking.booking_date = new Date().getTime();
                        long id = actBooking.save();
                        Toast.makeText(rowView.getContext(), "Success", Toast.LENGTH_LONG).show();
                    } catch (Exception ex){
                        Toast.makeText(rowView.getContext(), "Failed!!!", Toast.LENGTH_LONG).show();
                    }
                }
                else Toast.makeText(rowView.getContext(), "Activity is full", Toast.LENGTH_LONG).show();


            }

        });
        return rowView;

    };
}
