package com.example.hotelmanagement.controllers.admin;

import com.example.hotelmanagement.HelloApplication;
import com.example.hotelmanagement.beans.Customer;
import com.example.hotelmanagement.beans.Employee;
import com.example.hotelmanagement.dao.EmployeeDao;
import com.example.hotelmanagement.localStorage.AdminManager;
import com.example.hotelmanagement.localStorage.CustomerManager;
import com.example.hotelmanagement.config.PathConfig;
import com.example.hotelmanagement.config.Validation;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField usernameField, passwordField;
    @FXML
    private Label errorInfoLabel, usernameError, passwordError;

    public boolean verifyFields(ActionEvent event, String username, String password){
        boolean verified = false;
        errorInfoLabel.setText("");

        if(!Validation.isValidEmail(username)){
//            errors.add("Field must be not empty");
            usernameError.setText("Please Enter Valid email");
            verified = false;
        }else {
            usernameError.setText("");
            verified = true;
        }

        if (!Validation.isValidPassword(password)) {
            passwordError.setText("Password must be at least 8 characters long");
            verified = false;
        }
        else {
            passwordError.setText("");
            verified = true;
        }

        return verified;
    }
    public void logInAction(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if(verifyFields(event, username, password)){
            Map<String, Object> map = new HashMap<>();
            map.put("email", username);
            map.put("password", password);
            map.put("position", "admin");

            List<Object> employees = EmployeeDao.select(map);
            if(employees.size() == 1){
                Employee employee= (Employee) employees.get(0);
                AdminManager.getInstance().setAdmin(employee);

                switchToHomePage(event);
            }else {
                errorInfoLabel.setText("Email or password is incorrect :(");
            }
        }

    }
    public void switchToHomePage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/admin/HomePage-view.fxml"));
        root = loader.load();

        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToPreviousScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/welcome-view.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void closeStage(ActionEvent event){
        HelloApplication.stage.close();
    }

}
