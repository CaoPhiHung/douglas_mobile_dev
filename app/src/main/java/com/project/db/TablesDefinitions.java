package com.project.db;

/**
 * Created by 300282895 on 7/5/2018.
 */

public class TablesDefinitions {
    static final String USER = "CREATE TABLE user (id INTEGER PRIMARY KEY, " +
            "name TEXT, " +
            "phone TEXT)";

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
            "user_id INTEGER" +
            "type INTEGER, " +
            "quantity_adult INTEGER, " +
            "quantity_children INTEGER, " +
            "quantity_group INTEGER, " +
            "quantity_private INTEGER, " +
            "price_adult REAL, " +
            "price_children REAL, " +
            "price_group REAL, " +
            "price_private REAL, " +
            "price_subtotal REAL, " +
            "price_tax REAL, " +
            "price_total REAL, " +
            "booking_name TEXT, " +
            "booking_date INTEGER, " +
            "FOREIGN KEY (port_id) REFERENCES port(id) ON DELETE CASCADE," +
            "FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE)";

    static final String ROOM = "CREATE TABLE room (id INTEGER PRIMARY KEY, " +
            "name TEXT," +
            "type INTEGER," +
            "price REAL)";

    static final String ROOM_BOOKING = "CREATE TABLE room_booking (id INTEGER PRIMARY KEY, " +
            "room_id INTEGER," +
            "user_id INTEGER," +
            "price REAL," +
            "FOREIGN KEY (room_id) REFERENCES room(id) ON DELETE CASCADE," +
            "FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE)";

    static final String DROP_PORT = "DROP TABLE IF EXISTS port";
    static final String DROP_PORT_BOOKING = "DROP TABLE IF EXISTS port_booking";
    static final String DROP_ROOM = "DROP TABLE IF EXISTS room";
    static final String DROP_ROOM_BOOKING = "DROP TABLE IF EXISTS room_booking";
    static final String DROP_USER = "DROP TABLE IF EXISTS user";

}
