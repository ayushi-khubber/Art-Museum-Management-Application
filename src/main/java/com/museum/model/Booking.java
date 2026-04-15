package com.museum.model;

import java.time.LocalDate;

public class Booking {
    private String bookingId;
    private Room room;
    private String visitorName;
    private LocalDate date;

    public Booking(String bookingId, Room room, String visitorName, LocalDate date) {
        this.bookingId = bookingId;
        this.room = room;
        this.visitorName = visitorName;
        this.date = date;
    }

    public String getBookingId() { return bookingId; }
    public Room getRoom() { return room; }
    public String getVisitorName() { return visitorName; }
    public LocalDate getDate() { return date; }
}