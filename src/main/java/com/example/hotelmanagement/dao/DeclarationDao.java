package com.example.hotelmanagement.dao;

import com.example.hotelmanagement.beans.Declaration;
import com.example.hotelmanagement.daoFactory.CummonDbFcts;

import java.util.List;

public class DeclarationDao extends CummonDbFcts {
    public static final String TABLE_NAME = "declaration";
    public static final String[] TABLE_COLUMNS = {"declarationId", "declarantId", "declarantStatus","declaration","declarationDate", "response", "responseDate"};
    public static void insert(Declaration declaration){
        superInsert(declaration, TABLE_COLUMNS, TABLE_NAME);
    }

    public static int update(String updatedColumn, Object newColumnValue, String testColumn,Object testColumnValue){
        return superUpdate(TABLE_NAME, updatedColumn, newColumnValue, testColumn, testColumnValue);
    }

    public static int delete(String testColumn,Object testColumnValue){
        return superDelete(TABLE_NAME, testColumn, testColumnValue);
    }


    public static List<Object> selectAll() {
        List<Object> rows = superSelectAll(Declaration.class, TABLE_NAME, TABLE_COLUMNS);
        return rows;
    }
}
