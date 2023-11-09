package com.example.hotelmanagement;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Welcome {
    public Welcome(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("views/welcome-view.fxml"));
        Scene scene = new Scene(root);

        String cssFile = String.valueOf(this.getClass().getResource("css/welcome.css"));
        scene.getStylesheets().add(cssFile);

        primaryStage.initStyle(StageStyle.TRANSPARENT);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
