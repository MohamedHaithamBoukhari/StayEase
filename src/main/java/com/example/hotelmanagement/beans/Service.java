package com.example.hotelmanagement.beans;

public class Service {
    private int serviceId;
    private String serviceName;
    private String descreption;
    private String correspondingTable;
    private String status;

    public Service(){}
    public Service(String serviceName, String descreption, String correspondingTable, String status) {
        this.serviceName = serviceName;
        this.descreption = descreption;
        this.correspondingTable = correspondingTable;
        this.status = status;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDescreption() {
        return descreption;
    }

    public void setDescreption(String descreption) {
        this.descreption = descreption;
    }

    public String getCorrespondingTable() {
        return correspondingTable;
    }

    public void setCorrespondingTable(String correspondingTable) {
        this.correspondingTable = correspondingTable;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Service{" +
                "serviceId=" + serviceId +
                ", serviceName='" + serviceName + '\'' +
                ", descreption='" + descreption + '\'' +
                ", correspondingTable='" + correspondingTable + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
