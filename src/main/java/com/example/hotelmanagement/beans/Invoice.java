package com.example.hotelmanagement.beans;

public class Invoice {
    private int invoiceId;
    private int customerId;
    private int reservationId;
    private String status;
    private String invoiceDate;
    private int amount;

    public Invoice(){}
    public Invoice(int customerId, int reservationId, String status, String invoiceDate, int amount) {
        this.customerId = customerId;
        this.reservationId = reservationId;
        this.status = status;
        this.invoiceDate = invoiceDate;
        this.amount = amount;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getServiceId() {
        return reservationId;
    }

    public void setServiceId(int reservationId) {
        this.reservationId = reservationId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId=" + invoiceId +
                ", customerId=" + customerId +
                ", reservationId=" + reservationId +
                ", status=" + status +
                ", invoiceDate='" + invoiceDate + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
