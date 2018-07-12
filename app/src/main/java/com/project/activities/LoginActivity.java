package com.project.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.project.db.DBHelper;

public class LoginActivity extends AppCompatActivity {

    boolean isLogin = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LinearLayout layout = findViewById(R.id.layoutMain);

        DBHelper.initInstance(this);
    }

    public void switchScreen(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        Fragment newScreen;
        if (isLogin){
            newScreen = new RegisterFragment();
        } else {
            newScreen = new LoginFragment();
        }

        isLogin = !isLogin;
        transaction.replace(R.id.layoutMain, newScreen);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
