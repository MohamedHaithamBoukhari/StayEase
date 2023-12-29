package com.example.hotelmanagement.controllers.employee.manager;

import com.example.hotelmanagement.beans.Room;
import com.example.hotelmanagement.beans.RoomType;
import com.example.hotelmanagement.config.Validation;
import com.example.hotelmanagement.dao.EmployeeDao;
import com.example.hotelmanagement.dao.RoomDao;
import com.example.hotelmanagement.dao.RoomTypeDao;
import com.example.hotelmanagement.daoFactory.CummonDbFcts;
import com.example.hotelmanagement.localStorage.VarsManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.*;

public class RoomActionsController implements Initializable {
    @FXML private Label roomNumberError = new Label(), roomCapacityError = new Label(), roomTypeError = new Label(), roomStatusError = new Label(), newRoomTypeError = new Label(), newPriceDayError = new Label(), editedRoomTypeError = new Label(), editedPriceDayError = new Label();
    @FXML private Spinner<Integer> roomNumberSpinner_, roomCapacitySpinner_;
    @FXML private ComboBox<String> roomTypeComboBox_, roomStatusComboBox_,editedRoomTypeComboBox_;
    @FXML private Label roomIdLabel;
    @FXML private AnchorPane newRoomTypePane, editRoomTypePane;
    @FXML private TextField newRoomTypeField_,newDescriptionField_, newPriceDayField_, editedPriceDayField_, editedDescriptionField_, editedTypeNameField_;
    List<String> roomTypesList = new ArrayList<>();

    int roomNbr, capacity;
    String type, status;
    List<String> types = new ArrayList<>();
    String[] states ={"Available", "Occupied", "Needs Cleaning", "Under Cleaning", "Needs Maintenance","Under Maintenance", "Out of Service", "Checked Out"};
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if (VarsManager.actionStarted.equals("delete")) {
            roomIdLabel.setText(String.valueOf(VarsManager.selectedRoomId));
        }else {
            if (newRoomTypePane!=null){
                newRoomTypePane.setVisible(false);
            }
            if (editRoomTypePane!=null){
                editRoomTypePane.setVisible(false);
            }
            List<Object> roomTypes = RoomTypeDao.selectAll();
            for (int i=0; i<roomTypes.size(); i++){
                RoomType room = (RoomType)roomTypes.get(i);
                types.add(room.getType());
            }

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
    //--------------------------------------------------------------------
    public void showNewRoomTypePaneBtn(ActionEvent event){
        newRoomTypeField_.setText("");
        newDescriptionField_.setText("");
        newPriceDayField_.setText("");

        newRoomTypePane.setVisible(true);
        editRoomTypePane.setVisible(false);
    }
    public void addRoomType(ActionEvent event){
        String roomType = newRoomTypeField_.getText();
        String description = newDescriptionField_.getText();
        String priceDayString = newPriceDayField_.getText();
        int priceDay = 0;
        try {
            priceDay = Integer.parseInt(priceDayString);
            if(priceDay <= 0){
                priceDay = 0;
            }
            newPriceDayError.setText("");
        } catch (NumberFormatException e) {
            newPriceDayError.setText("Invalid price");
        }

        if(roomType.isEmpty()){
            newRoomTypeError.setText("Invalid room type");
            return;
        }else {
            newRoomTypeError.setText("");
        }
        if(priceDay == 0){
            return;
        }
        RoomType newRoomType = new RoomType(roomType,description,priceDay);
        RoomTypeDao.insert(newRoomType);
        hideRoomTypePane(event);

        roomTypesList.clear();
        List<Object> roomTypes = RoomTypeDao.selectAll();
        for (int i=0; i<roomTypes.size(); i++){
            RoomType rT = (RoomType) roomTypes.get(i);
            roomTypesList.add(rT.getType());
        }
        roomTypeComboBox_.getItems().clear();
        roomTypeComboBox_.getItems().addAll(roomTypesList);
    }
    public void hideRoomTypePane(ActionEvent event){
        newRoomTypePane.setVisible(false);
    }

    public void showEditRoomTypePaneBtn(ActionEvent event){
        editedTypeNameField_.setText("");
        editedDescriptionField_.setText("");
        editedPriceDayField_.setText("");
        editedRoomTypeComboBox_.getItems().clear();

        List<String> types = new ArrayList<>();
        List<Object> roomTypes = RoomTypeDao.selectAll();
        for (int i=0; i<roomTypes.size(); i++){
            RoomType room = (RoomType)roomTypes.get(i);
            types.add(room.getType());
        }
        editedRoomTypeComboBox_.getItems().addAll(types);

        editRoomTypePane.setVisible(true);
        newRoomTypePane.setVisible(false);
    }
    public void fillFieldsWithOldValues(ActionEvent event){
        String selectedType = editedRoomTypeComboBox_.getValue();
        if(selectedType == null){
            editedRoomTypeError.setVisible(true);
        }else{
            editedRoomTypeError.setVisible(false);
            Map map = new HashMap<>();
            map.put("type", selectedType);
            RoomType roomType = (RoomType) (RoomTypeDao.select(map,"*").get(0));
            editedTypeNameField_.setText(roomType.getType());
            editedDescriptionField_.setText(roomType.getDescription());
            editedPriceDayField_.setText(String.valueOf(roomType.getPrice_day()));
        }
    }
    public void editRoomType(ActionEvent event){

        String roomType = editedTypeNameField_.getText();
        String description = editedDescriptionField_.getText();
        String priceDayString = editedPriceDayField_.getText();
        int priceDay = 0;
        try {
            priceDay = Integer.parseInt(priceDayString);
            if(priceDay <= 0){
                editedPriceDayError.setText("Invalid price");
                return;
            }
            editedPriceDayError.setText("");
        } catch (NumberFormatException e) {
            editedPriceDayError.setText("Invalid price");
            editedPriceDayError.setVisible(true);
        }

        if(roomType.isEmpty()){
            roomType = editedRoomTypeComboBox_.getValue();
        }
        if(priceDay == 0){
            editedPriceDayError.setText("Invalid price");
            editedPriceDayError.setTextFill(Color.RED);
            return;
        }


        String[] updatedColumns = {"description", "price_day", "type"};
        Object[] newColumnsValue = {description, priceDay,roomType};
        String testColumn = "type";
        Object testColumnValue = editedRoomTypeComboBox_.getValue();
        RoomTypeDao.updateColumns(updatedColumns, newColumnsValue, testColumn, testColumnValue);

        //change values of the roomType combobox after edit combobox name
        roomTypeComboBox_.getItems().clear();
        types.clear();
        List<Object> roomTypes = RoomTypeDao.selectAll();
        for (int i=0; i<roomTypes.size(); i++){
            RoomType room = (RoomType)roomTypes.get(i);
            types.add(room.getType());
        }
        roomTypeComboBox_.getItems().addAll(types);

        //update roomsType if we change the name of type
        if(roomType != editedRoomTypeComboBox_.getValue()){
            String[] updatedColumns_ = {"type"};
            Object[] newColumnsValue_ = {roomType};
            String testColumn_ = "type";
            Object testColumnValue_ = editedRoomTypeComboBox_.getValue();
            RoomDao.updateColumns(updatedColumns_, newColumnsValue_, testColumn_, testColumnValue_);

        }
        hideEditRoomTypePane(event);
    }
    public void hideEditRoomTypePane(ActionEvent event){editRoomTypePane.setVisible(false);}
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
