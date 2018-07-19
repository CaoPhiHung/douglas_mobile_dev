package com.project.objects;

/**
 * Created by 300282895 on 7/19/2018.
 */

public class InfoUser extends Info {
    public String name, username, phone;

    public InfoUser(String name, String username, String phone) {
        super();
        this.type = Info.TYPE_USER_INFO;
        this.name = name;
        this.username = username;
        this.phone = phone;
    }
}
