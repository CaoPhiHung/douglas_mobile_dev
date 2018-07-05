package com.project.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.project.groupproject.R;
import com.project.objects.ListItem;

import java.util.ArrayList;

/**
 * Created by 300284134 on 6/25/2018.
 */

public class DestinationsFragment extends android.support.v4.app.Fragment{
    private static final String TAG = "DestinationsFragment";

    private Button btnTEST;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.destinations_fragment,container,false);

        // view
        ArrayList<ListItem> items = new ArrayList<ListItem>();
        items.add(new ListItem("Alaska", R.drawable.port_1));
        items.add(new ListItem("San Francisco", R.drawable.port_2));
        items.add(new ListItem("New York", R.drawable.port_1));
        items.add(new ListItem("Houston", R.drawable.port_2));
        ListItemAdapter adapter = new ListItemAdapter(view.getContext(), 0, items);

        ListView lv = (ListView)view.findViewById(R.id.listPorts);
        lv.setAdapter(adapter);

        return view;
    }
}
