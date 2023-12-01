package com.example.hotelmanagement.controllers.admin;

import com.example.hotelmanagement.HelloApplication;
import com.example.hotelmanagement.beans.Employee;
import com.example.hotelmanagement.dao.EmployeeDao;
import com.example.hotelmanagement.dao.PositionDao;
import com.example.hotelmanagement.dao.RoomDao;
import com.example.hotelmanagement.dao.RoomTypeDao;
import com.example.hotelmanagement.daoFactory.CummonDbFcts;
import com.example.hotelmanagement.localStorage.AdminManager;
import com.example.hotelmanagement.config.PathConfig;
import com.example.hotelmanagement.localStorage.SwitchedPageManager;
import com.example.hotelmanagement.localStorage.VarsManager;
import com.example.hotelmanagement.scenes.Welcome;
import com.example.hotelmanagement.tablesView.EmployeesTableView;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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

        } else if (currentPage.equals("Employee")) {
            empAddedMsg.setVisible(false);
            empUpdatedMsg.setVisible(false);
            empDeletedMsg.setVisible(false);
            loadDataOnTable(new ArrayList<>(), "", "", "", "");
            noRowsMsg.setVisible(false);
            rowSelectedError.setVisible(false);
        } else if (currentPage.equals("Services")) {

        } else if (currentPage.equals("Feedback")) {

        } else if (currentPage.equals("Earning")) {

        } else if (currentPage.equals("Email")) {

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
    public void switchToEmail(ActionEvent event) throws IOException {
        SwitchedPageManager.getInstance().setSwitchedPage("Email");
        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/admin/Email-view.fxml"));
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
        rowSelectedError.setVisible(false);
        if(empsTable.getSelectionModel().getSelectedItem() == null){
            rowSelectedError.setVisible(true);
            return;
        }

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
//-------------------------------------------------------------------------------
    public void hideMsg(Label msg,double time){
    Duration duration = Duration.seconds(time);
    Timeline timeline = new Timeline(new KeyFrame(duration, e -> msg.setVisible(false)));
    timeline.setCycleCount(1);
    timeline.play();
}
    public void logout(ActionEvent event) throws IOException {
        AdminManager.getInstance().setAdmin(new Employee("", "", "", "", "", "", 0, "", ""));
        HelloApplication.stage.close();
    }
}
