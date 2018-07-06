package com.project.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by 300282895 on 7/5/2018.
 */

public class Port {

    public static final String TABLE_NAME = "port";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PRICE_CHILDREN = "price_children";
    public static final String COLUMN_ADULT = "price_adult";
    public static final String COLUMN_PRIVATE = "price_private";
    public static final String COLUMN_GROUP = "price_group";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_MAX_PEOPLE = "max_people";

    public String name;
    public long id;
    public double price_children;
    public double price_adult;
    public double price_private;
    public double price_group;
    public long date;
    public int max_people;

    public static String[] getColumnNames(){
        return new String[]{
                COLUMN_ID,
                COLUMN_NAME,
                COLUMN_PRICE_CHILDREN,
                COLUMN_ADULT,
                COLUMN_PRIVATE,
                COLUMN_GROUP,
                COLUMN_DATE,
                COLUMN_MAX_PEOPLE
        };
    }

    public ContentValues toContentValues(){
        ContentValues content = new ContentValues();
        content.put(COLUMN_ID, this.id);
        content.put(COLUMN_NAME, this.name);
        content.put(COLUMN_PRICE_CHILDREN, this.price_children);
        content.put(COLUMN_ADULT, this.price_adult);
        content.put(COLUMN_PRIVATE, this.price_private);
        content.put(COLUMN_GROUP, this.price_group);
        content.put(COLUMN_DATE, this.date);
        content.put(COLUMN_MAX_PEOPLE, this.max_people);
        return content;
    }

    public static Port convertFromCursor(Cursor cursor){
        Port port = new Port();
        port.id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
        port.name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
        port.price_children = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE_CHILDREN));
        port.price_adult = cursor.getDouble(cursor.getColumnIndex(COLUMN_ADULT));
        port.price_private = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRIVATE));
        port.price_group = cursor.getDouble(cursor.getColumnIndex(COLUMN_GROUP));
        port.date = cursor.getLong(cursor.getColumnIndex(COLUMN_DATE));
        port.max_people = cursor.getInt(cursor.getColumnIndex(COLUMN_MAX_PEOPLE));
        return port;
    }

    public static Port[] getPorts(SQLiteDatabase db){
        Cursor cursor = db.query(Port.TABLE_NAME, Port.getColumnNames(), null, null, null, null, null);
        cursor.moveToFirst();

        Port[] ports = new Port[cursor.getCount()];
        int i = 0;
        do {
            ports[i] = Port.convertFromCursor(cursor);
            i++;
        } while (cursor.moveToNext());

        return ports;
    }
}
