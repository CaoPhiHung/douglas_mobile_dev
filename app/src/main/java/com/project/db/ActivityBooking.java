package com.project.db;

import android.content.ContentValues;
import android.database.Cursor;

public class ActivityBooking {
    static public String TABLE_NAME = "activity";
    static public String COLUMN_USER_ID = "user_id";
    static public String COLUMN_ACTIVITY_ID = "activity_id";
    static public String COLUMN_BOOKING_DATE = "booking_date";

    public long user_id, activity_id, booking_date;


    /**
     * is used for prepare data and save to database
     * @return
     */
    public ContentValues toContentValues() {
        ContentValues data = new ContentValues();
        data.put(COLUMN_ACTIVITY_ID, this.activity_id);
        data.put(COLUMN_USER_ID, this.user_id);
        data.put(COLUMN_BOOKING_DATE, this.booking_date);
        return data;
    }

    /**
     * take data from cursor and create a Booking object
     * @param cursor
     * @return
     */
    public static ActivityBooking convertFromCursor(Cursor cursor) {
        ActivityBooking booking = new ActivityBooking();

        booking.activity_id = cursor.getLong(cursor.getColumnIndex(COLUMN_ACTIVITY_ID));
        booking.user_id = cursor.getLong(cursor.getColumnIndex(COLUMN_USER_ID));
        booking.booking_date = cursor.getLong(cursor.getColumnIndex(COLUMN_BOOKING_DATE));

        return booking;
    }

    public static String[] getColumnNames() {
        return new String[] {
            COLUMN_USER_ID,
            COLUMN_ACTIVITY_ID,
            COLUMN_BOOKING_DATE
        };
    }
}
