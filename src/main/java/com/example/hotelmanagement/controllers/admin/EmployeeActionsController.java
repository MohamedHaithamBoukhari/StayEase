package com.example.hotelmanagement.controllers.admin;

import com.example.hotelmanagement.beans.Employee;
import com.example.hotelmanagement.beans.Room;
import com.example.hotelmanagement.beans.RoomType;
import com.example.hotelmanagement.config.Validation;
import com.example.hotelmanagement.dao.EmployeeDao;
import com.example.hotelmanagement.dao.RoomDao;
import com.example.hotelmanagement.dao.RoomTypeDao;
import com.example.hotelmanagement.daoFactory.CummonDbFcts;
import com.example.hotelmanagement.localStorage.VarsManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class EmployeeActionsController implements Initializable {
    @FXML
    private Label fullNameError = new Label(), cinError = new Label(), phoneError = new Label(), emailAddressError = new Label(), passwordError = new Label(), positionError = new Label(), salaryError = new Label(), workingHoursError = new Label(), workingDaysError = new Label();
    @FXML
    TextField fullNameField_, cinField_, phoneField_, emailAddressField_, passwordField_, positionField_, salaryField_, workingHoursField_, workingDaysField_;
    @FXML private Label empIdLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if (VarsManager.actionStarted.equals("delete")) {
            empIdLabel.setText(String.valueOf(VarsManager.selectedEmpId));
        }else if(VarsManager.actionStarted.equals("add")){

        }else {
            Map<String, Object> map = new HashMap<>();
            map.put("employeeId", VarsManager.selectedEmpId);
            Employee employeeSelected = (Employee)EmployeeDao.select(map,"*").get(0);
            fullNameField_.setText(employeeSelected.getFullName());
            cinField_.setText(employeeSelected.getCin());
            phoneField_.setText(employeeSelected.getPhone());
            emailAddressField_.setText(employeeSelected.getEmail());
            passwordField_.setText(employeeSelected.getPassword());
            positionField_.setText(employeeSelected.getPosition());
            salaryField_.setText(String.valueOf(employeeSelected.getSalary()));
            workingHoursField_.setText(employeeSelected.getWorkingHours());
            workingDaysField_.setText(employeeSelected.getWorkingDays());
        }
    }
    //----------------- Actions-------------------------------------
    public void addEmployee(ActionEvent event){
        String fullName = fullNameField_.getText();
        String cin = cinField_.getText();
        String phone = phoneField_.getText();
        String emailAddress = emailAddressField_.getText();
        String password = passwordField_.getText();
        String position = positionField_.getText();
        String salary = salaryField_.getText();
        String workingHours = workingHoursField_.getText();
        String workingDays = workingDaysField_.getText();

        if(verifyFields(event,fullName, cin, phone, emailAddress, password, salary, position, workingHours, workingDays)){
            Employee newEmployee = new Employee(fullName, cin, phone, emailAddress, password, position, Integer.parseInt(salary), workingHours, workingDays);
            EmployeeDao.insert(newEmployee);
            VarsManager.actionCompleted = "add";
            closeStage(event);
        }
    }
    public void editEmployee(ActionEvent event){
        String fullName = fullNameField_.getText();
        String cin = cinField_.getText();
        String phone = phoneField_.getText();
        String emailAddress = emailAddressField_.getText();
        String password = passwordField_.getText();
        String position = positionField_.getText();
        String salary = salaryField_.getText();
        String workingHours = workingHoursField_.getText();
        String workingDays = workingDaysField_.getText();

        if(verifyFields(event,fullName, cin, phone, emailAddress, password, position, salary, workingHours, workingDays)){
            String[] updatedColumns = {"fullName", "cin", "phone", "emailAddress", "password", "position", "salary", "workingHours", "workingDays"};
            Object[] newColumnsValue = {fullName, cin, phone, emailAddress, password, position, salary, workingHours, workingDays};
            String testColumn = "employeeId";
            Object testColumnValue = VarsManager.selectedEmpId;
            int i = EmployeeDao.updateColumns(updatedColumns, newColumnsValue, testColumn, testColumnValue);
            if(i == 1){
                VarsManager.actionCompleted = "update";
                closeStage(event);
            }
        }
    }
    public void deleteEmployee(ActionEvent event){
        String testColumn = "employeeId";
        Object testColumnValue = VarsManager.selectedEmpId;
        int i = EmployeeDao.delete(testColumn, testColumnValue);
        if (i == 1) {
            VarsManager.actionCompleted = "delete";
            closeStage(event);
        }
    }
    //----------------- verification-------------------------------------
    public boolean verifyFields(ActionEvent event, String fullName, String cin, String phone, String emailAddress, String password, String salary, String position, String workingHours, String workingDays){

        fullNameError.setText("");
        cinError.setText("");
        phoneError.setText("");
        emailAddressError.setText("");
        passwordError.setText("");
        positionError.setText("");
        salaryError.setText("");
        workingHoursError.setText("");
        workingDaysError.setText("");

        int i = 0;
        if (!Validation.isValidFullName(fullName)) {
            fullNameError.setText("-Enter a valid full name");
            i++;
        }

        if (!Validation.isValidCIN(cin)) {
            cinError.setText("-Enter a valid Customer Identification Number");
            i++;
        }

        if (!Validation.isValidPhone(phone)) {
            phoneError.setText("-Enter a valid phone number");
            i++;
        }

        if (!Validation.isValidEmail(emailAddress)) {
            emailAddressError.setText("-Enter a valid email address");
            i++;
        }

        if (!Validation.isValidPassword(password)) {
            passwordError.setText("-Enter a valid password");
            i++;
        }

        if (!Validation.isValidSalary(salary)) {
            salaryError.setText("-Enter a valid salary");
            i++;
        }

        if (!Validation.isValidPosition(position)) {
            positionError.setText("-Enter a valid position");
            i++;
        }

        if (!Validation.isValidWorkingHours(workingHours)) {
            workingHoursError.setText("iiiiiiiiiiiiiiiiiiiiiiii");
            i++;
        }

        if (!Validation.isValidWorkingDays(workingDays)) {
            workingDaysError.setText("iiiiiiiiiiiiiiiiiiiiiiii");
            i++;
        }

        return i == 0;
    }

    public void closeStage(ActionEvent event){
        HomePageController.childStage.close();
        VarsManager.actionStarted = "";
    }

}