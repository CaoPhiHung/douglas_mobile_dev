package com.project.activities;

import android.app.Activity;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListActivityAdapter extends ArrayAdapter {

    private final Activity context;
    private final String[] description;
    private final int[] actId;

    public ListActivityAdapter(Activity context, String[] itemname, int[] actId) {
        super(context, R.layout.list_activity_layout, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.description=itemname;
        this.actId=actId;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.list_activity_layout, null,false);

        ImageView imgView =  rowView.findViewById(R.id.actImg);
        TextView txtDesc = (TextView) rowView.findViewById(R.id.actDesc);
        imgView.setImageResource(actId[position]);
        txtDesc.setText(description[position]);

        return rowView;

    };
}
