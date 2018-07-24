package com.project.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;

public class Service {

    static public String TABLE_NAME = "service";
    static public String COLUMN_ID = "id";
    static public String COLUMN_NAME = "name";
    static public String COLUMN_DESCRIPTION = "description";
    static public String COLUMN_PRICE = "price";

    long id;
    double price;
    String name, description;

    public ContentValues toContentValues(){
        ContentValues content = new ContentValues();
        content.put(COLUMN_NAME, name);
        content.put(COLUMN_DESCRIPTION, description);
        content.put(COLUMN_PRICE, price);

        return content;
    }

    public static Service convertFromCursor(Cursor cursor){
        Service service = new Service();
        service.id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
        service.name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
        service.description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
        service.price = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE));

        return service;
    }

    public static String[] getColumnNames(){
        return new String[] {
                COLUMN_ID,
                COLUMN_NAME,
                COLUMN_DESCRIPTION,
                COLUMN_PRICE
        };
    }

    public long save(){
        SQLiteDatabase db = DBHelper.getDbInstance();
        if (id == 0){
            id = db.insert(TABLE_NAME, null, toContentValues());
            return id;
        } else {
            db.update(TABLE_NAME, toContentValues(), "id =? ", new String[] { String.valueOf(id)} );
            return id;
        }
    }

    public static Service get(long id){
        SQLiteDatabase db = DBHelper.getDbInstance();

        Cursor cursor = db.query(TABLE_NAME, getColumnNames(), "id = ?", new String[] { String.valueOf(id) }, null, null, null, null);
        cursor.moveToFirst();

        return convertFromCursor(cursor);
    }


    public static ArrayList<Service> getALl(){
        SQLiteDatabase db = DBHelper.getDbInstance();

        Cursor cursor = db.query(TABLE_NAME, getColumnNames(), null, null, null, null, null, null);
        cursor.moveToFirst();

        ArrayList<Service> services = new ArrayList<>();

        if (cursor.getCount() == 0)
            return services;

        do {
            services.add(convertFromCursor(cursor));
        } while (cursor.moveToNext());

        return services;
    }


    public static void seed(SQLiteDatabase db){
        Service s1 = new Service();
        s1.name = "Cleaning";
        s1.description = "This is a cleanning service";
        s1.price = 100;
        s1.id = db.insert(TABLE_NAME, null, s1.toContentValues());

        // service booking
        ServiceBooking b1 = new ServiceBooking();
        b1.booking_date = new Date().getTime();
        b1.user_id = 1;
        b1.service_id = s1.id;
        b1.room_id = 1;
        b1.price = s1.price;
        b1.id = db.insert(TABLE_NAME, null, b1.toContentValues());

        Service s2 = new Service();
        s2.name = "Luxury Dinner";
        s2.description = "Dinner for two";
        s2.price = 120;
        db.insert(TABLE_NAME, null, s2.toContentValues());

        // service booking
        ServiceBooking b2 = new ServiceBooking();
        b2.booking_date = new Date().getTime();
        b2.user_id = 1;
        b2.service_id = s2.id;
        b2.room_id = 1;
        b2.price = s2.price;
        b2.id = db.insert(TABLE_NAME, null, b2.toContentValues());

        Service s3 = new Service();
        s3.name = "Breakfast";
        s3.description = "breakfast";
        s3.price = 60;
        db.insert(TABLE_NAME, null, s3.toContentValues());
    }
}
