module com.example.hotelmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.hotelmanagement to javafx.fxml;
    exports com.example.hotelmanagement;
    exports com.example.hotelmanagement.daoFactory;
    opens com.example.hotelmanagement.daoFactory to javafx.fxml;
    exports com.example.hotelmanagement.controllers;
    opens com.example.hotelmanagement.controllers to javafx.fxml;
    exports com.example.hotelmanagement.dao;
    opens com.example.hotelmanagement.dao to javafx.fxml;
    exports com.example.hotelmanagement.beans;
    opens com.example.hotelmanagement.beans to javafx.fxml;
    exports com.example.hotelmanagement.tablesView;
    opens com.example.hotelmanagement.tablesView to javafx.fxml;
    exports com.example.hotelmanagement.scenes;
    opens com.example.hotelmanagement.scenes to javafx.fxml;
    exports com.example.hotelmanagement.scenes.customer;
    opens com.example.hotelmanagement.scenes.customer to javafx.fxml;
    exports com.example.hotelmanagement.controllers.customer;
    opens com.example.hotelmanagement.controllers.customer to javafx.fxml;
    exports com.example.hotelmanagement.scenes.admin;
    opens com.example.hotelmanagement.scenes.admin to javafx.fxml;
    exports com.example.hotelmanagement.controllers.admin;
    opens com.example.hotelmanagement.controllers.admin to javafx.fxml;
    exports com.example.hotelmanagement.scenes.manager;
    opens com.example.hotelmanagement.scenes.manager to javafx.fxml;
    exports com.example.hotelmanagement.controllers.employee;
    opens com.example.hotelmanagement.controllers.employee to javafx.fxml;
    exports com.example.hotelmanagement.controllers.employee.manager;
    opens com.example.hotelmanagement.controllers.employee.manager to javafx.fxml;
    exports com.example.hotelmanagement.controllers.employee.cleaner;
    opens com.example.hotelmanagement.controllers.employee.cleaner to javafx.fxml;
    exports com.example.hotelmanagement.controllers.employee.maintenanceStaff;
    opens com.example.hotelmanagement.controllers.employee.maintenanceStaff to javafx.fxml;
    exports com.example.hotelmanagement.controllers.employee.otherPosition;
    opens com.example.hotelmanagement.controllers.employee.otherPosition to javafx.fxml;


}