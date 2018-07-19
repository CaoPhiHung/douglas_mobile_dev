package com.project.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.project.db.OnboardActivity;

import java.util.ArrayList;

/**
 * Created by 300284134 on 6/25/2018.
 */

public class OnboardFragment extends Fragment{
    private static final String TAG = "OnboardFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_onboard,container,false);


        ArrayList<OnboardActivity> onboardActivitiesList = OnboardActivity.getAll();

//        OnboardActivity oa = new OnboardActivity();
//        oa.save();

        onboardActivitiesList = OnboardActivity.getAll();
        Toast.makeText(view.getContext(), "Size: " + onboardActivitiesList.size(), Toast.LENGTH_LONG).show();
        return view;
    }
}
