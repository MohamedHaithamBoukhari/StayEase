package com.example.hotelmanagement.beans;

public class Invoice {
    private int invoiceId;
    private int customerId;
    private int serviceId;
    private int status;
    private int invoiceDate;

    public Invoice(int invoiceId, int customerId, int serviceId, int status, int invoiceDate) {
        this.invoiceId = invoiceId;
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

    public int getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(int invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
}
