package com.project.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.project.objects.ListItem;
import com.project.objects.PortItem;
import com.project.objects.UserInfoItem;

import java.util.ArrayList;

/**
 * Created by 300284134 on 6/25/2018.
 */

public class UserFragment extends Fragment{
    private static final String TAG = "UserFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_fragment,container,false);

        ArrayList<ListItem> items = new ArrayList<ListItem>();
        items.add(new UserInfoItem(1,"User Information", R.drawable.port_1));
        items.add(new UserInfoItem(2,"Room Assignment", R.drawable.port_2));
        items.add(new UserInfoItem(3,"Room Service", R.drawable.port_1));

        ListItemAdapter adapter = new ListItemAdapter(view.getContext(), 0, items);
        ListView lv = (ListView)view.findViewById(R.id.listUserInfo);
        lv.setAdapter(adapter);

        return view;
    }
}
