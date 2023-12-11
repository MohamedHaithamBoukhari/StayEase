package com.example.hotelmanagement.tablesView;

import com.example.hotelmanagement.beans.RoomType;
import com.example.hotelmanagement.dao.RoomTypeDao;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.HashMap;
import java.util.Map;

public class ReservationTableView {
    private static Integer NBR=1;
    private Object i;
    private Object reservationId;
    private Object reservationDate;
    private Object check_inDate;
    private Object check_outDate;
    private Object duration;
    private Object roomNbr;
    private Object roomType;
    private Object price_day;
    private Object price;
    private Object resStatus;
    private Object capacity;

    public ReservationTableView(Object reservationId, Object reservationDate, Object check_inDate, Object check_outDate, Object roomNbr, Object roomtype, Object resStatus,Object capacity) {
        this.i = NBR;
        this.reservationId = reservationId;
        this.reservationDate = reservationDate;
        this.check_inDate = check_inDate;
        this.check_outDate = check_outDate;
        this.roomNbr = roomNbr;
        this.roomType = roomtype;
        this.resStatus = resStatus;
        this.capacity = capacity;
        this.setDuration(check_inDate, check_outDate);
        this.setPrice_day(roomtype, capacity);
        this.setPrice(this.getPrice_day(), this.getDuration());
        incrementId();
    }
    public static void incrementId(){
        NBR ++;
    }

    public Object getReservationId() {
        return reservationId;
    }

    public void setReservationId(Object reservationId) {
        this.reservationId = reservationId;
    }

    public static Integer getNBR() {
        return NBR;
    }

    public static void setNBR(Integer NBR) {
        ReservationTableView.NBR = NBR;
    }

    public Object getI() {
        return i;
    }

    public void setI(Object i) {
        this.i = i;
    }

    public Object getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Object reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Object getCheck_inDate() {
        return check_inDate;
    }

    public void setCheck_inDate(Object check_inDate) {
        this.check_inDate = check_inDate;
    }

    public Object getCheck_outDate() {
        return check_outDate;
    }

    public void setCheck_outDate(Object check_outDate) {
        this.check_outDate = check_outDate;
    }

    public Object getDuration() {
        return duration;
    }

    public void setDuration(Object check_inDate, Object check_outDate) {
        LocalDate check_in = LocalDate.parse((CharSequence) check_inDate);
        LocalDate check_out = LocalDate.parse((CharSequence) check_outDate);
        this.duration = ChronoUnit.DAYS.between(check_in, check_out);
    }

    public Object getRoomNbr() {
        return roomNbr;
    }

    public void setRoomNbr(Object roomNbr) {
        this.roomNbr = roomNbr;
    }

    public Object getPrice_day() {
        return price_day;
    }

    public void setPrice_day(Object roomType, Object capacity) {
        Map<String, Object> map = new HashMap<>();
        map.put("type", roomType);
        RoomType roomtype = (RoomType)RoomTypeDao.select(map,"*").get(0);
        this.price_day =(int)roomtype.getPrice_day()*(1+(int)capacity) - ((int) capacity*40);
    }

    public Object getPrice() {
        return price;
    }

    public void setPrice(Object price_day, Object duration) {
        this.price = (int)price_day * (long)duration;
    }

    public Object getResStatus() {
        return resStatus;
    }

    public void setResStatus(Object resStatus) {
        this.resStatus = resStatus;
    }

    public Object getRoomType() {
        return roomType;
    }

    public void setRoomType(Object roomType) {
        this.roomType = roomType;
    }

    public Object getCapacity() {
        return capacity;
    }

    public void setCapacity(Object capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "ReservationTableView{" +
                "i=" + i +
                ", reservationDate=" + reservationDate +
                ", check_inDate=" + check_inDate +
                ", check_outDate=" + check_outDate +
                ", duration=" + duration +
                ", roomNbr=" + roomNbr +
                ", roomtype=" + roomType +
                ", price_day=" + price_day +
                ", price=" + price +
                ", resStatus=" + resStatus +
                ", capacity=" + capacity +
                '}';
    }
}
