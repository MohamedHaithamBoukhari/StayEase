package com.example.hotelmanagement.beans;

import java.sql.Date;

public class CleaningAssignment {
    private int cleaningId;
    private int employeeId;
    private int roomId;
    private Date date;

    public CleaningAssignment(int cleaningId, int employeeId, int roomId, Date date) {
        this.cleaningId = cleaningId;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
