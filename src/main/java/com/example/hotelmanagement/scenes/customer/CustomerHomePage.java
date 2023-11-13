package com.example.hotelmanagement.scenes.customer;

import com.example.hotelmanagement.config.PathConfig;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public class CustomerHomePage {
    public CustomerHomePage(Stage primaryStage){
        Parent root = null;
        try {
            root = FXMLLoader.load(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/customer/customerHomePage-view.fxml"));
            Scene scene = new Scene(root);

            String cssFile = String.valueOf(new URL(PathConfig.RESSOURCES_ABS_PATH + "css/customer/customerHomePage.css"));
            scene.getStylesheets().add(cssFile);

            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
