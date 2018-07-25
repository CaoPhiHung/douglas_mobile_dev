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

import com.project.db.ActivityBooking;
import com.project.db.Invoice;
import com.project.db.PortBooking;
import com.project.db.RoomBooking;
import com.project.db.ServiceBooking;
import com.project.db.User;
import com.project.objects.Info;
import com.project.objects.InfoBooking;
import com.project.objects.InfoUser;
import com.project.objects.Utilities;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by 300284134 on 6/25/2018.
 */

public class UserFragment extends Fragment{
    private static final String TAG = "UserFragment";

    private ListInfoAdapter adaptUserInfo;
    private View view;

    public ListInfoAdapter getAdaptUserInfo() {
        return adaptUserInfo;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_user,container,false);
        this.view = view;
        initItems();
        return view;
    }

    public void initItems(){
        User currentUser = User.getCurrentUser();

        // list user info
        ArrayList<Info> mainList = new ArrayList<Info>();

        // add use info
        mainList.add(new InfoUser(currentUser.name, currentUser.username, currentUser.phone));

        ListView lvUser = view.findViewById(R.id.listUserInfo);


        /**
         * List Rooms booked
         */
        ArrayList<RoomBooking> roomBookings = RoomBooking.findByUserId(currentUser.id);
        mainList.add(new Info("Rooms"));
        for (RoomBooking rb : roomBookings){
            mainList.add(new InfoBooking(rb.room.name, rb.number_adult + " Adults, " + rb.number_children + " Children", InfoBooking.BOOKING_ROOM, rb.id));
        }

        /**
         * List room services
         */
        mainList.add(new Info("Services"));
        ArrayList<ServiceBooking> serviceBookings = ServiceBooking.findByUserId(currentUser.id);
        for (ServiceBooking sb : serviceBookings){
            mainList.add(new InfoBooking(sb.service.name, sb.room.name, InfoBooking.BOOKING_SERVICE, sb.id));
        }
//        mainList.add(new InfoBooking("Service sample 1", "sample description", InfoBooking.BOOKING_SERVICE, 1));
//        mainList.add(new InfoBooking("Service sample 1", "sample description", InfoBooking.BOOKING_SERVICE, 1));

        /**
         * List Activity
         */
        ArrayList<ActivityBooking> actBookings = ActivityBooking.getByUserId(currentUser.id);
        mainList.add(new Info("Activities Reservation"));
        for (ActivityBooking ab : actBookings){
            mainList.add(new InfoBooking(ab.activity.name, Utilities.dateFormat(ab.booking_date), InfoBooking.BOOKING_ROOM, ab.activity_id, ab.user_id));
        }

        /**
         * list ported of call booked
         */
        ArrayList<PortBooking> portBookings = PortBooking.getAllByUser(currentUser.id);
        ArrayList<Info> infos = new ArrayList<Info>();

        mainList.add(new Info("Port of calls reservation:"));
        for (PortBooking pb : portBookings){
            String name = "";
            String desc = "";
            name += pb.port.name;
            switch (pb.type){
                case PortBooking.TYPE_GROUP:
                    desc += "Group Tour";
                    break;
                case PortBooking.TYPE_PRIVATE:
                    desc += "Private Tour";
                    break;
                default:
                case PortBooking.TYPE_REGULAR:
                    desc += "Regular Tour";
                    break;
            }

            mainList.add(new InfoBooking(name, desc, InfoBooking.BOOKING_PORT, pb.id));
        }

        /**
         * set adapter
         */
        adaptUserInfo = new ListInfoAdapter(view.getContext(), 0, mainList);
        lvUser.setAdapter(adaptUserInfo);
    }
}
