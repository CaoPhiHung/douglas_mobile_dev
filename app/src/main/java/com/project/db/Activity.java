package com.project.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Activity {
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

    public static Activity convertFromCursor(Cursor cursor) {
        Activity activity = new Activity();

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

    /**
     * Retrieve all activities from database
     *
     * @return Activity[]
     */
    public static Activity[] getAll(){
        SQLiteDatabase db = DBHelper.getDbInstance();

        Cursor cursor = db.query(TABLE_NAME, getColumnNames(), null, null, null, null, null);
        cursor.moveToFirst();
        Activity[] activities = new Activity[cursor.getCount()];
        int count = 0;
        do {
            activities[count] = convertFromCursor(cursor) ;
            count++;
        } while (cursor.moveToNext());

        return activities;
    }
}
