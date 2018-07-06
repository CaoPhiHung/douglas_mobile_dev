package com.project.activities;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.project.db.DBHelper;
import com.project.db.Port;

public class PortActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_port);

        Bundle b = getIntent().getExtras();
        int id = b.getInt("id");

        // get from database
        Port port = Port.getPort(id);

        TextView tv = findViewById(R.id.txtTest);
        tv.setText("You have just open port id #" + port.id  + ", name: " + port.name + ", price adult " + port.price_adult);
    }
}
