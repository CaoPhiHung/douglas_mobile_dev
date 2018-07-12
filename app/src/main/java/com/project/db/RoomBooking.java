package com.project.db;

import android.content.ContentValues;
import android.database.Cursor;

public class RoomBooking {

    static public String TABLE_NAME = "room_booking";
    static public String COLUMN_ID = "id";
    static public String COLUMN_ROOM_ID = "room_id";
    static public String COLUMN_USER_ID = "user_id";
    static public String COLUMN_PRICE = "price";
    static public String COLUMN_NO_ADULT = "number_adult";
    static public String COLUMN_NO_CHILDREN = "number_children";
    static public String COLUMN_BOOKING_DATE = "booking_date";
    /*
     "room_id INTEGER," +
            "user_id INTEGER," +
            "price REAL," +
            "booking_date INTEGER, " +
     */

    long id, room_id, user_id, booking_date, number_adult, number_children;
    double price;

    public ContentValues toContentValues() {
        ContentValues data = new ContentValues();
        data.put(COLUMN_ID, this.id);
        data.put(COLUMN_ROOM_ID, this.room_id);
        data.put(COLUMN_USER_ID, this.user_id);
        data.put(COLUMN_PRICE, this.price);
        data.put(COLUMN_BOOKING_DATE, this.booking_date);
        data.put(COLUMN_NO_ADULT, this.number_adult);
        data.put(COLUMN_NO_CHILDREN, this.number_children);
        return data;
    }

    public static RoomBooking convertFromCursor(Cursor cursor) {
        RoomBooking booking = new RoomBooking();

        booking.id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
        booking.room_id = cursor.getLong(cursor.getColumnIndex(COLUMN_ROOM_ID));
        booking.user_id = cursor.getLong(cursor.getColumnIndex(COLUMN_USER_ID));
        booking.price = cursor.getInt(cursor.getColumnIndex(COLUMN_PRICE));
        booking.booking_date = cursor.getInt(cursor.getColumnIndex(COLUMN_BOOKING_DATE));
        booking.number_adult = cursor.getInt(cursor.getColumnIndex(COLUMN_NO_ADULT));
        booking.number_children = cursor.getInt(cursor.getColumnIndex(COLUMN_NO_CHILDREN));
        return booking;
    }

    public static String[] getColumnNames() {
        return new String[] {
                COLUMN_ID,
                COLUMN_USER_ID,
                COLUMN_ROOM_ID,
                COLUMN_PRICE,
                COLUMN_BOOKING_DATE,
                COLUMN_NO_ADULT,
                COLUMN_NO_CHILDREN
        };
    }

    // TODO: implement body of this method
    public static RoomBooking[] findByUserId(long user_id){
        RoomBooking[] $bookings = new RoomBooking[1];
        return $bookings;
    }
}
