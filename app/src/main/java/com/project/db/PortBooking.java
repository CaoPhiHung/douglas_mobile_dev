package com.project.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;

public class PortBooking {

    public static final String TABLE_NAME = "port_booking";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PORT_ID = "port_id";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_QUANTITY_ADULT = "quantity_adult";
    public static final String COLUMN_QUANTITY_CHILDREN = "quantity_children";
    public static final String COLUMN_QUANTITY_GROUP = "quantity_group";
    public static final String COLUMN_QUANTITY_PRIVATE = "quantity_private";
    public static final String COLUMN_PRICE_ADULT = "price_adult";
    public static final String COLUMN_PRICE_CHILDREN = "price_children";
    public static final String COLUMN_PRICE_GROUP = "price_group";
    public static final String COLUMN_PRICE_PRIVATE = "price_private";
    public static final String COLUMN_PRICE_SUBTOTAL = "price_subtotal";
    public static final String COLUMN_PRICE_TAX = "price_tax";
    public static final String COLUMN_PRICE_TOTAL = "price_total";
    public static final String COLUMN_BOOKING_DATE = "booking_date";

    public static final int TYPE_REGULAR     = 0;
    public static final int TYPE_PRIVATE = 1;
    public static final int TYPE_GROUP = 2;

    public long id;
    public long port_id;
    public long user_id;
    public int type;
    public int quantity_adult;
    public int quantity_children;
    public int quantity_group;
    public int quantity_private;
    public double price_children;
    public double price_adult;
    public double price_private;
    public double price_group;
    public double price_subtotal;
    public double price_tax;
    public double price_total;
    public long booking_date;

    static public String getTypeName(int type){
        switch (type){
            case TYPE_GROUP:
                return "Group";
            case TYPE_REGULAR:
                return "Regular";
            case TYPE_PRIVATE:
                return "Private";
            default:
                return "Unknown";
        }
    }

    static public String[] getColumns(){
        return new String[]{
                COLUMN_ID,
                COLUMN_PORT_ID,
                COLUMN_USER_ID,
                COLUMN_TYPE,
                COLUMN_QUANTITY_ADULT,
                COLUMN_QUANTITY_CHILDREN,
                COLUMN_QUANTITY_GROUP,
                COLUMN_QUANTITY_PRIVATE,
                COLUMN_PRICE_ADULT,
                COLUMN_PRICE_CHILDREN,
                COLUMN_PRICE_GROUP,
                COLUMN_PRICE_PRIVATE,
                COLUMN_PRICE_SUBTOTAL,
                COLUMN_PRICE_TAX,
                COLUMN_PRICE_TOTAL,
                COLUMN_BOOKING_DATE
        };
    }

    public PortBooking() {
        this.booking_date = new Date().getTime();
    }

    public PortBooking(long port_id) {
        this.port_id = port_id;
        this.booking_date = new Date().getTime();

        // auto fill price
        Port port = Port.get(port_id);
        this.price_group = port.price_group;
        this.price_private = port.price_private;
        this.price_children = port.price_children;
        this.price_adult= port.price_adult;
    }

    public ContentValues toContentValues() {
        ContentValues content = new ContentValues();
        content.put(COLUMN_PORT_ID, this.port_id);
        content.put(COLUMN_USER_ID, this.user_id);
        content.put(COLUMN_TYPE, this.type);
        content.put(COLUMN_QUANTITY_ADULT, this.quantity_adult);
        content.put(COLUMN_QUANTITY_CHILDREN, this.quantity_children);
        content.put(COLUMN_QUANTITY_GROUP, this.quantity_group);
        content.put(COLUMN_QUANTITY_PRIVATE, this.quantity_private);
        content.put(COLUMN_PRICE_ADULT, this.price_adult);
        content.put(COLUMN_PRICE_CHILDREN, this.price_children);
        content.put(COLUMN_PRICE_GROUP, this.price_group);
        content.put(COLUMN_PRICE_PRIVATE, this.price_private);
        content.put(COLUMN_PRICE_SUBTOTAL, this.price_subtotal);
        content.put(COLUMN_PRICE_TAX, this.price_tax);
        content.put(COLUMN_PRICE_TOTAL, this.price_total);
        content.put(COLUMN_BOOKING_DATE, this.booking_date);

        return content;
    }

    public void calculate(){
        this.price_subtotal = (quantity_adult * price_adult) + (quantity_children * price_children) + (quantity_group * price_group) + (quantity_private * price_private);
        this.price_tax = 0.12;
        this.price_total = price_subtotal * (price_tax + 1);
    }

    public long save(){
        ContentValues data = toContentValues();
        SQLiteDatabase db = DBHelper.getDbInstance();

        if (this.id == 0){
            long id = db.insert(TABLE_NAME, null, data);
            if (id != -1){
                this.id = id;
            }
            return id;
        } else {
            return db.update(TABLE_NAME, data, "id = ?", new String[] { String.valueOf(this.id) });
        }
    }

    static public PortBooking convertFromCursor(Cursor cursor){
        PortBooking booking = new PortBooking(cursor.getLong(cursor.getColumnIndex(COLUMN_PORT_ID)));
        booking.id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
        booking.port_id = cursor.getLong(cursor.getColumnIndex(COLUMN_PORT_ID));
        booking.user_id = cursor.getLong(cursor.getColumnIndex(COLUMN_USER_ID));
        booking.type = cursor.getInt(cursor.getColumnIndex(COLUMN_TYPE));
        booking.quantity_adult = cursor.getInt(cursor.getColumnIndex(COLUMN_QUANTITY_ADULT));
        booking.quantity_children = cursor.getInt(cursor.getColumnIndex(COLUMN_QUANTITY_CHILDREN));
        booking.quantity_group = cursor.getInt(cursor.getColumnIndex(COLUMN_QUANTITY_GROUP));
        booking.quantity_private = cursor.getInt(cursor.getColumnIndex(COLUMN_QUANTITY_PRIVATE));
        booking.price_adult = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE_ADULT));
        booking.price_children = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE_CHILDREN));
        booking.price_private = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE_PRIVATE));
        booking.price_group = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE_GROUP));
        booking.price_subtotal = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE_SUBTOTAL));
        booking.price_tax = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE_SUBTOTAL));
        booking.price_total = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE_TOTAL));
        booking.booking_date = cursor.getLong(cursor.getColumnIndex(COLUMN_BOOKING_DATE));
        return booking;
    }

    static public void seed(SQLiteDatabase db){
        Cursor cursor = db.query(Port.TABLE_NAME, null, null , null, null, null, null);
        cursor.moveToFirst();
        Port port1 = Port.convertFromCursor(cursor);
        cursor.moveToNext();
        Port port2 = Port.convertFromCursor(cursor);


        PortBooking b1 = new PortBooking();
        b1.port_id = port1.id;
        b1.type = TYPE_REGULAR;
        b1.quantity_adult = 2;
        b1.quantity_children = 2;
        b1.quantity_group = 0;
        b1.quantity_private = 0;
        b1.price_adult = port1.price_adult;
        b1.price_children = port1.price_children;
        b1.price_private = port1.price_private;
        b1.price_group = port1.price_group;
        b1.calculate();
        b1.booking_date = new Date().getTime();
        db.insert(TABLE_NAME, null, b1.toContentValues());


        PortBooking b2 = new PortBooking();
        b2.port_id = port2.id;
        b2.type = TYPE_REGULAR;
        b2.quantity_adult = 2;
        b2.quantity_children = 2;
        b2.quantity_group = 0;
        b2.quantity_private = 0;
        b2.price_adult = port1.price_adult;
        b2.price_children = port1.price_children;
        b2.price_private = port1.price_private;
        b2.price_group = port1.price_group;
        b2.calculate();
        b2.booking_date = new Date().getTime();
        db.insert(TABLE_NAME, null, b2.toContentValues());

    }

    public ArrayList<PortBooking> getAllByUser(long user_id){
        SQLiteDatabase db = DBHelper.getDbInstance();
        String[] selectedArgs = {String.valueOf(user_id)};
        Cursor cursor = db.query(TABLE_NAME, getColumns(), "user_id = ?", selectedArgs, null, null, null );
        cursor.moveToFirst();
        ArrayList<PortBooking> list = new ArrayList<PortBooking>();
        while (cursor.moveToNext()){
            PortBooking pb = PortBooking.convertFromCursor(cursor);
            list.add(pb);
        }
        return list;
    }
}
