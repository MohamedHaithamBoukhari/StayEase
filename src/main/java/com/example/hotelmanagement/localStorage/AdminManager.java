package com.example.hotelmanagement.localStorage;

import com.example.hotelmanagement.beans.Employee;

public class AdminManager {
    private static AdminManager instance;
    private Employee employee = new Employee();

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
        return employee;
    }

    public void setCustomer(Employee employee) {
        this.employee.setEmployeeId(employee.getEmployeeId());
        this.employee.setFullName(employee.getFullName());
        this.employee.setCin(employee.getCin());
        this.employee.setPhone(employee.getPhone());
        this.employee.setEmail(employee.getEmail());
        this.employee.setPassword(employee.getPassword());
        this.employee.setPosition(employee.getPosition());
        this.employee.setSalary(employee.getSalary());
        this.employee.setWorkingHours(employee.getWorkingHours());
        this.employee.setWorkingDays(employee.getWorkingDays());
    }
}
