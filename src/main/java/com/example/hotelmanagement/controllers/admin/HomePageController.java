package com.example.hotelmanagement.controllers.admin;

import com.example.hotelmanagement.HelloApplication;
import com.example.hotelmanagement.beans.Employee;
import com.example.hotelmanagement.beans.Feedback;
import com.example.hotelmanagement.beans.Service;
import com.example.hotelmanagement.dao.*;
import com.example.hotelmanagement.daoFactory.CummonDbFcts;
import com.example.hotelmanagement.localStorage.AdminManager;
import com.example.hotelmanagement.config.PathConfig;
import com.example.hotelmanagement.localStorage.CustomerManager;
import com.example.hotelmanagement.localStorage.SwitchedPageManager;
import com.example.hotelmanagement.localStorage.VarsManager;
import com.example.hotelmanagement.scenes.Welcome;
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
import java.sql.Statement;
import java.time.LocalDate;
import java.util.*;

public class HomePageController implements Initializable{
    private Stage stage;
    private Scene scene;
    private Parent root;
    public static Stage childStage;
    private String currentPage;
    private Employee admin;
    @FXML private Label fullnameLabel;
    @FXML public Label empAddedMsg, empUpdatedMsg, empDeletedMsg;
    @FXML private Label noRowsMsg, rowSelectedError;
    @FXML private CheckBox Admin, Manager, Cleaner, MaintenanceStaff, OtherJob;
    @FXML private TextField fullnameField, phoneField, cinField, emailField;
    @FXML private TableView<EmployeesTableView> empsTable;
    @FXML private TableColumn<EmployeesTableView, Object> idCol, fullNameCol, cinCol, emailCol, positionCol, phoneCol;

    @FXML private TableView<FeedbackTableView> feedbackTable;
    @FXML private TableColumn<FeedbackTableView, Object> id___Col, fullName_Col, feedbackDateCol, rateCol, visibilityCol, commentCol;
    @FXML private DatePicker feedbackDate;
    @FXML private TextField fullname_Field;
    @FXML private CheckBox Visible, Invisible, RateAsc, RateDesc;
    @FXML private Label visibilityMsg;

    @FXML private Label employeesNbr, reservationsNbr, feedbacksNbr, ratingAverage, roomsNbr, occupiedRoomNbr, roomTYpesNbr;
    @FXML private Label monthEarning, yearEarning, totalEarning;

    @FXML public Label addedMsg, updatedMsg, deletedMsg, deletedServiceName;
    @FXML private TableView<ServiceTableView> servicesTable;
    @FXML private TableColumn<ServiceTableView, Object> id____Col, serviceNameCol, tableCorreCol, descriptionCol, statusCol;
    @FXML private TextField serviceNameField, corrTableField, descriptionField, statusField,serviceNameField1, corrTableField1, descriptionField1, statusField1;
    @FXML private AnchorPane addServicePane, editServicePane, deleteServicePane;
    @FXML private TextField serviceName_Field;
    @FXML private CheckBox Available, Unavailable;

    //------------------------------------------------------------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentPage = SwitchedPageManager.getInstance().getSwitchedPage();
        admin = AdminManager.getInstance().getAdmin();
        System.out.println(admin);
        System.out.println(currentPage);
        System.out.println(currentPage.equals("CustomerInfos"));
        fullnameLabel.setText("- " + admin.getFullName() + " -");

        if(currentPage.equals("Home")){
            employeesNbr.setText(String.valueOf(CummonDbFcts.countRows(EmployeeDao.TABLE_NAME)));
            reservationsNbr.setText(String.valueOf(CummonDbFcts.countRows(ReservationDao.TABLE_NAME)));
            feedbacksNbr.setText(String.valueOf(CummonDbFcts.countRows(FeedbackDao.TABLE_NAME)));
            ratingAverage.setText(String.valueOf(FeedbackDao.calculateAverageRating()));
            roomsNbr.setText(String.valueOf(CummonDbFcts.countRows(RoomDao.TABLE_NAME)));
            Map map = new HashMap<>();
            map.put("status", "Occupied");
            occupiedRoomNbr.setText(String.valueOf((RoomDao.select(map,"*")).size()));
            roomTYpesNbr.setText(String.valueOf(CummonDbFcts.countRows(RoomTypeDao.TABLE_NAME)));
        } else if (currentPage.equals("Employee")) {
            empAddedMsg.setVisible(false);
            empUpdatedMsg.setVisible(false);
            empDeletedMsg.setVisible(false);
            loadDataOnTable(new ArrayList<>(), "", "", "", "");
            noRowsMsg.setVisible(false);
            rowSelectedError.setVisible(false);
        } else if (currentPage.equals("Services")) {
            addedMsg.setVisible(false);
            updatedMsg.setVisible(false);
            deletedMsg.setVisible(false);
            addServicePane.setVisible(false);
            editServicePane.setVisible(false);
            deleteServicePane.setVisible(false);
            loadDataOnServiceTable(false, false, "");
            rowSelectedError.setVisible(false);

        } else if (currentPage.equals("Feedback")) {
            noRowsMsg.setVisible(false);
            updatedMsg.setVisible(false);
            visibilityMsg.setVisible(false);

            loadDataOnFeedbackTable(false,false,"",null,"");
            rowSelectedError.setVisible(false);

        } else if (currentPage.equals("Earning")) {
            monthEarning.setText(InvoiceDao.monthEarning() + " DH");
            yearEarning.setText(InvoiceDao.yearEarning()   + " DH");
            totalEarning.setText(InvoiceDao.totalEarning() + " DH");
        }
    }
//------------------------------------------------------------------------------------------
    public void switchToHome(ActionEvent event) throws IOException {
        SwitchedPageManager.getInstance().setSwitchedPage("Home");
        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/admin/HomePage-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToEmployee(ActionEvent event) throws IOException {
        SwitchedPageManager.getInstance().setSwitchedPage("Employee");
        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/admin/EmployeeDetails-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToServices(ActionEvent event) throws IOException {
        SwitchedPageManager.getInstance().setSwitchedPage("Services");
        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/admin/Services-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToFeedback(ActionEvent event) throws IOException {
        SwitchedPageManager.getInstance().setSwitchedPage("Feedback");
        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/admin/Feedback-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToEarning(ActionEvent event) throws IOException {
        SwitchedPageManager.getInstance().setSwitchedPage("Earning");
        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/admin/Earning-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
//-------------------------------------------------------------------------------
    public void loadDataOnTable(List<String> jobsList, String  fullname, String phone, String cin, String email){
        noRowsMsg.setVisible(false);
        List<EmployeesTableView> empsList = new ArrayList<>();
        empsTable.getItems().clear();
        EmployeesTableView.setNBR(1);

        List<String> colToSelect =  new ArrayList<String>(List.of("emp.employeeId", "emp.fullName", "emp.cin", "emp.email", "emp.position", "emp.phone"));
        if(jobsList.isEmpty() && fullname.isEmpty() && phone.isEmpty() && cin.isEmpty() && email.isEmpty()){
            List<Object[]> empsDetails = CummonDbFcts.performJoinAndSelect(EmployeeDao.TABLE_NAME, "emp", PositionDao.TABLE_NAME,"pos", "position","empPosition", colToSelect, "");
            for (Object[] row : empsDetails) {
                EmployeesTableView empRow = new EmployeesTableView(row[0],row[1],row[2],row[3],row[4],row[5]);
                empsList.add(empRow);
            }
        }else{
            //join and select rooms with status checked and price < priceSelected and capacite< capacity
            String col1 = "emp.position", col2 = "emp.fullName", col3 = "emp.phone", col4 = "emp.email", col5 = "emp.cin";
            String whereClause = " WHERE ";
            if(!jobsList.isEmpty()){
                whereClause += "(";
                for (String job: jobsList){
                    if(job.equals("OtherJob")){
                        whereClause = whereClause + col1 + " NOT IN ('Admin', 'Manager', 'Cleaner', 'Maintenance Staff')" + " OR ";
                    }else{
                        whereClause = whereClause + col1 + " = '" + job + "' OR ";
                    }
                }
                whereClause = whereClause.substring(0, whereClause.length() - 4); //delete last " OR "
                whereClause += ") AND ";
            }
            if(!fullname.isEmpty()){
                whereClause += "("+ col2 + " LIKE '%" + fullname + "%') AND ";
            }
            if(!phone.isEmpty()){
                whereClause += "("+ col3 + " LIKE '%" + phone + "%') AND ";
            }
            if(!email.isEmpty()){
                whereClause += "("+ col4 + " LIKE '%" + email + "%') AND ";
            }
            if(!cin.isEmpty()){
                whereClause += "("+ col5 + " LIKE '%" + cin + "%') AND ";
            }
            whereClause = whereClause.substring(0, whereClause.length() - 5);//delete last " AND "
            System.out.println(whereClause);

            List<Object[]> empsDetails = CummonDbFcts.performJoinAndSelect(EmployeeDao.TABLE_NAME, "emp", PositionDao.TABLE_NAME,"pos", "position","empPosition", colToSelect, whereClause);
            for (Object[] row : empsDetails) {
                EmployeesTableView empRow = new EmployeesTableView(row[0],row[1],row[2],row[3],row[4],row[5]);
                //System.out.println(roomRow);
                empsList.add(empRow);
            }
        }

        idCol.setCellValueFactory(new PropertyValueFactory<>("i"));
        fullNameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        cinCol.setCellValueFactory(new PropertyValueFactory<>("cin"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        positionCol.setCellValueFactory(new PropertyValueFactory<>("position"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        empsTable.getItems().addAll(empsList);
        if(empsList.isEmpty()){
            noRowsMsg.setVisible(true);
        }
    }
    public void filterEmployees(ActionEvent event){
        List<String> jobList = new ArrayList<>();

        if(Admin.isSelected()) jobList.add("Admin");

        if(Manager.isSelected()) jobList.add("Manager");

        if(Cleaner.isSelected()) jobList.add("Cleaner");

        if(MaintenanceStaff.isSelected()) jobList.add("Maintenance Staff");

        if(OtherJob.isSelected()) jobList.add("OtherJob");

        String fullname = fullnameField.getText();
        String phone = phoneField.getText();
        String cin = cinField.getText();
        String email = emailField.getText();

        loadDataOnTable(jobList, fullname, phone, cin, email);
    }
    public void empDetailsWindow(ActionEvent event) throws IOException {
        VarsManager.actionStarted = "details";
        rowSelectedError.setVisible(false);
        if(empsTable.getSelectionModel().getSelectedItem() == null){
            rowSelectedError.setVisible(true);
            return;
        }
        VarsManager.selectedEmpId = (int) empsTable.getSelectionModel().getSelectedItem().getEmployeeId();

        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/admin/InfoDetailEmployee-view.fxml"));
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

        loadDataOnTable(new ArrayList<>(),"","","","");
    }
    public void newEmployeeWindow(ActionEvent event) throws IOException {
        rowSelectedError.setVisible(false);
        VarsManager.actionStarted = "add";

        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/admin/NewEmployee-view.fxml"));
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
            empDeletedMsg.setVisible(false);
            empUpdatedMsg.setVisible(false);
            empAddedMsg.setVisible(true);
            hideMsg(empAddedMsg,4);
        }
        loadDataOnTable(new ArrayList<>(),"","","","");
    }
    public void editEmployeeWindow(ActionEvent event) throws IOException {
        rowSelectedError.setVisible(false);
        if(empsTable.getSelectionModel().getSelectedItem() == null){
            rowSelectedError.setVisible(true);
            return;
        }
        VarsManager.actionStarted = "update";
        VarsManager.selectedEmpId = (int) empsTable.getSelectionModel().getSelectedItem().getEmployeeId();

        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/admin/EditEmployee-view.fxml"));
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
            empDeletedMsg.setVisible(false);
            empAddedMsg.setVisible(false);
            empUpdatedMsg.setVisible(true);
            hideMsg(empUpdatedMsg,4);
        }
        loadDataOnTable(new ArrayList<>(),"","","","");
    }
    public void deleteEmployeeWindow(ActionEvent event) throws IOException {
        rowSelectedError.setVisible(false);
        if(empsTable.getSelectionModel().getSelectedItem() == null){
            rowSelectedError.setVisible(true);
            return;
        }

        VarsManager.actionStarted = "delete";
        VarsManager.selectedEmpId = (int) empsTable.getSelectionModel().getSelectedItem().getEmployeeId();

        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/admin/DeleteEmployee-view.fxml"));
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
            empAddedMsg.setVisible(false);
            empUpdatedMsg.setVisible(false);
            empDeletedMsg.setVisible(true);
            hideMsg(empDeletedMsg,4);

        }
        loadDataOnTable(new ArrayList<>(),"","","","");
    }
    //----------------------------------- Feedback fcts --------------------------------------------
    public void loadDataOnFeedbackTable(Boolean visible, Boolean invisible, String fullname, LocalDate fbDate, String rateOrder){
        noRowsMsg.setVisible(false);

        List<FeedbackTableView> feedbackList = new ArrayList<>();
        feedbackTable.getItems().clear();
        FeedbackTableView.setNBR(1);

        List<String> colToSelect =  new ArrayList<String>(List.of ("f.feedbackId", "f.customerId", "cust.fullName", "f.visibility", "f.priority", "f.customerService_rate", "f.cleanliness_rate", "f.roomComfort_rate", "f.location_rate", "f.safety_rate", "f.environnement_rate", "f.view_rate", "f.serviceVSprice_rate", "f.review_rate", "f.feedback_date"));
        System.out.println(fullname);
        System.out.println(rateOrder);
        System.out.println(fbDate);
        if(fullname.isEmpty() && rateOrder.isEmpty() && fbDate == null && visible == false && invisible == false){
            List<Object[]> feedbackdetails = CummonDbFcts.performJoinAndSelect(FeedbackDao.TABLE_NAME, "f", CustomerDao.TABLE_NAME,"cust","customerId","customerId", colToSelect, "");
            for (Object[] row : feedbackdetails) {
                FeedbackTableView feedbackRow = new FeedbackTableView(row[0],row[1],row[2],row[3],row[4],row[5],row[6],row[7],row[8],row[9],row[10],row[11], row[12], row[13], row[14]);
                System.out.println(feedbackRow);
                feedbackList.add(feedbackRow);
            }
        }else{
            String whereClause = " WHERE ";
            int i = 0;
            List<String> visibilityList = new ArrayList<>();

            if(visible == true){
                visibilityList.add("Visible");
                i++;
            }
            if(invisible == true){
                visibilityList.add("Invisible");
                i++;
            }
            for (String vis: visibilityList){
                whereClause = whereClause + "visibility = '" +vis +"' OR ";
            }
            if (i!=0){
                whereClause = whereClause.substring(0, whereClause.length() - 4);//delete last " OR "
                whereClause += " AND ";
            }
            if(!fullname.isEmpty()){
                whereClause = whereClause + "cust.fullName LIKE '%" + fullname + "%' AND ";
                i++;
            }
            if(fbDate != null){
                whereClause = whereClause + "f.feedback_date LIKE '%" + fbDate + "%' AND ";
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
        fullName_Col.setCellValueFactory(new PropertyValueFactory<>("customerFullName"));
        feedbackDateCol.setCellValueFactory(new PropertyValueFactory<>("feedback_date"));
        rateCol.setCellValueFactory(new PropertyValueFactory<>("total_rate"));
        visibilityCol.setCellValueFactory(new PropertyValueFactory<>("visibility"));
        commentCol.setCellValueFactory(new PropertyValueFactory<>("review_comment"));

        feedbackTable.getItems().addAll(feedbackList);
        if(feedbackList.isEmpty()){
            noRowsMsg.setVisible(true);
        }
    }
    public void filterFeedbacks(ActionEvent event){
            String rateOrder = "";
            boolean visible = false;
            boolean invisible = false;
            LocalDate fbDate = feedbackDate.getValue();
            String fullname = fullname_Field.getText();

            if(Visible.isSelected()) visible = true ;
            if(Invisible.isSelected()) invisible = true ;

            if(RateDesc.isSelected()) {
                rateOrder = "DESC";
                RateAsc.setSelected(false);
            }

            if(RateAsc.isSelected()) {
                rateOrder = "ASC";
                RateDesc.setSelected(false);
            }

        loadDataOnFeedbackTable(visible, invisible, fullname, fbDate, rateOrder);
    }
    public void feedbackDetailsWindow(ActionEvent event) throws IOException {

        rowSelectedError.setVisible(false);
        VarsManager.actionStarted = "details";

        if(feedbackTable.getSelectionModel().getSelectedItem() == null){
            rowSelectedError.setVisible(true);
            return;
        }
        VarsManager.selectedFeedbackId = (int) feedbackTable.getSelectionModel().getSelectedItem().getFeedbackId();

        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/admin/FeedbackDetail-view.fxml"));
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

        loadDataOnFeedbackTable(false,false,"",null,"");
    }
    public void setFeedbackVisible(ActionEvent event){
        rowSelectedError.setVisible(false);
        if(feedbackTable.getSelectionModel().getSelectedItem() == null){
            rowSelectedError.setVisible(true);
            return;
        }
        VarsManager.selectedFeedbackId = (int) feedbackTable.getSelectionModel().getSelectedItem().getFeedbackId();
        Map map = new HashMap<>();
        map.put("feedbackId", VarsManager.selectedFeedbackId);
        String oldVisibility = ((Feedback) FeedbackDao.select(map,"*").get(0)).getVisibility();

        String newVsibility = oldVisibility.equals("Visible")? "Invisible":"Visible";
        String[] updatedColumns = {"visibility"};
        Object[] newColumnsValue = {newVsibility};
        String testColumn = "feedbackId";
        Object testColumnValue = VarsManager.selectedFeedbackId;

        int i = FeedbackDao.updateColumns(updatedColumns, newColumnsValue, testColumn, testColumnValue);
        if(i == 1){
            updatedMsg.setVisible(true);
            visibilityMsg.setVisible(true);
            hideMsg(updatedMsg, 4);
            hideMsg(visibilityMsg, 4);
        }
        loadDataOnFeedbackTable(false,false,"",null,"");

    }
//---------------------------------------- SERVICES ---------------------------------------
    public void loadDataOnServiceTable(Boolean available, Boolean unavailable, String serviceName){
    rowSelectedError.setVisible(false);

    List<ServiceTableView> serviceList = new ArrayList<>();
    servicesTable.getItems().clear();
    ServiceTableView.setNBR(1);

    if(serviceName.isEmpty() && available == false && unavailable == false){
        List<String> colToSelect =  new ArrayList<String>(List.of ("s.serviceId", "s.serviceName", "s.descreption", "s.correspondingTable", "s.status"));
        List<Object> servicesdetails = ServiceDao.selectAll();
        for (Object row : servicesdetails) {
            Service service = (Service) row;
            ServiceTableView serviceRow = new ServiceTableView(service.getServiceId(),service.getServiceName(),service.getDescreption(),service.getCorrespondingTable(),service.getStatus());
            serviceList.add(serviceRow);
        }
    }else{
        String query = "SELECT serviceId, serviceName, descreption, correspondingTable, status " +
                       "FROM service";
        query += " WHERE ";
        int i = 0;
        List<String> availibilityList = new ArrayList<>();

        if(available == true){
            availibilityList.add("Available");
            i++;
        }
        if(unavailable == true){
            availibilityList.add("Unavailable");
            i++;
        }
        for (String vis: availibilityList){
            query = query + "status = '" +vis +"' OR ";
        }
        if (i!=0){
            query = query.substring(0, query.length() - 4);//delete last " OR "
            query += " AND ";
        }
        if(!serviceName.isEmpty()){
            query = query + "serviceName LIKE '%" + serviceName + "%' AND ";
            i++;
        }
        query = query.substring(0, query.length() - 5);//delete last " AND "


        System.out.println(query);
        List<String> colToSelect =  new ArrayList<String>(List.of ("serviceId", "serviceName", "descreption", "correspondingTable", "status"));
        List<Object[]> servicesDetails = CummonDbFcts.querySelect(query, colToSelect);
        for (Object[] row : servicesDetails) {
            ServiceTableView serviceRow = new ServiceTableView(row[0],row[1],row[2],row[3],row[4]);
            System.out.println(serviceRow);
            serviceList.add(serviceRow);
        }
    }
    id____Col.setCellValueFactory(new PropertyValueFactory<>("i"));
    serviceNameCol.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
    tableCorreCol.setCellValueFactory(new PropertyValueFactory<>("correspondingTable"));
    descriptionCol.setCellValueFactory(new PropertyValueFactory<>("descreption"));
    statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

    servicesTable.getItems().addAll(serviceList);
}
    public void filterServices(ActionEvent event){
        boolean available = false;
        boolean unavailabe = false;
        String serviceName = serviceName_Field.getText();

        if(Available.isSelected()) available = true ;
        if(Unavailable.isSelected()) unavailabe = true ;

        loadDataOnServiceTable(available, unavailabe, serviceName);
    }
    public void displayAddPane(ActionEvent event){
        rowSelectedError.setVisible(false);

        deletedMsg.setVisible(false);
        updatedMsg.setVisible(false);
        addedMsg.setVisible(false);

        hideEditPane(event);
        hideDeletePane(event);

        serviceNameField.setText("");
        corrTableField.setText("");
        descriptionField.setText("");

        addServicePane.setVisible(true);
    }
    public void displayEditPane(ActionEvent event){
        deletedMsg.setVisible(false);
        updatedMsg.setVisible(false);
        addedMsg.setVisible(false);
        rowSelectedError.setVisible(false);
        if(servicesTable.getSelectionModel().getSelectedItem() == null){
            rowSelectedError.setVisible(true);
            return;
        }

        int selectedServiceId = (int) servicesTable.getSelectionModel().getSelectedItem().getServiceId();
        Map map = new HashMap<>();
        map.put("serviceId",selectedServiceId);
        Service selectedService = (Service) (ServiceDao.select(map, "*").get(0));
        //traitement
        System.out.println(selectedService);
        serviceNameField1.setText(selectedService.getServiceName());
        corrTableField1.setText(selectedService.getCorrespondingTable());
        descriptionField1.setText(selectedService.getDescreption());
        statusField1.setText(selectedService.getStatus());

        hideAddPane(event);
        hideDeletePane(event);
        editServicePane.setVisible(true);
    }
    public void displayDeletePane(ActionEvent event){
        rowSelectedError.setVisible(false);
        if(servicesTable.getSelectionModel().getSelectedItem() == null){
            rowSelectedError.setVisible(true);
            return;
        }

        deletedMsg.setVisible(false);
        updatedMsg.setVisible(false);
        addedMsg.setVisible(false);

        String selectedServiceName = (String)servicesTable.getSelectionModel().getSelectedItem().getServiceName();
        deletedServiceName.setText(selectedServiceName + " service");

        hideAddPane(event);
        hideEditPane(event);
        deleteServicePane.setVisible(true);
    }
    public void hideAddPane(ActionEvent event){
        addServicePane.setVisible(false);
    }
    public void hideEditPane(ActionEvent event){
        editServicePane.setVisible(false);
    }
    public void hideDeletePane(ActionEvent event){
        deleteServicePane.setVisible(false);
    }
    public void newService(ActionEvent event) throws IOException {
        String serviceName = serviceNameField.getText();
        String serviceTable = corrTableField.getText();
        String serviceDesc = descriptionField.getText();
        String serviceStatus = statusField.getText();

        if(!serviceName.isEmpty()){
            serviceNameField.setStyle("-fx-border-color: white;");
            statusField.setStyle("-fx-border-color: white;");

            if(!serviceStatus.toLowerCase().equals("available") && !serviceStatus.toLowerCase().equals("unavailable")){
                statusField.setStyle("-fx-border-color: red;");
                System.out.println(serviceStatus + "----------------");
                return;
            }
            Service service = new Service(serviceName, serviceDesc, serviceTable, serviceStatus);
            ServiceDao.insert(service);
        }else{
            serviceNameField.setStyle("-fx-border-color: red;");
            return;
        }
        hideAddPane(event);
        loadDataOnServiceTable(false,false,"");
        addedMsg.setVisible(true);
        hideMsg(addedMsg,4);
    }
    public void editService(ActionEvent event) throws IOException {

        String serviceName = serviceNameField1.getText();
        String serviceTable = corrTableField1.getText();
        String serviceDesc = descriptionField1.getText();
        String serviceStatus = statusField1.getText();

        if(!serviceName.isEmpty()){
            serviceNameField1.setStyle("-fx-border-color: white;");
            statusField1.setStyle("-fx-border-color: red;");
            if(!serviceStatus.toLowerCase().equals("available") && !serviceStatus.toLowerCase().equals("unavailable")){
                statusField1.setStyle("-fx-border-color: white;");
                return;
            }

            String[] updatedColumns = {"serviceName", "correspondingTable", "descreption", "status"};
            Object[] newColumnsValue = {serviceName, serviceTable, serviceDesc, serviceStatus};
            String testColumn = "serviceId";
            Object testColumnValue = servicesTable.getSelectionModel().getSelectedItem().getServiceId();
            System.out.println("testColumnValue = "+testColumnValue);
            int i =ServiceDao.updateColumns(updatedColumns, newColumnsValue, testColumn, testColumnValue);
            System.out.println("i = "+i);
        }else{
            serviceNameField1.setStyle("-fx-border-color: red;");
            return;
        }
        hideEditPane(event);
        loadDataOnServiceTable(false,false,"");
        updatedMsg.setVisible(true);
        hideMsg(updatedMsg,4);
    }
    public void deleteService(ActionEvent event) throws IOException {
        int selectedServiceId = (int) servicesTable.getSelectionModel().getSelectedItem().getServiceId();
        ServiceDao.delete("serviceId",selectedServiceId);
        loadDataOnServiceTable(false,false,"");
        hideDeletePane(event);
        deletedMsg.setVisible(true);
        hideMsg(deletedMsg,4);
    }
//-------------------------------------------------------------------------------
    public void hideMsg(Label msg,double time){
    Duration duration = Duration.seconds(time);
    Timeline timeline = new Timeline(new KeyFrame(duration, e -> msg.setVisible(false)));
    timeline.setCycleCount(1);
    timeline.play();
}
    public void logout(ActionEvent event) throws IOException {
        System.out.println(AdminManager.getInstance().getAdmin());
        AdminManager.getInstance().setAdmin(new Employee("", "", "", "", "", "", 0, "", ""));
        switchToWelcomePage(event);
    }
    public void switchToWelcomePage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/welcome-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println(AdminManager.getInstance().getAdmin());
    }
}
