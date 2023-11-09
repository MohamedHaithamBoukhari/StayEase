package com.example.hotelmanagement.beans;

import java.sql.Date;

public class Reservation {
    private int reservationId;
    private int customertId;
    private int roomId;
    private Date check_inDate;
    private Date check_ouDate;

    public Reservation(int reservationId, int customertId, int roomId, Date check_inDate, Date check_ouDate) {
        this.reservationId = reservationId;
        this.customertId = customertId;
        this.roomId = roomId;
        this.check_inDate = check_inDate;
        this.check_ouDate = check_ouDate;
    }

    public int getReservationId() {
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

    public Date getCheck_inDate() {
        return check_inDate;
    }

    public void setCheck_inDate(Date check_inDate) {
        this.check_inDate = check_inDate;
    }

    public Date getCheck_ouDate() {
        return check_ouDate;
    }

    public void setCheck_ouDate(Date check_ouDate) {
        this.check_ouDate = check_ouDate;
    }
}
