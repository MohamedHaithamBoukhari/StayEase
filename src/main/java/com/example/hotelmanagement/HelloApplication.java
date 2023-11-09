package com.example.hotelmanagement;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        HelloApplication.stage = stage;
        Welcome welcomePane = new Welcome(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}