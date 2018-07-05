package com.project.activities;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;

import com.project.groupproject.R;
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
        int[] colors = {R.color.colorBackground1,
                R.color.colorBackground2,
                R.color.colorBackground3,
                R.color.colorBackground4};
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_item_layout, null);

        view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 400));
        TextView textView = view.findViewById(R.id.liTitle);
        ImageView image = view.findViewById(R.id.liImage);

        ListItem item = items.get(position);
        textView.setText(item.title);
        textView.setLetterSpacing(0.2f);
        textView.setBackgroundResource( colors[position%4] );

        image.setImageResource(item.image);

        return view;

//        return super.getView(position, convertView, parent);
    }
}
