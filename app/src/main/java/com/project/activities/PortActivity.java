package com.project.activities;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.project.db.DBHelper;
import com.project.db.Port;
import com.project.db.PortBooking;

import java.util.ArrayList;

public class PortActivity extends AppCompatActivity {

    Port port;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_port);

        Bundle b = getIntent().getExtras();
        int id = b.getInt("id");

        // get from database
        final Port port = Port.getPort(id);
        this.port = port;

        TextView tv = findViewById(R.id.portDescription);
        tv.setText(port.description);

        ViewPager slider = (ViewPager) findViewById(R.id.slider);

        ArrayList<Integer> images = new ArrayList<Integer>();
        images.add(R.drawable.landscape0);
        images.add(R.drawable.landscape1);
        images.add(R.drawable.landscape2);
        slider.setAdapter(new SliderImageAdapter(this, images));

        // spinner
        final Spinner spinnerType = (Spinner)findViewById(R.id.type);
        final Spinner spinnerAdult = findViewById(R.id.quantityAdult);
        final Spinner spinnerChildren = findViewById(R.id.quantityChildren);
        final Spinner spinnerGroup = findViewById(R.id.quantityGroup);
        final Spinner spinnerPrivate = findViewById(R.id.quantityPrivate);
        final LinearLayout groupAdult = findViewById(R.id.groupAdult);
        final LinearLayout groupChildren = findViewById(R.id.groupChildren);
        final LinearLayout groupGroup = findViewById(R.id.groupGroup);
        final LinearLayout groupPrivate = findViewById(R.id.groupPrivate);
        final EditText txtName = findViewById(R.id.textName);
        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                groupAdult.setVisibility(View.GONE);
                groupChildren.setVisibility(View.GONE);
                groupGroup.setVisibility(View.GONE);
                groupPrivate.setVisibility(View.GONE);
                switch (position){
                    case 0:
                        groupAdult.setVisibility(View.VISIBLE);
                        groupChildren.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        groupGroup.setVisibility(View.VISIBLE);
                        break;
                    default:
                        groupPrivate.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button btnBook = findViewById(R.id.btnBook);
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PortBooking booking = new PortBooking(port.id);

                switch (spinnerType.getSelectedItemPosition()) {
                    case 0:
                        booking.quantity_adult = Integer.parseInt(spinnerAdult.getSelectedItem().toString());
                        booking.quantity_children = Integer.parseInt(spinnerChildren.getSelectedItem().toString());
                        break;
                    case 1:
                        booking.quantity_group = Integer.parseInt(spinnerGroup.getSelectedItem().toString());
                        break;
                    default:
                        booking.quantity_private = Integer.parseInt(spinnerPrivate.getSelectedItem().toString());
                }
                booking.type = spinnerType.getSelectedItem().toString();
                booking.price_adult = port.price_adult;
                booking.price_children = port.price_children;
                booking.price_group = port.price_group;
                booking.price_private = port.price_private;
                booking.calculate();
                long booking_id = booking.save();
                if (booking_id == -1){
                    Toast.makeText(PortActivity.this, "Cannot book, please try again", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(PortActivity.this, "You have successfully booked", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
