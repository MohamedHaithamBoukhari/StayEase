package com.example.hotelmanagement.controllers.customer;

import com.example.hotelmanagement.HelloApplication;
import com.example.hotelmanagement.beans.Customer;
import com.example.hotelmanagement.config.PathConfig;
import com.example.hotelmanagement.dao.CustomerDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerSignUpController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private List<String> errors = new ArrayList<>(6);
    @FXML
    Label error1 = new Label(), error2 = new Label(), error3 = new Label(), error4 = new Label(), error5 = new Label(), error6 = new Label();

    Label[]errorsLabel = {error1, error2, error3, error4, error5, error6};
    @FXML
    TextField fullNameField, cinField, emailAddressField, passwordField, phoneField, addressField;
    public boolean verifyFields(ActionEvent event, String fullName, String cin, String emailAddress, String password, String phone, String address){
        boolean verified = false;
        errors.clear();
        if (!isValidFullName(fullName)) {
            errors.add("-Enter a valid full name");
            verified = false;
        }else {
            errors.remove("-Enter a valid full name");
            verified = true;
        }

        if (!isValidEmail(emailAddress)) {
            errors.add("-Enter a valid email address");
            verified = false;
        }else {
            errors.remove("Enter a valid email address");
            verified = true;
        }

        if (!isValidCIN(cin)) {
            errors.add("-Enter a valid Customer Identification Number");
            verified = false;
        }else {
            errors.remove("-Enter a valid Customer Identification Number");
            verified = true;
        }

        if (!isValidPassword(password)) {
            errors.add("-Enter a valid password");
            verified = false;
        }else {
            errors.remove("-Enter a valid password");
            verified = true;
        }

        if (!isValidPhone(phone)) {
            errors.add("-Enter a valid phone number");
            verified = false;
        }else {
            errors.remove("-Enter a valid phone number");
            verified = true;
        }

        if (!isValidAddress(address)) {
            errors.add("-Enter a valid address");
            verified = false;
        }else {
            errors.remove("-Enter a valid address");
            verified = true;
        }
        return verified;
    }

    public void singUpAction(ActionEvent event) {
        String fullName = fullNameField.getText();
        String cin = cinField.getText();
        String emailAddress = emailAddressField.getText();
        String password = passwordField.getText();
        String phone = phoneField.getText();
        String address = addressField.getText();

        if(verifyFields(event,fullName, cin, emailAddress, password, phone, address)){
            CustomerDao.insert(new Customer(fullName, cin, emailAddress, password, phone, address));
            System.out.println("succes sign up");
//                switchToCustumerHomePage(event, customer.getClientId());
        }else{
            error1.setText(errors.get(0));
            error2.setText(errors.get(1));
            error3.setText(errors.get(2));
            error4.setText(errors.get(3));
            error5.setText(errors.get(4));
            error6.setText(errors.get(5));
//            for(int i=0; i<errors.size(); i++){
//                errorsLabel[i].setText(errors.get(i)); //we set error from errors to label from errors label
//            }
        }

    }
    public void switchToPreviousScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/customer/customerLogin-view.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void closeStage(ActionEvent event){
        HelloApplication.stage.close();
    }

    public static boolean isValidFullName(String fullName) {
        return fullName.matches("^[a-zA-Z\\s'-]+$");
    }

    public static boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }

    public static boolean isValidCIN(String cin) {
        // Define your specific pattern for CIN validation
        return cin.matches("^[A-Za-z0-9]{9}$");
    }

    public static boolean isValidPassword(String password) {
        // Password requires at least one uppercase, one lowercase, one number, and one special character
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$");
    }

    public static boolean isValidPhone(String phone) {
        // Define your specific pattern for phone validation
        return phone.matches("^\\+[0-9]{1,}$");
    }

    public static boolean isValidAddress(String address) {
        // Define your specific pattern for address validation
        return address.matches("^[a-zA-Z0-9,\\s-]+$");
    }
}
