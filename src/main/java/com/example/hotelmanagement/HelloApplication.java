package com.example.hotelmanagement;

import com.example.hotelmanagement.config.PathConfig;
import com.example.hotelmanagement.daoFactory.DatabaseUpdater;
import com.example.hotelmanagement.scenes.Welcome;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    public static Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        new DatabaseUpdater(); //update database automatically before that the application launchs
        HelloApplication.stage = stage;
        Image icon = new Image(PathConfig.RESSOURCES_ABS_PATH +"images/appIcon.png");
        stage.getIcons().add(icon);
        Welcome welcomePane = new Welcome(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}