package com.project.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.ObjectOutput;
import java.util.ArrayList;

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
                "('SkyDrive', 'SkyRide is a bit like riding a bike — you’ll never forget it. But SkyRide is also completely unlike riding a bike, because when was the last time you biked around a ship, high above the deck and even higher above the sparkling blue sea? Zip safely around our two-lane suspended course in a pedal-powered go-mobile, in search of your biggest racing victory, a lower-body workout… or simply the greatest view.', 50), " +
                "('IMAX', 'To compare the IMAX experience to \"just seeing a movie\" is like calling a Carnival cruise \"just a vacation.\" It sounds about right... but experienced folks know better. That''s why stepping into the IMAX Theatre you''ll find on a Carnival cruise is more like taking a brief escape from life. On the other side of the amazing screen you''ll find a world of first\u00AD-run Hollywood blockbusters, recent hit films and IMAX documentaries, all in a huge format that makes normal theaters look like your smartphone screen. And the very first IMAX Theatre at sea? Nowhere else but Carnival Vista.', 100), " +
                "('Spa Carnival', 'When it comes to complete relaxation from the inside out, nothing beats a trip to the spa. From the minute you step inside, the soothing ambiance begins to work its magic. Renew yourself with premium beauty and wellness therapies, like hot stone massages, aromatherapy or full-body wraps. This is your time to be spoiled, indulged and even beautified. Lie back, close your eyes, and feel the stress sail away as your body and mind experience total tranquility. (Oh yeah, and this feel-good stuff isn''t just for the ladies — dudes, there are plenty of treatments on our menu for you too.)', 200), " +
                "('WATERWORKS', 'Wanna splish — and splash — the day away? Head on over to WaterWorks, Carnival''s onboard waterpark. First up, there''s the Twister Waterslide, hundreds of feet of spiraling awesomeness that starts you off high in the air and gets you down low with one of the fastest, wettest rides you''ll find anywhere. Select ships have side-by-side racing slides, which make serious competition seriously fun. Wear your speed suit for Speedway Splash, which features hundreds of feet of racing action, plus special lighting effects you''ll experience on the road to victory. DrainPipe ends with one major swirl of a finish, while PowerDrencher takes soaking seriously — imagine the biggest bucket of water you''ve ever seen, raining down on you from above. And this isn''t just kids'' stuff — the young at heart are encouraged to zoom and splash around too! (WaterWorks configurations vary by ship.)', 100) ");

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

    public  void save(){
        SQLiteDatabase db = DBHelper.getDbInstance();
        db.insert(TABLE_NAME, null, toContentValues());
    }
}
