package com.project.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.project.db.Invoice;
import com.project.db.PortBooking;
import com.project.db.RoomBooking;
import com.project.db.User;
import com.project.objects.Info;
import com.project.objects.InfoBooking;
import com.project.objects.InfoUser;

import java.util.ArrayList;

/**
 * Created by 300284134 on 6/25/2018.
 */

public class UserFragment extends Fragment{
    private static final String TAG = "UserFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_user,container,false);
        User currentUser = User.getCurrentUser();

        // list user info
        ArrayList<Info> mainList = new ArrayList<Info>();

        // add use info
        mainList.add(new InfoUser(currentUser.name, currentUser.username, currentUser.phone));

        ListView lvUser = view.findViewById(R.id.listUserInfo);

        // Port Of Call
        ArrayList<PortBooking> portBookings = PortBooking.getAllByUser(currentUser.id);
        ArrayList<Info> infos = new ArrayList<Info>();

        mainList.add(new Info("Port of calls reservation:"));
        for (PortBooking pb : portBookings){
            String label = "";
            label += pb.port.name;
            switch (pb.type){
                case PortBooking.TYPE_GROUP:
                    label += "\n" + "Group Tour";
                    break;
                case PortBooking.TYPE_PRIVATE:
                    label += "\n" + "Private Tour";
                    break;
                default:
                case PortBooking.TYPE_REGULAR:
                    label += "\n" + "Regular Tour";
                    break;
            }

            mainList.add(new InfoBooking(label, InfoBooking.BOOKING_PORT, pb.id));
        }

        // room booking
        ArrayList<RoomBooking> roomBookings = RoomBooking.findByUserId(currentUser.id);

        ListInfoAdapter adaptUserInfo = new ListInfoAdapter(view.getContext(), 0, mainList);
        lvUser.setAdapter(adaptUserInfo);

        return view;
    }
}
