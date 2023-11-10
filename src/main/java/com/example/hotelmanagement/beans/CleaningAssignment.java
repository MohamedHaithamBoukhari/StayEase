package com.example.hotelmanagement.beans;

import java.sql.Date;

public class CleaningAssignment {
    private int cleaningId;
    private int employeeId;
    private int roomId;
    private String date;
    public CleaningAssignment(){}

    public CleaningAssignment(int employeeId, int roomId, String date) {
        this.employeeId = employeeId;
        this.roomId = roomId;
        this.date = date;
    }

    public int getCleaningId() {
        return cleaningId;
    }

    public void setCleaningId(int cleaningId) {
        this.cleaningId = cleaningId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CleaningAssignment{" +
                "cleaningId=" + cleaningId +
                ", employeeId=" + employeeId +
                ", roomId=" + roomId +
                ", date='" + date + '\'' +
                '}';
    }
}
