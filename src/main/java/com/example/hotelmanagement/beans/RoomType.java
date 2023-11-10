package com.example.hotelmanagement.beans;

public class RoomType {
    private int typeId;
    private String type;
    private String description;
    private int price_day;

    public RoomType() {}
    public RoomType(String type, String description, int price_day) {
        this.type = type;
        this.description = description;
        this.price_day = price_day;
    }


    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice_day() {
        return price_day;
    }

    public void setPrice_day(int price_day) {
        this.price_day = price_day;
    }

    @Override
    public String toString() {
        return "RoomType{" +
                "typeId=" + typeId +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", price_day=" + price_day +
                '}';
    }
}
