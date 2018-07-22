package com.project.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.project.db.Port;
import com.project.db.PortBooking;
import com.project.db.Room;
import com.project.db.User;
import com.project.objects.ListItem;
import com.project.objects.PortItem;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by 300284134 on 6/25/2018.
 */

public class DestinationsFragment extends android.support.v4.app.Fragment{
    private static final String TAG = "DestinationsFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_destinations,container,false);

        ArrayList<Port> ports = Port.getPorts();
        ArrayList<PortBooking> bookings = PortBooking.getAllByUser(User.getCurrentUser().id);

        // view
        ArrayList<ListItem> items = new ArrayList<ListItem>();
        int[] images = {R.drawable.port_1, R.drawable.port_2};
        int count = 0;

        for (Port port : ports){
            PortItem item = new PortItem((int)port.id ,port.name, images[count % 2]);

            for (PortBooking booking : bookings){
                if (booking.port_id == port.id){
                    item.enabled = false;
                    break;
                }
            }
            items.add(item);
            count++;
        }

        ListItemAdapter adapter = new ListItemAdapter(view.getContext(), 0, items);

        ListView lv = view.findViewById(R.id.listPorts);
        lv.setAdapter(adapter);
        return view;
    }
}
