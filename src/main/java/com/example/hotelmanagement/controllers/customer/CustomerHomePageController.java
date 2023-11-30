package com.example.hotelmanagement.controllers.customer;

import com.example.hotelmanagement.HelloApplication;
import com.example.hotelmanagement.beans.Customer;
import com.example.hotelmanagement.dao.RoomDao;
import com.example.hotelmanagement.dao.RoomTypeDao;
import com.example.hotelmanagement.daoFactory.CummonDbFcts;
import com.example.hotelmanagement.localStorage.CustomerManager;
import com.example.hotelmanagement.config.PathConfig;
import com.example.hotelmanagement.localStorage.SwitchedPageManager;
import com.example.hotelmanagement.tablesView.RoomsTableView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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

    @FXML private Label noRowsMsg;
    @FXML private CheckBox Available, Occupied, UnderCleaning, Cleaned, Maintenance, NeedsMaintenance, OutofService, CheckedOut;
    @FXML private TextField priceField, capacityField;
    @FXML private TableView<RoomsTableView> roomsTable;
    @FXML private TableColumn<RoomsTableView, Object> idCol, roomNumberCol, typeCol, capacityCol, statusCol, price_dayCol;


//------------------------------------------------------------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        currentPage = SwitchedPageManager.getInstance().getSwitchedPage();
        currentCustomer = CustomerManager.getInstance().getCustomer();
        System.out.println(currentCustomer);
        System.out.println(currentPage);
        System.out.println(currentPage.equals("CustomerInfos"));
        fullnameLabel.setText("- " + currentCustomer.getFullName() + " -");
        if(currentPage.equals("Home")){

        } else if (currentPage.equals("CustomerInfos")){
            succesMsg.setVisible(false);
            initializeFields(currentCustomer);
        } else if (currentPage.equals("RoomsDetail")) {
            loadDataOnTable(new ArrayList<>(), "", "");
            noRowsMsg.setVisible(false);
        } else if (currentPage.equals("Services")) {

        } else if (currentPage.equals("Invoices")) {

        } else if (currentPage.equals("Feedback")) {

        } else if (currentPage.equals("About")) {

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
//----------------------------------- room details fcts--------------------------------------------
    public void loadDataOnTable(List<String> statusList, String price, String capacity){
        noRowsMsg.setVisible(false);

        List<RoomsTableView> roomsList = new ArrayList<>();
        roomsTable.getItems().clear();
        RoomsTableView.setNBR(1);

        List<String> colToSelect =  new ArrayList<String>(List.of("room.roomId","room.numRoom","room.type","room.capacity","room.status","roomType.price_day"));
        if(statusList.isEmpty() && price.isEmpty() && capacity.isEmpty()){
            List<Object[]> roomsdetails = CummonDbFcts.performJoinAndSelect(RoomDao.TABLE_NAME, RoomTypeDao.TABLE_NAME,"type","type", colToSelect, "");
            for (Object[] row : roomsdetails) {
                RoomsTableView roomRow = new RoomsTableView(row[0],row[1],row[2],row[3],row[4],row[5]);
                //System.out.println(roomRow);
                roomsList.add(roomRow);
            }
        }else{
            //join and select rooms with status checked and price < priceSelected and capacite< capacity
            String col1 = "room.status", col2 = "roomType.price_day * (1 + room.capacity) - (room.capacity * 40)", col3 = "room.capacity";
            String whereClause = " WHERE ";
            if(!statusList.isEmpty()){
                whereClause += "(";
                for (String status: statusList){
                    whereClause = whereClause + col1 + " = '" + status + "' OR ";
                }
                whereClause = whereClause.substring(0, whereClause.length() - 4); //delete last " OR "
                whereClause += ") AND ";
            }
            if(!price.isEmpty()){
                whereClause += "("+ col2 + " <= " + price +") AND ";
            }
            if(!capacity.isEmpty()){
                whereClause += "("+ col3 + " <= " + capacity +") AND ";
            }
            whereClause = whereClause.substring(0, whereClause.length() - 5);//delete last " AND "
            System.out.println(whereClause);

            List<Object[]> roomsdetails = CummonDbFcts.performJoinAndSelect(RoomDao.TABLE_NAME, RoomTypeDao.TABLE_NAME,"type","type", colToSelect, whereClause);
            for (Object[] row : roomsdetails) {
                RoomsTableView roomRow = new RoomsTableView(row[0],row[1],row[2],row[3],row[4],row[5]);
                //System.out.println(roomRow);
                roomsList.add(roomRow);
            }
        }


        idCol.setCellValueFactory(new PropertyValueFactory<>("i"));
        roomNumberCol.setCellValueFactory(new PropertyValueFactory<>("numRoom"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        capacityCol.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        price_dayCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        roomsTable.getItems().addAll(roomsList);
        if(roomsList.isEmpty()){
            noRowsMsg.setVisible(true);
        }
    }
    public void filterRooms(ActionEvent event){
        List<String> statusList = new ArrayList<>();

        if(Available.isSelected()) statusList.add("Available");

        if(Occupied.isSelected()) statusList.add("Occupied");

        if(UnderCleaning.isSelected()) statusList.add("Under Cleaning");

        if(Cleaned.isSelected()) statusList.add("Cleaned");

        if(NeedsMaintenance.isSelected()) statusList.add("Needs Maintenance");

        if(Maintenance.isSelected()) statusList.add("Maintenance");

        if(OutofService.isSelected()) statusList.add("Out of Service");

        if(CheckedOut.isSelected()) statusList.add("Checked Out");

        String price = priceField.getText();
        String capacity = capacityField.getText();

        loadDataOnTable(statusList, price, capacity);

        System.out.println(statusList.toString());
        System.out.println(price);
        System.out.println(capacity);

    }
    public void logout(ActionEvent event){
        CustomerManager.getInstance().setCustomer(new Customer("","","","","",""));
        HelloApplication.stage.close();
    }

}
