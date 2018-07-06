package com.project.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class DBInstance {
    SQLiteDatabase db;

    public DBInstance(SQLiteDatabase db) {
        this.db = db;
    }


}
