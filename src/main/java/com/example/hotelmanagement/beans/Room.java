package com.example.hotelmanagement.beans;

public class Room {
    private int roomId;
    private int numRoom;
    private String type;
    private int capacity;
    private String status;

    public Room(){}
    public Room(int numRoom, String type, int capacity, String status) {
        this.numRoom = numRoom;
        this.type = type;
        this.capacity = capacity;
        this.status = status;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getNumRoom() {
        return numRoom;
    }

    public void setNumRoom(int numRoom) {
        this.numRoom = numRoom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", numRoom=" + numRoom +
                ", type='" + type + '\'' +
                ", capacity=" + capacity +
                ", status='" + status + '\'' +
                '}';
    }
}
