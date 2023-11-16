package com.example.hotelmanagement.dao;

import com.example.hotelmanagement.beans.Customer;
import com.example.hotelmanagement.beans.Declaration;
import com.example.hotelmanagement.beans.Employee;
import com.example.hotelmanagement.daoFactory.CummonDbFcts;

import java.util.List;
import java.util.Map;

public class EmployeeDao extends CummonDbFcts {
    public static final String TABLE_NAME = "employee";
    public static final String[] TABLE_COLUMNS = {"employeeId", "fullName", "cin","phone","email", "password", "position","salary", "workingHours", "workingDays"};
    public static List<Object> select(Map<String, Object> whereMap) {
        List<Object> rows = superSelect(Employee.class, TABLE_NAME, TABLE_COLUMNS, whereMap);
        return rows;
    }
    public static void insert(Employee employee){
        superInsert(employee, TABLE_COLUMNS, TABLE_NAME);
    }

    public static int update(String updatedColumn, Object newColumnValue, String testColumn,Object testColumnValue){
        return superUpdate(TABLE_NAME, updatedColumn, newColumnValue, testColumn, testColumnValue);
    }

    public static int delete(String testColumn,Object testColumnValue){
        return superDelete(TABLE_NAME, testColumn, testColumnValue);
    }


    public static List<Object> selectall() {
        List<Object> rows = superSelectAll(Employee.class, TABLE_NAME, TABLE_COLUMNS);
        return rows;
    }
}
