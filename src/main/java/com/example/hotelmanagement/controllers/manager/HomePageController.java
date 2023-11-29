package com.example.hotelmanagement.controllers.manager;

import com.example.hotelmanagement.HelloApplication;
import com.example.hotelmanagement.beans.Customer;
import com.example.hotelmanagement.beans.Employee;
import com.example.hotelmanagement.beans.Room;
import com.example.hotelmanagement.dao.RoomDao;
import com.example.hotelmanagement.dao.RoomTypeDao;
import com.example.hotelmanagement.daoFactory.CummonDbFcts;
import com.example.hotelmanagement.localStorage.CustomerManager;
import com.example.hotelmanagement.config.PathConfig;
import com.example.hotelmanagement.localStorage.ManagerManager;
import com.example.hotelmanagement.localStorage.SwitchedPageManager;
import com.example.hotelmanagement.localStorage.VarsManager;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomePageController implements Initializable{
    private Stage stage;
    public static Stage childStage;
    private Scene scene;
    private Parent root;
    @FXML public Label roomAddedMsg;
    @FXML public Label roomUpdatedMsg;
    @FXML public Label roomDeletedMsg;
    @FXML private AnchorPane rootPane;
    @FXML private Label fullnameLabel;

    @FXML private TableView<RoomsTableView> roomsTable;
    @FXML private TableColumn<RoomsTableView, Object> idCol;
    @FXML private TableColumn<RoomsTableView, Object> roomNumberCol;
    @FXML private TableColumn<RoomsTableView, Object> typeCol;
    @FXML private TableColumn<RoomsTableView, Object> capacityCol;
    @FXML private TableColumn<RoomsTableView, Object> statusCol;
    @FXML private TableColumn<RoomsTableView, Object> price_dayCol;
//------------------------------------------------------------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String currentPage = SwitchedPageManager.getInstance().getSwitchedPage();
        Employee manager = ManagerManager.getInstance().getManager();

        fullnameLabel.setText("- " + manager.getFullName() + " -");

        if(currentPage.equals("Home")){

        } else if (currentPage.equals("RoomsDetail")) {
            roomAddedMsg.setVisible(false);
            roomUpdatedMsg.setVisible(false);
            roomDeletedMsg.setVisible(false);
            loadDataOnTable();
        } else if (currentPage.equals("Reservation")) {

        } else if (currentPage.equals("Cleaning")) {

        } else if (currentPage.equals("Maintenance")) {

        } else if (currentPage.equals("Invoices")) {

        } else if (currentPage.equals("Complaint")) {

        }
    }
//------------------------------------------------------------------------------------------
    public void switchToHome(ActionEvent event) throws IOException {
        SwitchedPageManager.getInstance().setSwitchedPage("Home");
        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/manager/HomePage-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToRoomsDetail(ActionEvent event) throws IOException {
        SwitchedPageManager.getInstance().setSwitchedPage("RoomsDetail");
        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/manager/RoomsDetail-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.  setScene(scene);
        stage.show();
    }
    public void switchToReservation(ActionEvent event) throws IOException {
        SwitchedPageManager.getInstance().setSwitchedPage("Reservation");
        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/manager/Reservation-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToCleaning(ActionEvent event) throws IOException {
        SwitchedPageManager.getInstance().setSwitchedPage("Cleaning");
        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/manager/Cleaning-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToMaintenance(ActionEvent event) throws IOException {
        SwitchedPageManager.getInstance().setSwitchedPage("Maintenance");
        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/manager/Maintenance-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToInvoices(ActionEvent event) throws IOException {
        SwitchedPageManager.getInstance().setSwitchedPage("Invoices");
        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/manager/Invoices-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToComplaint(ActionEvent event) throws IOException {
        SwitchedPageManager.getInstance().setSwitchedPage("Complaint");
        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/manager/Complaint-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();}
//----------------------------------- room details fcts--------------------------------------------
    public void loadDataOnTable(){
        List<Object[]> roomsdetails = CummonDbFcts.performJoinAndSelect(RoomDao.TABLE_NAME, RoomTypeDao.TABLE_NAME,"type","type", new ArrayList<String>(List.of("room.roomId","room.numRoom","room.type","room.capacity","room.status","roomType.price_day")));
        List<RoomsTableView> roomsList = new ArrayList<>();
        roomsTable.getItems().clear();
        RoomsTableView.setNBR(1);

        for (Object[] row : roomsdetails) {
                RoomsTableView roomRow = new RoomsTableView(row[0],row[1],row[2],row[3],row[4],row[5]);
//              System.out.println(roomRow);
                roomsList.add(roomRow);
        }

        idCol.setCellValueFactory(new PropertyValueFactory<>("i"));
        roomNumberCol.setCellValueFactory(new PropertyValueFactory<>("numRoom"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        capacityCol.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        price_dayCol.setCellValueFactory(new PropertyValueFactory<>("price_day"));
        roomsTable.getItems().addAll(roomsList);
    }
    public void newRoomWindow(ActionEvent event) throws IOException {
        VarsManager.actionStarted ="add";

        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/manager/NewRoom-view.fxml"));
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

        if(VarsManager.actionCompleted.equals("add")){
            roomAddedMsg.setVisible(true);
        }
        loadDataOnTable();
    }
    public void editRoomWindow(ActionEvent event) throws IOException {
        VarsManager.actionStarted = "update";
        VarsManager.selectedRoomId = (int) roomsTable.getSelectionModel().getSelectedItem().getRoomId();

        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/manager/EditRoom-view.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);
        childStage = new Stage();
        childStage.setScene(scene);

        childStage.initStyle(StageStyle.TRANSPARENT);
        childStage.setScene(scene);

        Stage parentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        parentStage.setResizable(false);
        childStage.initOwner(parentStage);
        childStage.initModality(Modality.WINDOW_MODAL);

        childStage.showAndWait();

        if(VarsManager.actionCompleted.equals("update")){
            roomUpdatedMsg.setVisible(true);
        }
        loadDataOnTable();
    }
    public void deleteRoomWindow(ActionEvent event) throws IOException {
        VarsManager.actionStarted = "delete";
        VarsManager.selectedRoomId = (int) roomsTable.getSelectionModel().getSelectedItem().getRoomId();

        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/manager/DeleteRoom-view.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);
        childStage = new Stage();
        childStage.setScene(scene);

        childStage.initStyle(StageStyle.TRANSPARENT);
        childStage.setScene(scene);

        Stage parentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        parentStage.setResizable(false);
        childStage.initOwner(parentStage);
        childStage.initModality(Modality.WINDOW_MODAL);

        childStage.showAndWait();

        if(VarsManager.actionCompleted.equals("delete")){
            roomDeletedMsg.setVisible(true);
        }
        loadDataOnTable();
    }
//-------------------------------------------------------------------------------
    public void logout(ActionEvent event){
        ManagerManager.getInstance().setManager(new Employee("","","","","","",0,"",""));
        HelloApplication.stage.close();
    }

}
