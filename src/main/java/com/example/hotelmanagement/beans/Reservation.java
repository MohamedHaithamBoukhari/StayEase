package com.example.hotelmanagement.beans;

import java.sql.Date;

public class Reservation {
    private int reservationId;
    private String reservationDate;
    private int customerId;
    private int roomId;
    private String check_inDate;
    private String check_outDate;
    private String status;

    public Reservation(){}
    public Reservation(String reservationDate, int customerId, int roomId, String check_inDate, String check_outDate, String status) {
        this.customerId = customerId;
        this.reservationDate = reservationDate;
        this.roomId = roomId;
        this.check_inDate = check_inDate;
        this.check_outDate = check_outDate;
        this.status = status;
    }

    public int getReservationId()    {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customertId) {
        this.customerId = customertId;
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

    public String getCheck_outDate() {
        return check_outDate;
    }

    public void setCheck_outDate(String check_outDate) {
        this.check_outDate = check_outDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ",reservation_date=" + reservationDate +
                ", customerId=" + customerId +
                ", roomId=" + roomId +
                ", check_inDate='" + check_inDate +
                ", check_ouDate='" + check_outDate +
                ", status='" + status +
                '}';
    }
}
