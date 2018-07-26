package com.project.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by 300282895 on 7/5/2018.
 */

public class Port {

    public static final String TABLE_NAME = "port";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_PRICE_CHILDREN = "price_children";
    public static final String COLUMN_ADULT = "price_adult";
    public static final String COLUMN_PRIVATE = "price_private";
    public static final String COLUMN_GROUP = "price_group";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_MAX_PEOPLE = "max_people";

    public String name;
    public String description;
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
                COLUMN_DESCRIPTION,
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
//        content.put(COLUMN_ID, this.id);
        content.put(COLUMN_NAME, this.name);
        content.put(COLUMN_DESCRIPTION, this.description);
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
        port.description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
        port.price_children = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE_CHILDREN));
        port.price_adult = cursor.getDouble(cursor.getColumnIndex(COLUMN_ADULT));
        port.price_private = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRIVATE));
        port.price_group = cursor.getDouble(cursor.getColumnIndex(COLUMN_GROUP));
        port.date = cursor.getLong(cursor.getColumnIndex(COLUMN_DATE));
        port.max_people = cursor.getInt(cursor.getColumnIndex(COLUMN_MAX_PEOPLE));
        return port;
    }

    public static ArrayList<Port> getPorts(){
        SQLiteDatabase db = DBHelper.getDbInstance();
        Cursor cursor = db.query(Port.TABLE_NAME, Port.getColumnNames(), null, null, null, null, null);
        cursor.moveToFirst();

        ArrayList<Port> ports = new ArrayList<Port>();
        do {
            ports.add(convertFromCursor(cursor));
        } while (cursor.moveToNext());

        return ports;
    }

    public static ArrayList<Port> getAll() {
        SQLiteDatabase db = DBHelper.getDbInstance();
        Cursor cursor = db.query(Port.TABLE_NAME, Port.getColumnNames(), null, null, null, null, null);
        cursor.moveToFirst();

        ArrayList<Port> ports = new ArrayList<Port>();
        do {
            ports.add(Port.convertFromCursor(cursor));
        } while (cursor.moveToNext());

        return ports;
    }

    public static Port get(long id){
        SQLiteDatabase db = DBHelper.getDbInstance();

        String[] selectedArgs = {id + ""};
        Cursor cursor = db.query(Port.TABLE_NAME, Port.getColumnNames(), "id = ?", selectedArgs, null, null, null);
        cursor.moveToFirst();

        return Port.convertFromCursor(cursor);
    }

    public static void seed(SQLiteDatabase db){
        long date = Date.valueOf("2018-09-20").getTime();

        String desc1 = "Soak up the sights and sounds of wildlife as you drift through Disenchantment Bay, gazing in awe at a 600-foot natural wonder, a colossal tidewater glacier.";
        String desc2 = "The only private cruise ship destination in America is home to the worlds largest Tlingit village, rugged beauty and an authentic Alaskan experience for travelers.";
        String desc3 = "Rich in Russian and indigenous Tlingit culture, this capital city and former Gold Rush town thrills nature lovers with its rugged mountains, sweeping glaciers and temperate rainforests.";
        String desc4 = "Known as the Salmon Capital of the World, this frontier town boasts spawning salmon, historic totem poles, a proud Native American tradition and incomparable natural beauty.";

        db.execSQL("INSERT INTO " + Port.TABLE_NAME + " (name, description, price_children, price_adult, price_group, price_private, date, max_people) VALUES " +
                "('Hubbard Glacier, Alaska', '" + desc1 + "', 40, 80, 100, 120, "+date+", 50), " +
                "('Icy Strait Point, Alaska', '" +desc2 + "', 45, 85, 105, 125, "+date+", 50), " +
                "('Juneau, Alaska', '" + desc3 + "', 45, 85, 105, 125, "+date+", 50)," +
                "('Ketchikan, Alaska', '" + desc4 + "', 45, 85, 105, 125, "+date+", 50)");
    }
}
