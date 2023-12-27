package com.example.hotelmanagement.controllers.customer;

import com.example.hotelmanagement.beans.Customer;
import com.example.hotelmanagement.localStorage.CustomerManager;
import com.example.hotelmanagement.config.Validation;
import com.example.hotelmanagement.dao.CustomerDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

public class EditInfosController implements Initializable {
    private List<String> errors = new ArrayList<>((Collections.nCopies(6, "")));
    @FXML private Label error1 = new Label(), error2 = new Label(), error3 = new Label(), error4 = new Label(), error5 = new Label(), error6 = new Label();
    @FXML TextField fullNameField_, cinField_, emailAddressField_, passwordField_, phoneField_, addressField_;//fields of user edit infos view
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Customer customer = CustomerManager.getInstance().getCustomer();
        initializeFields_(customer);

    }
    public void initializeFields_(Customer customer){
        fullNameField_.setText(customer.getFullName());
        cinField_.setText(customer.getCin());
        emailAddressField_.setText(customer.getEmail());
        passwordField_.setText(customer.getPassword());
        phoneField_.setText(customer.getPhone());
        addressField_.setText(customer.getAddress());
    }
    public void saveChanges(ActionEvent event){
        String fullName = fullNameField_.getText();
        String cin = cinField_.getText();
        String emailAddress = emailAddressField_.getText();
        String password = passwordField_.getText();
        String phone = phoneField_.getText();
        String address = addressField_.getText();

        if(verifyFields(event,fullName, cin, emailAddress, password, phone, address)){
            String[] updatedColumns = {"fullName", "cin", "email", "password", "phone", "address"};
            Object[] newColumnsValue = {fullName, cin, emailAddress, password, phone, address};
            String testColumn = "customerId";
            Object testColumnValue = CustomerManager.getInstance().getCustomer().getCustomerId();

            int i = CustomerDao.updateColumns(updatedColumns, newColumnsValue, testColumn, testColumnValue);
            if(i == 1){
                CustomerHomePageController.updated = true;
                Map map = new HashMap<>();
                map.put(testColumn, testColumnValue);
                Customer updatedCustomer = (Customer)(CustomerDao.select(map,"*").get(0));
                CustomerManager.getInstance().setCustomer(updatedCustomer);
                closeStage(event);

            }

        }else{
            error1.setText(errors.get(0));
            error2.setText(errors.get(1));
            error3.setText(errors.get(2));
            error4.setText(errors.get(3));
            error5.setText(errors.get(4));
            error6.setText(errors.get(5));
        }
    }
    public boolean verifyFields(ActionEvent event, String fullName, String cin, String emailAddress, String password, String phone, String address){
        boolean verified = false;
        for (int i = 0; i<6; i++){
            errors.set(i,"");
        }
        int i = 0;
        if (!Validation.isValidFullName(fullName)) {
            errors.set(i,"-Enter a valid full name");
            i++;
        }

        if (!Validation.isValidEmail(emailAddress)) {
            errors.set(i,"-Enter a valid email address");
            i++;
        }

        if (!Validation.isValidCIN(cin)) {
            errors.set(i,"-Enter a valid Customer Identification Number");
            i++;
        }

        if (!Validation.isValidPassword(password)) {
            errors.set(i,"-Enter a valid password");
            i++;
        }

        if (!Validation.isValidPhone(phone)) {
            errors.set(i,"-Enter a valid phone number");
            i++;
        }

        if (!Validation.isValidAddress(address)) {
            errors.set(i,"-Enter a valid address");
            i++;
        }

        for (int j = i; j<6; j++){
            errors.set(i,"");
        }
        return i == 0;
    }
    public void closeStage(ActionEvent event){
        CustomerHomePageController.childStage.close();
    }

}
