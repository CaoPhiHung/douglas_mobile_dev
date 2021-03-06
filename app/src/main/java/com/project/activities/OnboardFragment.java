package com.project.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.db.OnboardActivity;
import com.project.objects.ActivityItem;

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
        int[] images = {R.drawable.act2,R.drawable.act3,R.drawable.act6,R.drawable.act5,R.drawable.act4};
        int index=0;

        ArrayList<ActivityItem> activities = new ArrayList<>();
        
        for (OnboardActivity value: onboardActivitiesList){
            int count = value.getBookCount();
            activities.add(new ActivityItem(images[index%5], value.name, String.format("Max %d people. %d booked", value.max_people, count),value.description, value.id));
            index++;
        }

        ListActivityAdapter adapter = new ListActivityAdapter(view.getContext(), activities);

        ListView listView = view.findViewById(R.id.listActivities100);
        listView.setAdapter(adapter);

        return view;

    }
}
