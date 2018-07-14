package com.project.activities;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.project.objects.Info;

import java.util.ArrayList;

public class ListInfoAdapter extends ArrayAdapter<Info> {

    Context context;
    ArrayList<Info> items;

    public ListInfoAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Info> objects) {
        super(context, resource, objects);
        this.context = context;
        this.items = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater li = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        Info item = items.get(position);
        View view;

        if (item.isHeader){
            view = li.inflate(R.layout.list_item_header, null);
            TextView heading = view.findViewById(R.id.textHeading);
            heading.setText(items.get(position).heading);
        } else {
            view = li.inflate(R.layout.list_item_info, null);
            TextView heading = view.findViewById(R.id.textHeading);
            TextView value = view.findViewById(R.id.textValue);
            value.setText(items.get(position).value);
            heading.setText(items.get(position).heading);
        }

        return view;
    }
}
