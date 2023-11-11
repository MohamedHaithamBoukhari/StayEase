package com.example.hotelmanagement;

import com.example.hotelmanagement.scenes.Welcome;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        HelloApplication.stage = stage;
//        RoomTypeDao.insert(new RoomType("aaa","e",1));
//        RoomTypeDao.update("description","desc", "typeId", 1);
//        RoomTypeDao.delete("typeId",11);
        Welcome welcomePane = new Welcome(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}