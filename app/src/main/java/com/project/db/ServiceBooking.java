package com.project.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ServiceBooking {

    static public String TABLE_NAME = "service_booking";
    static public String COLUMN_ID = "id";
    static public String COLUMN_USER_ID = "user_id";
    static public String COLUMN_ROOM_ID = "room_id";
    static public String COLUMN_SERVICE_ID = "service_id";
    static public String COLUMN_INVOICE_ITEM_ID = "invoice_item_id";
    static public String COLUMN_PRICE = "price";
    static public String COLUMN_BOOKING_DATE = "booking_date";


    public long id, user_id, room_id, service_id, booking_date, invoice_item_id;
    public double price;
    public Service service;
    public Room room;

    public ContentValues toContentValues(){
        ContentValues content = new ContentValues();
        content.put(COLUMN_USER_ID, user_id);
        content.put(COLUMN_ROOM_ID, room_id);
        content.put(COLUMN_SERVICE_ID, service_id);
        content.put(COLUMN_INVOICE_ITEM_ID, invoice_item_id);
        content.put(COLUMN_PRICE, price);
        content.put(COLUMN_BOOKING_DATE, booking_date);
        return content;
    }

    public static ServiceBooking convertFromCursor(Cursor cursor){
        ServiceBooking booking = new ServiceBooking();
        booking.id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
        booking.user_id = cursor.getLong(cursor.getColumnIndex(COLUMN_USER_ID));
        booking.room_id = cursor.getLong(cursor.getColumnIndex(COLUMN_ROOM_ID));
        booking.service_id = cursor.getLong(cursor.getColumnIndex(COLUMN_SERVICE_ID));
        booking.invoice_item_id = cursor.getLong(cursor.getColumnIndex(COLUMN_INVOICE_ITEM_ID));
        booking.price = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE));
        booking.booking_date = cursor.getLong(cursor.getColumnIndex(COLUMN_BOOKING_DATE));

        return booking;
    }

    public static String[] getColumnNames(){
        return new String[] {
                COLUMN_ID,
                COLUMN_USER_ID,
                COLUMN_ROOM_ID,
                COLUMN_SERVICE_ID,
                COLUMN_INVOICE_ITEM_ID,
                COLUMN_PRICE,
                COLUMN_BOOKING_DATE
        };
    }

    public long save(){
        SQLiteDatabase db = DBHelper.getDbInstance();

        Service service = Service.get(service_id);
        Invoice invoice = Invoice.getByUser(user_id);

        if (id == 0){
            id = db.insert(TABLE_NAME, null, toContentValues());
            InvoiceItem invoiceItem = invoice.generateInvoiceItem(service.name, price);
            invoiceItem.save();
            invoice_item_id = invoiceItem.id;
            return id;
        } else {
            db.update(TABLE_NAME, toContentValues(), "id =? ", new String[] { String.valueOf(id)} );
            return id;
        }
    }

    public static ServiceBooking get(long id){
        SQLiteDatabase db = DBHelper.getDbInstance();

        Cursor cursor = db.query(TABLE_NAME, getColumnNames(), "id = ?", new String[] { String.valueOf(id) }, null, null, null, null);
        cursor.moveToFirst();

        return convertFromCursor(cursor);
    }

    public static ArrayList<ServiceBooking> findByUserId(long user_id){
        ArrayList<ServiceBooking> bookings = new ArrayList<ServiceBooking>();

        SQLiteDatabase db = DBHelper.getDbInstance();
        Cursor cursor = db.query(TABLE_NAME, ServiceBooking.getColumnNames(), "user_id = ?", new String[] { String.valueOf(user_id) }, null, null, null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0) {
            do {
                ServiceBooking booking = ServiceBooking.convertFromCursor(cursor);
                //get service name
                Service service = Service.get(booking.service_id);
                booking.service = service;
                // get room
                booking.room = Room.findRoom(booking.room_id);
                bookings.add(booking);
            } while (cursor.moveToNext());
        }
        return bookings;
    }

}
