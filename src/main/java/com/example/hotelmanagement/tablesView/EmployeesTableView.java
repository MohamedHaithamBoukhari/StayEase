package com.example.hotelmanagement.tablesView;

public class EmployeesTableView {
    private static Integer NBR=1;
    private Object i;
    private Object employeeId;
    private Object fullName;
    private Object cin;
    private Object email;
    private Object position;
    private Object phone;

    public EmployeesTableView(Object employeeId, Object fullName, Object cin, Object email, Object position, Object phone) {
        this.i = NBR;
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.cin = cin;
        this.email = email;
        this.position = position;
        this.phone = phone;
        incrementId();
    }
    public static void incrementId(){
        NBR ++;
    }

    public static Integer getNBR() {
        return NBR;
    }

    public static void setNBR(Integer NBR) {
        EmployeesTableView.NBR = NBR;
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

    public Object getCin() {
        return cin;
    }

    public void setCin(Object cin) {
        this.cin = cin;
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

    public Object getPhone() {
        return phone;
    }

    public void setPhone(Object phone) {
        this.phone = phone;
    }
}

