package com.example.hotelmanagement.controllers.customer;

import com.example.hotelmanagement.HelloApplication;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerLoginController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private List<String> errors = new ArrayList<>();
    @FXML
    private TextField usernameField, passwordField;
    @FXML
    private Label usernameError, passwordError;
    public boolean verifyFields(ActionEvent event, String username, String password){
        boolean verified = false;

        if(username.isEmpty()){
//            errors.add("Field must be not empty");
            usernameError.setText("Field must be not empty");
            verified = false;
        }else {
            usernameError.setText("");
            verified = true;
        }

        if (password.isEmpty() || password.length() < 8) {
            passwordError.setText("Password must be at least 8 characters long");
            verified = false;
        }
        else {
            passwordError.setText("");
            verified = true;
        }

        return verified;
    }
    public void logInAction(ActionEvent event){
        String username = usernameField.getText();
        String password = passwordField.getText();

        if(verifyFields(event, username, password)){
            Map<String, Object> map = new HashMap<>();
            map.put("email", username);
            map.put("password", password);

            List<Object> custumer = CustomerDao.select(map);
            if(custumer.size() == 1){
                System.out.println("logged in");
            }else {
                System.out.println("incorrect informations");
            }
        }

    }
    public void switchToPreviousScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/welcome-view.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToSignUpScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/customer/customerSignUp-view.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void closeStage(ActionEvent event){
        HelloApplication.stage.close();
    }

}
