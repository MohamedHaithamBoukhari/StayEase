package com.example.hotelmanagement.controllers.employee.cleaner;

import com.example.hotelmanagement.HelloApplication;
import com.example.hotelmanagement.beans.Complaint;
import com.example.hotelmanagement.beans.Employee;
import com.example.hotelmanagement.beans.Task;
import com.example.hotelmanagement.config.PathConfig;
import com.example.hotelmanagement.dao.*;
import com.example.hotelmanagement.daoFactory.CummonDbFcts;
import com.example.hotelmanagement.localStorage.EmployeeManager;
import com.example.hotelmanagement.localStorage.SwitchedPageManager;
import com.example.hotelmanagement.localStorage.VarsManager;
import com.example.hotelmanagement.tablesView.AffectedTasksTableView;
import com.example.hotelmanagement.tablesView.ComplaintTableView;
import com.example.hotelmanagement.tablesView.RoomsTableView;
import com.example.hotelmanagement.tablesView.TaskTableView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class HomePageController implements Initializable {
    private Stage stage;
    public static Stage childStage;
    private Scene scene;
    private Parent root;

    @FXML private PieChart pieChart;
    @FXML private Label fullnameLabel;
    @FXML private Label tasksNbr, completedTasksNbr, onHoldTasksNbr, inProgressTasksNbr;


    @FXML private TableView<TaskTableView> tasksTable;
    @FXML private TableColumn<TaskTableView, Object> idCol, taskDateCol, taskStatusCol, roomNbrCol;
    @FXML private CheckBox OnHold, Completed, InProgress, TaskDateDesc, TaskDateAsc;
    @FXML private DatePicker taskDatePicker;
    @FXML private Label noRowsMsg;

    @FXML private TableView<ComplaintTableView> complaintTable;
    @FXML private TableColumn<ComplaintTableView, Object> id_Col, complaintObjectCol, responseStatusCol, complaintDateCol;
    @FXML private CheckBox Replied, Unreplied, ComplaintDateAsc, ComplaintDateDesc;
    @FXML private DatePicker complaintDatePicker;
    @FXML private TextField complaintObjectField, objectField;
    @FXML private TextArea complaintField;
    @FXML private AnchorPane addComplaintPane, confirmDeletePane, detailsPane;
    @FXML private Button confirmAddBtn, saveBtn;
    @FXML private Label detailResponseDate, detailResponse, detailComplaint, detailComplaintObject, detailDate;
    @FXML private Label addDeleteLabel, editError, deleteError, addedMsg, updatedMsg, deletedMsg;

    @FXML private AnchorPane actionPane,confirmActionPane;
    @FXML private Label rowSelectedError, confirmActionMsg, inProgressStatusError, completedStatusError;
    @FXML private Label statusUpdatedMsg;
    private String newTasKStatus;

    @FXML private ComboBox<String> positionComboBox_;
    @FXML TextField fullNameField_, cinField_, phoneField_, emailAddressField_, passwordField_, positionField_, salaryField_;
    @FXML private RadioButton from00to08, from08to16, from16to00;
    @FXML private CheckBox Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday;
    List<String> positionsList = new ArrayList<>();
    private String working_hours="";
    private List working_days = new ArrayList<>();

    //-------------------------------------------------------------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String currentPage = SwitchedPageManager.getInstance().getSwitchedPage();
        Employee cleaner = EmployeeManager.getInstance().getEmployee();

        fullnameLabel.setText("- " + cleaner.getFullName() + " -");

        if(currentPage.equals("Home")){
            Map map1 = new HashMap<>();
            map1.put("employeeId", cleaner.getEmployeeId());
            tasksNbr.setText(String.valueOf(TaskDao.select(map1, "*").size()));
            Map map3 = new HashMap<>();
            map3.put("status", "Completed");
            map3.put("employeeId", cleaner.getEmployeeId());
            String completedTasks =  String.valueOf(TaskDao.select(map3, "*").size());
            completedTasksNbr.setText(completedTasks);
            Map map4 = new HashMap<>();
            map4.put("status", "On Hold");
            map4.put("employeeId", cleaner.getEmployeeId());
            String onHoldTasks = String.valueOf(TaskDao.select(map4, "*").size());
            onHoldTasksNbr.setText(onHoldTasks);
            Map map5 = new HashMap<>();
            map5.put("status", "In progress");
            map5.put("employeeId", cleaner.getEmployeeId());
            String inProgresTasks = String.valueOf(TaskDao.select(map5, "*").size());
            inProgressTasksNbr.setText(inProgresTasks);
            ObservableList<PieChart.Data> pieChartData =
                    FXCollections.observableArrayList(
                            new PieChart.Data("Completed tasks", Double.parseDouble(completedTasks)),
                            new PieChart.Data("On Hold tasks", Double.parseDouble(onHoldTasks)),
                            new PieChart.Data("In Progress tasks", Double.parseDouble(inProgresTasks)));
            pieChartData.forEach(data ->
                    data.nameProperty().bind(
                            Bindings.concat(data.getName(), data.pieValueProperty())
                    )
            );
            pieChart.getData().addAll(pieChartData);

        } else if (currentPage.equals("Cleaning")) {
            rowSelectedError.setVisible(false);
            inProgressStatusError.setVisible(false);
            completedStatusError.setVisible(false);
            rowSelectedError.setVisible(false);
            statusUpdatedMsg.setVisible(false);
            confirmActionPane.setVisible(false);
            loadDataOnTaskTable(new ArrayList<>(), "", "");
        }else if (currentPage.equals("Complaint")) {
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
        }else if (currentPage.equals("Profile")){
            Map<String, Object> map = new HashMap<>();
            map.put("employeeId", EmployeeManager.getInstance().getEmployee().getEmployeeId());
            Employee employeeSelected = (Employee) EmployeeDao.select(map, "*").get(0);
            fullNameField_.setText(employeeSelected.getFullName());
            cinField_.setText(employeeSelected.getCin());
            phoneField_.setText(employeeSelected.getPhone());
            emailAddressField_.setText(employeeSelected.getEmail());
            passwordField_.setText(employeeSelected.getPassword());
            positionComboBox_.getSelectionModel().select(employeeSelected.getPosition());
            salaryField_.setText(String.valueOf(employeeSelected.getSalary()));

            getAndSetWorkingHours(new ActionEvent(), employeeSelected.getWorkingHours());
            getAndSetWorkingDays(new ActionEvent(), employeeSelected.getWorkingDays());
        }
    }

    public void switchToHome(ActionEvent event) throws IOException {
        SwitchedPageManager.getInstance().setSwitchedPage("Home");
        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/employee/cleaner/HomePage-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToCleaning(ActionEvent event) throws IOException {
        SwitchedPageManager.getInstance().setSwitchedPage("Cleaning");
        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/employee/cleaner/Cleaning-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToComplaint(ActionEvent event) throws IOException {
        SwitchedPageManager.getInstance().setSwitchedPage("Complaint");
        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/employee/cleaner/Complaint-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToProfile(ActionEvent event) throws IOException {
        SwitchedPageManager.getInstance().setSwitchedPage("Profile");
        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/employee/cleaner/Profile-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    //-------------------------------------Cleaning Tasks----------------------------------------------
    public void loadDataOnTaskTable(List<String> statusList, String  taskDateOrder, String taskDate){
        noRowsMsg.setVisible(false);
        List<TaskTableView> tasksList = new ArrayList<>();
        tasksTable.getItems().clear();
        TaskTableView.setNBR(1);

        List<String> colToSelect =  new ArrayList<String>(List.of("t.taskId", "t.status","t.taskDate", "t.roomId"));

        if(statusList.isEmpty() && taskDateOrder.isEmpty() && taskDate.isEmpty()){
            String whereClause = " WHERE e.employeeId = " + EmployeeManager.getInstance().getEmployee().getEmployeeId();
            List<Object[]> tasksDetails = CummonDbFcts.performJoinAndSelect(TaskDao.TABLE_NAME, "t", EmployeeDao.TABLE_NAME,"e","employeeId","employeeId", colToSelect, whereClause);
            for (Object[] row : tasksDetails) {
                TaskTableView taskRow = new TaskTableView(row[0],row[1],row[2],row[3]);
                System.out.println(taskRow);
                tasksList.add(taskRow);
            }
        }else{
            String col1 = "t.status", col2 = "t.taskDate";
            String whereClause= " WHERE e.employeeId = " + EmployeeManager.getInstance().getEmployee().getEmployeeId()+" AND ";
            if(!statusList.isEmpty()){
                whereClause += "(";
                for (String job: statusList){
                    whereClause =whereClause  + col1 + " = '" + job + "' OR ";
                }
                whereClause = whereClause .substring(0,whereClause .length() - 4); //delete last " OR "
                whereClause += ") AND ";
            }
            if(!taskDate.isEmpty()){
                whereClause += "("+ col2 + " LIKE '%" + taskDate + "%') AND ";
            }

            if(!taskDateOrder.equals("")){
                whereClause = whereClause.substring(0, whereClause.length() - 5);//delete last " AND "
                whereClause = whereClause + " ORDER BY t.taskDate " + taskDateOrder + " AND ";
            }
            whereClause = whereClause.substring(0, whereClause.length() - 5);//delete last "AND "
            System.out.println(whereClause);


            List<Object[]> tasksDetails = CummonDbFcts.performJoinAndSelect(TaskDao.TABLE_NAME, "t", EmployeeDao.TABLE_NAME,"e","employeeId","employeeId", colToSelect, whereClause);
            for (Object[] row : tasksDetails) {
                TaskTableView taskRow = new TaskTableView(row[0],row[1],row[2],row[3]);
                System.out.println(taskRow);
                tasksList.add(taskRow);
            }
        }

        idCol.setCellValueFactory(new PropertyValueFactory<>("i"));
        taskDateCol.setCellValueFactory(new PropertyValueFactory<>("taskDate"));
        taskStatusCol.setCellValueFactory(new PropertyValueFactory<>("taskStatus"));
        roomNbrCol.setCellValueFactory(new PropertyValueFactory<>("roomNbr"));
        tasksTable.getItems().addAll(tasksList);

        if(tasksList.isEmpty()){
            noRowsMsg.setVisible(true);
        }

    }
    public void filterTasks(ActionEvent event){
        List<String> statusList = new ArrayList<>();
        String taskDateOrder = "";

        if(OnHold.isSelected()) statusList.add("On Hold");
        if(InProgress.isSelected()) statusList.add("In Progress");
        if(Completed.isSelected()) statusList.add("Completed");

        if(TaskDateDesc.isSelected()) {
            taskDateOrder = "DESC";
            TaskDateAsc.setSelected(false);
        }
        if(TaskDateAsc.isSelected()) {
            taskDateOrder = "ASC";
            TaskDateDesc.setSelected(false);
        }

        LocalDate taskDate__ = taskDatePicker.getValue();
        String taskDate="";
        if (taskDate__ != null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Adjust the pattern as needed
            taskDate = taskDate__.format(formatter);
        }

        loadDataOnTaskTable(statusList, taskDateOrder, taskDate);
    }
    public void confirmAction(ActionEvent event){
        completedStatusError.setVisible(false);
        inProgressStatusError.setVisible(false);

        int taskId = (int) (tasksTable.getSelectionModel().getSelectedItem().getTaskId());
        TaskDao.update("status",newTasKStatus,"taskId",taskId);
        if(newTasKStatus.equals("Completed")){//the room is available
        int roomId = (int) (tasksTable.getSelectionModel().getSelectedItem().getRoomId());
            RoomDao.update("status","Available","roomId",roomId);
        }
        newTasKStatus = "";
        confirmActionPane.setVisible(false);
        actionPane.setVisible(true);

        statusUpdatedMsg.setVisible(true);
        hideMsg(statusUpdatedMsg,4);

        loadDataOnTaskTable(new ArrayList<>(),"","");
    }
    public void displayConfirmCompletedActionPane(ActionEvent event){
        completedStatusError.setVisible(false);
        inProgressStatusError.setVisible(false);
        rowSelectedError.setVisible(false);

        if(tasksTable.getSelectionModel().getSelectedItem() == null){
            rowSelectedError.setVisible(true);
            return;
        }
        if(tasksTable.getSelectionModel().getSelectedItem().getTaskStatus().equals("Completed") || tasksTable.getSelectionModel().getSelectedItem().getTaskStatus().equals("On Hold") ){
            completedStatusError.setVisible(true);
            return;
        }
        actionPane.setVisible(false);
        confirmActionPane.setVisible(true);
        confirmActionMsg.setText("Change task Status from In Progress to Completed for room " + tasksTable.getSelectionModel().getSelectedItem().getRoomNbr());
        newTasKStatus = "Completed";
    }
    public void displayConfirmInProgressActionPane(ActionEvent event){
        completedStatusError.setVisible(false);
        inProgressStatusError.setVisible(false);
        rowSelectedError.setVisible(false);

        if(tasksTable.getSelectionModel().getSelectedItem() == null){
            rowSelectedError.setVisible(true);
            return;
        }
        if(tasksTable.getSelectionModel().getSelectedItem().getTaskStatus().equals("Completed") || tasksTable.getSelectionModel().getSelectedItem().getTaskStatus().equals("In Progress") ){
            inProgressStatusError.setVisible(true);
            return;
        }

        actionPane.setVisible(false);
        confirmActionPane.setVisible(true);
        confirmActionMsg.setText("Change task Status from ON HIDE to In Progress for room " + tasksTable.getSelectionModel().getSelectedItem().getRoomNbr());
        newTasKStatus = "In Progress";
    }
    public void hideConfirmActionPane(ActionEvent event){
        confirmActionPane.setVisible(false);
        actionPane.setVisible(true);
    }
    //---------------------------------------Complaints--------------------------------------------
    public void loadDataOnComplaintsTable(List<String> responseStatusList, String  complaintDateOrder, String complaintDate, String complaintObject){
        noRowsMsg.setVisible(false);
        List<ComplaintTableView> complaintsList = new ArrayList<>();
        complaintTable.getItems().clear();
        ComplaintTableView.setNBR(1);

        List<String> colToSelect =  new ArrayList<String>(List.of("declarationId", "declarantId", "declarantStatus", "declarationObject", "declaration", "declarationDate", "response", "responseDate"));
        String declarantStatus = "Cleaner";
        String query = "SELECT declarationId, declarantId, declarantStatus, declarationObject, declaration, declarationDate, response, responseDate " +
                "FROM declaration " +
                "WHERE declarantId = " + EmployeeManager.getInstance().getEmployee().getEmployeeId() + " AND declarantStatus = '" + declarantStatus + "'";
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
            int declarantId = EmployeeManager.getInstance().getEmployee().getEmployeeId();
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
    //-----       ------      -----       ------      -----       ------
    public void getAndSetWorkingHours(ActionEvent event, String working_hours){
        if(working_hours.equals("00:00 -> 08:00")) from00to08.setSelected(true);
        if(working_hours.equals("08:00 -> 16:00")) from08to16.setSelected(true);
        if(working_hours.equals("16:00 -> 00:00")) from16to00.setSelected(true);
    }
    public void getAndSetWorkingDays(ActionEvent event, String working_days){
        List<String> daysList = Arrays.asList(working_days.split(", "));
        if(daysList.contains("Monday")) Monday.setSelected(true);
        if(daysList.contains("Tuesday")) Tuesday.setSelected(true);
        if(daysList.contains("Wednesday")) Wednesday.setSelected(true);
        if(daysList.contains("Thursday")) Thursday.setSelected(true);
        if(daysList.contains("Friday")) Friday.setSelected(true);
        if(daysList.contains("Saturday")) Saturday.setSelected(true);
        if(daysList.contains("Sunday")) Sunday.setSelected(true);
    }

    //-----       ------      -----       ------      -----       ------

    //-----------------------------------------------------------------------------------
    public void hideMsg(Label msg, double time){
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