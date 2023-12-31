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
            //change reseration status automaticly
            String updateReservationQuery =
                    "UPDATE reservation " +
                    "SET status = " +
                    "    CASE " +
                    "        WHEN (CURRENT_TIMESTAMP BETWEEN check_inDate + INTERVAL 12 HOUR AND check_outDate + INTERVAL 11 HOUR + INTERVAL 59 MINUTE) THEN 'I-P' " +
                    "        WHEN (CURRENT_TIMESTAMP > check_outDate + INTERVAL 11 HOUR + INTERVAL 59 MINUTE) THEN 'C-S' " +
                    "        ELSE status " +
                    "    END " +
                    "WHERE status <> 'Cancelled' AND status <> 'Completed Stay'";

            //change status of room that its reservation stay is completed
            String updateRoomQuery1 = "UPDATE room r " +
                    "JOIN reservation res ON r.roomId = res.roomId " +
                    "SET r.status = 'Needs Maintenance', res.status = 'Completed Stay' " +
                    "WHERE res.status = 'C-S'";

            //change status of room that its reservation stay is completed
            String updateRoomQuery2 = "UPDATE room r " +
                    "JOIN reservation res ON r.roomId = res.roomId " +
                    "SET r.status = 'Occupied', res.status = 'In Progresss' " +
                    "WHERE res.status = 'I-P'";

            preparedStatement = connection.prepareStatement(updateReservationQuery);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(updateRoomQuery1);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(updateRoomQuery2);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
