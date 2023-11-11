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
    exports com.example.hotelmanagement.scenes;
    opens com.example.hotelmanagement.scenes to javafx.fxml;


}