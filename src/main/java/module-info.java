module com.example.hotelmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.hotelmanagement to javafx.fxml;
    exports com.example.hotelmanagement;
    exports com.example.hotelmanagement.db;
    opens com.example.hotelmanagement.db to javafx.fxml;
    exports com.example.hotelmanagement.controllers;
    opens com.example.hotelmanagement.controllers to javafx.fxml;


}