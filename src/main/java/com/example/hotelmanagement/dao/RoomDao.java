package com.example.hotelmanagement.dao;

import com.example.hotelmanagement.beans.Customer;
import com.example.hotelmanagement.beans.Room;
import com.example.hotelmanagement.daoFactory.CummonDbFcts;

import java.util.List;
import java.util.Map;

public class RoomDao extends CummonDbFcts {
    public static final String TABLE_NAME = "room";
    public static final String[] TABLE_COLUMNS = {"roomId", "numRoom", "type","capacity","status"};
    public static List<Object> select(Map<String, Object> whereMap) {
        List<Object> rows = superSelect(Room.class, TABLE_NAME, TABLE_COLUMNS, whereMap);
        return rows;
    }
    public static void insert(Room room){
        superInsert(room, TABLE_COLUMNS, TABLE_NAME);
    }

    public static int update(String updatedColumn, Object newColumnValue, String testColumn,Object testColumnValue){
        return superUpdate(TABLE_NAME, updatedColumn, newColumnValue, testColumn, testColumnValue);
    }

    public static int delete(String testColumn,Object testColumnValue){
        return superDelete(TABLE_NAME, testColumn, testColumnValue);
    }


    public static List<Object> selectAll() {
        List<Object> rows = superSelectAll(Room.class, TABLE_NAME, TABLE_COLUMNS);
        return rows;
    }
}
