package com.example.hotelmanagement.tablesView;

public class ServiceTableView {
    private static Integer NBR=1;
    private Object i;
    private Object serviceId;
    private Object serviceName;
    private Object descreption;
    private Object correspondingTable;
    private Object status;

    public ServiceTableView(Object serviceId, Object serviceName, Object descreption, Object correspondingTable,Object status) {
        this.i = NBR;
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.descreption = descreption;
        this.correspondingTable = correspondingTable;
        this.status = status;
        incrementId();
    }
    public static void incrementId(){
        NBR ++;
    }

    public static Integer getNBR() {
        return NBR;
    }

    public static void setNBR(Integer NBR) {
        ServiceTableView.NBR = NBR;
    }

    public Object getI() {
        return i;
    }

    public void setI(Object i) {
        this.i = i;
    }

    public Object getServiceId() {
        return serviceId;
    }

    public void setServiceId(Object serviceId) {
        this.serviceId = serviceId;
    }

    public Object getServiceName() {
        return serviceName;
    }

    public void setServiceName(Object serviceName) {
        this.serviceName = serviceName;
    }

    public Object getDescreption() {
        return descreption;
    }

    public void setDescreption(Object descreption) {
        this.descreption = descreption;
    }

    public Object getCorrespondingTable() {
        return correspondingTable;
    }

    public void setCorrespondingTable(Object correspondingTable) {
        this.correspondingTable = correspondingTable;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }
}
