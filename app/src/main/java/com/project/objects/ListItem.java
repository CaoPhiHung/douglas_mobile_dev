package com.project.objects;

import android.view.View;

public abstract class ListItem {

    public String title;
    public int image;
    public int id;
    public boolean enabled;

    public ListItem(int id, String title, int image) {
        this.title = title;
        this.image = image;
        this.id = id;
        this.enabled = true;
    }

    public abstract View.OnClickListener getListener();
}
