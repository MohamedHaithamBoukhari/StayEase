package com.example.hotelmanagement.controllers.customer;

import com.example.hotelmanagement.HelloApplication;
import com.example.hotelmanagement.beans.Customer;
import com.example.hotelmanagement.localStorage.CustomerManager;
import com.example.hotelmanagement.config.PathConfig;
import com.example.hotelmanagement.localStorage.SwitchedPageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class CustomerHomePageController implements Initializable{
    private Stage stage;
    public static Stage childStage;
    public static boolean updated = false;
    private Scene scene;
    private Parent root;
    private Customer currentCustomer;
    private String currentPage;

    @FXML private Label succesMsg;
    @FXML private AnchorPane rootPane;
    @FXML private Label fullnameLabel;
    @FXML TextField fullNameField, cinField, emailAddressField, passwordField, phoneField, addressField;//fields of user infos
//------------------------------------------------------------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        currentPage = SwitchedPageManager.getInstance().getSwitchedPage();
        currentCustomer = CustomerManager.getInstance().getCustomer();
        System.out.println(currentCustomer);
        System.out.println(currentPage);
        System.out.println(currentPage.equals("CustomerInfos"));
        fullnameLabel.setText("- " + currentCustomer.getFullName() + " -");
        if(currentPage.equals("CustomerInfos")){
            succesMsg.setVisible(false);
            initializeFields(currentCustomer);
        }
    }
    public void initializeFields(Customer customer){
        fullNameField.setText(customer.getFullName());
        cinField.setText(customer.getCin());
        emailAddressField.setText(customer.getEmail());
        passwordField.setText(customer.getPassword());
        phoneField.setText(customer.getPhone());
        addressField.setText(customer.getAddress());
    }
//------------------------------------------------------------------------------------------
    public void switchToHome(ActionEvent event) throws IOException {
        SwitchedPageManager.getInstance().setSwitchedPage("Home");
        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/customer/customerHomePage-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToCustomerInfos(ActionEvent event) throws IOException {
        SwitchedPageManager.getInstance().setSwitchedPage("CustomerInfos");
        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/customer/CustomerInfos-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToRoomsDetail(ActionEvent event) throws IOException {
        SwitchedPageManager.getInstance().setSwitchedPage("RoomsDetail");
        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/customer/RoomsDetail-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToServices(ActionEvent event) throws IOException {
        SwitchedPageManager.getInstance().setSwitchedPage("Services");
        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/customer/Services-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToInvoices(ActionEvent event) throws IOException {
        SwitchedPageManager.getInstance().setSwitchedPage("Invoices");
        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/customer/Invoices-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToFeedback(ActionEvent event) throws IOException {
        SwitchedPageManager.getInstance().setSwitchedPage("Feedback");
        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/customer/Feedback-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToAbout(ActionEvent event) throws IOException {
        SwitchedPageManager.getInstance().setSwitchedPage("About");
        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/customer/About-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
//-------------------------------------------------------------------------------
    public void newEditInfoWindow(ActionEvent event) throws IOException {
//        rootPane.setStyle(" -fx-background-color:rgb(28,36,58);");

        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/customer/editInfo-view.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);
        childStage = new Stage();
        childStage.setScene(scene);

        String cssFile = String.valueOf(new URL(PathConfig.RESSOURCES_ABS_PATH + "css/customer/customerSignUp.css"));
        scene.getStylesheets().add(cssFile);

        childStage.initStyle(StageStyle.TRANSPARENT);
        childStage.setScene(scene);

        Stage parentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        parentStage.setResizable(false);
        childStage.initOwner(parentStage);
        childStage.initModality(Modality.WINDOW_MODAL);

        childStage.showAndWait();

        if(updated){//if we update customer infos the values of updated is set to true
            initializeFields(CustomerManager.getInstance().getCustomer());
            succesMsg.setVisible(true);
        }

    }
    public void logout(ActionEvent event){
        CustomerManager.getInstance().setCustomer(new Customer("","","","","",""));
        HelloApplication.stage.close();
    }

}
