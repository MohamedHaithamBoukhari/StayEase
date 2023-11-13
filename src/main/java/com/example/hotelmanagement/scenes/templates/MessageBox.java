package com.example.hotelmanagement.scenes.templates;

import javafx.scene.control.Alert;

public class MessageBox {
    public MessageBox(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
