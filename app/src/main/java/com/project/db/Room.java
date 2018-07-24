package com.project.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Room {

    static public String TABLE_NAME = "room";
    static public String COLUMN_ID = "id";
    static public String COLUMN_NAME = "name";
    static public String COLUMN_DECK = "deck";
    static public String COLUMN_MAX_ADULT = "max_adult";
    static public String COLUMN_MAX_CHILDREN = "max_children";
    static public String COLUMN_DESC = "desc";
    static public String COLUMN_TYPE = "type";
    static public String COLUMN_PRICE = "price";
    static public String COLUMN_BOOKED = "booked";

    static public int TYPE_OCEAN_VIEW = 1;
    static public int TYPE_CONCIERGE = 2;
    static public int TYPE_INSIDE = 3;
    static public int TYPE_VERANDAH = 4;

    static String[] types = {"Ocean View", "Concierge", "Inside", "Verandah"};

    public long id;
    public String name;
    public String desc;
    public int deck;
    public int max_adult;
    public int max_children;
    public int type;
    public double price;
    public int booked;

    public long save(SQLiteDatabase db){

        if (id == 0){ // insert
            long id = db.insert(TABLE_NAME, null, toContentValues());
            this.id = id;
            return id;
        } else {
            db.update(TABLE_NAME, toContentValues(), "id = ?", new String[] {String.valueOf(id)} );
            return id;
        }
    }

    public ContentValues toContentValues() {
        ContentValues data = new ContentValues();
        data.put(COLUMN_ID, this.id);
        data.put(COLUMN_NAME, this.name);
        data.put(COLUMN_TYPE, this.type);
        data.put(COLUMN_PRICE, this.price);
        data.put(COLUMN_DECK, this.deck);
        data.put(COLUMN_DESC, this.desc);
        data.put(COLUMN_MAX_ADULT, this.max_adult);
        data.put(COLUMN_MAX_CHILDREN, this.max_children);
        data.put(COLUMN_BOOKED, this.booked);
        return data;
    }

    public String roomType(){
        return types[type - 1];
    }

    public static Room convertFromCursor(Cursor cursor) {
        Room room = new Room();

        room.id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
        room.name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
        room.desc = cursor.getString(cursor.getColumnIndex(COLUMN_DESC));
        room.type = cursor.getInt(cursor.getColumnIndex(COLUMN_TYPE));
        room.price = cursor.getInt(cursor.getColumnIndex(COLUMN_PRICE));
        room.deck = cursor.getInt(cursor.getColumnIndex(COLUMN_DECK));
        room.max_adult = cursor.getInt(cursor.getColumnIndex(COLUMN_MAX_ADULT));
        room.max_children = cursor.getInt(cursor.getColumnIndex(COLUMN_MAX_CHILDREN));
        room.booked = cursor.getInt(cursor.getColumnIndex(COLUMN_BOOKED));
        return room;
    }

    public static String[] getColumnNames() {
        return new String[] {
            COLUMN_ID,
            COLUMN_NAME,
            COLUMN_DESC,
            COLUMN_TYPE,
            COLUMN_PRICE,
            COLUMN_DECK,
            COLUMN_MAX_ADULT,
            COLUMN_MAX_CHILDREN,
            COLUMN_BOOKED
        };
    }

    public static  void seed(SQLiteDatabase db){

        db.execSQL("INSERT INTO " + Room.TABLE_NAME + " (name, type, price) VALUES " +
                "('OCEAN 01', " + Room.TYPE_OCEAN_VIEW + ", 500), " +
                "('CONCIERGE 01', " + Room.TYPE_CONCIERGE + ", 300), " +
                "('INSIDE 01', " + Room.TYPE_INSIDE + ", 200), " +
                "('VERANDAH 01', " + Room.TYPE_VERANDAH+ ", 100) ");

    }

    static public Room findRoom(long id) {
        SQLiteDatabase db = DBHelper.getDbInstance();

        String[] selectedArgs = { String.valueOf(id) };
        Cursor cursor = db.query(TABLE_NAME, getColumnNames(), "id = ?", selectedArgs, null, null, null);
        cursor.moveToFirst();

        return convertFromCursor(cursor);
    }

    static public ArrayList<Room> getAllAvailabelRoom() {
        SQLiteDatabase db = DBHelper.getDbInstance();
        ArrayList<Room> availabel_rooms = new ArrayList<Room>();
        Cursor cursor = db.query(TABLE_NAME, getColumnNames(), null, null, null, null, null);
        cursor.moveToFirst();

        do{
            availabel_rooms.add(convertFromCursor(cursor));
        }while (cursor.moveToNext());

        return availabel_rooms;
    }
}
