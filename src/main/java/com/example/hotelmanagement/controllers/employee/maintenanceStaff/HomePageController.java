package com.example.hotelmanagement.controllers.employee.maintenanceStaff;

import com.example.hotelmanagement.HelloApplication;
import com.example.hotelmanagement.beans.Employee;
import com.example.hotelmanagement.config.PathConfig;
import com.example.hotelmanagement.dao.EmployeeDao;
import com.example.hotelmanagement.dao.RoomDao;
import com.example.hotelmanagement.dao.TaskDao;
import com.example.hotelmanagement.daoFactory.CummonDbFcts;
import com.example.hotelmanagement.localStorage.EmployeeManager;
import com.example.hotelmanagement.localStorage.SwitchedPageManager;
import com.example.hotelmanagement.tablesView.TaskTableView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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

    @FXML private AnchorPane actionPane,confirmActionPane;
    @FXML private Label rowSelectedError, confirmActionMsg, inProgressStatusError, completedStatusError;
    @FXML private Label statusUpdatedMsg;
    private String newTasKStatus;

    //-------------------------------------------------------------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String currentPage = SwitchedPageManager.getInstance().getSwitchedPage();
        Employee mainenanceStaff = EmployeeManager.getInstance().getEmployee();

        fullnameLabel.setText("- " + mainenanceStaff.getFullName() + " -");

        if(currentPage.equals("Home")){
            Map map1 = new HashMap<>();
            map1.put("employeeId", mainenanceStaff.getEmployeeId());
            tasksNbr.setText(String.valueOf(TaskDao.select(map1, "*").size()));
            Map map3 = new HashMap<>();
            map3.put("status", "Completed");
            map3.put("employeeId", mainenanceStaff.getEmployeeId());
            String completedTasks =  String.valueOf(TaskDao.select(map3, "*").size());
            completedTasksNbr.setText(completedTasks);
            Map map4 = new HashMap<>();
            map4.put("status", "On Hold");
            map4.put("employeeId", mainenanceStaff.getEmployeeId());
            String onHoldTasks = String.valueOf(TaskDao.select(map4, "*").size());
            onHoldTasksNbr.setText(onHoldTasks);
            Map map5 = new HashMap<>();
            map5.put("status", "In progress");
            map5.put("employeeId", mainenanceStaff.getEmployeeId());
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

        } else if (currentPage.equals("Cleaning") || currentPage.equals("Maintenance")) {
            rowSelectedError.setVisible(false);
            inProgressStatusError.setVisible(false);
            completedStatusError.setVisible(false);
            rowSelectedError.setVisible(false);
            statusUpdatedMsg.setVisible(false);
            confirmActionPane.setVisible(false);
//            loadDataOnTaskTable(new ArrayList<>(), "", "");
        }else if (currentPage.equals("Complaint")) {

        }
    }

    public void switchToHome(ActionEvent event) throws IOException {
        SwitchedPageManager.getInstance().setSwitchedPage("Home");
        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/employee/maintenanceStaff/HomePage-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToCleaning(ActionEvent event) throws IOException {
        SwitchedPageManager.getInstance().setSwitchedPage("Maintenance");
        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/employee/maintenanceStaff/Cleaning-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToComplaint(ActionEvent event) throws IOException {
        SwitchedPageManager.getInstance().setSwitchedPage("Complaint");
        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/employee/maintenanceStaff/Complaint-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    //-------------------------------------Cleaning Tasks----------------------------------------------
//    public void loadDataOnTaskTable(List<String> statusList, String  taskDateOrder, String taskDate){
//        noRowsMsg.setVisible(false);
//        List<TaskTableView> tasksList = new ArrayList<>();
//        tasksTable.getItems().clear();
//        TaskTableView.setNBR(1);
//
//        List<String> colToSelect =  new ArrayList<String>(List.of("t.taskId", "t.status","t.taskDate", "t.roomId"));
//
//        if(statusList.isEmpty() && taskDateOrder.isEmpty() && taskDate.isEmpty()){
//            String whereClause = " WHERE e.employeeId = " + EmployeeManager.getInstance().getEmployee().getEmployeeId();
//            List<Object[]> tasksDetails = CummonDbFcts.performJoinAndSelect(TaskDao.TABLE_NAME, "t", EmployeeDao.TABLE_NAME,"e","employeeId","employeeId", colToSelect, whereClause);
//            for (Object[] row : tasksDetails) {
//                TaskTableView taskRow = new TaskTableView(row[0],row[1],row[2],row[3]);
//                System.out.println(taskRow);
//                tasksList.add(taskRow);
//            }
//        }else{
//            String col1 = "t.status", col2 = "t.taskDate";
//            String whereClause= " WHERE e.employeeId = " + EmployeeManager.getInstance().getEmployee().getEmployeeId()+" AND ";
//            if(!statusList.isEmpty()){
//                whereClause += "(";
//                for (String job: statusList){
//                    whereClause =whereClause  + col1 + " = '" + job + "' OR ";
//                }
//                whereClause = whereClause .substring(0,whereClause .length() - 4); //delete last " OR "
//                whereClause += ") AND ";
//            }
//            if(!taskDate.isEmpty()){
//                whereClause += "("+ col2 + " LIKE '%" + taskDate + "%') AND ";
//            }
//
//            if(!taskDateOrder.equals("")){
//                whereClause = whereClause.substring(0, whereClause.length() - 5);//delete last " AND "
//                whereClause = whereClause + " ORDER BY t.taskDate " + taskDateOrder + " AND ";
//            }
//            whereClause = whereClause.substring(0, whereClause.length() - 5);//delete last "AND "
//            System.out.println(whereClause);
//
//
//            List<Object[]> tasksDetails = CummonDbFcts.performJoinAndSelect(TaskDao.TABLE_NAME, "t", EmployeeDao.TABLE_NAME,"e","employeeId","employeeId", colToSelect, whereClause);
//            for (Object[] row : tasksDetails) {
//                TaskTableView taskRow = new TaskTableView(row[0],row[1],row[2],row[3]);
//                System.out.println(taskRow);
//                tasksList.add(taskRow);
//            }
//        }
//
//        idCol.setCellValueFactory(new PropertyValueFactory<>("i"));
//        taskDateCol.setCellValueFactory(new PropertyValueFactory<>("taskDate"));
//        taskStatusCol.setCellValueFactory(new PropertyValueFactory<>("taskStatus"));
//        roomNbrCol.setCellValueFactory(new PropertyValueFactory<>("roomNbr"));
//        tasksTable.getItems().addAll(tasksList);
//
//        if(tasksList.isEmpty()){
//            noRowsMsg.setVisible(true);
//        }
//
//    }
//    public void filterTasks(ActionEvent event){
//        List<String> statusList = new ArrayList<>();
//        String taskDateOrder = "";
//
//        if(OnHold.isSelected()) statusList.add("On Hold");
//        if(InProgress.isSelected()) statusList.add("In Progress");
//        if(Completed.isSelected()) statusList.add("Completed");
//
//        if(TaskDateDesc.isSelected()) {
//            taskDateOrder = "DESC";
//            TaskDateAsc.setSelected(false);
//        }
//        if(TaskDateAsc.isSelected()) {
//            taskDateOrder = "ASC";
//            TaskDateDesc.setSelected(false);
//        }
//
//        LocalDate taskDate__ = taskDatePicker.getValue();
//        String taskDate="";
//        if (taskDate__ != null){
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Adjust the pattern as needed
//            taskDate = taskDate__.format(formatter);
//        }
//
//        loadDataOnTaskTable(statusList, taskDateOrder, taskDate);
//    }
//    public void confirmAction(ActionEvent event){
//        completedStatusError.setVisible(false);
//        inProgressStatusError.setVisible(false);
//
//        int taskId = (int) (tasksTable.getSelectionModel().getSelectedItem().getTaskId());
//        TaskDao.update("status",newTasKStatus,"taskId",taskId);
//        if(newTasKStatus.equals("Completed")){//the room is available
//        int roomId = (int) (tasksTable.getSelectionModel().getSelectedItem().getRoomId());
//            RoomDao.update("status","Available","roomId",roomId);
//        }
//        newTasKStatus = "";
//        confirmActionPane.setVisible(false);
//        actionPane.setVisible(true);
//
//        statusUpdatedMsg.setVisible(true);
//        hideMsg(statusUpdatedMsg,4);
//
//        loadDataOnTaskTable(new ArrayList<>(),"","");
//    }
//    public void displayConfirmCompletedActionPane(ActionEvent event){
//        completedStatusError.setVisible(false);
//        inProgressStatusError.setVisible(false);
//        rowSelectedError.setVisible(false);
//
//        if(tasksTable.getSelectionModel().getSelectedItem() == null){
//            rowSelectedError.setVisible(true);
//            return;
//        }
//        if(tasksTable.getSelectionModel().getSelectedItem().getTaskStatus().equals("Completed") || tasksTable.getSelectionModel().getSelectedItem().getTaskStatus().equals("On Hold") ){
//            completedStatusError.setVisible(true);
//            return;
//        }
//        actionPane.setVisible(false);
//        confirmActionPane.setVisible(true);
//        confirmActionMsg.setText("Change task Status from In Progress to Completed for room " + tasksTable.getSelectionModel().getSelectedItem().getRoomNbr());
//        newTasKStatus = "Completed";
//    }
//    public void displayConfirmInProgressActionPane(ActionEvent event){
//        completedStatusError.setVisible(false);
//        inProgressStatusError.setVisible(false);
//        rowSelectedError.setVisible(false);
//
//        if(tasksTable.getSelectionModel().getSelectedItem() == null){
//            rowSelectedError.setVisible(true);
//            return;
//        }
//        if(tasksTable.getSelectionModel().getSelectedItem().getTaskStatus().equals("Completed") || tasksTable.getSelectionModel().getSelectedItem().getTaskStatus().equals("In Progress") ){
//            inProgressStatusError.setVisible(true);
//            return;
//        }
//
//        actionPane.setVisible(false);
//        confirmActionPane.setVisible(true);
//        confirmActionMsg.setText("Change task Status from ON HIDE to In Progress for room " + tasksTable.getSelectionModel().getSelectedItem().getRoomNbr());
//        newTasKStatus = "In Progress";
//    }
//    public void hideConfirmActionPane(ActionEvent event){
//        confirmActionPane.setVisible(false);
//        actionPane.setVisible(true);
//    }
//    //-----------------------------------------------------------------------------------
//    public void hideMsg(Label msg, double time){
//        Duration duration = Duration.seconds(time);
//        Timeline timeline = new Timeline(new KeyFrame(duration, e -> msg.setVisible(false)));
//        timeline.setCycleCount(1);
//        timeline.play();
//    }
    public void logout(ActionEvent event){
        EmployeeManager.getInstance().setManager(new Employee("","","","","","",0,"",""));
        HelloApplication.stage.close();
    }

}
