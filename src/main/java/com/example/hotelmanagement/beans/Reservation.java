package com.example.hotelmanagement.beans;

import java.sql.Date;

public class Reservation {
    private int reservationId;
    private int customertId;
    private int roomId;
    private String check_inDate;
    private String check_outDate;

    public Reservation(){}
    public Reservation(int customertId, int roomId, String check_inDate, String check_outDate) {
        this.customertId = customertId;
        this.roomId = roomId;
        this.check_inDate = check_inDate;
        this.check_outDate = check_outDate;
    }

    public int getReservationId()    {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getCustomertId() {
        return customertId;
    }

    public void setCustomertId(int customertId) {
        this.customertId = customertId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getCheck_inDate() {
        return check_inDate;
    }

    public void setCheck_inDate(String check_inDate) {
        this.check_inDate = check_inDate;
    }

    public String getCheck_ouDate() {
        return check_outDate;
    }

    public void setCheck_ouDate(String check_ouDate) {
        this.check_outDate = check_ouDate;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", customertId=" + customertId +
                ", roomId=" + roomId +
                ", check_inDate='" + check_inDate + '\'' +
                ", check_ouDate='" + check_outDate + '\'' +
                '}';
    }
}
