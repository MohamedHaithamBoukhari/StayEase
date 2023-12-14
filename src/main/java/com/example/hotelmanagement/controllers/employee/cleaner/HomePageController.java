package com.example.hotelmanagement.controllers.employee.cleaner;

import com.example.hotelmanagement.HelloApplication;
import com.example.hotelmanagement.beans.Employee;
import com.example.hotelmanagement.config.PathConfig;
import com.example.hotelmanagement.dao.*;
import com.example.hotelmanagement.daoFactory.CummonDbFcts;
import com.example.hotelmanagement.localStorage.EmployeeManager;
import com.example.hotelmanagement.localStorage.SwitchedPageManager;
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
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    private Stage stage;
    public static Stage childStage;
    private Scene scene;
    private Parent root;

    @FXML private PieChart pieChart;
    @FXML private Label fullnameLabel;
    @FXML private Label tasksNbr, completedTasksNbr, onHoldTasksNbr;

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
            ObservableList<PieChart.Data> pieChartData =
                    FXCollections.observableArrayList(
                            new PieChart.Data("Completed tasks", Double.parseDouble(completedTasks)),
                            new PieChart.Data("On Hold tasks", Double.parseDouble(onHoldTasks)));
            pieChartData.forEach(data ->
                    data.nameProperty().bind(
                            Bindings.concat(data.getName(), data.pieValueProperty())
                    )
            );
            pieChart.getData().addAll(pieChartData);

        } else if (currentPage.equals("Cleaning")) {
//            rowSelectedError.setVisible(false);
//            cleaningAssigningError.setVisible(false);
//            rowSelectedError.setVisible(false);
//            addedMsg.setVisible(false);
//            loadDataOnCleaningTable(new ArrayList<>(), "", "");
        }else if (currentPage.equals("Complaint")) {

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
    //-----------------------------------------------------------------------------------
    public void hideMsg(Label msg, double time){
        Duration duration = Duration.seconds(time);
        Timeline timeline = new Timeline(new KeyFrame(duration, e -> msg.setVisible(false)));
        timeline.setCycleCount(1);
        timeline.play();
    }
    public void logout(ActionEvent event){
        EmployeeManager.getInstance().setManager(new Employee("","","","","","",0,"",""));
        HelloApplication.stage.close();
    }

}
