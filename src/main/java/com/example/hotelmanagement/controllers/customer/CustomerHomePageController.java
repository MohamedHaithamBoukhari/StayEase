package com.example.hotelmanagement.controllers.customer;

import com.example.hotelmanagement.HelloApplication;
import com.example.hotelmanagement.beans.Customer;
import com.example.hotelmanagement.dao.CustomerDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class CustomerHomePageController{
    private int customerId;
    @FXML private Label fullnameLabel;

    public void setFullname(String fullname){
        fullnameLabel.setText("- "+fullname+" -");
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void switchToCustomerInfos(ActionEvent event){
        System.out.println(customerId);
//        Map<String, Object> map = new HashMap<>();
//        map.put("customerId", customerId);
//
//        List<Object> custumers = CustomerDao.select(map);
//        Customer customer = (Customer) custumers.get(0);
//        System.out.println(customer.getFullName());
    }
    public void logout(ActionEvent event){
        HelloApplication.stage.close();
    }
}
