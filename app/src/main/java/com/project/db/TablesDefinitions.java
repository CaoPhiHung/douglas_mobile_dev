package com.project.db;

/**
 * Created by 300282895 on 7/5/2018.
 */

public class TablesDefinitions {
    static final String PORT = "CREATE TABLE port (id INTEGER PRIMARY KEY, " +
            "name TEXT, " +
            "price_children REAL, " +
            "price_adult REAL, " +
            "price_group REAL, " +
            "price_private REAL," +
            "date INTEGER," +
            "max_people INTEGER)";

    static final String PORT_BOOKING = "CREATE TABLE port_booking (id INTEGER PRIMARY KEY, " +
            "port_id INTEGER, " +
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
            "FOREIGN KEY (port_id) REFERENCES port(id) ON DELETE CASCADE)";

    static final String PORT_DROP = "DROP TABLE port";
    static final String PORT_BOOKING_DROP = "DROP TABLE port_booking";

}
