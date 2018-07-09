package com.project.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Room {

    static public String TABLE_NAME = "room";
    static public String COLUMN_ID = "id";
    static public String COLUMN_NAME = "name";
    static public String COLUMN_TYPE = "type";
    static public String COLUMN_PRICE = "price";

    static public int TYPE_OCEAN_VIEW = 1;
    static public int TYPE_CONCIERGE = 2;
    static public int TYPE_INSIDE = 3;
    static public int TYPE_VERANDAH = 4;

    static String[] types = {"Ocean View", "Concierge", "Inside", "Verandah"};

    public long id;
    public String name;
    public int type;
    public double price;

    public ContentValues toContentValues() {
        ContentValues data = new ContentValues();
        data.put(COLUMN_ID, this.id);
        data.put(COLUMN_NAME, this.name);
        data.put(COLUMN_TYPE, this.type);
        data.put(COLUMN_PRICE, this.price);
        return data;
    }

    public String roomType(){
        return types[type - 1];
    }

    public static Room convertFromCursor(Cursor cursor) {
        Room room = new Room();

        room.id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
        room.name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
        room.type = cursor.getInt(cursor.getColumnIndex(COLUMN_TYPE));
        room.price = cursor.getInt(cursor.getColumnIndex(COLUMN_PRICE));

        return room;
    }

    public static String[] getColumnNames() {
        return new String[] {
            COLUMN_ID,
            COLUMN_NAME,
            COLUMN_TYPE,
            COLUMN_PRICE
        };
    }

    static public Room findRoom(long id) {
        SQLiteDatabase db = DBHelper.getDbInstance();

        String[] selectedArgs = { String.valueOf(id) };
        Cursor cursor = db.query(TABLE_NAME, getColumnNames(), "id = ?", selectedArgs, null, null, null);
        cursor.moveToFirst();

        return convertFromCursor(cursor);
    }
}
