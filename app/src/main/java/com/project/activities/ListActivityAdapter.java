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
import android.widget.ImageView;
import android.widget.TextView;

import com.project.db.OnboardActivity;
import com.project.objects.ListItem;

import java.util.List;

public class ListActivityAdapter extends ArrayAdapter {

    private Context context;
    private List<ListItem> items;
    private final String[] actDesc;
    private final int[] actId;

    public ListActivityAdapter(Context context, String[] description, int[] actId) {
        super(context, 0, description);
        //super(context, R.layout.list_activity_layout, description);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.actDesc=description;
        this.actId=actId;
    }

    public View getView(int position, View view, ViewGroup parent) {
        //LayoutInflater inflater=context.getLayoutInflater();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_activity_layout, null,false);

        ImageView imgView =  rowView.findViewById(R.id.actImg);
        TextView txtDesc = rowView.findViewById(R.id.actDesc);
        imgView.setImageResource(actId[position]);
        txtDesc.setText( actDesc[position] );

        return rowView;

    };
}
