package com.project.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class User {

    static public String TABLE_NAME = "user";
    static public String COLUMN_ID = "id";
    static public String COLUMN_NAME = "name";
    static public String COLUMN_USERNAME = "username";
    static public String COLUMN_PASSWORD= "password";
    static public String COLUMN_PHONE = "phone";

    static User currentUser;

    public long id;
    public String name;
    public String username;
    public String password;
    public String phone;

    static public User getCurrentUser(){
        if (currentUser == null){
            currentUser = findUser(1);
        }
        return currentUser;
    }

    public ContentValues toContentValues() {
        ContentValues data = new ContentValues();
        data.put(COLUMN_ID, this.id);
        data.put(COLUMN_NAME, this.name);
        data.put(COLUMN_USERNAME, this.name);
        data.put(COLUMN_PASSWORD, this.name);
        data.put(COLUMN_PHONE, this.phone);
        return data;
    }

    public static User convertFromCursor(Cursor cursor) {
        User user = new User();

        user.id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
        user.name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
        user.username = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME));
        user.password = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));
        user.phone = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE));
        return user;
    }

    public static String[] getColumnNames(){
        return new String[] {
            COLUMN_ID,
            COLUMN_NAME,
            COLUMN_USERNAME,
            COLUMN_PASSWORD,
            COLUMN_PHONE
        };
    }

    static User findUser(long id){
        SQLiteDatabase db = DBHelper.getDbInstance();

        String[] selectedArgs = { String.valueOf(id) };
        Cursor cursor = db.query(TABLE_NAME, getColumnNames(), "id = ?", selectedArgs, null, null, null);
        cursor.moveToFirst();

        return convertFromCursor(cursor);
    }
}
