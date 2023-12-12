package com.example.hotelmanagement.beans;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CleaningTask {
    private int idCleaning;
    private int idEmp;
    private int idRoom;
    private String status;
    private String taskDate;

    public CleaningTask(int idEmp, int idRoom, String status) {
        this.idEmp = idEmp;
        this.idRoom = idRoom;
        this.status = status;
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.taskDate = currentDateTime.format(formatter);
    }

    public int getIdCleaning() {
        return idCleaning;
    }

    public void setIdCleaning(int idCleaning) {
        this.idCleaning = idCleaning;
    }

    public int getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(int idEmp) {
        this.idEmp = idEmp;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CleaningTasks{" +
                "idCleaning=" + idCleaning +
                ", idEmp=" + idEmp +
                ", idRoom=" + idRoom +
                ", status=" + status +
                ", taskDate='" + taskDate + '\'' +
                '}';
    }
}
