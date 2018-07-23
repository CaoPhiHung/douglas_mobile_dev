package com.project.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.project.objects.Info;
import com.project.objects.ProjectException;

import java.security.MessageDigest;
import java.util.ArrayList;

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
    public String password_confirm;
    public String phone;

    static public User getCurrentUser(){
        return currentUser;
    }

    public ContentValues toContentValues() {
        ContentValues data = new ContentValues();
        data.put(COLUMN_NAME, this.name);
        data.put(COLUMN_USERNAME, this.username);
        data.put(COLUMN_PASSWORD, this.password);
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

    public long save(){
        ContentValues data = toContentValues();
        SQLiteDatabase db = DBHelper.getDbInstance();

        if (this.id == 0){
            data.remove(COLUMN_ID);
            long id = db.insert(TABLE_NAME, null, data);
            if (id != -1){
                this.id = id;
            }
            return id;
        } else {
            return db.update(TABLE_NAME, data, "id = ?", new String[] { String.valueOf(this.id) });
        }
    }

    static User findUser(long id){
        SQLiteDatabase db = DBHelper.getDbInstance();

        String[] selectedArgs = { String.valueOf(id) };
        Cursor cursor = db.query(TABLE_NAME, getColumnNames(), "id = ?", selectedArgs, null, null, null);
        cursor.moveToFirst();

        if (cursor.getCount() == 0)
            return null;
        else
            return convertFromCursor(cursor);
    }

    static User findByUsername(String username){
        SQLiteDatabase db = DBHelper.getDbInstance();

        String[] selectedArgs = { username };
        Cursor cursor = db.query(TABLE_NAME, getColumnNames(), "username = ?", selectedArgs, null, null, null);
        cursor.moveToFirst();

        if (cursor.getCount() == 0)
            return null;
        else
            return convertFromCursor(cursor);
    }

    /**
     *
     * @return
     */
    public void register() throws ProjectException {
        if (!password_confirm.equals(password)){
            throw new ProjectException("Password confirmation has to be matched");
        }

        User oldUser = findByUsername(username);
        if (oldUser != null)
            throw new ProjectException("This username has been used. Please choose another one");

        save();
    }

    /**
     *
     * @param username
     * @param password
     */
    public static void login(String username, String password) throws ProjectException {
        User user = findByUsername(username);
        if (user == null)
            throw new ProjectException("User is not existed");

        if (!user.password.equals(password))
            throw new ProjectException("Password is incorrect");

        currentUser = user;
    }

    /**
     * Log a user out
     */
    public static void logout(){
        currentUser = null;
    }

    public ArrayList<Info> getInfoArray(){
        ArrayList<Info> list = new ArrayList<Info>();
        list.add(new Info("Name", this.name));
        list.add(new Info("Username", this.username));
        list.add(new Info("Phone", this.phone));
        return list;
    }


}
