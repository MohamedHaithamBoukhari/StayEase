package com.example.hotelmanagement.controllers.manager;

import com.example.hotelmanagement.beans.Employee;
import com.example.hotelmanagement.beans.Room;
import com.example.hotelmanagement.beans.RoomType;
import com.example.hotelmanagement.config.Validation;
import com.example.hotelmanagement.controllers.manager.HomePageController;
import com.example.hotelmanagement.dao.EmployeeDao;
import com.example.hotelmanagement.dao.RoomDao;
import com.example.hotelmanagement.dao.RoomTypeDao;
import com.example.hotelmanagement.daoFactory.CummonDbFcts;
import com.example.hotelmanagement.localStorage.ManagerManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.w3c.dom.events.MouseEvent;

import java.net.URL;
import java.util.*;

public class NewRoomController implements Initializable {
    @FXML private Label roomNumberError = new Label(), roomCapacityError = new Label(), roomTypeError = new Label(), roomStatusError = new Label();
    @FXML private Spinner<Integer> roomNumberSpinner_, roomCapacitySpinner_;
    @FXML private ComboBox<String> roomTypeComboBox_, roomStatusComboBox_;
    int roomNbr, capacity;
    String type, status;
    List<String> types = new ArrayList<>();
    String[] states ={"Available", "Occupied", "Under Cleaning","Maintenance","Cleaned", "Needs Maintenance", "Out of Service", "Checked Out"};
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Map<String, Object> map = new HashMap<>();
        List<Object> roomTypes = RoomTypeDao.selectAll();
        System.out.println(roomTypes);
        for (int i=0; i<roomTypes.size(); i++){
            RoomType room = (RoomType)roomTypes.get(i);
            types.add(room.getType());
        }

        System.out.println(types);
        int maxRoomNbr = CummonDbFcts.selectMaxVal(RoomDao.TABLE_NAME, "numRoom");
        SpinnerValueFactory<Integer> valueFactoryNbr = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,maxRoomNbr+1, maxRoomNbr+1);
        SpinnerValueFactory<Integer> valueFactoryCapacity = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,7);

        roomNumberSpinner_.setValueFactory(valueFactoryNbr);
        roomCapacitySpinner_.setValueFactory(valueFactoryCapacity);

        roomTypeComboBox_.getItems().addAll(types);
        roomStatusComboBox_.getItems().addAll(states);


    }

    public void addRoom(ActionEvent event){
        roomNbr = roomNumberSpinner_.getValue();
        capacity = roomCapacitySpinner_.getValue();
        type = roomTypeComboBox_.getValue();
        status = roomStatusComboBox_.getValue();

        if(verifyFields(event,roomNbr, capacity, type, status)){

            Room room = new Room(roomNbr,type,capacity,status);
            RoomDao.insert(room);
            com.example.hotelmanagement.controllers.manager.HomePageController.roomAdded = true;
            closeStage(event);
        }
    }
    //----------------- verification-------------------------------------
    boolean isValidRoomNbr(int roomNbr){
        Map<String, Object> map = new HashMap<>();
        map.put("numRoom", roomNbr);

        List<Object> rooms = RoomDao.select(map,"*");
        if(rooms.size() == 1){
            return false; //room nbr exists
        }
        return true;
    }
    boolean isValidCapacity(int capacity){
        return true;
    }
    boolean isValidRoomType(String type){
        return type != null;
    }
    boolean isValidRoomStatus(String status){
        return status != null;
    }
    public boolean verifyFields(ActionEvent event, int roomNbr, int capacity, String type, String status){
        int i = 0;
        roomNumberError.setText("");
        roomCapacityError.setText("");
        roomStatusError.setText("");
        roomTypeError.setText("");
        if (!isValidRoomNbr(roomNbr)) {
            roomNumberError.setText("-Room number already exist");
            i++;
        }

        if (!isValidCapacity(capacity)) {
            roomCapacityError.setText("-Enter capacity");
            i++;
        }

        if (!isValidRoomType(type)) {
            roomTypeError.setText("-Chise room type");
            i++;
        }

        if (!isValidRoomStatus(status)) {
            roomStatusError.setText("-Choise roome status");
            i++;
        }

        return i == 0;
    }
    //--------------------------------------------------------------------------------
    public void closeStage(ActionEvent event){
        HomePageController.childStage.close();
    }


}
