package com.example.hotelmanagement.localStorage;

import com.example.hotelmanagement.beans.Employee;

public class AdminManager {
    private static AdminManager instance;
    private Employee admin = new Employee();

    private AdminManager() {
        // Private constructor to prevent direct instantiation
    }

    public static AdminManager getInstance() {
        if (instance == null) {
            instance = new AdminManager();
        }
        return instance;
    }

    public Employee getAdmin() {
        return admin;
    }

    public void setAdmin(Employee employee) {
        this.admin.setEmployeeId(employee.getEmployeeId());
        this.admin.setFullName(employee.getFullName());
        this.admin.setCin(employee.getCin());
        this.admin.setPhone(employee.getPhone());
        this.admin.setEmail(employee.getEmail());
        this.admin.setPassword(employee.getPassword());
        this.admin.setPosition(employee.getPosition());
        this.admin.setSalary(employee.getSalary());
        this.admin.setWorkingHours(employee.getWorkingHours());
        this.admin.setWorkingDays(employee.getWorkingDays());
    }
}
