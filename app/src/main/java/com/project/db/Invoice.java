package com.project.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;

public class Invoice {

    static public String TABLE_NAME = "invoice";
    static public String COLUMN_ID = "id";
    static public String COLUMN_USER_ID = "user_id";
    static public String COLUMN_DATE = "date";
    static public String COLUMN_SUBTOTAL = "subtotal";
    static public String COLUMN_TAX = "tax";
    static public String COLUMN_TOTAL = "total";

    static public double TAX_AMOUNT = 0.12;

    public long id;
    public long user_id;
    public long date;
    public double subtotal;
    public double tax;
    public double total;
    public ArrayList<InvoiceItem> invoiceItems;

    public Invoice(){
        date = new Date().getTime();
    }

    static public Invoice convertFromCursor(Cursor cursor) {
        Invoice item = new Invoice();

        item.id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
        item.user_id = cursor.getLong(cursor.getColumnIndex(COLUMN_USER_ID));
        item.date = cursor.getLong(cursor.getColumnIndex(COLUMN_DATE));
        item.subtotal = cursor.getDouble(cursor.getColumnIndex(COLUMN_SUBTOTAL));
        item.tax = cursor.getDouble(cursor.getColumnIndex(COLUMN_TAX));
        item.total = cursor.getDouble(cursor.getColumnIndex(COLUMN_TOTAL));

        return item;
    }

    public ContentValues toContentValues() {
        ContentValues data = new ContentValues();
        data.put(COLUMN_USER_ID, user_id);
        data.put(COLUMN_DATE, date);
        data.put(COLUMN_SUBTOTAL, subtotal);
        data.put(COLUMN_TAX, tax);
        data.put(COLUMN_TOTAL, total);
        return data;
    }

    static public String[] getColumnNames() {
        return new String[] {
            COLUMN_ID,
            COLUMN_USER_ID,
            COLUMN_DATE,
            COLUMN_SUBTOTAL,
            COLUMN_TAX,
            COLUMN_TOTAL
        };
    }

    public void calculate(){

    }

    public void generateInvoiceItem(String name, double price){
        invoiceItems.add(InvoiceItem.createNewInvoiceItem(id, name, price));
    }

    static public Invoice generate(long user_id){
        SQLiteDatabase db = DBHelper.getDbInstance();
        Invoice invoice = new Invoice();
        invoice.user_id = user_id;
        invoice.date = new Date().getTime();

        ContentValues data = new ContentValues();
        data.put(COLUMN_USER_ID, invoice.user_id);
        data.put(COLUMN_DATE, invoice.date);
        data.put(COLUMN_SUBTOTAL, invoice.subtotal);
        data.put(COLUMN_TAX, invoice.tax);
        data.put(COLUMN_TOTAL, invoice.total);

        db.insert(TABLE_NAME, null, data);
        return invoice;
    }

    static public boolean hasInvoice(long user_id){
        SQLiteDatabase db = DBHelper.getDbInstance();
        Cursor cursor = db.query(TABLE_NAME, getColumnNames(), "user_id = ?", new String[] { String.valueOf(user_id) }, null, null, null, null);

        // return null if there is no invoice
        return cursor.getCount() > 0 ? true : false;
    }

    static public Invoice getByUser(long user_id) {
        SQLiteDatabase db = DBHelper.getDbInstance();
        Cursor cursor = db.query(TABLE_NAME, getColumnNames(), "user_id = ?", new String[] { String.valueOf(user_id) }, null, null, null, null);

        // return null if there is no invoice
        if (cursor.getCount() == 0) return null;

        cursor.moveToFirst();
        Invoice invoice = convertFromCursor(cursor);

        // get invoice item
        invoice.invoiceItems = new ArrayList<>();
        cursor = db.query(InvoiceItem.TABLE_NAME, InvoiceItem.getColumnNames(), "invoice_id = ?", new String[] { String.valueOf(invoice.id) }, null, null, null, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0){
            do {
                invoice.invoiceItems.add(InvoiceItem.convertFromCursor(cursor));
            } while (cursor.moveToNext());
        }
        return invoice;
    }

    public static void seed(SQLiteDatabase db){
        ContentValues data = new ContentValues();
        data.put(COLUMN_USER_ID, 1);
        data.put(COLUMN_DATE, new Date().getTime());
        data.put(COLUMN_SUBTOTAL, 0);
        data.put(COLUMN_TAX, 0);
        data.put(COLUMN_TOTAL, 0);
        db.insert(TABLE_NAME, null, data);
    }

}
