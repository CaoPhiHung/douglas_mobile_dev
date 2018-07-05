package com.project.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 300282895 on 7/5/2018.
 */
public class DBFactory extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "groupproject";
    static final int DATABASE_VERSION = 1;

    public DBFactory(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db);
    }

    public void createTables(SQLiteDatabase db){
        String[] tables = {
                TablesDefinitions.PORT,
                TablesDefinitions.PORT_BOOKING,
        };

        for (String table : tables){
            db.execSQL(table);
        }
    }

    public void dropTables(SQLiteDatabase db){
        String[] tables = {
                TablesDefinitions.PORT_DROP,
                TablesDefinitions.PORT_BOOKING_DROP,
        };

        for (String table : tables){
            db.execSQL(table);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        dropTables(db);
        createTables(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);

        dropTables(db);
        createTables(db);
    }
}
