package com.project.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LinearLayout layout = findViewById(R.id.layoutMain);
        FragmentManager fragMan = getFragmentManager();
        Fragment fragmentLogin = fragMan.findFragmentById(R.id.fragLogin);

        Button btn = fragmentLogin.getView().findViewById(R.id.btnChangeToRegister);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }
}
