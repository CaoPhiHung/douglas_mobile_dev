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
        mainList.add(new Info("User Information"));
        mainList.addAll(User.getCurrentUser().getInfoArray());
        ListView lvUser = view.findViewById(R.id.listUserInfo);

        // activity
//        ArrayList<PortBooking> portBookings = PortBooking.getAllByUser(currentUser.id);
//        ArrayList<Info> infos = new ArrayList<Info>();
//        for (PortBooking pb : portBookings){
//            infos.add(new Info(pb.port.name, String.format("$%.2f", pb.price_total)));
//        }
//        mainList.add(new Info("Port of calls reservation:"));
//        mainList.addAll(infos);

        // room booking
        ArrayList<RoomBooking> roomBookings = RoomBooking.findByUserId(currentUser.id);

        ListInfoAdapter adaptUserInfo = new ListInfoAdapter(view.getContext(), 0, mainList);
        lvUser.setAdapter(adaptUserInfo);

        // button view Invoice
        Button btn = view.findViewById(R.id.btnInvoice);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(view.getContext(), InvoiceActivity.class);
                startActivity(i);
            }
        });

        return view;
    }
}
