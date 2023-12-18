package com.example.hotelmanagement.tablesView;

public class ComplaintTableView {
    private static Integer NBR=1;
    private Object i;
    private Object declarationId;
    private Object declarantId;
    private Object declarantStatus;
    private Object declarationObject;
    private Object declaration;
    private Object declarationDate;
    private Object response;
    private Object responseDate;
    private Object responseStatus;

    public ComplaintTableView(Object declarationId, Object declarantId, Object declarantStatus, Object declarationObject, Object declaration, Object declarationDate, Object response, Object responseDate) {
        this.i = NBR;
        this.declarationId = declarationId;
        this.declarantId = declarantId;
        this.declarantStatus = declarantStatus;
        this.declarationObject = declarationObject;
        this.declaration = declaration;
        this.declarationDate = declarationDate;
        this.response = response;
        this.responseDate = responseDate;
        this.setResponseStatus(response);
        incrementId();
    }

    public static Integer getNBR() {
        return NBR;
    }

    public static void setNBR(Integer NBR) {
        ComplaintTableView.NBR = NBR;
    }

    public Object getI() {
        return i;
    }

    public void setI(Object i) {
        this.i = i;
    }

    public Object getDeclarationId() {
        return declarationId;
    }

    public void setDeclarationId(Object declarationId) {
        this.declarationId = declarationId;
    }

    public Object getDeclarantId() {
        return declarantId;
    }

    public void setDeclarantId(Object declarantId) {
        this.declarantId = declarantId;
    }

    public Object getDeclarantStatus() {
        return declarantStatus;
    }

    public void setDeclarantStatus(Object declarantStatus) {
        this.declarantStatus = declarantStatus;
    }

    public Object getDeclarationObject() {
        return declarationObject;
    }

    public void setDeclarationObject(Object declarationObject) {
        this.declarationObject = declarationObject;
    }

    public Object getDeclaration() {
        return declaration;
    }

    public void setDeclaration(Object declaration) {
        this.declaration = declaration;
    }

    public Object getDeclarationDate() {
        return declarationDate;
    }

    public void setDeclarationDate(Object declarationDate) {
        this.declarationDate = declarationDate;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public Object getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Object responseDate) {
        this.responseDate = responseDate;
    }

    public Object getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(Object response) {
        if (response!=null && !String.valueOf(response).equals("")){
            this.responseStatus = "replied";
        }else {
            this.responseStatus = "Unreplied";
        }
    }

    public static void incrementId(){
        NBR ++;
    }

    @Override
    public String toString() {
        return "ComplaintTableView{" +
                "i=" + i +
                ", declarationId=" + declarationId +
                ", declarantId=" + declarantId +
                ", declarantStatus=" + declarantStatus +
                ", declarationObject=" + declarationObject +
                ", declaration=" + declaration +
                ", declarationDate=" + declarationDate +
                ", response=" + response +
                ", responseDate=" + responseDate +
                '}';
    }
}
