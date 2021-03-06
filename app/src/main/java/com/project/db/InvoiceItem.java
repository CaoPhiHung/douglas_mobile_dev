package com.project.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class InvoiceItem {

    static public String TABLE_NAME = "invoice_item";
    static public String COLUMN_ID = "id";
    static public String COLUMN_INVOICE_ID = "invoice_id";
    static public String COLUMN_NAME = "name";
    static public String COLUMN_PRICE = "price";

    public long id;
    public long invoice_id;
    public String name;
    public double price;

    public InvoiceItem() {
    }

    public InvoiceItem(long invoice_id, String name, double price) {
        this.invoice_id = invoice_id;
        this.name = name;
        this.price = price;
    }

    public static String[] getColumnNames(){
        return new String[] {
            COLUMN_ID,
            COLUMN_INVOICE_ID,
            COLUMN_NAME,
            COLUMN_PRICE
        };
    }

    public long save(){
        SQLiteDatabase db = DBHelper.getDbInstance();
        ContentValues data = new ContentValues();
        data.put(COLUMN_INVOICE_ID, invoice_id);
        data.put(COLUMN_NAME, name);
        data.put(COLUMN_PRICE, price);

        if (id == 0){
            id = db.insert(TABLE_NAME, null, data);
            return id;
        } else {
            id = db.update(TABLE_NAME, data, "id = ? ", new String[] {String.valueOf(id)} );
            return id;
        }
    }

    public ContentValues toContentValues(){
        ContentValues data = new ContentValues();
        data.put(COLUMN_INVOICE_ID, invoice_id);
        data.put(COLUMN_NAME, name);
        data.put(COLUMN_PRICE, price);
        return data;
    }

    public static InvoiceItem createNewInvoiceItem(long invoice_id, String name, double price){
        InvoiceItem item = new InvoiceItem();
        item.invoice_id = invoice_id;
        item.name = name;
        item.price = price;
        item.id = item.save();

        return item;
    }

    public static InvoiceItem convertFromCursor(Cursor cursor){
        InvoiceItem item = new InvoiceItem();

        item.id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
        item.invoice_id = cursor.getLong(cursor.getColumnIndex(COLUMN_INVOICE_ID));
        item.name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
        item.price = cursor.getLong(cursor.getColumnIndex(COLUMN_PRICE));

        return item;
    }

    public static InvoiceItem get(long id){
        SQLiteDatabase db = DBHelper.getDbInstance();
        Cursor cursor = db.query(TABLE_NAME, getColumnNames(), "id = ?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor.getCount() == 0)
            return new InvoiceItem();
        cursor.moveToFirst();
        return convertFromCursor(cursor);
    }
}
