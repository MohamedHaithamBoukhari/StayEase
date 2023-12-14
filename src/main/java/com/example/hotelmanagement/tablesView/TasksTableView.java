package com.example.hotelmanagement.tablesView;

public class TasksTableView {
    private static Integer NBR=1;
    private Object i;
    private Object employeeId;
    private Object fullName;
    private Object email;
    private Object position;
    private Object assignedRoomsNbr;
    public static void incrementId(){
        NBR ++;
    }

    public TasksTableView(Object employeeId, Object fullName, Object email, Object position, Object assignedRoomsNbr) {
        this.i = NBR;
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.email = email;
        this.position = position;
        this.assignedRoomsNbr = assignedRoomsNbr;
        this.incrementId();
    }

    public static Integer getNBR() {
        return NBR;
    }

    public static void setNBR(Integer NBR) {
        TasksTableView.NBR = NBR;
    }

    public Object getI() {
        return i;
    }

    public void setI(Object i) {
        this.i = i;
    }

    public Object getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Object employeeId) {
        this.employeeId = employeeId;
    }

    public Object getFullName() {
        return fullName;
    }

    public void setFullName(Object fullName) {
        this.fullName = fullName;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Object getPosition() {
        return position;
    }

    public void setPosition(Object position) {
        this.position = position;
    }

    public Object getAssignedRoomsNbr() {
        return assignedRoomsNbr;
    }

    public void setAssignedRoomsNbr(Object assignedRoomsNbr) {
        this.assignedRoomsNbr = assignedRoomsNbr;
    }

    @Override
    public String toString() {
        return "TasksTableView{" +
                "i=" + i +
                ", employeeId=" + employeeId +
                ", fullName=" + fullName +
                ", email=" + email +
                ", position=" + position +
                ", assignedRoomsNbr=" + assignedRoomsNbr +
                '}';
    }
}
