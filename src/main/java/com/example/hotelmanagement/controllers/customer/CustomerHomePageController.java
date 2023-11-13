package com.example.hotelmanagement.controllers.customer;

import com.example.hotelmanagement.HelloApplication;
import javafx.event.ActionEvent;

public class CustomerHomePageController {
    private int customerId;

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void switchToCustomerInfos(ActionEvent event){
        System.out.println(customerId);
    }
    public void logout(ActionEvent event){
        HelloApplication.stage.close();
    }
}
