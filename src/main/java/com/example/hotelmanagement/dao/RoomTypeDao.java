package com.example.hotelmanagement.dao;

import com.example.hotelmanagement.beans.RoomType;
import com.example.hotelmanagement.daoFactory.CummonDbFcts;

import java.util.List;

public class RoomTypeDao extends CummonDbFcts {
    public static final String TABLE_NAME = "roomType";
    public static final String[] TABLE_COLUMNS = {"typeId", "type", "description","price_day"};
    public static void insert(RoomType type){
        superInsert(type, TABLE_COLUMNS, TABLE_NAME);
    }

    public static int update(String updatedColumn, Object newColumnValue, String testColumn,Object testColumnValue){
        return superUpdate(TABLE_NAME, updatedColumn, newColumnValue, testColumn, testColumnValue);
    }

    public static int delete(String testColumn,Object testColumnValue){
        return superDelete(TABLE_NAME, testColumn, testColumnValue);
    }


    public static List<Object> selectAll() {
        List<Object> rows = superSelectAll(RoomType.class, TABLE_NAME, TABLE_COLUMNS);
        return rows;
    }
}
