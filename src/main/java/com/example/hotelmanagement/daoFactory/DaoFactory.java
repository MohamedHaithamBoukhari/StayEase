package com.example.hotelmanagement.daoFactory;

import com.example.hotelmanagement.config.DbConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
    private static Connection conn = null;
    public static Connection getConnection(){
        String URL = DbConfig.DB_HOST + "/" + DbConfig.DB_NAME;
        try {
            conn = DriverManager.getConnection(URL,DbConfig.DB_USERNAME,DbConfig.DB_PASSWORD);
        } catch (SQLException e){
            System.out.println("::not connected:" + e);
        }
        return conn;
    }
}
