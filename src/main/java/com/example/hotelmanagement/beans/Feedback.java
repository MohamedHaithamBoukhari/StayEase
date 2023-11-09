package com.example.hotelmanagement.beans;

public class Feedback {
    private int feedbackId;
    private int customertId;
    private String visibility;
    private int priority;
    private int customerService_rate;
    private int cleanliness_rate;
    private int roomComfort_rate;
    private int location_rate;
    private int safety_rate;
    private int environnement_rate;
    private int view_rate;
    private int serviceVSprice_rate;
    private int review_rate;

    public Feedback(int feedbackId, int customertId, String visibility, int priority, int customerService_rate, int cleanliness_rate, int roomComfort_rate, int location_rate, int safety_rate, int environnement_rate, int view_rate, int serviceVSprice_rate, int review_rate) {
        this.feedbackId = feedbackId;
        this.customertId = customertId;
        this.visibility = visibility;
        this.priority = priority;
        this.customerService_rate = customerService_rate;
        this.cleanliness_rate = cleanliness_rate;
        this.roomComfort_rate = roomComfort_rate;
        this.location_rate = location_rate;
        this.safety_rate = safety_rate;
        this.environnement_rate = environnement_rate;
        this.view_rate = view_rate;
        this.serviceVSprice_rate = serviceVSprice_rate;
        this.review_rate = review_rate;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public int getCustomertId() {
        return customertId;
    }

    public void setCustomertId(int customertId) {
        this.customertId = customertId;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getCustomerService_rate() {
        return customerService_rate;
    }

    public void setCustomerService_rate(int customerService_rate) {
        this.customerService_rate = customerService_rate;
    }

    public int getCleanliness_rate() {
        return cleanliness_rate;
    }

    public void setCleanliness_rate(int cleanliness_rate) {
        this.cleanliness_rate = cleanliness_rate;
    }

    public int getRoomComfort_rate() {
        return roomComfort_rate;
    }

    public void setRoomComfort_rate(int roomComfort_rate) {
        this.roomComfort_rate = roomComfort_rate;
    }

    public int getLocation_rate() {
        return location_rate;
    }

    public void setLocation_rate(int location_rate) {
        this.location_rate = location_rate;
    }

    public int getSafety_rate() {
        return safety_rate;
    }

    public void setSafety_rate(int safety_rate) {
        this.safety_rate = safety_rate;
    }

    public int getEnvironnement_rate() {
        return environnement_rate;
    }

    public void setEnvironnement_rate(int environnement_rate) {
        this.environnement_rate = environnement_rate;
    }

    public int getView_rate() {
        return view_rate;
    }

    public void setView_rate(int view_rate) {
        this.view_rate = view_rate;
    }

    public int getServiceVSprice_rate() {
        return serviceVSprice_rate;
    }

    public void setServiceVSprice_rate(int serviceVSprice_rate) {
        this.serviceVSprice_rate = serviceVSprice_rate;
    }

    public int getReview_rate() {
        return review_rate;
    }

    public void setReview_rate(int review_rate) {
        this.review_rate = review_rate;
    }
}