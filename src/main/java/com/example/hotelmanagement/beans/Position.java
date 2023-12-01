package com.example.hotelmanagement.beans;

public class Position {
    private int positionId;
    private String empPosition;

    public Position(String empPosition) {
        this.empPosition = empPosition;
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
}
