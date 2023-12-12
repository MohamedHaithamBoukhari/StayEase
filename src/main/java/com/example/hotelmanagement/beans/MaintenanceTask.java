package com.example.hotelmanagement.beans;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MaintenanceTask {
    private int idMaintenance;
    private int idEmp;
    private int idRoom;
    private String status;
    private String taskDate;

    public MaintenanceTask(int idEmp, int idRoom, String status) {
        this.idEmp = idEmp;
        this.idRoom = idRoom;
        this.status = status;
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.taskDate = currentDateTime.format(formatter);
    }

    public int getIdMaintenance() {
        return idMaintenance;
    }

    public void setIdMaintenance(int idMaintenance) {
        this.idMaintenance = idMaintenance;
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
        return "MaintenanceTasks{" +
                "idMaintenance=" + idMaintenance +
                ", idEmp=" + idEmp +
                ", idRoom=" + idRoom +
                ", status='" + status +
                ", taskDate='" + taskDate + '\'' +
                '}';
    }
}
