package com.example.hotelmanagement.beans;

public class Position {
    private int positionId;
    private String empPosition;
    private String description;
    public Position(){}
    public Position(String empPosition, String description) {
        this.empPosition = empPosition;
        this.description = description;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public String getEmpPosition() {
        return empPosition;
    }

    public void setEmpPosition(String empPosition) {
        this.empPosition = empPosition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
