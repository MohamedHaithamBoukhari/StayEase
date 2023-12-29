package com.example.hotelmanagement.daoFactory;

import java.sql.*;

public class DatabaseUpdater {
    public static Connection connection = null;
    public static PreparedStatement preparedStatement = null;

    public DatabaseUpdater(){
        updateReservationStatus();
    }

    public void updateReservationStatus(){
        try{
            connection = DaoFactory.getConnection();
            String updateQuery =
                    "UPDATE reservation " +
                            "SET status = " +
                            "    CASE " +
                            "        WHEN (CURRENT_TIMESTAMP BETWEEN check_inDate + INTERVAL 12 HOUR AND check_outDate + INTERVAL 11 HOUR + INTERVAL 59 MINUTE) THEN 'In Progress' " +
                            "        WHEN (CURRENT_TIMESTAMP > check_outDate + INTERVAL 11 HOUR + INTERVAL 59 MINUTE) THEN 'Completed Stay' " +
                            "        ELSE status " +
                            "    END " +
                            "WHERE status <> 'Cancelled'";

            preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
