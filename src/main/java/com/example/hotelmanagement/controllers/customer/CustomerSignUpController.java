package com.example.hotelmanagement.controllers.customer;

import com.example.hotelmanagement.HelloApplication;
import com.example.hotelmanagement.beans.Customer;
import com.example.hotelmanagement.config.PathConfig;
import com.example.hotelmanagement.config.Validation;
import com.example.hotelmanagement.dao.CustomerDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class CustomerSignUpController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private List<String> errors = new ArrayList<>((Collections.nCopies(6, "")));
    @FXML private Label succesMsg;
    @FXML private Button signUpBtn;
    @FXML private Label error1 = new Label(), error2 = new Label(), error3 = new Label(), error4 = new Label(), error5 = new Label(), error6 = new Label();
    @FXML TextField fullNameField, cinField, emailAddressField, passwordField, phoneField, addressField;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        succesMsg.setVisible(false);
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
    public void singUpAction(ActionEvent event) {
        String fullName = fullNameField.getText();
        String cin = cinField.getText();
        String emailAddress = emailAddressField.getText();
        String password = passwordField.getText();
        String phone = phoneField.getText();
        String address = addressField.getText();

        if(verifyFields(event,fullName, cin, emailAddress, password, phone, address)){
            CustomerDao.insert(new Customer(fullName, cin, phone, emailAddress, password, address));

            fullNameField.setDisable(true);
            cinField.setDisable(true);
            emailAddressField.setDisable(true);
            passwordField.setDisable(true);
            phoneField.setDisable(true);
            addressField.setDisable(true);

            succesMsg.setVisible(true);
            signUpBtn.setDisable(true);
        }else{
            error1.setText(errors.get(0));
            error2.setText(errors.get(1));
            error3.setText(errors.get(2));
            error4.setText(errors.get(3));
            error5.setText(errors.get(4));
            error6.setText(errors.get(5));
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
}
