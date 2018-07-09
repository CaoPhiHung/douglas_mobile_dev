package com.project.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;

/**
 * Created by 300282895 on 7/5/2018.
 */
public class DBHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "groupproject";
    static final int DATABASE_VERSION = 6;

    static DBHelper instance;

    public static void initInstance(Context context){
        // get from database
        instance = new DBHelper(context);
    }

    public static SQLiteDatabase getDbInstance(){
        return instance.getWritableDatabase();
    }

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db);
        seed(db);
    }

    public void createTables(SQLiteDatabase db){
        String[] tables = {
                "PRAGMA foreign_keys = 1",
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

    public void seed(SQLiteDatabase db){
        long date = Date.valueOf("2018-09-20").getTime();

        db.execSQL("INSERT INTO " + Port.TABLE_NAME + " (name, price_children, price_adult, price_group, price_private, date, max_people) VALUES " +
                "('Hubbard Glacier, Alaska', 40, 80, 100, 120, "+date+", 50), " +
                "('Icy Strait Point, Alaska', 45, 85, 105, 125, "+date+", 50), " +
                "('Juneau, Alaska', 45, 85, 105, 125, "+date+", 50)," +
                "('Hubbard Glacier, Alaska', 45, 85, 105, 125, "+date+", 50)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        dropTables(db);
        createTables(db);
        seed(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);

        dropTables(db);
        createTables(db);
        seed(db);
    }
}
