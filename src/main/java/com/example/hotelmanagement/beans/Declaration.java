package com.example.hotelmanagement.beans;

import java.sql.Date;

public class Declaration {
    private int declarationId;
    private int declarantId;
    private String declarantStatus;
    private String declaration;
    private String declarationDate;
    private String response;
    private String responseDate;

    public Declaration(){}
    public Declaration(int declarantId, String declarantStatus, String declaration, String declarationDate, String response, String responseDate) {
        this.declarantId = declarantId;
        this.declarantStatus = declarantStatus;
        this.declaration = declaration;
        this.declarationDate = declarationDate;
        this.response = response;
        this.responseDate = responseDate;
    }

    public int getDeclarationId() {
        return declarationId;
    }

    public void setDeclarationId(int declarationId) {
        this.declarationId = declarationId;
    }

    public int getDeclarantId() {
        return declarantId;
    }

    public void setDeclarantId(int declarantId) {
        this.declarantId = declarantId;
    }

    public String getDeclarantStatus() {
        return declarantStatus;
    }

    public void setDeclarantStatus(String declarantStatus) {
        this.declarantStatus = declarantStatus;
    }

    public String getDeclaration() {
        return declaration;
    }

    public void setDeclaration(String declaration) {
        this.declaration = declaration;
    }

    public String getDeclarationDate() {
        return declarationDate;
    }

    public void setDeclarationDate(String declarationDate) {
        this.declarationDate = declarationDate;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(String responseDate) {
        this.responseDate = responseDate;
    }

    @Override
    public String toString() {
        return "Declaration{" +
                "declarationId=" + declarationId +
                ", declarantId=" + declarantId +
                ", declarantStatus='" + declarantStatus + '\'' +
                ", declaration='" + declaration + '\'' +
                ", declarationDate='" + declarationDate + '\'' +
                ", response='" + response + '\'' +
                ", responseDate='" + responseDate + '\'' +
                '}';
    }
}
