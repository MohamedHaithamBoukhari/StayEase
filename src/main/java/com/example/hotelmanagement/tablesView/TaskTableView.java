package com.example.hotelmanagement.tablesView;

import com.example.hotelmanagement.beans.Room;
import com.example.hotelmanagement.dao.RoomDao;

import java.util.HashMap;
import java.util.Map;

public class TaskTableView {
    private static Integer NBR=1;
    private Object i;
    private Object taskId;
    private Object taskStatus;
    private Object taskDate;
    private Object roomId;
    private Object roomNbr;

    public TaskTableView(Object taskId, Object taskStatus, Object taskDate, Object roomId) {
        this.i= NBR;
        this.taskId = taskId;
        this.taskStatus = taskStatus;
        this.taskDate = taskDate;
        this.roomId = roomId;
        this.setRoomNbr(roomId);
        this.incrementId();
    }

    public static void incrementId(){
        NBR ++;
    }

    public static Integer getNBR() {
        return NBR;
    }

    public static void setNBR(Integer NBR) {
        TaskTableView.NBR = NBR;
    }

    public Object getI() {
        return i;
    }

    public void setI(Object i) {
        this.i = i;
    }

    public Object getTaskId() {
        return taskId;
    }

    public void setTaskId(Object taskId) {
        this.taskId = taskId;
    }

    public Object getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Object taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Object getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Object taskDate) {
        this.taskDate = taskDate;
    }

    public Object getRoomNbr() {
        return roomNbr;
    }

    public void setRoomNbr(Object roomId) {
        Map map = new HashMap<>();
        map.put("roomId",roomId);

        this.roomNbr = ((Room)(RoomDao.select(map,"*").get(0))).getNumRoom();
    }

    public Object getRoomId() {
        return roomId;
    }

    public void setRoomId(Object roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "TaskTableView{" +
                "i=" + i +
                ", taskId=" + taskId +
                ", taskStatus=" + taskStatus +
                ", taskDate=" + taskDate +
                ", roomNbr=" + roomNbr +
                '}';
    }
}
