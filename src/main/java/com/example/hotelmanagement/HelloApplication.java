package com.example.hotelmanagement;

import com.example.hotelmanagement.daoFactory.DatabaseUpdater;
import com.example.hotelmanagement.scenes.Welcome;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        new DatabaseUpdater(); //update database automaticly before upplication lunchs
        HelloApplication.stage = stage;
        Welcome welcomePane = new Welcome(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}