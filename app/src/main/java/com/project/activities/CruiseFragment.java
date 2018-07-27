package com.project.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 300284134 on 6/25/2018.
 */

public class CruiseFragment extends Fragment{
    private static final String TAG = "CruiseFragment";

    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHash;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cruise,container,false);
        TextView title = view.findViewById(R.id.myItinerary);

        Spannable label = new SpannableString("  Itinerary");
        label.setSpan(new ImageSpan(view.getContext(), R.drawable.schedule,
                ImageSpan.ALIGN_BOTTOM), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        title.setText(label);


        listView = (ExpandableListView)view.findViewById(R.id.lvExp);
        initData();
        listAdapter = new ExpandableListAdapter(view.getContext(),listDataHeader,listHash);
        listView.setAdapter(listAdapter);

        //Adjust listView height to fit scrollView
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                setListViewHeight(parent, groupPosition);
                return false;
            }
        });

        return view;
    }

    private void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        listDataHeader.add("Day 1 - Jul 23:\nVancouver (BC), Canada");
        listDataHeader.add("Day 2 - Jul 24:\nAt sea");
        listDataHeader.add("Day 3 - Jul 25:\nHubbard Glacier, Alaska");
        listDataHeader.add("Day 4 - Jul 26:\nIcy Strait Point, Alaska");
        listDataHeader.add("Day 5 - Jul 27:\nJuneau, Alaska");
        listDataHeader.add("Day 6 - Jul 28:\nKetchikan, Alaska");
        listDataHeader.add("Day 7 - Jul 29:\nAt sea");
        listDataHeader.add("Day 8 - Jul 30:\nVancouver (BC), Canada");

        List<String> day1 = new ArrayList<>();
        day1.add("Guest Onboard 4:00pm");
        day1.add("Sail-Away celebration activity");

        List<String> day2 = new ArrayList<>();
        day2.add("Pirate Night event - 18:00pm");

        List<String> day3 = new ArrayList<>();
        day3.add("Guest Ashore 7:00am");
        day3.add("Guest Onboard 8:15pm");


        List<String> day4 = new ArrayList<>();
        day4.add("Guest Ashore 7:15am");
        day4.add("Guest Onboard 8:00pm");

        List<String> day5 = new ArrayList<>();
        day5.add("Guest Ashore 6:45am");
        day5.add("Guest Onboard 4:45pm");

        List<String> day6 = new ArrayList<>();
        day6.add("Guest Ashore 11:15am");
        day6.add("Guest Onboard 7:45pm");

        List<String> day7 = new ArrayList<>();
        day7.add("Pirate Night event - 18:00pm");

        List<String> day8 = new ArrayList<>();
        day8.add("Guest Ashore 8:30pm");


        listHash.put(listDataHeader.get(0),day1);
        listHash.put(listDataHeader.get(1),day2);
        listHash.put(listDataHeader.get(2),day3);
        listHash.put(listDataHeader.get(3),day4);
        listHash.put(listDataHeader.get(4),day5);
        listHash.put(listDataHeader.get(5),day6);
        listHash.put(listDataHeader.get(6),day7);
        listHash.put(listDataHeader.get(7),day8);
    }

    private void setListViewHeight(ExpandableListView listView,
                                   int group) {
        ExpandableListAdapter listAdapter = (ExpandableListAdapter) listView.getExpandableListAdapter();
        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(),
                View.MeasureSpec.EXACTLY);
        for (int i = 0; i < listAdapter.getGroupCount(); i++) {
            View groupItem = listAdapter.getGroupView(i, false, null, listView);
            groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

            totalHeight += groupItem.getMeasuredHeight();

            if (((listView.isGroupExpanded(i)) && (i != group))
                    || ((!listView.isGroupExpanded(i)) && (i == group))) {
                for (int j = 0; j < listAdapter.getChildrenCount(i); j++) {
                    View listItem = listAdapter.getChildView(i, j, false, null,
                            listView);
                    listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

                    totalHeight += listItem.getMeasuredHeight();

                }
            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        int height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getGroupCount() - 1));
        if (height < 10)
            height = 200;
        params.height = height;
        listView.setLayoutParams(params);
        listView.requestLayout();

    }
}
