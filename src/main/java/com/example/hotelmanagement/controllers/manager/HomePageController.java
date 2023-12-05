package com.example.hotelmanagement.controllers.manager;

import com.example.hotelmanagement.HelloApplication;
import com.example.hotelmanagement.beans.Customer;
import com.example.hotelmanagement.beans.Employee;
import com.example.hotelmanagement.beans.Room;
import com.example.hotelmanagement.dao.CustomerDao;
import com.example.hotelmanagement.dao.InvoiceDao;
import com.example.hotelmanagement.dao.RoomDao;
import com.example.hotelmanagement.dao.RoomTypeDao;
import com.example.hotelmanagement.daoFactory.CummonDbFcts;
import com.example.hotelmanagement.localStorage.CustomerManager;
import com.example.hotelmanagement.config.PathConfig;
import com.example.hotelmanagement.localStorage.ManagerManager;
import com.example.hotelmanagement.localStorage.SwitchedPageManager;
import com.example.hotelmanagement.localStorage.VarsManager;
import com.example.hotelmanagement.tablesView.InvoicesTableView;
import com.example.hotelmanagement.tablesView.RoomsTableView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomePageController implements Initializable{
    private Stage stage;
    public static Stage childStage;
    private Scene scene;
    private Parent root;
    @FXML public Label roomAddedMsg, roomUpdatedMsg, roomDeletedMsg;
    @FXML public Label addedMsg, updatedMsg, deletedMsg;
    @FXML private Label fullnameLabel;
    @FXML private Label noRowsMsg, rowSelectedError, confirmPayMsg, changeStatusError;

    @FXML private TableView<RoomsTableView> roomsTable;
    @FXML private TableColumn<RoomsTableView, Object> idCol, roomNumberCol, typeCol, capacityCol, statusCol, price_dayCol;
    @FXML private CheckBox Available, Occupied, UnderCleaning, Cleaned, Maintenance, NeedsMaintenance, OutofService, CheckedOut;
    @FXML private TextField priceField, capacityField;

    @FXML private TableView<InvoicesTableView> invoicesTable;
    @FXML private TableColumn<InvoicesTableView, Object> id_Col, invoiceIdCol, invoiceDateCol, fullnameCol, cinCol, amountCol, status_Col;
    @FXML private CheckBox Paid, Unpaid, Cancelled;
    @FXML private TextField cinField, fullnameField;
    @FXML private DatePicker invoiceDate;
    @FXML private Button payBtn, confirmPayBtn, hideConfirmPayBtn;
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
            loadDataOnTable(new ArrayList<>(), "", "");
            noRowsMsg.setVisible(false);
            rowSelectedError.setVisible(false);
        } else if (currentPage.equals("Reservation")) {

        } else if (currentPage.equals("Cleaning")) {

        } else if (currentPage.equals("Maintenance")) {

        } else if (currentPage.equals("Invoices")) {
            updatedMsg.setVisible(false);
            loadDataOnInvoicesTable(new ArrayList<>(), "", "", null);
            noRowsMsg.setVisible(false);
            rowSelectedError.setVisible(false);
            confirmPayMsg.setVisible(false);
            changeStatusError.setVisible(false);
            confirmPayBtn.setVisible(false);
            hideConfirmPayBtn.setVisible(false);



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
    public void loadDataOnTable(List<String> statusList, String price, String capacity){
        noRowsMsg.setVisible(false);
        List<RoomsTableView> roomsList = new ArrayList<>();
        roomsTable.getItems().clear();
        RoomsTableView.setNBR(1);

        List<String> colToSelect =  new ArrayList<String>(List.of("r.roomId","r.numRoom","r.type","r.capacity","r.status","rT.price_day"));        if(statusList.isEmpty() && price.isEmpty() && capacity.isEmpty()){
            List<Object[]> roomsdetails = CummonDbFcts.performJoinAndSelect(RoomDao.TABLE_NAME, "r", RoomTypeDao.TABLE_NAME,"rT","type","type", colToSelect, "");
            for (Object[] row : roomsdetails) {
                    RoomsTableView roomRow = new RoomsTableView(row[0],row[1],row[2],row[3],row[4],row[5]);
                    //System.out.println(roomRow);
                    roomsList.add(roomRow);
            }
        }else{
            //join and select rooms with status checked and price < priceSelected and capacite< capacity
            String col1 = "r.status", col2 = "rT.price_day * (1 + r.capacity) - (r.capacity * 40)", col3 = "r.capacity";
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

            List<Object[]> roomsdetails = CummonDbFcts.performJoinAndSelect(RoomDao.TABLE_NAME, "r", RoomTypeDao.TABLE_NAME,"rT","type","type", colToSelect, whereClause);
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
    public void newRoomWindow(ActionEvent event) throws IOException {
        rowSelectedError.setVisible(false);
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
            roomUpdatedMsg.setVisible(false);
            roomDeletedMsg.setVisible(false);
            roomAddedMsg.setVisible(true);
            hideMsg(roomAddedMsg,4);
        }
        loadDataOnTable(new ArrayList<>(), "", "");
    }
    public void editRoomWindow(ActionEvent event) throws IOException {
        rowSelectedError.setVisible(false);
        if(roomsTable.getSelectionModel().getSelectedItem() == null){
            rowSelectedError.setVisible(true);
            return;
        }
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
            roomDeletedMsg.setVisible(false);
            roomAddedMsg.setVisible(false);
            roomUpdatedMsg.setVisible(true);
            hideMsg(roomUpdatedMsg,4);
        }
        loadDataOnTable(new ArrayList<>(), "", "");
    }
    public void deleteRoomWindow(ActionEvent event) throws IOException {
        rowSelectedError.setVisible(false);
        if(roomsTable.getSelectionModel().getSelectedItem() == null){
            rowSelectedError.setVisible(true);
            return;
        }

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
            roomAddedMsg.setVisible(false);
            roomUpdatedMsg.setVisible(false);
            roomDeletedMsg.setVisible(true);
            hideMsg(roomDeletedMsg,4);

        }
        loadDataOnTable(new ArrayList<>(), "", "");
    }
//--------------------------------------Invoices -----------------------------------------
    public void loadDataOnInvoicesTable(List<String> statusList, String cin, String fullname, LocalDate invDate){
        noRowsMsg.setVisible(false);
        rowSelectedError.setVisible(false);
        confirmPayMsg.setVisible(false);
        changeStatusError.setVisible(false);
        confirmPayBtn.setVisible(false);
        hideConfirmPayBtn.setVisible(false);

        List<InvoicesTableView> invoiceList = new ArrayList<>();
        invoicesTable.getItems().clear();
        InvoicesTableView.setNBR(1);

        List<String> colToSelect =  new ArrayList<String>(List.of("inv.invoiceId", "inv.customerId", "cust.fullName", "cust.cin", "inv.reservationId", "inv.status", "inv.amount", "inv.invoiceDate"));
        if(statusList.isEmpty() && cin.isEmpty() && fullname.isEmpty() && invDate == null){
            List<Object[]> invoicesdetails = CummonDbFcts.performJoinAndSelect(InvoiceDao.TABLE_NAME, "inv", CustomerDao.TABLE_NAME,"cust","customerId","customerId", colToSelect, "");
            for (Object[] row : invoicesdetails) {
                InvoicesTableView invoiceRow = new InvoicesTableView(row[0],row[1],row[2],row[3],row[4],row[5],row[6],row[7]);
                //System.out.println(roomRow);
                invoiceList.add(invoiceRow);
            }
        }else{
            //join and select rooms with status checked and price < priceSelected and capacite< capacity
            String col1 = "inv.status", col2 = "cust.cin", col3 = "cust.fullName", col4 = "inv.invoiceDate";
            String whereClause = " WHERE ";
            if(!statusList.isEmpty()){
                whereClause += "(";
                for (String status: statusList){
                    whereClause = whereClause + col1 + " = '" + status + "' OR ";
                }
                whereClause = whereClause.substring(0, whereClause.length() - 4); //delete last " OR "
                whereClause += ") AND ";
            }
            if(!cin.isEmpty()){
                whereClause += "("+ col2 + " LIKE '%" + cin + "%') AND ";
            }
            if(!fullname.isEmpty()){
                whereClause += "("+ col3 + " LIKE '%" + fullname + "%') AND ";
            }
            if(invDate != null){
                whereClause += "("+ col4 + " LIKE '%" + invDate + "%') AND ";
            }
            whereClause = whereClause.substring(0, whereClause.length() - 5);//delete last " AND "
            System.out.println(whereClause);

            List<Object[]> invoicesdetails = CummonDbFcts.performJoinAndSelect(InvoiceDao.TABLE_NAME, "inv", CustomerDao.TABLE_NAME,"cust","customerId","customerId", colToSelect, whereClause);
            for (Object[] row : invoicesdetails) {
                InvoicesTableView invoiceRow = new InvoicesTableView(row[0],row[1],row[2],row[3],row[4],row[5],row[6],row[7]);
                //System.out.println(roomRow);
                invoiceList.add(invoiceRow);
            }
        }

        id_Col.setCellValueFactory(new PropertyValueFactory<>("i"));
        invoiceIdCol.setCellValueFactory(new PropertyValueFactory<>("invoiceId"));
        invoiceDateCol.setCellValueFactory(new PropertyValueFactory<>("invoiceDate"));
        fullnameCol.setCellValueFactory(new PropertyValueFactory<>("customerFullName"));
        cinCol.setCellValueFactory(new PropertyValueFactory<>("customerCin"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        status_Col.setCellValueFactory(new PropertyValueFactory<>("status"));
        invoicesTable.getItems().addAll(invoiceList);
        if(invoiceList.isEmpty()){
            noRowsMsg.setVisible(true);
        }
    }
    public void filterInvoices(ActionEvent event){
        List<String> statusList = new ArrayList<>();

        if(Paid.isSelected()) statusList.add("Paid");

        if(Unpaid.isSelected()) statusList.add("Unpaid");

        if(Cancelled.isSelected()) statusList.add("Cancelled");

        String cin = cinField.getText();
        String fullname = fullnameField.getText();
        LocalDate invDate = invoiceDate.getValue();

        loadDataOnInvoicesTable(statusList, cin, fullname, invDate);
    }
    public void displayConfirmation (ActionEvent event){
        confirmPayMsg.setVisible(false);
        confirmPayBtn.setVisible(false);
        hideConfirmPayBtn.setVisible(false);
        rowSelectedError.setVisible(false);
        changeStatusError.setVisible(false);

        if(invoicesTable.getSelectionModel().getSelectedItem() == null){
            rowSelectedError.setVisible(true);
            return;
        }

        String currentStatus = (String) invoicesTable.getSelectionModel().getSelectedItem().getStatus();
        if(currentStatus.equals("Unpaid")){
            payBtn.setVisible(false);
            confirmPayMsg.setVisible(true);
            confirmPayBtn.setVisible(true);
            hideConfirmPayBtn.setVisible(true);
        } else {
            changeStatusError.setVisible(true);
        }
    }
    public void hideConfirmation (ActionEvent event){
        confirmPayMsg.setVisible(false);
        confirmPayBtn.setVisible(false);
        hideConfirmPayBtn.setVisible(false);
        payBtn.setVisible(true);
    }
    public void payInvoice(ActionEvent event) throws IOException {

        VarsManager.selectedInvoiceId = (int) invoicesTable.getSelectionModel().getSelectedItem().getInvoiceId();
        int i = InvoiceDao.update("status","Paid","invoiceId",VarsManager.selectedInvoiceId);
        VarsManager.selectedInvoiceId = 0;

        if(i>0){
            updatedMsg.setVisible(true);
            hideMsg(updatedMsg,4);
        }
        loadDataOnInvoicesTable(new ArrayList<>(), "", "",null);
        hideConfirmation(event);
    }


//-------------------------------------------------------------------------------
    public void hideMsg(Label msg,double time){
        Duration duration = Duration.seconds(time);
        Timeline timeline = new Timeline(new KeyFrame(duration, e -> msg.setVisible(false)));
        timeline.setCycleCount(1);
        timeline.play();
    }
    public void logout(ActionEvent event){
        ManagerManager.getInstance().setManager(new Employee("","","","","","",0,"",""));
        HelloApplication.stage.close();
    }

}
