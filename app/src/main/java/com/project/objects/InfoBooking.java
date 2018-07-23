package com.project.objects;

import com.project.db.PortBooking;

public class InfoBooking extends Info {
    public static final int BOOKING_PORT = 1;
    public static final int BOOKING_ROOM = 2;
    public static final int BOOKING_SERVICE = 3;

    public int bookingType;
    public long bookingId;
    public long activityId, userId;


    public InfoBooking() {
        super();
    }

    public InfoBooking(String heading, String desc, int bookingType, long bookingId) {
        super();
        this.heading = heading;
        this.type = Info.TYPE_BOOKING;
        this.value = desc;
        this.bookingType = bookingType;
        this.bookingId = bookingId;
    }

    public InfoBooking(String heading, String desc, int bookingType, long activityId, long userId) {
        this.heading = heading;
        this.type = Info.TYPE_BOOKING;
        this.value = desc;
        this.bookingType = bookingType;
        this.activityId = activityId;
        this.userId = userId;
    }

    public void cancel() {
        if (this.bookingType == BOOKING_PORT){
            PortBooking.delete(bookingId);
        } else if (this.bookingType == BOOKING_ROOM){

        } else if (this.bookingType == BOOKING_SERVICE){

        }
    }
}
