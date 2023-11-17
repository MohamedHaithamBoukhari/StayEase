package com.example.hotelmanagement.controllers.admin;

import com.example.hotelmanagement.beans.Employee;
import com.example.hotelmanagement.config.Validation;
import com.example.hotelmanagement.dao.EmployeeDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class NewEmployeeController{
    @FXML
    private Label fullNameError = new Label(), cinError = new Label(), phoneError = new Label(), emailAddressError = new Label(), passwordError = new Label(), positionError = new Label(), salaryError = new Label(), workingHoursError = new Label(), workingDaysError = new Label();
    @FXML
    TextField fullNameField_, cinField_, phoneField_, emailAddressField_, passwordField_, positionField_, salaryField_, workingHoursField_, workingDaysField_;

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

        if(verifyFields(event,fullName, cin, phone, emailAddress, password, position, salary, workingHours, workingDays)){
//            String[] updatedColumns = {"fullName", "cin", "phone", "emailAddress", "password", "position", "salary", "workingHours", "workingDays"};
//            Object[] newColumnsValue = {fullName, cin, phone, emailAddress, password, position, salary, workingHours, workingDays};
//            String testColumn = "customerId";
//            Object testColumnValue = CustomerManager.getInstance().getCustomer().getCustomerId();
//
            Employee newEmployee = new Employee(fullName, cin, phone, emailAddress, password, position, Integer.parseInt(salary), workingHours, workingDays);
            EmployeeDao.insert(newEmployee);
            HomePageController.empAdded = true;
            closeStage(event);

        }
    }
    public boolean verifyFields(ActionEvent event, String fullName, String cin, String phone, String emailAddress, String password, String salary,String position, String workingHours, String workingDays){
        boolean verified = false;

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

        if (!Validation.isValidCIN(emailAddress)) {
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
    }

}
