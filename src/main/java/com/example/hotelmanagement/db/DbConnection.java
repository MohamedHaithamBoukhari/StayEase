package com.example.hotelmanagement.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static final String HOST = "jdbc:mysql://localhost:3306";
    private static Connection conn = null;
    public static Connection getConnection(String dbName, String username, String password){
        String URL = HOST + "/" + dbName;
        try {
            conn = DriverManager.getConnection(URL, username, password);
            System.out.println("::connected::");
        } catch (SQLException e){
            System.out.println("::not connected::" + e);
        }
        return conn;
    }
    public boolean checkConnection(){
        return conn != null;
    }

    public static void closeConnection(){
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
