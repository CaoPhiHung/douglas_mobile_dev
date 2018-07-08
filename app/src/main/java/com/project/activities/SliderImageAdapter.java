package com.project.activities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.ArrayList;

public class SliderImageAdapter extends PagerAdapter {

    Context context;
    ArrayList<Integer> images;
    LayoutInflater inflater;

    public SliderImageAdapter(Context context, ArrayList<Integer> images ) {
        this.context = context;
        this.images = images;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = this.inflater.inflate(R.layout.adapter_image_slider, container, false);

        assert view != null;
        ImageView image = view.findViewById(R.id.imageSlider);

        image.setImageResource(images.get(position));
        container.addView(view, 0);

        return view;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
//        return false;
    }
}
