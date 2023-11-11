package com.example.hotelmanagement.scenes;

import com.example.hotelmanagement.config.PathConfig;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public class Welcome {
    public Welcome(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/welcome-view.fxml"));
        Scene scene = new Scene(root);

        String cssFile = String.valueOf(new URL(PathConfig.RESSOURCES_ABS_PATH + "css/welcome.css"));
        String cssFile2 = String.valueOf(new URL(PathConfig.RESSOURCES_ABS_PATH + "css/general.css"));
        scene.getStylesheets().add(cssFile);
        scene.getStylesheets().add(cssFile2);

        primaryStage.initStyle(StageStyle.TRANSPARENT);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
