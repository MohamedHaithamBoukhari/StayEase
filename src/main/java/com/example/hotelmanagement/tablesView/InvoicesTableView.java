package com.example.hotelmanagement.tablesView;

import com.example.hotelmanagement.beans.Reservation;
import com.example.hotelmanagement.dao.ReservationDao;

import java.util.HashMap;
import java.util.Map;

public class InvoicesTableView {
    private static Integer NBR=1;
    private Object i;
    private Object invoiceId;
    private Object customerId;
    private Object customerFullName;
    private Object customerCin;
    private Object reservationId;
    private Object status;
    private Object amount;
    private Object invoiceDate;
    private Object reservationDuration;

    public InvoicesTableView(Object invoiceId, Object customerId, Object customerFullName, Object customerCin, Object reservationId, Object status, Object amount, Object invoiceDate) {
        this.i = NBR;
        this.invoiceId = invoiceId;
        this.customerId = customerId;
        this.customerFullName = customerFullName;
        this.customerCin = customerCin;
        this.reservationId = reservationId;
        this.status = status;
        this.amount = amount;
        this.invoiceDate = invoiceDate;
        this.setReservationDuration(reservationId);
        incrementId();
    }

    public static Integer getNBR() {
        return NBR;
    }

    public static void setNBR(Integer NBR) {
        InvoicesTableView.NBR = NBR;
    }

    public Object getI() {
        return i;
    }

    public void setI(Object i) {
        this.i = i;
    }

    public Object getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Object invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Object getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Object customerId) {
        this.customerId = customerId;
    }

    public Object getCustomerFullName() {
        return customerFullName;
    }

    public void setCustomerFullName(Object customerFullName) {
        this.customerFullName = customerFullName;
    }

    public Object getCustomerCin() {
        return customerCin;
    }

    public void setCustomerCin(Object customerCin) {
        this.customerCin = customerCin;
    }

    public Object getReservationId() {
        return reservationId;
    }

    public void setReservationId(Object reservationId) {
        this.reservationId = reservationId;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public Object getAmount() {
        return amount;
    }

    public void setAmount(Object amount) {
        this.amount = amount;
    }

    public Object getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Object invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Object getReservationDuration() {
        return reservationDuration;
    }

    public void setReservationDuration(Object reservationId) {
        Map<String, Object> map = new HashMap<>();
        map.put("reservationId",(int)reservationId);
        Reservation reservation = (Reservation) ReservationDao.select(map,"*").get(0);
        this.reservationDuration = " From " + reservation.getCheck_inDate() + " to " + reservation.getCheck_outDate();
    }

    public static void incrementId(){
        NBR ++;
    }
    @Override
    public String toString() {
        return "InvoicesTableView{" +
                "i=" + i +
                ", invoiceId=" + invoiceId +
                ", customerId=" + customerId +
                ", customerFullName=" + customerFullName +
                ", customerCin=" + customerCin +
                ", reservationId=" + reservationId +
                ", status=" + status +
                ", amount=" + amount +
                ", invoiceDate=" + invoiceDate +
                ", reservationDuration=" + reservationDuration +
                '}';
    }

}
