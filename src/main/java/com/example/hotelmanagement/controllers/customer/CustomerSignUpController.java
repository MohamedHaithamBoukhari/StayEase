package com.example.hotelmanagement.controllers.customer;

import com.example.hotelmanagement.HelloApplication;
import com.example.hotelmanagement.config.PathConfig;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class CustomerSignUpController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    TextField fullNameField, cinField, emailAddressField, passwordField, phoneField, addressField;
    public void switchToPreviousScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/customer/customerLogin-view.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void signUpAction(ActionEvent event){
        System.out.println("sign  up btn clicked");
        System.out.println(fullNameField.getText());
        System.out.println(cinField.getText());
        System.out.println(emailAddressField.getText());
        System.out.println(passwordField.getText());
        System.out.println(phoneField.getText());
        System.out.println(addressField.getText());
    }
    public void closeStage(ActionEvent event){
        HelloApplication.stage.close();
    }
}
