package com.example.hotelmanagement.beans;

public class Employee {
    private int employeeId;
    private String fullName;
    private String cin;
    private String phone;
    private String email;
    private String password;
    private String position;
    private int salary;
    private String workingHours;
    private String workingDays;

    public Employee(){}
    public Employee(String fullName, String cin, String phone, String email, String password, String position, int salary, String workingHours, String workingDays) {
        this.fullName = fullName;
        this.cin = cin;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.position = position;
        this.salary = salary;
        this.workingHours = workingHours;
        this.workingDays = workingDays;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public String getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(String workingDays) {
        this.workingDays = workingDays;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", fullName='" + fullName + '\'' +
                ", cin='" + cin + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                ", workingHours='" + workingHours + '\'' +
                ", workingDays='" + workingDays + '\'' +
                '}';
    }
}
