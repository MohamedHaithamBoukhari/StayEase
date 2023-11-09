package com.example.hotelmanagement.beans;

import java.sql.Date;

public class Declaration {
    private int declarationId;
    private int declarantId;
    private String declarantStatus;
    private String declaration;
    private Date declarationDate;
    private String response;
    private Date responseDate;

    public Declaration(int declarationId, int declarantId, String declarantStatus, String declaration, Date declarationDate, String response, Date responseDate) {
        this.declarationId = declarationId;
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

    public Date getDeclarationDate() {
        return declarationDate;
    }

    public void setDeclarationDate(Date declarationDate) {
        this.declarationDate = declarationDate;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Date getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
    }
}
