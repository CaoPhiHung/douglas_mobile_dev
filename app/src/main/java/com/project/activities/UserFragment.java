package com.project.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.project.db.PortBooking;
import com.project.db.User;
import com.project.objects.Info;

import java.util.ArrayList;

/**
 * Created by 300284134 on 6/25/2018.
 */

public class UserFragment extends Fragment{
    private static final String TAG = "UserFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user,container,false);

        // list user info
        ArrayList<Info> mainList = new ArrayList<Info>();
        mainList.add(new Info("User Information"));
        mainList.addAll(User.getCurrentUser().getInfoArray());
        ListView lvUser = view.findViewById(R.id.listUserInfo);

        // activity

        ArrayList<Info> infos = new ArrayList<Info>();
        infos.add(new Info("Heading 1", "lorem"));
        infos.add(new Info("Heading 1", "lorem"));
        infos.add(new Info("Heading 1", "lorem"));
        infos.add(new Info("Heading 1", "lorem"));
        infos.add(new Info("Heading 1", "lorem"));
        infos.add(new Info("Heading 1", "lorem"));
        infos.add(new Info("Heading 1", "lorem"));
        infos.add(new Info("Heading 1", "lorem"));
        infos.add(new Info("Heading 1", "lorem"));
        mainList.add(new Info("Activites"));
        mainList.addAll(infos);

        ListInfoAdapter adaptUserInfo = new ListInfoAdapter(view.getContext(), 0, mainList);
        lvUser.setAdapter(adaptUserInfo);

        return view;
    }
}
