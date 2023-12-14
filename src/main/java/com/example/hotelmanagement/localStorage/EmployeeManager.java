package com.example.hotelmanagement.localStorage;

import com.example.hotelmanagement.beans.Employee;

public class EmployeeManager {
    private static EmployeeManager instance;
    private Employee employee = new Employee();

    private EmployeeManager() {
        // Private constructor to prevent direct instantiation
    }

    public static EmployeeManager getInstance() {
        if (instance == null) {
            instance = new EmployeeManager();
        }
        return instance;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setManager(Employee employee) {
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
