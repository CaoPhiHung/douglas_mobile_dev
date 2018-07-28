package com.project.objects;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {
    public static String dateFormat(long date){
        SimpleDateFormat df = new SimpleDateFormat("MMMM d, YYYY");
        return df.format(new Date(date));
    }
    public static String dateTimeFormat(long date){
        SimpleDateFormat df = new SimpleDateFormat("MMMM d, YYYY  H:m:s");
        return df.format(new Date(date));
    }
}
