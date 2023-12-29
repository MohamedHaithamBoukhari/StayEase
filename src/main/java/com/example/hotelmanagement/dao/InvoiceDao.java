package com.example.hotelmanagement.dao;

import com.example.hotelmanagement.beans.Customer;
import com.example.hotelmanagement.beans.Feedback;
import com.example.hotelmanagement.beans.Invoice;
import com.example.hotelmanagement.daoFactory.CummonDbFcts;
import com.example.hotelmanagement.daoFactory.DaoFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class InvoiceDao extends CummonDbFcts {
    public static final String TABLE_NAME = "invoice";
    public static final String[] TABLE_COLUMNS = {"invoiceId", "customerId", "reservationId","status","invoiceDate", "amount"};
    public static List<Object> select(Map<String, Object> whereMap, String selectedCols) {
        List<Object> rows = superSelect(Invoice.class, TABLE_NAME, selectedCols, TABLE_COLUMNS, whereMap);
        return rows;
    }
    public static void insert(Invoice invoice){
        superInsert(invoice, TABLE_COLUMNS, TABLE_NAME);
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
        List<Object> rows = superSelectAll(Invoice.class, TABLE_NAME, TABLE_COLUMNS);
        return rows;
    }

    public static double monthEarning(){
        try (Statement stmt = DaoFactory.getConnection().createStatement()) {
            ResultSet resultSet = stmt.executeQuery("SELECT SUM(amount) AS monthlyEarnings FROM invoice WHERE status = 'Paid' AND MONTH(invoiceDate) = MONTH(CURRENT_DATE) AND YEAR(invoiceDate) = YEAR(CURRENT_DATE)");

            if (resultSet.next()) {
                return resultSet.getDouble("monthlyEarnings");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
    public static double yearEarning(){
        try (Statement stmt = DaoFactory.getConnection().createStatement()) {
            ResultSet resultSet = stmt.executeQuery("SELECT SUM(amount) AS yearlyEarnings FROM invoice WHERE status = 'Paid' AND YEAR(invoiceDate) = YEAR(CURRENT_DATE)");

            if (resultSet.next()) {
                return resultSet.getDouble("yearlyEarnings");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
    public static double totalEarning(){
        try (Statement stmt = DaoFactory.getConnection().createStatement()) {
            ResultSet resultSet = stmt.executeQuery("SELECT SUM(amount) AS totalEarnings FROM invoice WHERE status = 'Paid'");

            if (resultSet.next()) {
                return resultSet.getDouble("totalEarnings");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
}
