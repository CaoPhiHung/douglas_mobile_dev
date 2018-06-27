package com.project.groupproject;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.objects.ListItem;

import java.util.ArrayList;
import java.util.List;

public class ListItemAdapter extends ArrayAdapter<ListItem> {
    private Context context;
    private List<ListItem> items;

    public ListItemAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ListItem> objects) {
        super(context, resource, objects);
        this.context = context;
        this.items = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_item_layout, null);

        ImageView imageView = view.findViewById(R.id.liImage);
        TextView textView = view.findViewById(R.id.liTitle);

        ListItem item = items.get(position);

        imageView.setImageResource(item.image);
        textView.setText(item.title);

        return view;

//        return super.getView(position, convertView, parent);
    }
}
