package com.example.hotelmanagement.controllers.manager;

import com.example.hotelmanagement.beans.Customer;
import com.example.hotelmanagement.beans.Room;
import com.example.hotelmanagement.beans.RoomType;
import com.example.hotelmanagement.config.Validation;
import com.example.hotelmanagement.controllers.customer.CustomerHomePageController;
import com.example.hotelmanagement.dao.CustomerDao;
import com.example.hotelmanagement.dao.RoomDao;
import com.example.hotelmanagement.dao.RoomTypeDao;
import com.example.hotelmanagement.daoFactory.CummonDbFcts;
import com.example.hotelmanagement.localStorage.CustomerManager;
import com.example.hotelmanagement.localStorage.VarsManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.*;

public class RoomActionsController implements Initializable {
    @FXML private Label roomNumberError = new Label(), roomCapacityError = new Label(), roomTypeError = new Label(), roomStatusError = new Label();
    @FXML private Spinner<Integer> roomNumberSpinner_, roomCapacitySpinner_;
    @FXML private ComboBox<String> roomTypeComboBox_, roomStatusComboBox_;
    @FXML private Label roomIdLabel;
    int roomNbr, capacity;
    String type, status;
    List<String> types = new ArrayList<>();
    String[] states ={"Available", "Occupied", "Needs Cleaning", "Under Cleaning", "Needs Maintenance","Under Maintenance", "Out of Service", "Checked Out"};
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if (VarsManager.actionStarted.equals("delete")) {
            roomIdLabel.setText(String.valueOf(VarsManager.selectedRoomId));
        }else {
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

            if(VarsManager.actionStarted.equals("update")){
                Map<String, Object> map = new HashMap<>();
                map.put("roomId", VarsManager.selectedRoomId);
                Object room = RoomDao.select(map, "*").get(0);
                System.out.println(room);

                roomNumberSpinner_.getValueFactory().setValue(((Room)room).getNumRoom());
                roomCapacitySpinner_.getValueFactory().setValue(((Room)room).getCapacity());
                roomTypeComboBox_.getSelectionModel().select(((Room) room).getType());
                roomStatusComboBox_.getSelectionModel().select(((Room) room).getStatus());
            }
        }
    }
    //----------------- Actions-------------------------------------
    public void addRoom(ActionEvent event){
        roomNbr = roomNumberSpinner_.getValue();
        capacity = roomCapacitySpinner_.getValue();
        type = roomTypeComboBox_.getValue();
        status = roomStatusComboBox_.getValue();

        if(verifyFields(event,roomNbr, capacity, type, status, "add")){

            Room room = new Room(roomNbr,type,capacity,status);
            RoomDao.insert(room);
            VarsManager.actionCompleted = "add";
            closeStage(event);
        }
    }
    public void editRoom(ActionEvent event){
        roomNbr = roomNumberSpinner_.getValue();
        capacity = roomCapacitySpinner_.getValue();
        type = roomTypeComboBox_.getValue();
        status = roomStatusComboBox_.getValue();

        if(verifyFields(event,roomNbr, capacity, type, status, "update")){
            String[] updatedColumns = {"roomNbr", "capacity", "type", "status"};
            Object[] newColumnsValue = {roomNbr, capacity, type, status};
            String testColumn = "roomId";
            Object testColumnValue = VarsManager.selectedRoomId;

            int i = RoomDao.updateColumns(updatedColumns, newColumnsValue, testColumn, testColumnValue);
            if(i == 1){
                VarsManager.actionCompleted = "update";
                closeStage(event);

            }
        }

    }
    public void deleteRoom(ActionEvent event){
        String testColumn = "roomId";
        Object testColumnValue = VarsManager.selectedRoomId;
        int i = RoomDao.delete(testColumn, testColumnValue);
        if (i == 1) {
            VarsManager.actionCompleted = "delete";
            closeStage(event);
        }
    }
    //----------------- verification-------------------------------------
    public boolean verifyFields(ActionEvent event, int roomNbr, int capacity, String type, String status, String action){
        int i = 0;
        roomNumberError.setText("");
        roomCapacityError.setText("");
        roomStatusError.setText("");
        roomTypeError.setText("");
        if (!Validation.isValidRoomNbr(roomNbr, action)) {
            roomNumberError.setText("-Room number already exist");
            i++;
        }

        if (!Validation.isValidCapacity(capacity)) {
            roomCapacityError.setText("-Enter capacity");
            i++;
        }

        if (!Validation.isValidRoomType(type)) {
            roomTypeError.setText("-Chise room type");
            i++;
        }

        if (!Validation.isValidRoomStatus(status)) {
            roomStatusError.setText("-Choise roome status");
            i++;
        }

        return i == 0;
    }
    //--------------------------------------------------------------------------------
    public void closeStage(ActionEvent event){
        HomePageController.childStage.close();
        VarsManager.actionStarted = "";
    }
}
