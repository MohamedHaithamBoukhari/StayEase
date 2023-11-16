package com.example.hotelmanagement.localStorage;

import com.example.hotelmanagement.beans.Employee;

public class ManagerManager {
    private static ManagerManager instance;
    private Employee manager = new Employee();

    private ManagerManager() {
        // Private constructor to prevent direct instantiation
    }

    public static ManagerManager getInstance() {
        if (instance == null) {
            instance = new ManagerManager();
        }
        return instance;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee employee) {
        this.manager.setEmployeeId(employee.getEmployeeId());
        this.manager.setFullName(employee.getFullName());
        this.manager.setCin(employee.getCin());
        this.manager.setPhone(employee.getPhone());
        this.manager.setEmail(employee.getEmail());
        this.manager.setPassword(employee.getPassword());
        this.manager.setPosition(employee.getPosition());
        this.manager.setSalary(employee.getSalary());
        this.manager.setWorkingHours(employee.getWorkingHours());
        this.manager.setWorkingDays(employee.getWorkingDays());
    }
}
