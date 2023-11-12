package com.example.hotelmanagement.dao;

import com.example.hotelmanagement.beans.CleaningAssignment;
import com.example.hotelmanagement.beans.Customer;
import com.example.hotelmanagement.beans.RoomType;
import com.example.hotelmanagement.daoFactory.CummonDbFcts;

import java.util.List;
import java.util.Map;

public class CustomerDao extends CummonDbFcts {
    public static final String TABLE_NAME = "customer";
    public static final String[] TABLE_COLUMNS = {"customerId", "fullName", "cin","phone", "email", "password", "address"};
    public static List<Object> select(Map<String, Object> whereMap) {
        List<Object> rows = superSelect(Customer.class, TABLE_NAME, TABLE_COLUMNS, whereMap);
        return rows;
    }

    public static void insert(Customer customer){
        superInsert(customer, TABLE_COLUMNS, TABLE_NAME);
    }

    public static int update(String updatedColumn, Object newColumnValue, String testColumn,Object testColumnValue){
        return superUpdate(TABLE_NAME, updatedColumn, newColumnValue, testColumn, testColumnValue);
    }

    public static int delete(String testColumn,Object testColumnValue){
        return superDelete(TABLE_NAME, testColumn, testColumnValue);
    }


    public static List<Object> selectAll() {
        List<Object> rows = superSelectAll(Customer.class, TABLE_NAME, TABLE_COLUMNS);
        return rows;
    }
}
