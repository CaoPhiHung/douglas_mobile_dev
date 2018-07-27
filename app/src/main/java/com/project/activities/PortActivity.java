package com.project.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.project.db.Port;
import com.project.db.PortBooking;
import com.project.db.User;
import com.project.objects.ProjectException;

import java.util.ArrayList;

public class PortActivity extends AppCompatActivity {

    Port port;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_port);

        Bundle b = getIntent().getExtras();
        long id = b.getLong("id");

        // get from database
        final Port port = Port.get(id);
        this.port = port;

        TextView tv = findViewById(R.id.portDescription);
        tv.setText(port.description);

        // update rice
        ((TextView)findViewById(R.id.txtQtyAdult)).setText(String.format("Adult ($%.2f)", port.price_adult));
        ((TextView)findViewById(R.id.txtQtyChildren)).setText(String.format("Children ($%.2f)", port.price_children));
        ((TextView)findViewById(R.id.txtQtyGroup)).setText(String.format("Quantity ($%.2f)", port.price_group));
        ((TextView)findViewById(R.id.txtQtyPrivate)).setText(String.format("Quantity ($%.2f)", port.price_private));

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
                        booking.type = PortBooking.TYPE_REGULAR;
                        break;
                    case 1:
                        booking.quantity_group = Integer.parseInt(spinnerGroup.getSelectedItem().toString());
                        booking.type = PortBooking.TYPE_GROUP;
                        break;
                    default:
                        booking.quantity_private = Integer.parseInt(spinnerPrivate.getSelectedItem().toString());
                        booking.type = PortBooking.TYPE_PRIVATE;
                }
                booking.user_id = User.getCurrentUser().id;
                booking.price_adult = port.price_adult;
                booking.price_children = port.price_children;
                booking.price_group = port.price_group;
                booking.price_private = port.price_private;

                try {
                    if (booking.type == PortBooking.TYPE_REGULAR &&
                            booking.quantity_adult == 0 && booking.quantity_children == 0){
                        throw new ProjectException("Quantity of Adult and Children can not be zero");
                    } else if (booking.type == PortBooking.TYPE_PRIVATE &&
                            booking.quantity_private == 0) {
                        throw new ProjectException("Quantity cannot be zero");
                    } else if (booking.type == PortBooking.TYPE_GROUP &&
                            booking.quantity_group == 0) {
                        throw new ProjectException("Quantity cannot be zero");
                    }

                    long booking_id = booking.save();

                    if (booking_id == -1){
                        Toast.makeText(PortActivity.this, "Cannot book, please try again", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(PortActivity.this, "You have successfully booked", Toast.LENGTH_LONG).show();
                        Intent i = new Intent();
                        setResult(Activity.RESULT_OK, i);
                        finish();
                    }

                } catch (ProjectException e){
                    Toast.makeText(PortActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
