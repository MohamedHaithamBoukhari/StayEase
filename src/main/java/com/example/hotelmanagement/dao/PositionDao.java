package com.example.hotelmanagement.dao;

import com.example.hotelmanagement.beans.Position;
import com.example.hotelmanagement.beans.Room;
import com.example.hotelmanagement.daoFactory.CummonDbFcts;

import java.util.List;
import java.util.Map;

public class PositionDao extends CummonDbFcts {
    public static final String TABLE_NAME = "position";
    public static final String[] TABLE_COLUMNS = {"positionId", "empPosition", "description"};
    public static List<Object> select(Map<String, Object> whereMap, String selectedCols) {
        List<Object> rows = superSelect(Position.class, TABLE_NAME, selectedCols, TABLE_COLUMNS, whereMap);
        return rows;
    }
    public static void insert(Position position){
        superInsert(position, TABLE_COLUMNS, TABLE_NAME);
    }

    public static int update(String updatedColumn, Object newColumnValue, String testColumn,Object testColumnValue){
        return superUpdate(TABLE_NAME, updatedColumn, newColumnValue, testColumn, testColumnValue);
    }
    public static int updateColumns(String[] updatedColumns, Object[] newColumnsValue, String testColumn, Object testColumnValue){
        int i = updatedColumns.length;
        int columnsUpdated = 0;
        for (int j=0; j<i; j++){
            columnsUpdated = superUpdate(TABLE_NAME, updatedColumns[j], newColumnsValue[j], testColumn, testColumnValue);
        }
        return columnsUpdated;
    }

    public static int delete(String testColumn,Object testColumnValue){
        return superDelete(TABLE_NAME, testColumn, testColumnValue);
    }

    public static List<Object> selectAll() {
        List<Object> rows = superSelectAll(Position.class, TABLE_NAME, TABLE_COLUMNS);
        return rows;
    }

}
