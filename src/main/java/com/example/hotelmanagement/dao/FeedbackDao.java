package com.example.hotelmanagement.dao;

import com.example.hotelmanagement.beans.Customer;
import com.example.hotelmanagement.beans.Employee;
import com.example.hotelmanagement.beans.Feedback;
import com.example.hotelmanagement.daoFactory.CummonDbFcts;
import com.example.hotelmanagement.daoFactory.DaoFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class FeedbackDao extends CummonDbFcts {
    public static final String TABLE_NAME = "feedback";
    public static final String[] TABLE_COLUMNS = {"feedbackId", "customerId", "visibility","priority","customerService_rate", "cleanliness_rate", "roomComfort_rate", "location_rate",  "safety_rate", "environnement_rate","view_rate","serviceVSprice_rate", "totalRate", "review_rate", "feedback_date"};
    public static List<Object> select(Map<String, Object> whereMap, String selectedCols) {
        List<Object> rows = superSelect(Feedback.class, TABLE_NAME, selectedCols, TABLE_COLUMNS, whereMap);
        return rows;
    }
    public static void insert(Feedback feedback){
        superInsert(feedback, TABLE_COLUMNS, TABLE_NAME);
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
        List<Object> rows = superSelectAll(Feedback.class, TABLE_NAME, TABLE_COLUMNS);
        return rows;
    }
    public static double calculateAverageRating() {
        double averageRating = 0.0;
        try (Statement statement = DaoFactory.getConnection().createStatement()) {
            String query = "SELECT AVG(totalRate) AS average_rating FROM feedback";
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                averageRating = resultSet.getDouble("average_rating");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return averageRating;
    }
}
