package com.project.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.project.objects.Info;
import com.project.objects.InfoBooking;
import com.project.objects.InfoUser;

import java.util.ArrayList;

public class ListInfoAdapter extends ArrayAdapter<Info> {

    Context context;
    ArrayList<Info> items;

    public ListInfoAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Info> objects) {
        super(context, resource, objects);
        this.context = context;
        this.items = objects;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater li = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        Info item = items.get(position);
        View view;
        TextView heading;
        TextView value;
        final ListInfoAdapter instance = this;

        if (item.type == Info.TYPE_USER_INFO){
            view = initUserInfo(li, position);
        } else if (item.type == Info.TYPE_BOOKING){
            view = li.inflate(R.layout.list_item_booking, null);
            final InfoBooking infoBooking = (InfoBooking) items.get(position);
            heading = view.findViewById(R.id.textHeading);
            heading.setText( infoBooking.heading );
            // event
            Button btn = view.findViewById(R.id.btnCancel);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    infoBooking.cancel();
                    items.remove(position);
                    instance.notifyDataSetChanged();
                }
            });

        } else if (item.type == Info.TYPE_HEADING) {
            view = li.inflate(R.layout.list_item_header, null);
            heading = view.findViewById(R.id.textHeading);
            heading.setText(items.get(position).heading);
        } else {
            view = li.inflate(R.layout.list_item_info, null);
            heading = view.findViewById(R.id.textHeading);
            value = view.findViewById(R.id.textValue);
            value.setText(items.get(position).value);
            heading.setText(items.get(position).heading);
        }

        return view;
    }

    public View initUserInfo(LayoutInflater li, int position){
        InfoUser infoUser = (InfoUser)items.get(position);
        View view = li.inflate(R.layout.layout_user_info, null);

        TextView textName = view.findViewById(R.id.textUser);
        textName.setText(infoUser.name);

        TextView textUsername = view.findViewById(R.id.textUsername);
        textUsername.setText(infoUser.username);

        TextView textPhone = view.findViewById(R.id.textPhone);
        textPhone.setText(infoUser.phone);

        Button btn = view.findViewById(R.id.btnViewInvoice);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), InvoiceActivity.class);
                view.getContext().startActivity(i);
            }
        });

        return view;
    }
}
