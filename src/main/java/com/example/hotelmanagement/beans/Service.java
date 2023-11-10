package com.example.hotelmanagement.beans;

public class Service {
    private int serviceId;
    private String serviceName;
    private String descreption;
    private String correspondingTable;

    public Service(){}
    public Service(String serviceName, String descreption, String correspondingTable) {
        this.serviceName = serviceName;
        this.descreption = descreption;
        this.correspondingTable = correspondingTable;
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

    @Override
    public String toString() {
        return "Service{" +
                "serviceId=" + serviceId +
                ", serviceName='" + serviceName + '\'' +
                ", descreption='" + descreption + '\'' +
                ", correspondingTable='" + correspondingTable + '\'' +
                '}';
    }
}
