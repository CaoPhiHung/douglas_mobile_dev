package com.project.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.Date;

public class OnboardActivity {
    static public String TABLE_NAME = "activity";
    static public String COLUMN_ID = "id";
    static public String COLUMN_NAME = "name";
    static public String COLUMN_DESCRIPTION = "description";
    static public String COLUMN_MAX_PEOPLE = "max_people";

    public long id;
    public String name, description;
    public int max_people;

    public ContentValues toContentValues() {
        ContentValues data = new ContentValues();
        data.put(COLUMN_ID, this.id);
        data.put(COLUMN_NAME, this.name);
        data.put(COLUMN_DESCRIPTION, this.description);
        data.put(COLUMN_MAX_PEOPLE, this.max_people);
        return data;
    }

    public static OnboardActivity convertFromCursor(Cursor cursor) {
        OnboardActivity activity = new OnboardActivity();

        activity.id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
        activity.name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
        activity.description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
        activity.max_people = cursor.getInt(cursor.getColumnIndex(COLUMN_MAX_PEOPLE));

        return activity;
    }

    public static String[] getColumnNames() {
        return new String[] {
                COLUMN_ID,
                COLUMN_NAME,
                COLUMN_DESCRIPTION,
                COLUMN_MAX_PEOPLE
        };
    }

    public static void seed(SQLiteDatabase db){

        db.execSQL("INSERT INTO " + TABLE_NAME + " (name, description, max_people) VALUES " +
                "('WINE TASTING', 'Discover libation tastings that take you from Amari to Whiskey\nTime: Everyday from 4:00pm - 7:00pm', 100), " +
                "('QUIET COVE POOL', 'Splash it up in freshwater pool, aqua play areas and waterslides designed for kids, and adults.\nTime: Everyday from 7:00am - 21:00pm', 500), " +
                "('PIRATE NIGHT', 'Feast on a pirate-themed dinner followed by a “Pirates in the Caribbean” show and deck party.\nTime: Day 2 (Jul 24) || Day 7 (Jul 29)', 2000), " +
                "('VIBE', 'Chill out, listen to music, watch TV, play group games and more with cruisers your age.Maximum 2 hours for each group booking.\nTime: Everyday from 9:00am - 10:00pm ', 20)," +
                "('SENSES SPA & SALON', 'Experience high-end salon services and treatments inside an elegant spa boasting an ocean view.\nTime: Everyday from 10:00am - 16:00pm', 3) ");

        // create booking for activity 5
        ActivityBooking b1 = new ActivityBooking();
        b1.user_id = 2;
        b1.activity_id = 5;
        b1.booking_date = new Date().getTime();
        db.insert(ActivityBooking.TABLE_NAME, null, b1.toContentValues());

        // create booking for activity 5
        ActivityBooking b2 = new ActivityBooking();
        b2.user_id = 3;
        b2.activity_id = 5;
        b2.booking_date = new Date().getTime();
        db.insert(ActivityBooking.TABLE_NAME, null, b2.toContentValues());

        // create booking for activity 5
        ActivityBooking b3 = new ActivityBooking();
        b3.user_id = 4;
        b3.activity_id = 5;
        b3.booking_date = new Date().getTime();
        db.insert(ActivityBooking.TABLE_NAME, null, b3.toContentValues());
    }

    /**
     * Retrieve all activities from database
     *
     * @return OnboardActivity[]
     */
    public static ArrayList<OnboardActivity> getAll(){
        SQLiteDatabase db = DBHelper.getDbInstance();

        Cursor cursor = db.query(TABLE_NAME, getColumnNames(), null, null, null, null, null);
        cursor.moveToFirst();
        ArrayList<OnboardActivity> activities = new ArrayList<OnboardActivity>();
        if(cursor.moveToFirst()){
            do {
                activities.add(convertFromCursor(cursor));
            } while (cursor.moveToNext());
        }
        return activities;
    }

    public static OnboardActivity get(long id){
        SQLiteDatabase db = DBHelper.getDbInstance();

        Cursor cursor = db.query(TABLE_NAME, getColumnNames(), "id = ? ", new String[] {String.valueOf(id)}, null, null, null);
        cursor.moveToFirst();

        return convertFromCursor(cursor);
    }

    public int getBookCount(){
        SQLiteDatabase db = DBHelper.getDbInstance();

        Cursor cursor = db.rawQuery("SELECT count(*) as count from " + ActivityBooking.TABLE_NAME + " where activity_id = ? ", new String[] { String.valueOf(id) });

        cursor.moveToFirst();

        return cursor.getInt(cursor.getColumnIndex("count"));
    }

    public  void save(){
        SQLiteDatabase db = DBHelper.getDbInstance();
        db.insert(TABLE_NAME, null, toContentValues());
    }
}
