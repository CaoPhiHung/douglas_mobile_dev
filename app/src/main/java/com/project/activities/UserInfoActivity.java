package com.project.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class UserInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_port);

        Bundle b = getIntent().getExtras();
        int id = b.getInt("id");

        TextView tv = findViewById(R.id.txtTest);
        tv.setText("You have just open port id #" + id);
    }
}
