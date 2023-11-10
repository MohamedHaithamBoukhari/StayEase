package com.example.hotelmanagement.daoFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CummonDbFcts<T> {
    public static Connection connection = null;
    static Statement statement = null;
    public static PreparedStatement preparedStatement = null;
    public static ResultSet resultSet = null;
    public static int countRows(String tableName) {
        int rowCount = 0;

        try {
            connection = DaoFactory.getConnection();

            String countQuery = "SELECT COUNT(*) FROM " + tableName;

            preparedStatement = connection.prepareStatement(countQuery);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                rowCount = resultSet.getInt(1); // La première colonne de la première ligne
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResultSet();
            closePreparedStm();
            closeConn();
        }

        return rowCount;
    }
    public static void superInsert(Object obj, String[] columns, String tableName){

        try {
            connection = DaoFactory.getConnection();

            String cols = "";
            String colsVal = ""; //for question marks
            for (int i=1; i<columns.length; i++){
                cols += columns[i]+",";
                colsVal += "?, ";
            }
            cols = cols.substring(0, cols.length() - 1);         // delete the last ,
            colsVal = colsVal.substring(0, colsVal.length() - 2);//delete the last ", " (comma and space)

            preparedStatement = connection.prepareStatement("INSERT INTO "+tableName+"("+cols+")"+" VALUES("+colsVal+");");

            for (int i=1; i<columns.length ; i++){
                String columnGetter = "get"+columns[i].substring(0,1).toUpperCase() + columns[i].substring(1);
                Method getter = obj.getClass().getMethod(columnGetter);
                Object value = getter.invoke(obj);
                preparedStatement.setObject(i, value);
            }

            preparedStatement.executeUpdate();

        } catch (SQLException | InvocationTargetException | NoSuchMethodException |IllegalAccessException e) {
            e.printStackTrace();
        }finally {
            closePreparedStm();
            closeConn();
        }
    }
    public static int superUpdate(String tableName, String updatedColumn, Object newColumnValue, String testColumn,Object testColumnValue){
            int rowsAffected = 0;
            try {
                connection = DaoFactory.getConnection();

                preparedStatement = connection.prepareStatement("UPDATE "+tableName+" SET "+updatedColumn+" = ? WHERE "+testColumn+" = ?");
                preparedStatement.setObject(1, newColumnValue);
                preparedStatement.setObject(2, testColumnValue);

                rowsAffected = preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                closePreparedStm();
                closeConn();
            }
            return rowsAffected;
    }
    public static int superDelete(String tableName, String testColumn,Object testColumnValue){
        int rowsDeleted =0;

        try {
            connection = DaoFactory.getConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM "+tableName+" WHERE " + testColumn + " = ?");
            preparedStatement.setObject(1, testColumnValue);

            rowsDeleted = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePreparedStm();
            closeConn();
        }

        return rowsDeleted;
    }
    public static List<Object> superSelectAll(Class<?> objectClass, String tableName, String[] columnNames) {
        List<Object> objects = new ArrayList<>();

        try {
            connection = DaoFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM " + tableName);

            while (resultSet.next()) {
                Object obj = objectClass.getDeclaredConstructor().newInstance();

                for (String columnName : columnNames) {

                    Field field = objectClass.getDeclaredField(columnName);
                    field.setAccessible(true);

                    if (field.getType() == int.class) {
                        field.set(obj, resultSet.getInt(columnName));
                    } else if (field.getType() == String.class) {
                        field.set(obj, resultSet.getString(columnName));
                    }
                }

                objects.add(obj);
            }
        } catch (SQLException | NoSuchFieldException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        } finally {
            closeConn();
            closeStatement();
            closeResultSet();
        }

        return objects;
    }
    public static void closeConn(){
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void closePreparedStm(){
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void closeStatement(){
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void closeResultSet(){
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}