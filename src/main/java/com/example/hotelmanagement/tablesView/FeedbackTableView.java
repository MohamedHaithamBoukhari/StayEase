package com.example.hotelmanagement.tablesView;

public class FeedbackTableView {
    private static Integer NBR=1;
    private Object i;
    private Object feedbackId;
    private Object customerId;
    private Object customerFullName;
    private Object visibility;
    private Object priority;
    private Object customerService_rate;
    private Object cleanliness_rate;
    private Object roomComfort_rate;
    private Object location_rate;
    private Object safety_rate;
    private Object environnement_rate;
    private Object view_rate;
    private Object serviceVSprice_rate;
    private Object review_comment;
    private Object feedback_date;
    private Object total_rate;

    public FeedbackTableView(Object feedbackId, Object customerId, Object customerFullName, Object visibility, Object priority, Object customerService_rate, Object cleanliness_rate, Object roomComfort_rate, Object location_rate, Object safety_rate, Object environnement_rate, Object view_rate, Object serviceVSprice_rate, Object review_comment, Object feedback_date) {
        this.i = NBR;
        this.feedbackId = feedbackId;
        this.customerId = customerId;
        this.customerFullName = customerFullName;
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
        this.review_comment = review_comment;
        this.feedback_date = feedback_date;
        this.setTotal_rate(customerService_rate, cleanliness_rate, roomComfort_rate, location_rate, safety_rate, environnement_rate, view_rate, serviceVSprice_rate);
        incrementId();
    }

    public static void incrementId(){
        NBR ++;
    }

    public static Integer getNBR() {
        return NBR;
    }

    public static void setNBR(Integer NBR) {
        FeedbackTableView.NBR = NBR;
    }

    public Object getI() {
        return i;
    }

    public void setI(Object i) {
        this.i = i;
    }

    public Object getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Object feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Object getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Object customerId) {
        this.customerId = customerId;
    }

    public Object getCustomerFullName() {
        return customerFullName;
    }

    public void setCustomerFullName(Object customerFullName) {
        this.customerFullName = customerFullName;
    }

    public Object getVisibility() {
        return visibility;
    }

    public void setVisibility(Object visibility) {
        this.visibility = visibility;
    }

    public Object getPriority() {
        return priority;
    }

    public void setPriority(Object priority) {
        this.priority = priority;
    }

    public Object getCustomerService_rate() {
        return customerService_rate;
    }

    public void setCustomerService_rate(Object customerService_rate) {
        this.customerService_rate = customerService_rate;
    }

    public Object getCleanliness_rate() {
        return cleanliness_rate;
    }

    public void setCleanliness_rate(Object cleanliness_rate) {
        this.cleanliness_rate = cleanliness_rate;
    }

    public Object getRoomComfort_rate() {
        return roomComfort_rate;
    }

    public void setRoomComfort_rate(Object roomComfort_rate) {
        this.roomComfort_rate = roomComfort_rate;
    }

    public Object getLocation_rate() {
        return location_rate;
    }

    public void setLocation_rate(Object location_rate) {
        this.location_rate = location_rate;
    }

    public Object getSafety_rate() {
        return safety_rate;
    }

    public void setSafety_rate(Object safety_rate) {
        this.safety_rate = safety_rate;
    }

    public Object getEnvironnement_rate() {
        return environnement_rate;
    }

    public void setEnvironnement_rate(Object environnement_rate) {
        this.environnement_rate = environnement_rate;
    }

    public Object getView_rate() {
        return view_rate;
    }

    public void setView_rate(Object view_rate) {
        this.view_rate = view_rate;
    }

    public Object getServiceVSprice_rate() {
        return serviceVSprice_rate;
    }

    public void setServiceVSprice_rate(Object serviceVSprice_rate) {
        this.serviceVSprice_rate = serviceVSprice_rate;
    }

    public Object getReview_comment() {
        return review_comment;
    }

    public void setReview_comment(Object review_comment) {
        this.review_comment = review_comment;
    }

    public Object getFeedback_date() {
        return feedback_date;
    }

    public void setFeedback_date(Object feedback_date) {
        this.feedback_date = feedback_date;
    }

    public Object getTotal_rate() {
        return total_rate;
    }

    public void setTotal_rate(Object customerService_rate, Object cleanliness_rate, Object roomComfort_rate, Object location_rate, Object safety_rate, Object environnement_rate, Object view_rate, Object serviceVSprice_rate) {
        this.total_rate =  ((int) customerService_rate + (int) cleanliness_rate + (int) roomComfort_rate + (int) location_rate + (int) safety_rate + (int) environnement_rate + (int) view_rate + (int) serviceVSprice_rate) / 8;
    }

    @Override
    public String toString() {
        return "FeedbackTableView{" +
                "i=" + i +
                ", feedbackId=" + feedbackId +
                ", customerId=" + customerId +
                ", customerFullName=" + customerFullName +
                ", visibility=" + visibility +
                ", priority=" + priority +
                ", customerService_rate=" + customerService_rate +
                ", cleanliness_rate=" + cleanliness_rate +
                ", roomComfort_rate=" + roomComfort_rate +
                ", location_rate=" + location_rate +
                ", safety_rate=" + safety_rate +
                ", environnement_rate=" + environnement_rate +
                ", view_rate=" + view_rate +
                ", serviceVSprice_rate=" + serviceVSprice_rate +
                ", review_comment=" + review_comment +
                ", feedback_date=" + feedback_date +
                ", feedback_date=" +  +
                '}';
    }
}
