package com.example.hotelmanagement.tablesView;

import com.example.hotelmanagement.beans.Room;
import com.example.hotelmanagement.beans.RoomType;
import com.example.hotelmanagement.dao.RoomTypeDao;
import javafx.scene.control.Button;

import java.util.HashMap;
import java.util.Map;

public class RoomsTableView {
    private static Integer NBR=1;
    private Object i;
    private Object roomId;
    private Object numRoom;
    private Object type;
    private Object capacity;
    private Object status;
    private Object price_day;//price day of type
    private Object price;//price day of type+capacity
    private Object reservationPrice;//price day of type+capacity
    private Object duration;//price day of type+capacity

    public RoomsTableView(Object roomId, Object numRoom, Object type, Object capacity, Object status, Object price_day, Object duration) {
        this.i = NBR;
        this.roomId = roomId;
        this.numRoom = numRoom;
        this.type = type;
        this.capacity = capacity;
        this.status = status;
        this.duration = duration;

        this.setPrice_day(price_day,type);
        System.out.println("price_day"+this.price_day);

        this.price = (int)this.price_day*(1+(int)capacity) - ((int) capacity*40);
        System.out.println("price"+this.price);
        System.out.println(this.price.getClass());
        System.out.println(this.capacity.getClass());
        System.out.println("duration class "+duration.getClass()+" value "+duration );
        if(duration instanceof Long){
            this.reservationPrice = (int)this.price * (Long)this.duration;
        }else if (duration instanceof  Integer){
            this.reservationPrice = (int)this.price * (Integer)this.duration;
        }
        incrementId();
    }
    public static void incrementId(){
        NBR ++;
    }

    public Object getRoomId() {
        return roomId;
    }

    public void setRoomId(Object roomId) {
        this.roomId = roomId;
    }

    public Object getNumRoom() {
        return numRoom;
    }

    public void setNumRoom(Object numRoom) {
        this.numRoom = numRoom;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public Object getCapacity() {
        return capacity;
    }

    public void setCapacity(Object capacity) {
        this.capacity = capacity;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public Object getPrice_day() {
        return price_day;
    }

    public void setPrice_day(Object price_day, Object type) {
        if(price_day != null){ // if we pass a value of priceDay we assigned it, else we select it from roomType dao
            this.price_day = price_day;
            System.out.println(price_day);
        }else {
            Map map = new HashMap<>();
            map.put("type", type);
            RoomType roomType = (RoomType)(RoomTypeDao.select(map, "*").get(0));
            this.price_day = roomType.getPrice_day();
            System.out.println( "price_day rom db  " + roomType.getPrice_day());
            System.out.println( "priceday from setter "+this.getPrice_day());
        }

    }

    public static Integer getNBR() {
        return NBR;
    }

    public static void setNBR(Integer i) {
        RoomsTableView.NBR = i;
    }

    public void setI(Object i) {
        this.i = i;
    }

    public Object getI() {
        return i;
    }

    public Object getPrice() {
        return price;
    }

    public void setPrice(Object price) {
        this.price = price;
    }

    public Object getReservationPrice() {
        return reservationPrice;
    }

    public void setReservationPrice(Object reservationPrice) {
        this.reservationPrice = reservationPrice;
    }

    @Override
    public String toString() {
        return "RoomsTableView{" +
                "i=" + i +
                ",roomId=" + roomId +
                ", numRoom=" + numRoom +
                ", type=" + type +
                ", capacity=" + capacity +
                ", status=" + status +
                ", price_day=" + price_day +
                ", price=" + price +
                ", reservationPrice=" + reservationPrice +
                '}';
    }
}
