package com.example.hotelmanagement.tablesView;

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
                '}';
    }

}
