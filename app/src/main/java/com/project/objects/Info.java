package com.project.objects;

public class Info {
    public static final int TYPE_USER_INFO = 1;
    public static final int TYPE_HEADING = 2;
    public static final int TYPE_INFO = 3;

    public String heading;
    public String value;
    public boolean isHeader;
    public int type = TYPE_INFO;

    public Info(){
    }

    public Info(String heading, String value) {
        this.heading = heading;
        this.value = value;
        this.type = TYPE_INFO;
    }

    public Info(String heading) {
        this.heading = heading;
        this.value = "";
        this.type = TYPE_HEADING;
        this.isHeader = true;
    }
}
