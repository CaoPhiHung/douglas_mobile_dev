package com.project.objects;

import com.project.db.PortBooking;

public class InfoBooking extends Info {
    public static final int BOOKING_PORT = 1;
    public static final int BOOKING_ROOM = 2;
    public static final int BOOKING_SERVICE = 3;

    public int bookingType;
    public long bookingId;

    public InfoBooking() {
        super();
    }

    public InfoBooking(String heading, int bookingType, long bookingId) {
        super();
        this.heading = heading;
        this.type = Info.TYPE_BOOKING;
        this.bookingType = bookingType;
        this.bookingId = bookingId;
    }

    public void cancel() {
        if (this.bookingType == BOOKING_PORT){
            PortBooking.delete(bookingId);
        } else if (this.bookingType == BOOKING_ROOM){

        } else if (this.bookingType == BOOKING_SERVICE){

        }
    }
}
