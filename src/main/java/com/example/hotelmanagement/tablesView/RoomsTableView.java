package com.example.hotelmanagement.tablesView;

import com.example.hotelmanagement.beans.Room;
import javafx.scene.control.Button;

public class RoomsTableView {
    private static Integer NBR=1;
    private Object i;
    private Object roomId;
    private Object numRoom;
    private Object type;
    private Object capacity;
    private Object status;
    private Object price_day;

    public RoomsTableView(Object roomId, Object numRoom, Object type, Object capacity, Object status, Object price_day) {
        this.i = NBR;
        this.roomId = roomId;
        this.numRoom = numRoom;
        this.type = type;
        this.capacity = capacity;
        this.status = status;
        this.price_day = price_day;
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

    public void setPrice_day(Object price_day) {
        this.price_day = price_day;
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
                '}';
    }
}
