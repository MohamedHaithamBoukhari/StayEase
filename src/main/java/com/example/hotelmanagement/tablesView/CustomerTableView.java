package com.example.hotelmanagement.tablesView;

import com.example.hotelmanagement.dao.InvoiceDao;
import com.example.hotelmanagement.dao.ReservationDao;

import java.util.HashMap;
import java.util.Map;

public class CustomerTableView {
    private static Integer NBR=1;
    private Object i;
    private Object customerId;
    private Object cin;
    private Object email;
    private Object account_status;
    private Object unpaid_invoices;
    private Object cancelled_reservations;

    public CustomerTableView(Object customerId, Object cin, Object email, Object account_status) {
        this.i = NBR;
        this.customerId = customerId;
        this.cin = cin;
        this.email = email;
        this.account_status = account_status;
        this.setUnpaid_invoices(customerId);
        this.setCancelled_reservations(customerId);
        incrementId();
    }

    public static void incrementId(){
        NBR ++;
    }

    public static void setNBR(Integer NBR) {
        CustomerTableView.NBR = NBR;
    }

    public Object getI() {
        return i;
    }

    public void setI(Object i) {
        this.i = i;
    }

    public Object getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Object customerId) {
        this.customerId = customerId;
    }

    public Object getCin() {
        return cin;
    }

    public void setCin(Object cin) {
        this.cin = cin;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Object getAccount_status() {
        return account_status;
    }

    public void setAccount_status(Object account_status) {
        this.account_status = account_status;
    }

    public Object getUnpaid_invoices() {
        return unpaid_invoices;
    }

    public void setUnpaid_invoices(Object customerId) {
        Map map = new HashMap<>();
        map.put("customerId", customerId);
        map.put("status", "Unpaid");
        this.unpaid_invoices = InvoiceDao.select(map,"*").size();
    }

    public Object getCancelled_reservations() {
        return cancelled_reservations;
    }

    public void setCancelled_reservations(Object customerId) {
        Map map = new HashMap<>();
        map.put("customerId", customerId);
        map.put("status", "Cancelled");
        this.cancelled_reservations = ReservationDao.select(map,"*").size();    }

    @Override
    public String toString() {
        return "CustomerTableView{" +
                "i=" + i +
                ", customerId=" + customerId +
                ", cin=" + cin +
                ", email=" + email +
                ", account_status=" + account_status +
                ", unpaid_invoices=" + unpaid_invoices +
                ", cancelled_reservations=" + cancelled_reservations +
                '}';
    }
}
