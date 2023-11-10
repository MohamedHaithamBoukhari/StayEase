package com.example.hotelmanagement.dao;

import com.example.hotelmanagement.beans.Employee;
import com.example.hotelmanagement.beans.Feedback;
import com.example.hotelmanagement.daoFactory.CummonDbFcts;

import java.util.List;

public class FeedbackDao extends CummonDbFcts {
    public static final String TABLE_NAME = "feedback";
    public static final String[] TABLE_COLUMNS = {"feedbackId", "customertId", "visibility","priority","customerService_rate", "cleanliness_rate", "roomComfort_rate", "location_rate",  "safety_rate", "environnement_rate","view_rate","serviceVSprice_rate", "review_rate", "feedback_date"};
    public static void insert(Feedback feedback){
        superInsert(feedback, TABLE_COLUMNS, TABLE_NAME);
    }

    public static int update(String updatedColumn, Object newColumnValue, String testColumn,Object testColumnValue){
        return superUpdate(TABLE_NAME, updatedColumn, newColumnValue, testColumn, testColumnValue);
    }

    public static int delete(String testColumn,Object testColumnValue){
        return superDelete(TABLE_NAME, testColumn, testColumnValue);
    }


    public static List<Object> selectAll() {
        List<Object> rows = superSelectAll(Feedback.class, TABLE_NAME, TABLE_COLUMNS);
        return rows;
    }
}
