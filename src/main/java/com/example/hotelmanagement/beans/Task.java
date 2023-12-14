package com.example.hotelmanagement.beans;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private int taskId;
    private int employeeId;
    private int roomId;
    private String status;
    private String taskDate;
    private String taskType;

    public Task(){}
    public Task(int employeeId, int idRoom, String status, String taskType) {
        this.employeeId = employeeId;
        this.roomId = idRoom;
        this.status = status;
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.taskDate = currentDateTime.format(formatter);
        this.taskType = taskType;
    }



    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        return "MaintenanceTasks{" +
                "taskId=" + taskId +
                ", employeeId=" + employeeId +
                ", roomId=" + roomId +
                ", status='" + status +
                ", taskType='" + taskType +
                ", taskDate='" + taskDate + '\'' +
                '}';
    }
}
