package com.project.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

public class RoomBooking {

    static public String TABLE_NAME = "room_booking";
    static public String COLUMN_ID = "id";
    static public String COLUMN_ROOM_ID = "room_id";
    static public String COLUMN_USER_ID = "user_id";
    static public String COLUMN_INVOICE_ITEM_ID = "invoice_item_id";
    static public String COLUMN_PRICE = "price";
    static public String COLUMN_NO_ADULT = "number_adult";
    static public String COLUMN_ADULT_NAMES = "adult_names";
    static public String COLUMN_NO_CHILDREN = "number_children";
    static public String COLUMN_CHILDREN_NAMES = "children_names";
    static public String COLUMN_BOOKING_DATE = "booking_date";

    public Room room;


    /*
     "room_id INTEGER," +
            "user_id INTEGER," +
            "price REAL," +
            "booking_date INTEGER, " +
     */

    public long id, room_id, user_id, booking_date, number_adult, number_children;

    public long invoice_item_id;
    public double price;
    public String adult_names, children_names;
    public String adult_names2, children_names2;

    public void save(){
        SQLiteDatabase db = DBHelper.getDbInstance();

        Invoice invoice = Invoice.getByUser(User.getCurrentUser().id);
        InvoiceItem invoiceItem;
        if (invoice_item_id == 0){
            invoiceItem = new InvoiceItem();
        } else {
            invoiceItem = InvoiceItem.get(invoice_item_id);
        }

        invoiceItem.name = Room.findRoom(room_id).name;
        invoiceItem.price = price;
        invoiceItem.invoice_id = invoice.id;
        invoice_item_id = invoiceItem.save();

        if (id == 0){ // insert
            long id = db.insert(TABLE_NAME, null, toContentValues());
            this.id = id;
        } else {
            db.update(TABLE_NAME, toContentValues(), "id = ?", new String[] {String.valueOf(id)} );
        }
    }

    public ContentValues toContentValues() {
        
        ContentValues data = new ContentValues();
        data.put(COLUMN_ROOM_ID, this.room_id);
        data.put(COLUMN_USER_ID, this.user_id);
        data.put(COLUMN_INVOICE_ITEM_ID, this.invoice_item_id);
        data.put(COLUMN_PRICE, this.price);
        data.put(COLUMN_BOOKING_DATE, this.booking_date);
        data.put(COLUMN_NO_ADULT, this.number_adult);
        data.put(COLUMN_ADULT_NAMES, this.adult_names);
        data.put(COLUMN_NO_CHILDREN, this.number_children);
        data.put(COLUMN_CHILDREN_NAMES, this.children_names);
        data.put(COLUMN_NO_ADULT, this.number_adult);
        return data;
    }

    public static RoomBooking convertFromCursor(Cursor cursor) {
        RoomBooking booking = new RoomBooking();

        booking.id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
        booking.room_id = cursor.getLong(cursor.getColumnIndex(COLUMN_ROOM_ID));
        booking.user_id = cursor.getLong(cursor.getColumnIndex(COLUMN_USER_ID));
        booking.invoice_item_id = cursor.getLong(cursor.getColumnIndex(COLUMN_INVOICE_ITEM_ID));
        booking.price = cursor.getInt(cursor.getColumnIndex(COLUMN_PRICE));
        booking.booking_date = cursor.getInt(cursor.getColumnIndex(COLUMN_BOOKING_DATE));
        booking.number_adult = cursor.getInt(cursor.getColumnIndex(COLUMN_NO_ADULT));
        booking.adult_names = cursor.getString(cursor.getColumnIndex(COLUMN_ADULT_NAMES));
        booking.number_children = cursor.getInt(cursor.getColumnIndex(COLUMN_NO_CHILDREN));
        booking.children_names = cursor.getString(cursor.getColumnIndex(COLUMN_CHILDREN_NAMES));
        return booking;
    }

    public static String[] getColumnNames() {
        return new String[] {
                COLUMN_ID,
                COLUMN_USER_ID,
                COLUMN_ROOM_ID,
                COLUMN_INVOICE_ITEM_ID,
                COLUMN_PRICE,
                COLUMN_BOOKING_DATE,
                COLUMN_NO_ADULT,
                COLUMN_ADULT_NAMES,
                COLUMN_NO_CHILDREN,
                COLUMN_CHILDREN_NAMES
        };
    }

    public static ArrayList<RoomBooking> findByUserId(long user_id){
        ArrayList<RoomBooking> bookings = new ArrayList<RoomBooking>();

        SQLiteDatabase db = DBHelper.getDbInstance();
        Cursor cursor = db.query(TABLE_NAME, RoomBooking.getColumnNames(), "user_id = ?", new String[] { String.valueOf(user_id) }, null, null, null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0) {
            do {
                RoomBooking booking = RoomBooking.convertFromCursor(cursor);
                booking.room = Room.findRoom(booking.room_id);
                bookings.add(booking);
            } while (cursor.moveToNext());
        }
        return bookings;
    }

    public static void seed(SQLiteDatabase db){
        Cursor cursor = db.query(Room.TABLE_NAME, Room.getColumnNames(), null, null, null, null, null);
        cursor.moveToFirst();
        ArrayList<Room> rooms = new ArrayList<Room>();
        do {
            rooms.add(Room.convertFromCursor(cursor));
        } while (cursor.moveToNext());

        Cursor userCursor = db.query(User.TABLE_NAME, User.getColumnNames(), "id = ?", new String[] { "1" }, null, null, null);
        userCursor.moveToFirst();
        User currentUser = User.convertFromCursor(userCursor);

        cursor = db.query(Invoice.TABLE_NAME, Invoice.getColumnNames(), "user_id = 1", null, null, null, null);
        cursor.moveToFirst();
        Invoice invoice = Invoice.convertFromCursor(cursor);

        Room r1 = rooms.get(0);
        Room r2 = rooms.get(1);
        RoomBooking b1 = new RoomBooking();
        b1.user_id = currentUser.id;
        b1.room_id = r1.id;
        b1.price = r1.price;
        b1.booking_date = new Date().getTime();
        b1.number_adult = 2;
        b1.number_children = 0;
        r1.booked = 1;
        r1.save(db);

        //
        InvoiceItem ii1 = new InvoiceItem();
        ii1.invoice_id = invoice.id;
        ii1.name = "Room: " + r1.name;
        ii1.price = b1.price;
        long id1 = db.insert(InvoiceItem.TABLE_NAME, null, ii1.toContentValues());
        ii1.id = id1;

        b1.invoice_item_id = id1;
        ContentValues b1Values = b1.toContentValues();
        db.insert(TABLE_NAME, null, b1Values);


        RoomBooking b2 = new RoomBooking();
        b2.user_id = currentUser.id;
        b2.room_id = r2.id;
        b2.price = r2.price;
        b2.booking_date = new Date().getTime();
        b2.number_adult = 2;
        b2.number_children = 0;
        b2.adult_names = "";
        b2.children_names = "";
        r2.booked = 1;
        r2.save(db);

        InvoiceItem ii2 = new InvoiceItem();
        ii2.invoice_id = invoice.id;
        ii2.name = "Room: " + r2.name;
        ii2.price = r2.price;
        long id2 = db.insert(InvoiceItem.TABLE_NAME, null, ii2.toContentValues());
        ii2.id = id2;

        b2.id = id2;
        ContentValues b2Values = b2.toContentValues();
        db.insert(TABLE_NAME, null, b2Values);
    }

    static public boolean hasBooked(long user_id){
        SQLiteDatabase db = DBHelper.getDbInstance();
        Cursor cursor = db.query(TABLE_NAME, getColumnNames(), "user_id = ?", new String[] { String.valueOf(user_id) }, null, null, null, null);

        // return null if there is no invoice
        return cursor.getCount() > 0 ? true : false;
    }

}
