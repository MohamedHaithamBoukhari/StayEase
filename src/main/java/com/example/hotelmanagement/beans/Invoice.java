package com.example.hotelmanagement.beans;

public class Invoice {
    private int invoiceId;
    private int customerId;
    private int serviceId;
    private int status;
    private String invoiceDate;

    public Invoice(){}
    public Invoice(int customerId, int serviceId, int status, String invoiceDate) {
        this.customerId = customerId;
        this.serviceId = serviceId;
        this.status = status;
        this.invoiceDate = invoiceDate;
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
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId=" + invoiceId +
                ", customerId=" + customerId +
                ", serviceId=" + serviceId +
                ", status=" + status +
                ", invoiceDate='" + invoiceDate + '\'' +
                '}';
    }
}
