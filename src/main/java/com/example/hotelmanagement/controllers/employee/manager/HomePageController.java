package com.example.hotelmanagement.controllers.employee.manager;

import com.example.hotelmanagement.HelloApplication;
import com.example.hotelmanagement.beans.Complaint;
import com.example.hotelmanagement.beans.Employee;
import com.example.hotelmanagement.beans.Service;
import com.example.hotelmanagement.dao.*;
import com.example.hotelmanagement.daoFactory.CummonDbFcts;
import com.example.hotelmanagement.localStorage.CustomerManager;
import com.example.hotelmanagement.config.PathConfig;
import com.example.hotelmanagement.localStorage.EmployeeManager;
import com.example.hotelmanagement.localStorage.SwitchedPageManager;
import com.example.hotelmanagement.localStorage.VarsManager;
import com.example.hotelmanagement.tablesView.*;
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
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class HomePageController implements Initializable{
    private Stage stage;
    public static Stage childStage;
    private Scene scene;
    private Parent root;
    @FXML private Label maintenanceStaffNbr,cleanersNbr,customersNbr, reservationsNbr, complaintsNbr,tasksNbr, completedTasksNbr, onHoldTasksNbr, roomsNbr, occupiedRoomNbr, roomTYpesNbr, roomNeedsMaintenanceNbr, roomNeedsCleaningNbr;
    @FXML public Label roomAddedMsg, roomUpdatedMsg, roomDeletedMsg;
    @FXML public Label addedMsg, updatedMsg, deletedMsg;
    @FXML private Label fullnameLabel;
    @FXML private Label noRowsMsg, rowSelectedError, confirmPayMsg, changeStatusError;

    @FXML private CheckBox Available, Occupied, NeedsCleaning, UnderCleaning, UnderMaintenance, NeedsMaintenance, OutofService, CheckedOut;
    @FXML private TextField priceField, capacityField, typeField;

    @FXML private TableView<RoomsTableView> roomsTable;
    @FXML private TableColumn<RoomsTableView, Object> idCol, roomNumberCol, typeCol, capacityCol, statusCol, price_dayCol;

    @FXML private TableView<RoomsTableView> maintenanceTable;
    @FXML private Label maintenanceAssigningError;

    @FXML private TableView<RoomsTableView> cleaningTable;
    @FXML private Label cleaningAssigningError;

    @FXML private TableView<ReservationTableView> reservationTable;
    @FXML private TableColumn<ReservationTableView, Object> id__Col, ReservationDateCol, CheckInDateCol, CheckOutDate, DurationCol, roomNbrCol, RoomTypeCol, PriceCol, StatusCol;
    @FXML public CheckBox Upcoming, InProgress, CompletedStay, Cancelled_;

    @FXML private TableView<InvoicesTableView> invoicesTable;
    @FXML private TableColumn<InvoicesTableView, Object> id_Col, invoiceIdCol, invoiceDateCol, fullnameCol, cinCol, amountCol, status_Col;
    @FXML private CheckBox Paid, Unpaid, Cancelled;
    @FXML private TextField cinField, fullnameField;
    @FXML private DatePicker invoiceDate;
    @FXML private Button payBtn, confirmPayBtn, hideConfirmPayBtn;

    @FXML private TableView<ComplaintTableView> complaintTable;
    @FXML private TableColumn<ComplaintTableView, Object> complaintObjectCol, responseStatusCol, complaintDateCol,declarantStatusCol,declarantEmailCol;
    @FXML private CheckBox Replied, Unreplied, ComplaintDateAsc, ComplaintDateDesc, Customer, MaintenanceStaff, Cleaner;
    @FXML private DatePicker complaintDatePicker;
    @FXML private TextField complaintObjectField, objectField;
    @FXML private TextArea complaintField,responseField;
    @FXML private AnchorPane replyComplaintPane, confirmDeletePane, detailsPane;
    @FXML private Label detailResponseDate, detailResponse, detailComplaint, detailComplaintObject;
    @FXML private Label replyError;
    @FXML private AnchorPane actionPane;

    @FXML private TableView<CustomerTableView> customersTable;
    @FXML private TableColumn<CustomerTableView, Object> id___Col, emailCol, cin_Col, unpaid_invoicesCol, cancelled_reservationsCol, account_statusCol;
    @FXML private Label accountBlockedError, accountUnblockedError;
    @FXML private Button blockBtn, unblockBtn;
    @FXML private AnchorPane blockAccountPane, unblockAccountPane;
    @FXML private CheckBox Active, Blocked;
    @FXML private TextField cin_Field, emailField;
    @FXML private Label blockedMsg, unblockedMsg;
//------------------------------------------------------------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String currentPage = SwitchedPageManager.getInstance().getSwitchedPage();
        Employee manager = EmployeeManager.getInstance().getEmployee();

        fullnameLabel.setText("- " + manager.getFullName() + " -");

        if(currentPage.equals("Home")){
            Map map1 = new HashMap<>();
            map1.put("position", "Maintenance Staff");
            maintenanceStaffNbr.setText(String.valueOf(EmployeeDao.select(map1, "*").size()));
            Map map2 = new HashMap<>();
            map2.put("position", "Cleaner");
            cleanersNbr.setText(String.valueOf(EmployeeDao.select(map2, "*").size()));
            customersNbr.setText(String.valueOf(CummonDbFcts.countRows(CustomerDao.TABLE_NAME)));
            reservationsNbr.setText(String.valueOf(CummonDbFcts.countRows(ReservationDao.TABLE_NAME)));
            complaintsNbr.setText(String.valueOf(CummonDbFcts.countRows(DeclarationDao.TABLE_NAME)));
            tasksNbr.setText(String.valueOf(CummonDbFcts.countRows(TaskDao.TABLE_NAME)));
            Map map3 = new HashMap<>();
            map3.put("status", "Completed");
            completedTasksNbr.setText(String.valueOf(TaskDao.select(map3, "*").size()));
            Map map4 = new HashMap<>();
            map4.put("status", "On Hold");
            onHoldTasksNbr.setText(String.valueOf(TaskDao.select(map4, "*").size()));
            roomsNbr.setText(String.valueOf(CummonDbFcts.countRows(RoomDao.TABLE_NAME)));
            Map map5 = new HashMap<>();
            map5.put("status", "Occupied");
            occupiedRoomNbr.setText(String.valueOf((RoomDao.select(map5,"*")).size()));
            Map map6 = new HashMap<>();
            map6.put("status", "Needs Maintenance");
            roomNeedsMaintenanceNbr.setText(String.valueOf((RoomDao.select(map6,"*")).size()));
            Map map7 = new HashMap<>();
            map7.put("status", "Needs Cleaning");
            roomNeedsCleaningNbr.setText(String.valueOf((RoomDao.select(map7,"*")).size()));
        } else if (currentPage.equals("Customers")) {
            noRowsMsg.setVisible(false);
            rowSelectedError.setVisible(false);
            accountBlockedError.setVisible(false);
            accountUnblockedError.setVisible(false);
            blockAccountPane.setVisible(false);
            unblockAccountPane.setVisible(false);
            blockedMsg.setVisible(false);
            unblockedMsg.setVisible(false);
            loadDataOnCustomersTable(new ArrayList<>(),"","");

        }else if (currentPage.equals("RoomsDetail")) {
            roomAddedMsg.setVisible(false);
            roomUpdatedMsg.setVisible(false);
            roomDeletedMsg.setVisible(false);
            loadDataOnTable(new ArrayList<>(), "", "");
            noRowsMsg.setVisible(false);
            rowSelectedError.setVisible(false);
        } else if (currentPage.equals("Reservation")) {
            loadDataOnReservationTable(new ArrayList<>());
            noRowsMsg.setVisible(false);
        } else if (currentPage.equals("Cleaning")) {
            rowSelectedError.setVisible(false);
            cleaningAssigningError.setVisible(false);
            rowSelectedError.setVisible(false);
            addedMsg.setVisible(false);
            loadDataOnCleaningTable(new ArrayList<>(), "", "");
        } else if (currentPage.equals("Maintenance")) {
            rowSelectedError.setVisible(false);
            maintenanceAssigningError.setVisible(false);
            rowSelectedError.setVisible(false);
            addedMsg.setVisible(false);
            loadDataOnMaintenanceTable(new ArrayList<>(), "", "");
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
            noRowsMsg.setVisible(false);
            replyError.setVisible(false);
            addedMsg.setVisible(false);
            deletedMsg.setVisible(false);
            rowSelectedError.setVisible(false);
            replyComplaintPane.setVisible(false);
            confirmDeletePane.setVisible(false);
            detailsPane.setVisible(false);
            loadDataOnComplaintsTable(new ArrayList<>(), new ArrayList<>(),"","", "");
        }
    }
//------------------------------------------------------------------------------------------
    public void switchToHome(ActionEvent event) throws IOException {
        SwitchedPageManager.getInstance().setSwitchedPage("Home");
        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/employee/manager/HomePage-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToCustomers(ActionEvent event) throws IOException {
        SwitchedPageManager.getInstance().setSwitchedPage("Customers");
        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/employee/manager/CustomersPage-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToRoomsDetail(ActionEvent event) throws IOException {
        SwitchedPageManager.getInstance().setSwitchedPage("RoomsDetail");
        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/employee/manager/RoomsDetail-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.  setScene(scene);
        stage.show();
    }
    public void switchToReservation(ActionEvent event) throws IOException {
        SwitchedPageManager.getInstance().setSwitchedPage("Reservation");
        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/employee/manager/Reservation-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToCleaning(ActionEvent event) throws IOException {
        SwitchedPageManager.getInstance().setSwitchedPage("Cleaning");
        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/employee/manager/Cleaning-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToMaintenance(ActionEvent event) throws IOException {
        SwitchedPageManager.getInstance().setSwitchedPage("Maintenance");
        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/employee/manager/Maintenance-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToInvoices(ActionEvent event) throws IOException {
        SwitchedPageManager.getInstance().setSwitchedPage("Invoices");
        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/employee/manager/Invoices-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToComplaint(ActionEvent event) throws IOException {
        SwitchedPageManager.getInstance().setSwitchedPage("Complaint");
        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/employee/manager/Complaint-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
//----------------------------------- customers fcts--------------------------------------------
    public void loadDataOnCustomersTable(List<String> statusList, String cin, String email){
        noRowsMsg.setVisible(false);
        rowSelectedError.setVisible(false);
        accountBlockedError.setVisible(false);
        accountUnblockedError.setVisible(false);
        blockAccountPane.setVisible(false);
        unblockAccountPane.setVisible(false);
        blockedMsg.setVisible(false);
        unblockedMsg.setVisible(false);

        List<CustomerTableView> customersList = new ArrayList<>();
        customersTable.getItems().clear();
        CustomerTableView.setNBR(1);

        String query = "SELECT customerId, cin, email, account_status " +
                       "FROM customer ";
        List<String> colToSelect =  new ArrayList<String>(List.of("customerId", "cin", "email", "account_status"));
        if(statusList.isEmpty() && cin.isEmpty() && email.isEmpty()){
            List<Object[]> customersdetails = CummonDbFcts.querySelect(query,colToSelect);
            for (Object[] row : customersdetails) {
                CustomerTableView customerRow = new CustomerTableView(row[0],row[1],row[2],row[3]);
                customersList.add(customerRow);
            }
        }else{
            String col1 = "account_status", col2 = "cin", col3 = "email";
            query += "WHERE ";
            if(!statusList.isEmpty()){
                query += "(";
                for (String status: statusList){
                    query = query + col1 + " = '" + status + "' OR ";
                }
                query = query.substring(0, query.length() - 4); //delete last " OR "
                query += ") AND ";
            }
            if(!cin.isEmpty()){
                query += "("+ col2 + " LIKE '%" + cin + "%') AND ";
            }
            if(!email.isEmpty()){
                query += "("+ col3 + " LIKE '%" + email + "%') AND ";
            }
            query = query.substring(0, query.length() - 5);//delete last " AND "
            System.out.println(query);

            List<Object[]> customersdetails = CummonDbFcts.querySelect(query,colToSelect);
            for (Object[] row : customersdetails) {
                CustomerTableView customerRow = new CustomerTableView(row[0],row[1],row[2],row[3]);
                customersList.add(customerRow);
            }
        }

        id___Col.setCellValueFactory(new PropertyValueFactory<>("i"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        cin_Col.setCellValueFactory(new PropertyValueFactory<>("cin"));
        unpaid_invoicesCol.setCellValueFactory(new PropertyValueFactory<>("unpaid_invoices"));
        cancelled_reservationsCol.setCellValueFactory(new PropertyValueFactory<>("cancelled_reservations"));
        account_statusCol.setCellValueFactory(new PropertyValueFactory<>("account_status"));
        customersTable.getItems().addAll(customersList);
        if(customersList.isEmpty()){
            noRowsMsg.setVisible(true);
        }
    }
    public void filterCustomers(ActionEvent event){
        List<String> statusList = new ArrayList<>();

        if(Active.isSelected()) statusList.add("Active");

        if(Blocked.isSelected()) statusList.add("Blocked");

        String cin = cin_Field.getText();
        String email = emailField.getText();

        loadDataOnCustomersTable(statusList, cin, email);
    }
    public void displayBlockAccountPane(ActionEvent event){
        rowSelectedError.setVisible(false);
        accountBlockedError.setVisible(false);
        accountUnblockedError.setVisible(false);
        unblockAccountPane.setVisible(false);
        blockedMsg.setVisible(false);
        unblockedMsg.setVisible(false);
        accountUnblockedError.setVisible(false);

        if(customersTable.getSelectionModel().getSelectedItem() == null){
            rowSelectedError.setVisible(true);
            return;
        }
        if(customersTable.getSelectionModel().getSelectedItem().getAccount_status().equals("Blocked")){
            accountBlockedError.setVisible(true);
            hideMsg(accountBlockedError,4);
            return;
        }
        blockBtn.setVisible(false);
        unblockBtn.setVisible(false);
        blockAccountPane.setVisible(true);
    }
    public void displayUnblockAccountPane(ActionEvent event){
        rowSelectedError.setVisible(false);
        accountBlockedError.setVisible(false);
        accountUnblockedError.setVisible(false);
        blockAccountPane.setVisible(false);
        blockedMsg.setVisible(false);
        unblockedMsg.setVisible(false);
        accountBlockedError.setVisible(false);

        if(customersTable.getSelectionModel().getSelectedItem() == null){
            rowSelectedError.setVisible(true);
            return;
        }
        if(customersTable.getSelectionModel().getSelectedItem().getAccount_status().equals("Active")){
            accountUnblockedError.setVisible(true);
            hideMsg(accountUnblockedError,4);
            return;
        }
        blockBtn.setVisible(false);
        unblockBtn.setVisible(false);
        unblockAccountPane.setVisible(true);
    }
    public void hideBlockPane(ActionEvent event){
        blockBtn.setVisible(true);
        unblockBtn.setVisible(true);
        blockAccountPane.setVisible(false);
    }
    public void hideUnblockPane(ActionEvent event){
        blockBtn.setVisible(true);
        unblockBtn.setVisible(true);
        unblockAccountPane.setVisible(false);
    }

    public void confirmBlockAccount(ActionEvent event) throws IOException {
        int customerId = (int)customersTable.getSelectionModel().getSelectedItem().getCustomerId();
        CustomerDao.update("account_status","Blocked","customerId",customerId);

        hideBlockPane(event);
        loadDataOnCustomersTable(new ArrayList<>(),"","");
        blockedMsg.setVisible(true);
        hideMsg(blockedMsg,4);
    }
    public void confirmUnblockAccount(ActionEvent event) throws IOException {
        int customerId = (int)customersTable.getSelectionModel().getSelectedItem().getCustomerId();
        CustomerDao.update("account_status","Active","customerId",customerId);

        hideUnblockPane(event);
        loadDataOnCustomersTable(new ArrayList<>(),"","");
        unblockedMsg.setVisible(true);
        hideMsg(unblockedMsg,4);
    }
//----------------------------------- room details fcts--------------------------------------------
    public void loadDataOnTable(List<String> statusList, String price, String capacity){
        noRowsMsg.setVisible(false);
        List<RoomsTableView> roomsList = new ArrayList<>();
        roomsTable.getItems().clear();
        RoomsTableView.setNBR(1);

        List<String> colToSelect =  new ArrayList<String>(List.of("r.roomId","r.numRoom","r.type","r.capacity","r.status","rT.price_day"));
        if(statusList.isEmpty() && price.isEmpty() && capacity.isEmpty()){
            List<Object[]> roomsdetails = CummonDbFcts.performJoinAndSelect(RoomDao.TABLE_NAME, "r", RoomTypeDao.TABLE_NAME,"rT","type","type", colToSelect, "");
            for (Object[] row : roomsdetails) {
                    RoomsTableView roomRow = new RoomsTableView(row[0],row[1],row[2],row[3],row[4],row[5],0);
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
                RoomsTableView roomRow = new RoomsTableView(row[0],row[1],row[2],row[3],row[4],row[5],0);
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

        if(NeedsCleaning.isSelected()) statusList.add("Needs Cleaning");

        if(UnderCleaning.isSelected()) statusList.add("Under Cleaning");

        if(NeedsMaintenance.isSelected()) statusList.add("Needs Maintenance");

        if(UnderMaintenance.isSelected()) statusList.add("Under Maintenance");

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

        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/employee/manager/NewRoom-view.fxml"));
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
            VarsManager.actionCompleted = "";
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

        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/employee/manager/EditRoom-view.fxml"));
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
            VarsManager.actionCompleted = "";
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

        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/employee/manager/DeleteRoom-view.fxml"));
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
            VarsManager.actionCompleted = "";
        }
        loadDataOnTable(new ArrayList<>(), "", "");
    }
//--------------------------------------Reservations -----------------------------------------
    public void loadDataOnReservationTable(List<String> statusList){
        noRowsMsg.setVisible(false);

        List<ReservationTableView> reservationList = new ArrayList<>();
        reservationTable.getItems().clear();
        ReservationTableView.setNBR(1);
        int customerId = CustomerManager.getInstance().getCustomer().getCustomerId();

        List<String> colToSelect =  new ArrayList<String>(List.of("res.reservationId", "res.reservationDate", "res.check_inDate", "res.check_outDate", "r.numRoom", "r.type", "res.status", "r.capacity"));
        if(statusList.isEmpty()){
            List<Object[]> reservationsDetail = CummonDbFcts.performJoinAndSelect(ReservationDao.TABLE_NAME, "res", RoomDao.TABLE_NAME,"r","roomId","roomId", colToSelect, "");
            for (Object[] row : reservationsDetail) {
                ReservationTableView resRow = new ReservationTableView(row[0],row[1],row[2],row[3],row[4],row[5],row[6],row[7]);
                //System.out.println(roomRow);
                reservationList.add(resRow);
            }
        }else{
            String col1 = "res.status";
            String whereClause = " WHERE ";

            for (String status: statusList){
                whereClause = whereClause + col1 + " = '" + status + "' OR ";
            }
            whereClause = whereClause.substring(0, whereClause.length() - 4); //delete last " OR "
            System.out.println(whereClause);

            List<Object[]> reservationsDetail = CummonDbFcts.performJoinAndSelect(ReservationDao.TABLE_NAME, "res", RoomDao.TABLE_NAME,"r","roomId","roomId", colToSelect, whereClause);
            for (Object[] row : reservationsDetail) {
                ReservationTableView resRow = new ReservationTableView(row[0],row[1],row[2],row[3],row[4],row[5],row[6],row[7]);
                reservationList.add(resRow);
            }
        }

        id__Col.setCellValueFactory(new PropertyValueFactory<>("i"));
        ReservationDateCol.setCellValueFactory(new PropertyValueFactory<>("reservationDate"));
        CheckInDateCol.setCellValueFactory(new PropertyValueFactory<>("check_inDate"));
        CheckOutDate.setCellValueFactory(new PropertyValueFactory<>("check_outDate"));
        DurationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));
        roomNbrCol.setCellValueFactory(new PropertyValueFactory<>("roomNbr"));
        RoomTypeCol.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        PriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        StatusCol.setCellValueFactory(new PropertyValueFactory<>("resStatus"));

        reservationTable.getItems().addAll(reservationList);
        if(reservationList.isEmpty()){
            noRowsMsg.setVisible(true);
        }
    }
    public void filteReservations(){
        List<String> statusList = new ArrayList<>();

        if(Upcoming.isSelected()) statusList.add("Upcoming");

        if(InProgress.isSelected()) statusList.add("In Progress");

        if(CompletedStay.isSelected()) statusList.add("Completed Stay");

        if(Cancelled_.isSelected()) statusList.add("Cancelled");

        loadDataOnReservationTable(statusList);
    }
//--------------------------------------Cleaning -----------------------------------------
    public void loadDataOnCleaningTable(List<String> statusList, String type, String capacity){
    noRowsMsg.setVisible(false);
    List<RoomsTableView> roomsList = new ArrayList<>();
    cleaningTable.getItems().clear();
    RoomsTableView.setNBR(1);

    List<String> colToSelect =  new ArrayList<String>(List.of("r.roomId","r.numRoom","r.type","r.capacity","r.status","rT.price_day"));
    if(statusList.isEmpty() && type.isEmpty() && capacity.isEmpty()){
        List<Object[]> roomsdetails = CummonDbFcts.performJoinAndSelect(RoomDao.TABLE_NAME, "r", RoomTypeDao.TABLE_NAME,"rT","type","type", colToSelect, " WHERE r.status IN ('Needs Cleaning', 'Under Cleaning')");
        for (Object[] row : roomsdetails) {
            RoomsTableView roomRow = new RoomsTableView(row[0],row[1],row[2],row[3],row[4],row[5],0);
            //System.out.println(roomRow);
            roomsList.add(roomRow);
        }
    }else{
        //join and select rooms with status checked and price < priceSelected and capacite< capacity
        String col1 = "r.status", col2 = "r.type", col3 = "r.capacity";
        String whereClause = " WHERE ";
        if(!statusList.isEmpty()){
            whereClause += "(";
            for (String status: statusList){
                whereClause = whereClause + col1 + " = '" + status + "' OR ";
            }
            whereClause = whereClause.substring(0, whereClause.length() - 4); //delete last " OR "
            whereClause += ") AND ";
        }else{
            whereClause += "r.status IN ('Needs Cleaning', 'Under Cleaning') AND ";
        }
        if(!type.isEmpty()){
            whereClause += "("+ col2 + " Like '%" + type + "%' ) AND ";
        }
        if(!capacity.isEmpty()){
            whereClause += "("+ col3 + " <= " + capacity +") AND ";
        }
        whereClause = whereClause.substring(0, whereClause.length() - 5);//delete last " AND "
        System.out.println(whereClause);

        List<Object[]> roomsdetails = CummonDbFcts.performJoinAndSelect(RoomDao.TABLE_NAME, "r", RoomTypeDao.TABLE_NAME,"rT","type","type", colToSelect, whereClause);
        for (Object[] row : roomsdetails) {
            RoomsTableView roomRow = new RoomsTableView(row[0],row[1],row[2],row[3],row[4],row[5],0);
            //System.out.println(roomRow);
            roomsList.add(roomRow);
        }
    }


    idCol.setCellValueFactory(new PropertyValueFactory<>("i"));
    roomNumberCol.setCellValueFactory(new PropertyValueFactory<>("numRoom"));
    typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
    capacityCol.setCellValueFactory(new PropertyValueFactory<>("capacity"));
    statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
    cleaningTable.getItems().addAll(roomsList);
    if(roomsList.isEmpty()){
        noRowsMsg.setVisible(true);
    }
}
    public void filterCleaningRooms(ActionEvent event){
        List<String> statusList = new ArrayList<>();

        if(NeedsCleaning.isSelected()) statusList.add("Needs Cleaning");

        if(UnderCleaning.isSelected()) statusList.add("Under Cleaning");

        String type = typeField.getText();
        String capacity = capacityField.getText();

        loadDataOnCleaningTable(statusList, type, capacity);
    }
    public void assignCleanignRoomWindow(ActionEvent event) throws IOException {
        rowSelectedError.setVisible(false);
        cleaningAssigningError.setVisible(false);
        if(cleaningTable.getSelectionModel().getSelectedItem() == null){
            rowSelectedError.setVisible(true);
            return;
        }
        if(cleaningTable.getSelectionModel().getSelectedItem().getStatus().equals("Under Cleaning")){
            cleaningAssigningError.setVisible(true);
            return;
        }

        VarsManager.selectedRoomId = (int) cleaningTable.getSelectionModel().getSelectedItem().getRoomId();
        VarsManager.actionStarted ="assignCleaningTask";

        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/manager/AssignRooms-view.fxml"));
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

        if(VarsManager.actionCompleted.equals("assignCleaningTask")){
            addedMsg.setVisible(true);
            hideMsg(addedMsg,4);
            VarsManager.actionCompleted = "";
        }
        loadDataOnCleaningTable(new ArrayList<>(), "", "");
    }
    //--------------------------------------Maintenance -----------------------------------------
    public void loadDataOnMaintenanceTable(List<String> statusList, String type, String capacity){
    noRowsMsg.setVisible(false);
    List<RoomsTableView> roomsList = new ArrayList<>();
    maintenanceTable.getItems().clear();
    RoomsTableView.setNBR(1);

    List<String> colToSelect =  new ArrayList<String>(List.of("r.roomId","r.numRoom","r.type","r.capacity","r.status","rT.price_day"));
    if(statusList.isEmpty() && type.isEmpty() && capacity.isEmpty()){
        List<Object[]> roomsdetails = CummonDbFcts.performJoinAndSelect(RoomDao.TABLE_NAME, "r", RoomTypeDao.TABLE_NAME,"rT","type","type", colToSelect, " WHERE r.status IN ('Needs Maintenance', 'Under Maintenance')");
        for (Object[] row : roomsdetails) {
            RoomsTableView roomRow = new RoomsTableView(row[0],row[1],row[2],row[3],row[4],row[5],0);
            //System.out.println(roomRow);
            roomsList.add(roomRow);
        }
    }else{
        //join and select rooms with status checked and price < priceSelected and capacite< capacity
        String col1 = "r.status", col2 = "r.type", col3 = "r.capacity";
        String whereClause = " WHERE ";
        if(!statusList.isEmpty()){
            whereClause += "(";
            for (String status: statusList){
                whereClause = whereClause + col1 + " = '" + status + "' OR ";
            }
            whereClause = whereClause.substring(0, whereClause.length() - 4); //delete last " OR "
            whereClause += ") AND ";
        }else{
            whereClause += "r.status IN ('Needs Maintenance', 'Under Maintenance') AND ";
        }
        if(!type.isEmpty()){
            whereClause += "("+ col2 + " Like '%" + type + "%' ) AND ";
        }
        if(!capacity.isEmpty()){
            whereClause += "("+ col3 + " <= " + capacity +") AND ";
        }
        whereClause = whereClause.substring(0, whereClause.length() - 5);//delete last " AND "
        System.out.println(whereClause);

        List<Object[]> roomsdetails = CummonDbFcts.performJoinAndSelect(RoomDao.TABLE_NAME, "r", RoomTypeDao.TABLE_NAME,"rT","type","type", colToSelect, whereClause);
        for (Object[] row : roomsdetails) {
            RoomsTableView roomRow = new RoomsTableView(row[0],row[1],row[2],row[3],row[4],row[5],0);
            //System.out.println(roomRow);
            roomsList.add(roomRow);
        }
    }


    idCol.setCellValueFactory(new PropertyValueFactory<>("i"));
    roomNumberCol.setCellValueFactory(new PropertyValueFactory<>("numRoom"));
    typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
    capacityCol.setCellValueFactory(new PropertyValueFactory<>("capacity"));
    statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
    maintenanceTable.getItems().addAll(roomsList);
    if(roomsList.isEmpty()){
        noRowsMsg.setVisible(true);
    }
}
    public void filterMaintenanceRooms(ActionEvent event){
        List<String> statusList = new ArrayList<>();

        if(NeedsMaintenance.isSelected()) statusList.add("Needs Maintenance");

        if(UnderMaintenance.isSelected()) statusList.add("Under Maintenance");

        String type = typeField.getText();
        String capacity = capacityField.getText();

        loadDataOnMaintenanceTable(statusList, type, capacity);
    }
    public void assignMaintenanceRoomWindow(ActionEvent event) throws IOException {
        rowSelectedError.setVisible(false);
        maintenanceAssigningError.setVisible(false);
        if(maintenanceTable.getSelectionModel().getSelectedItem() == null){
            rowSelectedError.setVisible(true);
            return;
        }
        if(maintenanceTable.getSelectionModel().getSelectedItem().getStatus().equals("Under Maintenance")){
            maintenanceAssigningError.setVisible(true);
            return;
        }

        VarsManager.selectedRoomId = (int) maintenanceTable.getSelectionModel().getSelectedItem().getRoomId();
        VarsManager.actionStarted ="assignMaintenanceTask";

        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/manager/AssignRooms-view.fxml"));
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

        if(VarsManager.actionCompleted.equals("assignMaintenanceTask")){
            addedMsg.setVisible(true);
            hideMsg(addedMsg,4);
            VarsManager.actionCompleted = "";
        }
        loadDataOnMaintenanceTable(new ArrayList<>(), "", "");
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
//-------------------------------------- Complaints -----------------------------------------
    public void loadDataOnComplaintsTable(List<String> responseStatusList, List<String> declarantStatusList, String  complaintDateOrder, String complaintDate, String complaintObject){
    noRowsMsg.setVisible(false);
    List<ComplaintTableView> complaintsList = new ArrayList<>();
    complaintTable.getItems().clear();
    ComplaintTableView.setNBR(1);

    List<String> colToSelect =  new ArrayList<String>(List.of("declarationId", "declarantId", "declarantStatus", "declarationObject", "declaration", "declarationDate", "response", "responseDate"));
    String query = "SELECT declarationId, declarantId, declarantStatus, declarationObject, declaration, declarationDate, response, responseDate " +
            "FROM declaration";
    if(responseStatusList.isEmpty() && declarantStatusList.isEmpty() && complaintDateOrder.isEmpty() && complaintDate.isEmpty() && complaintObject.isEmpty()){
        List<Object[]> complaintsDetails = CummonDbFcts.querySelect(query, colToSelect);
        for (Object[] row : complaintsDetails) {
            ComplaintTableView complaintRow = new ComplaintTableView(row[0],row[1],row[2],row[3],row[4],row[5],row[6],row[7]);
            System.out.println(complaintRow);
            complaintsList.add(complaintRow);
        }
    }else{
        query += " WHERE ";
        int i =0;
        String col1 = "response", col2 = "declarantStatus", col3 = "declarationDate", col4 = "declarationObject";
        if(!responseStatusList.isEmpty()){
            i++;
            query += "(";
            for (String responseStatus: responseStatusList){
                if(responseStatus.equals("Replied")){
                    query =query  + col1 + " IS NOT NULL OR ";
                } else if (responseStatus.equals("Unreplied")) {
                    query =query  + col1 + " IS NULL OR ";
                }
            }
            query = query .substring(0,query .length() - 4); //delete last " OR "
            query += ") AND ";
        }
        if(!declarantStatusList.isEmpty()){
            i++;
            query += "(";
            for (String position: declarantStatusList){
                query =query  + col2 + " = '" + position + "' OR ";
            }
            query = query .substring(0,query .length() - 4); //delete last " OR "
            query += ") AND ";
        }
        if(!complaintDate.isEmpty()){
            i++;
            query += "("+ col3 + " LIKE '%" + complaintDate + "%') AND ";
        }

        if(!complaintObject.equals("")){
            i++;
            query += "("+ col4 + " LIKE '%" + complaintObject + "%') AND ";
        }

        if(!complaintDateOrder.equals("")){
            if(i==0){
                query = query.substring(0, query.length() - 7);//delete last " WHERE "
            }else {
                query = query.substring(0, query.length() - 5);//delete last " AND "
            }
            query = query + " ORDER BY declarationDate " + complaintDateOrder + " AND ";
        }

        query = query.substring(0, query.length() - 5);//delete last "AND "
        System.out.println(query);


        List<Object[]> complaintsDetails = CummonDbFcts.querySelect(query, colToSelect);
        for (Object[] row : complaintsDetails) {
            ComplaintTableView complaintRow = new ComplaintTableView(row[0],row[1],row[2],row[3],row[4],row[5],row[6],row[7]);
            System.out.println(complaintRow);
            complaintsList.add(complaintRow);
        }
    }

    id_Col.setCellValueFactory(new PropertyValueFactory<>("i"));
    complaintDateCol.setCellValueFactory(new PropertyValueFactory<>("declarationDate"));
    complaintObjectCol.setCellValueFactory(new PropertyValueFactory<>("declarationObject"));
    declarantEmailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
    declarantStatusCol.setCellValueFactory(new PropertyValueFactory<>("declarantStatus"));
    responseStatusCol.setCellValueFactory(new PropertyValueFactory<>("responseStatus"));
        System.out.println(complaintsList);
    complaintTable.getItems().addAll(complaintsList);

    if(complaintsList.isEmpty()){
        noRowsMsg.setVisible(true);
    }

}
    public void filterComplaints(ActionEvent event){
        List<String> responseStatusList = new ArrayList<>();
        List<String> declarantStatusList = new ArrayList<>();
        String complaintDateOrder = "";
        String complaintObject = complaintObjectField.getText();

        if(Replied.isSelected()) responseStatusList.add("Replied");
        if(Unreplied.isSelected()) responseStatusList.add("Unreplied");

        if(Customer.isSelected()) declarantStatusList.add("Customer");
        if(Cleaner.isSelected()) declarantStatusList.add("Cleaner");
        if(MaintenanceStaff.isSelected()) declarantStatusList.add("Maintenance Staff");

        if(ComplaintDateDesc.isSelected()) {
            complaintDateOrder = "DESC";
            ComplaintDateAsc.setSelected(false);
        }
        if(ComplaintDateAsc.isSelected()) {
            complaintDateOrder = "ASC";
            ComplaintDateDesc.setSelected(false);
        }

        LocalDate taskDate__ = complaintDatePicker.getValue();
        String complaintDate="";
        if (taskDate__ != null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Adjust the pattern as needed
            complaintDate = taskDate__.format(formatter);
        }

        loadDataOnComplaintsTable(responseStatusList,declarantStatusList, complaintDateOrder, complaintDate, complaintObject);
    }
    //------     ------     -------       ------      ------       ------          -------
    public void displayReplyComplaintPane(ActionEvent event){
        rowSelectedError.setVisible(false);
        replyError.setVisible(false);

        if(complaintTable.getSelectionModel().getSelectedItem() == null){
            rowSelectedError.setVisible(true);
            return;
        }

        Map map = new HashMap<>();
        map.put("declarationId", complaintTable.getSelectionModel().getSelectedItem().getDeclarationId());
        Complaint complaint = (Complaint)DeclarationDao.select(map,"*").get(0);

        System.out.println("================"+complaintTable.getSelectionModel().getSelectedItem().getResponseStatus());
        System.out.println("======bool======"+complaintTable.getSelectionModel().getSelectedItem().getResponseStatus().equals("Replied"));
        if(complaintTable.getSelectionModel().getSelectedItem().getResponseStatus().equals("Replied")){
            replyError.setVisible(true);
            System.out.println("replyerro true");
            return;
        }

        replyComplaintPane.setVisible(true);
        objectField.setText(complaint.getDeclarationObject());
        complaintField.setText(complaint.getDeclaration());
        responseField.setText("");
    }
    public void replyComplaint(ActionEvent event){
        String complaintResponse = responseField.getText();
        if (!complaintResponse.isEmpty()){
            int declarantionId = (int)complaintTable.getSelectionModel().getSelectedItem().getDeclarationId();
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String responseDate = currentDate.format(formatter);

            String[] updatedColumns = {"response", "responseDate"};
            Object[] newColumnsValue = {complaintResponse,responseDate};
            String testColumn = "declarationId";
            Object testColumnValue = declarantionId;

            DeclarationDao.updateColumns(updatedColumns, newColumnsValue, testColumn, testColumnValue);

            replyComplaintPane.setVisible(false);
            loadDataOnComplaintsTable(new ArrayList<>(),new ArrayList<>(),"","","");
            addedMsg.setVisible(true);
            hideMsg(addedMsg,4);
        }
    }

    public void hideReplyComplaintPane(ActionEvent event){
        replyComplaintPane.setVisible(false);
    }
    public void displayConfirmDeletePane(ActionEvent event){
        rowSelectedError.setVisible(false);
        addedMsg.setVisible(false);
        deletedMsg.setVisible(false);
        replyError.setVisible(false);


        if(complaintTable.getSelectionModel().getSelectedItem() == null){
            rowSelectedError.setVisible(true);
            return;
        }

        actionPane.setVisible(false);
        confirmDeletePane.setVisible(true);
    }
    public void hideConfirmDeletePane(ActionEvent event){
        confirmDeletePane.setVisible(false);
        actionPane.setVisible(true);
    }
    public void confirmDelete(ActionEvent event){
        replyError.setVisible(false);
        DeclarationDao.delete("declarationId",complaintTable.getSelectionModel().getSelectedItem().getDeclarationId());
        confirmDeletePane.setVisible(false);

        loadDataOnComplaintsTable(new ArrayList<>(),new ArrayList<>(),"","","");
        actionPane.setVisible(true);
        deletedMsg.setVisible(true);
        hideMsg(deletedMsg,4);
    }

    public void displayDetailPane(ActionEvent event){
        rowSelectedError.setVisible(false);
        replyError.setVisible(false);
        addedMsg.setVisible(false);
        deletedMsg.setVisible(false);

        if(complaintTable.getSelectionModel().getSelectedItem() == null){
            rowSelectedError.setVisible(true);
            return;
        }

        Map map = new HashMap<>();
        map.put("declarationId", complaintTable.getSelectionModel().getSelectedItem().getDeclarationId());
        Complaint complaint = (Complaint)DeclarationDao.select(map,"*").get(0);

        detailComplaintObject.setText(String.valueOf(complaint.getDeclarationObject()));
        detailComplaint.setText(String.valueOf(complaint.getDeclaration()));
        String respDate = String.valueOf(complaint.getResponseDate()).equals("null")?"_______________":String.valueOf(complaint.getResponseDate());
        String resp = String.valueOf(complaint.getResponse()).equals("null")?"_______________":String.valueOf(complaint.getResponse());
        detailResponseDate.setText(respDate);
        detailResponse.setText(resp);
        detailsPane.setVisible(true);
    }
    public void hideDetailsComplaintPane(ActionEvent event){
        detailsPane.setVisible(false);
    }
    //-------------------------------------------------------------------------------
    public void hideMsg(Label msg,double time){
        Duration duration = Duration.seconds(time);
        Timeline timeline = new Timeline(new KeyFrame(duration, e -> msg.setVisible(false)));
        timeline.setCycleCount(1);
        timeline.play();
    }
    public void logout(ActionEvent event) throws IOException {
        System.out.println(EmployeeManager.getInstance().getEmployee());
        EmployeeManager.getInstance().setManager(new Employee("","","","","","",0,"",""));
        switchToWelcomePage(event);
    }
    public void switchToWelcomePage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/welcome-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println(EmployeeManager.getInstance().getEmployee());
    }
}
