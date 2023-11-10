package com.example.hotelmanagement.dao;

import com.example.hotelmanagement.beans.Reservation;
import com.example.hotelmanagement.daoFactory.CummonDbFcts;

import java.util.List;

public class ReservationDao extends CummonDbFcts {
    public static final String TABLE_NAME = "reservation";
    public static final String[] TABLE_COLUMNS = {"reservationId", "customertId", "roomId","check_inDate","check_outDate"};

    public static void insert(Reservation reservation){
        superInsert(reservation, TABLE_COLUMNS, TABLE_NAME);
    }

    public static int update(String updatedColumn, Object newColumnValue, String testColumn,Object testColumnValue){
        return superUpdate(TABLE_NAME, updatedColumn, newColumnValue, testColumn, testColumnValue);
    }

    public static int delete(String testColumn,Object testColumnValue){
        return superDelete(TABLE_NAME, testColumn, testColumnValue);
    }


    public static List<Object> selectAll() {
        List<Object> rows = superSelectAll(Reservation.class, TABLE_NAME, TABLE_COLUMNS);
        return rows;
    }
}
