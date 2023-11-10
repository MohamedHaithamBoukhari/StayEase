package com.example.hotelmanagement.dao;

import com.example.hotelmanagement.beans.Room;
import com.example.hotelmanagement.daoFactory.CummonDbFcts;

import java.util.List;

public class RoomDao extends CummonDbFcts {
    public static final String TABLE_NAME = "room";
    public static final String[] TABLE_COLUMNS = {"roomId", "numRoom", "type","capacity","status"};
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
