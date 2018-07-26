package com.project.db;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ActivityBooking {
    static public String TABLE_NAME = "activity_booking";
    static public String COLUMN_USER_ID = "user_id";
    static public String COLUMN_ACTIVITY_ID = "activity_id";
    static public String COLUMN_BOOKING_DATE = "booking_date";

    public long user_id, activity_id, booking_date;
    public OnboardActivity activity;


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

        booking.user_id = cursor.getLong(cursor.getColumnIndex(COLUMN_USER_ID));
        booking.activity_id = cursor.getLong(cursor.getColumnIndex(COLUMN_ACTIVITY_ID));
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

    public long save(){
        SQLiteDatabase db = DBHelper.getDbInstance();
        return db.insert(TABLE_NAME, null, toContentValues());
    }

    public static int getCountByActivityId(long activity_id){
        SQLiteDatabase db = DBHelper.getDbInstance();

        String[] activity_ids = { String.valueOf(activity_id) };
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM activity_booking WHERE activity_id = ?", activity_ids);

        cursor.moveToFirst();
        int count = cursor.getInt( 0 );
        return count;
    }

    /**
     * Retrieve all booking from a single user
     * @param user_id
     * @return Array of bookings
     */
    public static ArrayList<ActivityBooking> getByUserId(long user_id){
        SQLiteDatabase db = DBHelper.getDbInstance();
        Cursor cursor = db.query(TABLE_NAME, getColumnNames(), "user_id = ?", new String[] {String.valueOf(user_id)}, null, null, null, null);
        cursor.moveToFirst();

        ArrayList<ActivityBooking> list = new ArrayList<>();
        if (cursor.getCount() == 0 ) return list;
        do {
            ActivityBooking booking = convertFromCursor(cursor);
            OnboardActivity activity = OnboardActivity.get(booking.activity_id);
            booking.activity = activity;
            list.add(booking);
        } while (cursor.moveToNext());

        return list;
    }

    /**
     *
     */
    public static void delete(long user_id, long activity_id){
        SQLiteDatabase db = DBHelper.getDbInstance();
        db.delete(TABLE_NAME, "user_id = ? AND activity_id = ? ", new String[] {String.valueOf(user_id), String.valueOf(activity_id)}  );
    }
}
