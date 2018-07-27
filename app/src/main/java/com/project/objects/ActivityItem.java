package com.project.objects;

public class ActivityItem {
    public int image;
    public String desc;
    public String subdesc;
    public String time;
    public long id;

    public ActivityItem(int image, String desc, String subdesc, String time, long id) {
        this.image = image;
        this.desc = desc;
        this.subdesc = subdesc;
        this.time = time;
        this.id = id;
    }
}
