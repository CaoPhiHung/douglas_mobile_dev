package com.project.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Date;

/**
 * Created by 300282895 on 7/5/2018.
 */

public class Port {

    public static final String TABLE_NAME = "port";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_PRICE_CHILDREN = "price_children";
    public static final String COLUMN_ADULT = "price_adult";
    public static final String COLUMN_PRIVATE = "price_private";
    public static final String COLUMN_GROUP = "price_group";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_MAX_PEOPLE = "max_people";

    public String name;
    public String description;
    public long id;
    public double price_children;
    public double price_adult;
    public double price_private;
    public double price_group;
    public long date;
    public int max_people;

    public static String[] getColumnNames(){
        return new String[]{
                COLUMN_ID,
                COLUMN_NAME,
                COLUMN_DESCRIPTION,
                COLUMN_PRICE_CHILDREN,
                COLUMN_ADULT,
                COLUMN_PRIVATE,
                COLUMN_GROUP,
                COLUMN_DATE,
                COLUMN_MAX_PEOPLE
        };
    }

    public ContentValues toContentValues(){
        ContentValues content = new ContentValues();
        content.put(COLUMN_ID, this.id);
        content.put(COLUMN_NAME, this.name);
        content.put(COLUMN_DESCRIPTION, this.description);
        content.put(COLUMN_PRICE_CHILDREN, this.price_children);
        content.put(COLUMN_ADULT, this.price_adult);
        content.put(COLUMN_PRIVATE, this.price_private);
        content.put(COLUMN_GROUP, this.price_group);
        content.put(COLUMN_DATE, this.date);
        content.put(COLUMN_MAX_PEOPLE, this.max_people);
        return content;
    }

    public static Port convertFromCursor(Cursor cursor){
        Port port = new Port();
        port.id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
        port.name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
        port.description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
        port.price_children = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE_CHILDREN));
        port.price_adult = cursor.getDouble(cursor.getColumnIndex(COLUMN_ADULT));
        port.price_private = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRIVATE));
        port.price_group = cursor.getDouble(cursor.getColumnIndex(COLUMN_GROUP));
        port.date = cursor.getLong(cursor.getColumnIndex(COLUMN_DATE));
        port.max_people = cursor.getInt(cursor.getColumnIndex(COLUMN_MAX_PEOPLE));
        return port;
    }

    public static Port[] getPorts(){
        SQLiteDatabase db = DBHelper.getDbInstance();
        Cursor cursor = db.query(Port.TABLE_NAME, Port.getColumnNames(), null, null, null, null, null);
        cursor.moveToFirst();

        Port[] ports = new Port[cursor.getCount()];
        int i = 0;
        do {
            ports[i] = Port.convertFromCursor(cursor);
            i++;
        } while (cursor.moveToNext());

        return ports;
    }

    public static Port getPort(long id){
        SQLiteDatabase db = DBHelper.getDbInstance();

        String[] selectedArgs = {id + ""};
        Cursor cursor = db.query(Port.TABLE_NAME, Port.getColumnNames(), "id = ?", selectedArgs, null, null, null);
        cursor.moveToFirst();

        return Port.convertFromCursor(cursor);
    }

    public static void seed(SQLiteDatabase db){
        long date = Date.valueOf("2018-09-20").getTime();

        String desc1 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris in lectus aliquam, suscipit sem nec, imperdiet nisi. Proin nec vulputate nisl. Lorem ipsum dolor sit amet, consectetur adipiscing elit. ";
        String desc2 = "Curabitur sed ornare magna. Quisque consequat metus in orci porta sagittis ut in mauris. Sed ultricies sapien luctus dictum vestibulum. Quisque et mauris interdum, condimentum est et, ornare orci. Suspendisse sodales quis sem sit amet ornare. ";
        String desc3 = "Fusce at nibh enim. Sed ac tortor ac magna ornare porttitor et sed nisi. Aliquam libero velit, posuere eu lacus in, lobortis interdum metus. Integer nec neque nisi.";
        String desc4 = "Interdum et malesuada fames ac ante ipsum primis in faucibus. Pellentesque vel molestie nunc. Ut id velit et eros vulputate semper. Nullam scelerisque nibh quis magna imperdiet commodo";

        db.execSQL("INSERT INTO " + Port.TABLE_NAME + " (name, description, price_children, price_adult, price_group, price_private, date, max_people) VALUES " +
                "('Hubbard Glacier, Alaska', '" + desc1 + "', 40, 80, 100, 120, "+date+", 50), " +
                "('Icy Strait Point, Alaska', '" +desc2 + "', 45, 85, 105, 125, "+date+", 50), " +
                "('Juneau, Alaska', '" + desc3 + "', 45, 85, 105, 125, "+date+", 50)," +
                "('Hubbard Glacier, Alaska', '" + desc4 + "', 45, 85, 105, 125, "+date+", 50)");
    }
}
