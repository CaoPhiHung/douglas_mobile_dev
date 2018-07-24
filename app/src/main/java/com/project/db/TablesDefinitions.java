package com.project.db;

/**
 * Created by 300282895 on 7/5/2018.
 */

public class TablesDefinitions {
    static final String USER = "CREATE TABLE user (id INTEGER PRIMARY KEY, " +
            "name TEXT, " +
            "username TEXT, " +
            "password TEXT, " +
            "phone TEXT," +
            "CONSTRAINT unique_username UNIQUE (username))";

    static final String PORT = "CREATE TABLE port (id INTEGER PRIMARY KEY, " +
            "name TEXT, " +
            "description TEXT, " +
            "price_children REAL, " +
            "price_adult REAL, " +
            "price_group REAL, " +
            "price_private REAL," +
            "date INTEGER," +
            "max_people INTEGER)";

    static final String PORT_BOOKING = "CREATE TABLE port_booking (id INTEGER PRIMARY KEY, " +
            "port_id INTEGER, " +
            "user_id INTEGER," +
            "invoice_item_id INTEGER," +
            "type INTEGER, " +
            "quantity_adult INTEGER, " +
            "quantity_children INTEGER, " +
            "quantity_group INTEGER, " +
            "quantity_private INTEGER, " +
            "price_adult REAL, " +
            "price_children REAL, " +
            "price_group REAL, " +
            "price_private REAL, " +
            "booking_date INTEGER, " +
            "FOREIGN KEY (invoice_item_id) REFERENCES invoice_item(id) ON DELETE CASCADE," +
            "FOREIGN KEY (port_id) REFERENCES port(id) ON DELETE CASCADE," +
            "FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE)";

    static final String ROOM = "CREATE TABLE room (id INTEGER PRIMARY KEY, " +
            "name TEXT," +
            "desc TEXT," +
            "type INTEGER," +
            "deck INTEGER," +
            "description TEXT," +
            "max_adult INTEGER," +
            "max_children INTEGER," +
            "booked INTEGER," +
            "price REAL)";

    static final String ROOM_BOOKING = "CREATE TABLE room_booking (id INTEGER PRIMARY KEY, " +
            "room_id INTEGER," +
            "user_id INTEGER," +
            "invoice_item_id INTEGER," +
            "number_adult INTEGER," +
            "adult_names TEXT," +
            "number_children INTEGER," +
            "children_names TEXT," +
            "price REAL," +
            "booking_date INTEGER, " +
            "FOREIGN KEY (invoice_item_id) REFERENCES invoice_item(id) ON DELETE CASCADE," +
            "FOREIGN KEY (room_id) REFERENCES room(id) ON DELETE CASCADE," +
            "FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE)";

    static final String ACTIVITY = "CREATE TABLE activity (id INTEGER PRIMARY KEY, " +
            "name TEXT," +
            "description TEXT," +
            "max_people INTEGER)";

    static final String ACTIVITY_BOOKING = "CREATE TABLE activity_booking " +
            "(activity_id INTEGER," +
            "user_id INTEGER," +
            "booking_date integer," +
            "PRIMARY KEY (activity_id, user_id)," +
            "FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE," +
            "FOREIGN KEY (activity_id) REFERENCES activity(id) ON DELETE CASCADE)";


    static final String SERVICE = "CREATE TABLE service (id INTEGER PRIMARY KEY, " +
            "name TEXT," +
            "description TEXT," +
            "price REAL)";

    static final String SERVICE_BOOKING = "CREATE TABLE service_booking (id INTEGER PRIMARY KEY, " +
            "user_id INTEGER," +
            "service_id INTEGER," +
            "room_id INTEGER," +
            "booking_date INTEGER," +
            "price REAL)";

    static final String INVOICE = "CREATE TABLE invoice " +
            "(id INTEGER PRIMARY KEY," +
            "user_id INTEGER," +
            "date integer," +
            "subtotal REAL," +
            "tax REAL," +
            "total REAL," +
            "FOREIGN KEY (user_id) REFERENCES user(id))";

    static final String INVOICE_ITEM = "CREATE TABLE invoice_item " +
            "(id INTEGER PRIMARY KEY," +
            "invoice_id INTEGER," +
            "name TEXT," +
            "price REAL," +
            "FOREIGN KEY (invoice_id) REFERENCES invoice(id))";

    static final String DROP_PORT = "DROP TABLE IF EXISTS port";
    static final String DROP_PORT_BOOKING = "DROP TABLE IF EXISTS port_booking";
    static final String DROP_ROOM = "DROP TABLE IF EXISTS room";
    static final String DROP_ROOM_BOOKING = "DROP TABLE IF EXISTS room_booking";
    static final String DROP_USER = "DROP TABLE IF EXISTS user";
    static final String DROP_ACTIVITY = "DROP TABLE IF EXISTS activity";
    static final String DROP_ACTIVITY_BOOKING = "DROP TABLE IF EXISTS activity_booking";
    static final String DROP_SERVICE = "DROP TABLE IF EXISTS service";
    static final String DROP_SERVICE_BOOKING = "DROP TABLE IF EXISTS service_booking";
    static final String DROP_INVOICE_ITEM = "DROP TABLE IF EXISTS invoice_item";
    static final String DROP_INVOICE = "DROP TABLE IF EXISTS invoice";

}
