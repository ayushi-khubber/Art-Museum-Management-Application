package com.museum.model;

public class Room {
    private String id;
    private String name;
    private String exhibit;
    private int capacity;
    private boolean booked;

    public Room(String id, String name, String exhibit, int capacity) {
        this.id = id;
        this.name = name;
        this.exhibit = exhibit;
        this.capacity = capacity;
        this.booked = false;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getExhibit() { return exhibit; }
    public int getCapacity() { return capacity; }
    public boolean isBooked() { return booked; }
    public String getStatus() { return booked ? "Booked" : "Available"; }

    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setExhibit(String exhibit) { this.exhibit = exhibit; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public void setBooked(boolean booked) { this.booked = booked; }
}