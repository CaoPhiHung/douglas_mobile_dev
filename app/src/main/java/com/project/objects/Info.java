package com.project.objects;

public class Info {
    public String heading;
    public String value;
    public boolean isHeader;

    public Info(String heading, String value) {
        this.heading = heading;
        this.value = value;
        this.isHeader = isHeader;
    }

    public Info(String heading) {
        this.heading = heading;
        this.value = "";
        this.isHeader = true;
    }
}
