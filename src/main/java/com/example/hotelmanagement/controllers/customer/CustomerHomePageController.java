package com.example.hotelmanagement.controllers.customer;

import com.example.hotelmanagement.HelloApplication;
import com.example.hotelmanagement.beans.Complaint;
import com.example.hotelmanagement.beans.Customer;
import com.example.hotelmanagement.beans.Feedback;
import com.example.hotelmanagement.beans.Reservation;
import com.example.hotelmanagement.dao.*;
import com.example.hotelmanagement.daoFactory.CummonDbFcts;
import com.example.hotelmanagement.localStorage.CustomerManager;
import com.example.hotelmanagement.config.PathConfig;
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
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CustomerHomePageController implements Initializable{
    private Stage stage;
    public static Stage childStage;
    public static boolean updated = false;
    private Scene scene;
    private Parent root;
    private Customer currentCustomer;
    private String currentPage;

    @FXML private Label reservationsNbr, feedbacksNbr, complaintsNbr, invoicesNbr, unpaidInvoicesNbr;

    @FXML private Label succesMsg;
    @FXML private AnchorPane rootPane;
    @FXML private Label fullnameLabel;
    @FXML TextField fullNameField, cinField, emailAddressField, passwordField, phoneField, addressField;//fields of user infos

    @FXML private TextField priceField, capacityField;
    @FXML private Label noRowsMsg, rowSelectedError;
    @FXML private TableView<RoomsTableView> roomsTable;
    @FXML private TableColumn<RoomsTableView, Object> idCol, roomNumberCol, typeCol, capacityCol, statusCol, price_dayCol;
    @FXML private CheckBox Available, Occupied, UnderCleaning, NeedsCleaning, UnderMaintenance, NeedsMaintenance, OutofService, CheckedOut;

    @FXML private TableView<ReservationTableView> reservationTable;
    @FXML private TableColumn<ReservationTableView, Object> id_Col, ReservationDateCol, CheckInDateCol, CheckOutDate, DurationCol, roomNbrCol, RoomTypeCol, PriceCol, StatusCol;
    @FXML public CheckBox Upcoming, InProgress, CompletedStay, Cancelled;
    @FXML public Label addedMsg, updatedMsg, deletedMsg, editNotAllowedError;

    @FXML private TableView<InvoicesTableView> invoicesTable;
    @FXML private TableColumn<InvoicesTableView, Object> id__Col, invoiceDateCol, resDurationCol, amountCol, status_Col;
    @FXML private CheckBox Paid, Unpaid, Cancelled_;
    @FXML private DatePicker invoiceDate;

    @FXML private TableView<FeedbackTableView> feedbackTable;
    @FXML private TableColumn<FeedbackTableView, Object> id___Col, fullNameCol, feedbackDateCol, rateCol, commentCol;
    @FXML private DatePicker feedbackDate;
    @FXML private TextField fullnameField;
    @FXML private CheckBox MyFeedbacks, RateAsc, RateDesc;
    @FXML private Label updatemsgError, deletemsgError;

    @FXML private TableView<ComplaintTableView> complaintTable;
    @FXML private TableColumn<ComplaintTableView, Object> complaintObjectCol, responseStatusCol, complaintDateCol;
    @FXML private CheckBox Replied, Unreplied, ComplaintDateAsc, ComplaintDateDesc;
    @FXML private DatePicker complaintDatePicker;
    @FXML private TextField complaintObjectField, objectField;
    @FXML private TextArea complaintField;
    @FXML private AnchorPane addComplaintPane, confirmDeletePane, detailsPane, actionPane;
    @FXML private Button confirmAddBtn, saveBtn;
    @FXML private Label detailResponseDate, detailResponse, detailComplaint, detailComplaintObject, detailDate;
    @FXML private Label addDeleteLabel, editError, deleteError;

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
            Map map1 = new HashMap<>();
            map1.put("customerId", CustomerManager.getInstance().getCustomer().getCustomerId());
            reservationsNbr.setText(String.valueOf(ReservationDao.select(map1,"*").size()));
            feedbacksNbr.setText(String.valueOf(FeedbackDao.select(map1,"*").size()));
            Map map2 = new HashMap<>();
            map2.put("declarantId", CustomerManager.getInstance().getCustomer().getCustomerId());
            map2.put("declarantStatus", "Customer");
            complaintsNbr.setText(String.valueOf(DeclarationDao.select(map2,"*").size()));
            invoicesNbr.setText(String.valueOf(InvoiceDao.select(map1,"*").size()));
            map1.put("status", "Unpaid");
            unpaidInvoicesNbr.setText(String.valueOf(InvoiceDao.select(map1,"*").size()));
        } else if (currentPage.equals("CustomerInfos")){
            succesMsg.setVisible(false);
            initializeFields(currentCustomer);
        } else if (currentPage.equals("RoomsDetail")) {
            loadDataOnRoomTable(new ArrayList<>(), "", "");
            noRowsMsg.setVisible(false);
        } else if (currentPage.equals("Services")) {

        }else if (currentPage.equals("BookingService")) {
            editNotAllowedError.setVisible(false);
            addedMsg.setVisible(false);
            updatedMsg.setVisible(false);
            deletedMsg.setVisible(false);
            loadDataOnReservationTable(new ArrayList<>());
            noRowsMsg.setVisible(false);
            rowSelectedError.setVisible(false);
        } else if (currentPage.equals("Invoices")) {
            noRowsMsg.setVisible(false);
            loadDataOnInvoicesTable(new ArrayList<>(), null);
        } else if (currentPage.equals("Feedback")) {
            noRowsMsg.setVisible(false);
            addedMsg.setVisible(false);
            updatedMsg.setVisible(false);
            deletedMsg.setVisible(false);
            loadDataOnFeedbackTable("",null, false,"");
            rowSelectedError.setVisible(false);
            updatemsgError.setVisible(false);
            deletemsgError.setVisible(false);
        } else if (currentPage.equals("complaint")) {
            noRowsMsg.setVisible(false);
            addedMsg.setVisible(false);
            updatedMsg.setVisible(false);
            deletedMsg.setVisible(false);

            rowSelectedError.setVisible(false);
            editError.setVisible(false);
            deleteError.setVisible(false);

            addComplaintPane.setVisible(false);
            confirmDeletePane.setVisible(false);
            detailsPane.setVisible(false);
            loadDataOnComplaintsTable(new ArrayList<>(),"","", "");
        }else if (currentPage.equals("About")) {

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
    public void switchToBookingService(ActionEvent event) throws IOException {
        SwitchedPageManager.getInstance().setSwitchedPage("BookingService");
        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/customer/BookingRoom-view.fxml"));
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
    public void switchToComplaint(ActionEvent event) throws IOException {
        SwitchedPageManager.getInstance().setSwitchedPage("complaint");
        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/customer/Complaint-view.fxml"));
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

        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/customer/editInfo-view.fxml"));
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

        if(updated){//if we update customer infos the values of updated is set to true
            initializeFields(CustomerManager.getInstance().getCustomer());
            succesMsg.setVisible(true);
            hideMsg(succesMsg, 4);
        }

    }
//----------------------------------- room details fcts--------------------------------------------
    public void loadDataOnRoomTable(List<String> statusList, String price, String capacity){
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

        if(UnderCleaning.isSelected()) statusList.add("Under Cleaning");

        if(NeedsCleaning.isSelected()) statusList.add("Needs Cleaning");

        if(NeedsMaintenance.isSelected()) statusList.add("Needs Maintenance");

        if(UnderMaintenance.isSelected()) statusList.add("Under Maintenance");

        if(OutofService.isSelected()) statusList.add("Out of Service");

        if(CheckedOut.isSelected()) statusList.add("Checked Out");

        String price = priceField.getText();
        String capacity = capacityField.getText();

        loadDataOnRoomTable(statusList, price, capacity);

        System.out.println(statusList.toString());
        System.out.println(price);
        System.out.println(capacity);

    }
//----------------------------------- services(Reservations) fcts--------------------------------------------
    public void loadDataOnReservationTable(List<String> statusList){
        noRowsMsg.setVisible(false);
        editNotAllowedError.setVisible(false);

        List<ReservationTableView> reservationList = new ArrayList<>();
        reservationTable.getItems().clear();
        ReservationTableView.setNBR(1);
        int customerId = CustomerManager.getInstance().getCustomer().getCustomerId();

        List<String> colToSelect =  new ArrayList<String>(List.of("res.reservationId", "res.reservationDate", "res.check_inDate", "res.check_outDate", "r.numRoom", "r.type", "res.status", "r.capacity"));
        if(statusList.isEmpty()){
            String whereClause = " WHERE res.customerId = " + customerId;
            List<Object[]> reservationsDetail = CummonDbFcts.performJoinAndSelect(ReservationDao.TABLE_NAME, "res", RoomDao.TABLE_NAME,"r","roomId","roomId", colToSelect, whereClause);
            for (Object[] row : reservationsDetail) {
                ReservationTableView resRow = new ReservationTableView(row[0],row[1],row[2],row[3],row[4],row[5],row[6],row[7]);
                //System.out.println(roomRow);
                reservationList.add(resRow);
            }
        }else{
            String col1 = "res.status";
            String whereClause = " WHERE res.customerId = " + customerId + " AND ";

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

        id_Col.setCellValueFactory(new PropertyValueFactory<>("i"));
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

        if(Cancelled.isSelected()) statusList.add("Cancelled");

        loadDataOnReservationTable(statusList);
    }
    public void newReservationWindow(ActionEvent event) throws IOException {
        rowSelectedError.setVisible(false);
        editNotAllowedError.setVisible(false);

        VarsManager.actionStarted = "add";

        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/customer/NewReservation-view.fxml"));
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

        if(VarsManager.actionCompleted.equals("confirmReservation")){
            deletedMsg.setVisible(false);
            updatedMsg.setVisible(false);
            addedMsg.setVisible(true);
            hideMsg(addedMsg,4);
            VarsManager.actionCompleted = "";
        }
        loadDataOnReservationTable(new ArrayList<>());
    }
    public void deleteReservationWindow(ActionEvent event) throws IOException {
        rowSelectedError.setVisible(false);
        if(reservationTable.getSelectionModel().getSelectedItem() == null){
            rowSelectedError.setVisible(true);
            return;
        }

        VarsManager.actionStarted = "delete";
        VarsManager.selectedResId = (int) reservationTable.getSelectionModel().getSelectedItem().getReservationId();

        Map map = new HashMap<>();
        map.put("reservationId", VarsManager.selectedResId);
        Reservation reservation = (Reservation) (ReservationDao.select(map,"*").get(0));
        if(LocalDate.now().plusDays(1).isBefore(LocalDate.parse(reservation.getCheck_inDate()))){
            editNotAllowedError.setVisible(true);
            return;
        }
        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/customer/DeleteReservation-view.fxml"));
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
            addedMsg.setVisible(false);
            updatedMsg.setVisible(false);
            deletedMsg.setVisible(true);
            hideMsg(deletedMsg,4);
        }
        loadDataOnReservationTable(new ArrayList<>());
    }
//----------------------------------- invoices fcts--------------------------------------------
    public void loadDataOnInvoicesTable(List<String> statusList, LocalDate invDate){
        noRowsMsg.setVisible(false);

        List<InvoicesTableView> invoiceList = new ArrayList<>();
        invoicesTable.getItems().clear();
        InvoicesTableView.setNBR(1);

        List<String> colToSelect =  new ArrayList<String>(List.of("inv.invoiceId", "inv.customerId", "cust.fullName", "cust.cin", "inv.reservationId", "inv.status", "inv.amount", "inv.invoiceDate"));
        if(statusList.isEmpty() && invDate == null){
            List<Object[]> invoicesdetails = CummonDbFcts.performJoinAndSelect(InvoiceDao.TABLE_NAME, "inv", CustomerDao.TABLE_NAME,"cust","customerId","customerId", colToSelect, " WHERE cust.customerId = " + CustomerManager.getInstance().getCustomer().getCustomerId());
            for (Object[] row : invoicesdetails) {
                InvoicesTableView invoiceRow = new InvoicesTableView(row[0],row[1],row[2],row[3],row[4],row[5],row[6],row[7]);
                //System.out.println(roomRow);
                invoiceList.add(invoiceRow);
            }
        }else{
            //join and select rooms with status checked and price < priceSelected and capacite< capacity
            String col1 = "inv.status", col2 = "inv.invoiceDate";
            String whereClause = " WHERE cust.customerId = " + CustomerManager.getInstance().getCustomer().getCustomerId() + " AND ";
            if(!statusList.isEmpty()){
                whereClause += "(";
                for (String status: statusList){
                    whereClause = whereClause + col1 + " = '" + status + "' OR ";
                }
                whereClause = whereClause.substring(0, whereClause.length() - 4); //delete last " OR "
                whereClause += ") AND ";
            }
            if(invDate != null){
                whereClause += "("+ col2 + " LIKE '%" + invDate + "%') AND ";
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

        id__Col.setCellValueFactory(new PropertyValueFactory<>("i"));
        invoiceDateCol.setCellValueFactory(new PropertyValueFactory<>("invoiceDate"));
        resDurationCol.setCellValueFactory(new PropertyValueFactory<>("reservationDuration"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        status_Col.setCellValueFactory(new PropertyValueFactory<>("status"));
        invoicesTable.getItems().addAll(invoiceList);

        System.out.println(invoiceList);

        if(invoiceList.isEmpty()){
            noRowsMsg.setVisible(true);
        }
    }
    public void filterInvoices(ActionEvent event){
        List<String> statusList = new ArrayList<>();

        if(Paid.isSelected()) statusList.add("Paid");

        if(Unpaid.isSelected()) statusList.add("Unpaid");

        if(Cancelled_.isSelected()) statusList.add("Cancelled");

        LocalDate invDate = invoiceDate.getValue();

        loadDataOnInvoicesTable(statusList, invDate);
    }
//----------------------------------- Feedback fcts --------------------------------------------
    public void loadDataOnFeedbackTable(String fullname, LocalDate fbDate, boolean myFeedback, String rateOrder){
        noRowsMsg.setVisible(false);

        List<FeedbackTableView> feedbackList = new ArrayList<>();
        feedbackTable.getItems().clear();
        FeedbackTableView.setNBR(1);

        List<String> colToSelect =  new ArrayList<String>(List.of ("f.feedbackId", "f.customerId", "cust.fullName", "f.visibility", "f.priority", "f.customerService_rate", "f.cleanliness_rate", "f.roomComfort_rate", "f.location_rate", "f.safety_rate", "f.environnement_rate", "f.view_rate", "f.serviceVSprice_rate", "f.review_rate", "f.feedback_date"));
            System.out.println(fullname);
            System.out.println(rateOrder);
            System.out.println(fbDate);
            System.out.println(myFeedback);
        if(fullname.isEmpty() && rateOrder.isEmpty() && fbDate == null && myFeedback == false){
            List<Object[]> feedbackdetails = CummonDbFcts.performJoinAndSelect(FeedbackDao.TABLE_NAME, "f", CustomerDao.TABLE_NAME,"cust","customerId","customerId", colToSelect, "");
            for (Object[] row : feedbackdetails) {
                FeedbackTableView feedbackRow = new FeedbackTableView(row[0],row[1],row[2],row[3],row[4],row[5],row[6],row[7],row[8],row[9],row[10],row[11], row[12], row[13], row[14]);
                System.out.println(feedbackRow);
                feedbackList.add(feedbackRow);
            }
        }else{
            String whereClause = " WHERE ";
            int i = 0;
            if(!fullname.isEmpty()){
                whereClause = whereClause + "cust.fullName LIKE '%" + fullname + "%' AND ";
                i++;
            }
            if(fbDate != null){
                whereClause = whereClause + "f.feedback_date LIKE '%" + fbDate + "%' AND ";
                i++;
            }
            if(myFeedback == true){
                whereClause = whereClause + "f.customerId = " + CustomerManager.getInstance().getCustomer().getCustomerId() + " AND ";
                i++;
            }
            whereClause = whereClause.substring(0, whereClause.length() - 5);//delete last " AND "


            if(!rateOrder.equals("")){
                if(i==0){
                    whereClause = whereClause.substring(0, whereClause.length() - 2);
                }
                whereClause = whereClause + " ORDER BY f.totalRate " + rateOrder;
            }

            System.out.println(whereClause);
            List<Object[]> feedbackdetails = CummonDbFcts.performJoinAndSelect(FeedbackDao.TABLE_NAME, "f", CustomerDao.TABLE_NAME,"cust","customerId","customerId", colToSelect, whereClause);
            for (Object[] row : feedbackdetails) {
                FeedbackTableView feedbackRow = new FeedbackTableView(row[0],row[1],row[2],row[3],row[4],row[5],row[6],row[7],row[8],row[9],row[10],row[11], row[12], row[13], row[14]);
                System.out.println(feedbackRow);
                feedbackList.add(feedbackRow);
            }
        }

        id___Col.setCellValueFactory(new PropertyValueFactory<>("i"));
        fullNameCol.setCellValueFactory(new PropertyValueFactory<>("customerFullName"));
        feedbackDateCol.setCellValueFactory(new PropertyValueFactory<>("feedback_date"));
        rateCol.setCellValueFactory(new PropertyValueFactory<>("total_rate"));
        commentCol.setCellValueFactory(new PropertyValueFactory<>("review_comment"));

        feedbackTable.getItems().addAll(feedbackList);
        if(feedbackList.isEmpty()){
            noRowsMsg.setVisible(true);
        }
    }
    public void filterFeedbacks(ActionEvent event){
        boolean myFeedback = false;
        String rateOrder = "";
        LocalDate fbDate = feedbackDate.getValue();
        String fullname = fullnameField.getText();

        if(MyFeedbacks.isSelected()) myFeedback = true ;

        if(RateDesc.isSelected()) {
            rateOrder = "DESC";
            RateAsc.setSelected(false);
        }

        if(RateAsc.isSelected()) {
            rateOrder = "ASC";
            RateDesc.setSelected(false);
        }

        loadDataOnFeedbackTable(fullname, fbDate, myFeedback, rateOrder);
    }
    public void feedbackDetailsWindow(ActionEvent event) throws IOException {
        updatemsgError.setVisible(false);
        deletemsgError.setVisible(false);
        rowSelectedError.setVisible(false);
        VarsManager.actionStarted = "details";

        if(feedbackTable.getSelectionModel().getSelectedItem() == null){
            rowSelectedError.setVisible(true);
            return;
        }
        VarsManager.selectedFeedbackId = (int) feedbackTable.getSelectionModel().getSelectedItem().getFeedbackId();

        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/customer/FeedbackDetail-view.fxml"));
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

        loadDataOnFeedbackTable("",null,false,"");
    }
    public void newFeedbackWindow(ActionEvent event) throws IOException {
        updatemsgError.setVisible(false);
        deletemsgError.setVisible(false);
        rowSelectedError.setVisible(false);
        VarsManager.actionStarted = "add";

        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/customer/NewFeedback-view.fxml"));
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

        if(VarsManager.actionCompleted.equals("add")){
            deletedMsg.setVisible(false);
            updatedMsg.setVisible(false);
            addedMsg.setVisible(true);
            hideMsg(addedMsg,4);
        }
        loadDataOnFeedbackTable("",null,false,"");
    }
    public void editFeedbackWindow(ActionEvent event) throws IOException {
        updatemsgError.setVisible(false);
        deletemsgError.setVisible(false);
        rowSelectedError.setVisible(false);
        if(feedbackTable.getSelectionModel().getSelectedItem() == null){
            rowSelectedError.setVisible(true);
            return;
        }
        VarsManager.actionStarted = "update";
        VarsManager.selectedFeedbackId = (int) feedbackTable.getSelectionModel().getSelectedItem().getFeedbackId();

        Map map = new HashMap<>();
        map.put("feedbackId", VarsManager.selectedFeedbackId);
        int selectedCustomerId = ((Feedback)(FeedbackDao.select(map, "*").get(0))).getCustomerId();
        if(selectedCustomerId != CustomerManager.getInstance().getCustomer().getCustomerId()){
            updatemsgError.setVisible(true);
            return;//You cannot update feedback that is not yours.
        }

        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/customer/EditFeedback-view.fxml"));
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
            deletedMsg.setVisible(false);
            addedMsg.setVisible(false);
            updatedMsg.setVisible(true);
            hideMsg(updatedMsg,4);
        }
        loadDataOnFeedbackTable("",null,false,"");
    }
    public void deleteFeedbackWindow(ActionEvent event) throws IOException {
        updatemsgError.setVisible(false);
        deletemsgError.setVisible(false);
        rowSelectedError.setVisible(false);
        if(feedbackTable.getSelectionModel().getSelectedItem() == null){
            rowSelectedError.setVisible(true);
            return;
        }

        VarsManager.actionStarted = "delete";
        VarsManager.selectedFeedbackId = (int) feedbackTable.getSelectionModel().getSelectedItem().getFeedbackId();

        Map map = new HashMap<>();
        map.put("feedbackId", VarsManager.selectedFeedbackId);
        int selectedCustomerId = ((Feedback)(FeedbackDao.select(map, "*").get(0))).getCustomerId();
        if(selectedCustomerId != CustomerManager.getInstance().getCustomer().getCustomerId()){
            deletemsgError.setVisible(true);
            return;//You cannot delete feedback that is not yours.
        }

        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/customer/DeleteFeedback-view.fxml"));
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
            addedMsg.setVisible(false);
            updatedMsg.setVisible(false);
            deletedMsg.setVisible(true);
            hideMsg(deletedMsg,4);

        }
        loadDataOnFeedbackTable("",null,false,"");
    }
    //---------------------------------------Complaints--------------------------------------------
    public void loadDataOnComplaintsTable(List<String> responseStatusList, String  complaintDateOrder, String complaintDate, String complaintObject){
        noRowsMsg.setVisible(false);
        List<ComplaintTableView> complaintsList = new ArrayList<>();
        complaintTable.getItems().clear();
        ComplaintTableView.setNBR(1);

        List<String> colToSelect =  new ArrayList<String>(List.of("declarationId", "declarantId", "declarantStatus", "declarationObject", "declaration", "declarationDate", "response", "responseDate"));
        String declarantStatus = "Customer";
        String query = "SELECT declarationId, declarantId, declarantStatus, declarationObject, declaration, declarationDate, response, responseDate " +
                "FROM declaration " +
                "WHERE declarantId = " + CustomerManager.getInstance().getCustomer().getCustomerId() + " AND declarantStatus = '" + declarantStatus + "'";
        if(responseStatusList.isEmpty() && complaintDateOrder.isEmpty() && complaintDate.isEmpty() && complaintObject.isEmpty()){
            List<Object[]> complaintsDetails = CummonDbFcts.querySelect(query, colToSelect);
            for (Object[] row : complaintsDetails) {
                ComplaintTableView complaintRow = new ComplaintTableView(row[0],row[1],row[2],row[3],row[4],row[5],row[6],row[7]);
                System.out.println(complaintRow);
                complaintsList.add(complaintRow);
            }
        }else{
            query += " AND ";

            String col1 = "response", col2 = "declarationDate", col3 = "declarationObject";
            if(!responseStatusList.isEmpty()){
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
            if(!complaintDate.isEmpty()){
                query += "("+ col2 + " LIKE '%" + complaintDate + "%') AND ";
            }

            if(!complaintObject.equals("")){
                query += "("+ col3 + " LIKE '%" + complaintObject + "%') AND ";
            }

            if(!complaintDateOrder.equals("")){
                query = query.substring(0, query.length() - 5);//delete last " AND "
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
        responseStatusCol.setCellValueFactory(new PropertyValueFactory<>("responseStatus"));
        complaintTable.getItems().addAll(complaintsList);

        if(complaintsList.isEmpty()){
            noRowsMsg.setVisible(true);
        }

    }
    public void filterComplaints(ActionEvent event){
        List<String> responseStatusList = new ArrayList<>();
        String complaintDateOrder = "";
        String complaintObject = complaintObjectField.getText();

        if(Replied.isSelected()) responseStatusList.add("Replied");
        if(Unreplied.isSelected()) responseStatusList.add("Unreplied");

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

        loadDataOnComplaintsTable(responseStatusList, complaintDateOrder, complaintDate, complaintObject);
    }

    public void displayAddComplaintPane(ActionEvent event){
        rowSelectedError.setVisible(false);
        editError.setVisible(false);
        deleteError.setVisible(false);
        saveBtn.setVisible(false);
        confirmAddBtn.setVisible(true);
        objectField.setText("");
        complaintField.setText("");
        addDeleteLabel.setText("Add complaint");

        addComplaintPane.setVisible(true);
    }
    public void addComplaint(ActionEvent event){
        String object = objectField.getText();
        String complaintBody = complaintField.getText();
        if (!object.isEmpty()){
            int declarantId = CustomerManager.getInstance().getCustomer().getCustomerId();
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String complaintDate = currentDate.format(formatter);

            Complaint complaint = new Complaint(declarantId,"Customer",object,complaintBody,complaintDate,null,null);
            DeclarationDao.insert(complaint);

            addComplaintPane.setVisible(false);
            loadDataOnComplaintsTable(new ArrayList<>(),"","","");
            addedMsg.setVisible(true);
            hideMsg(addedMsg,4);
        }
    }
    public void displayEditComplaintPane(ActionEvent event){
        rowSelectedError.setVisible(false);
        editError.setVisible(false);
        deleteError.setVisible(false);
        saveBtn.setVisible(true);
        confirmAddBtn.setVisible(false);
        addDeleteLabel.setText("Edit complaint");


        if(complaintTable.getSelectionModel().getSelectedItem() == null){
            rowSelectedError.setVisible(true);
            return;
        }
        if(!String.valueOf(complaintTable.getSelectionModel().getSelectedItem().getResponse()).toLowerCase().equals("null")){
            editError.setVisible(true);
            return;
        }
        objectField.setText(String.valueOf(complaintTable.getSelectionModel().getSelectedItem().getDeclarationObject()));
        complaintField.setText(String.valueOf(complaintTable.getSelectionModel().getSelectedItem().getDeclaration()));
        addComplaintPane.setVisible(true);
    }
    public void editComplaint(ActionEvent event){
        String object = objectField.getText();
        String complaintBody = complaintField.getText();
        System.out.println("'''''''''''"+object);
        System.out.println("'''''''''''"+complaintBody);

        if (!object.isEmpty()){
            String[] updatedColumns = {"declarationObject", "declaration"};
            Object[] newColumnsValue = {object, complaintBody};
            String testColumn = "declarationId";
            Object testColumnValue = complaintTable.getSelectionModel().getSelectedItem().getDeclarationId();

            DeclarationDao.updateColumns(updatedColumns, newColumnsValue, testColumn, testColumnValue);

            addComplaintPane.setVisible(false);
            loadDataOnComplaintsTable(new ArrayList<>(),"","","");
            updatedMsg.setVisible(true);
            hideMsg(updatedMsg,4);
        }
    }
    public void hideEditAddComplaintPane(ActionEvent event){
        addComplaintPane.setVisible(false);
    }

    public void displayConfirmDeletePane(ActionEvent event){
        rowSelectedError.setVisible(false);
        editError.setVisible(false);
        deleteError.setVisible(false);
        addedMsg.setVisible(false);
        updatedMsg.setVisible(false);
        deletedMsg.setVisible(false);

        if(complaintTable.getSelectionModel().getSelectedItem() == null){
            rowSelectedError.setVisible(true);
            return;
        }
        if(!String.valueOf(complaintTable.getSelectionModel().getSelectedItem().getResponse()).toLowerCase().equals("null")){
            deleteError.setVisible(true);
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
        DeclarationDao.delete("declarationId",complaintTable.getSelectionModel().getSelectedItem().getDeclarationId());
        confirmDeletePane.setVisible(false);

        loadDataOnComplaintsTable(new ArrayList<>(),"","","");
        actionPane.setVisible(true);
        deletedMsg.setVisible(true);
        hideMsg(deletedMsg,4);
    }

    public void displayDetailPane(ActionEvent event){
        rowSelectedError.setVisible(false);
        editError.setVisible(false);
        deleteError.setVisible(false);
        addedMsg.setVisible(false);
        updatedMsg.setVisible(false);
        deletedMsg.setVisible(false);

        if(complaintTable.getSelectionModel().getSelectedItem() == null){
            rowSelectedError.setVisible(true);
            return;
        }
        detailDate.setText(String.valueOf(complaintTable.getSelectionModel().getSelectedItem().getDeclarationDate()));
        detailComplaintObject.setText(String.valueOf(complaintTable.getSelectionModel().getSelectedItem().getDeclarationObject()));
        detailComplaint.setText(String.valueOf(complaintTable.getSelectionModel().getSelectedItem().getDeclaration()));
        String respDate = String.valueOf(complaintTable.getSelectionModel().getSelectedItem().getResponseDate()).equals("null")?"_______________":String.valueOf(complaintTable.getSelectionModel().getSelectedItem().getResponseDate());
        String resp = String.valueOf(complaintTable.getSelectionModel().getSelectedItem().getResponse()).equals("null")?"_______________":String.valueOf(complaintTable.getSelectionModel().getSelectedItem().getResponse());
        detailResponseDate.setText(respDate);
        detailResponse.setText(resp);
        detailsPane.setVisible(true);
    }
    public void hideDetailsComplaintPane(ActionEvent event){
        detailsPane.setVisible(false);
    }

    //----------------------------------- --------------------------------------------
    public void hideMsg(Label msg,double time){
        Duration duration = Duration.seconds(time);
        Timeline timeline = new Timeline(new KeyFrame(duration, e -> msg.setVisible(false)));
        timeline.setCycleCount(1);
        timeline.play();
    }
    public void logout(ActionEvent event) throws IOException {
        System.out.println(CustomerManager.getInstance().getCustomer());
        CustomerManager.getInstance().setCustomer(new Customer("","","","","",""));
        switchToWelcomePage(event);
    }

    public void switchToWelcomePage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/welcome-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println(CustomerManager.getInstance().getCustomer());
    }
}
