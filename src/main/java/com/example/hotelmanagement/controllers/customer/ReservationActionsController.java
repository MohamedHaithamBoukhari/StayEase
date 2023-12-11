package com.example.hotelmanagement.controllers.customer;

import com.example.hotelmanagement.HelloApplication;
import com.example.hotelmanagement.beans.Customer;
import com.example.hotelmanagement.beans.RoomType;
import com.example.hotelmanagement.dao.RoomDao;
import com.example.hotelmanagement.dao.RoomTypeDao;
import com.example.hotelmanagement.daoFactory.CummonDbFcts;
import com.example.hotelmanagement.localStorage.CustomerManager;
import com.example.hotelmanagement.localStorage.VarsManager;
import com.example.hotelmanagement.tablesView.RoomsTableView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import org.w3c.dom.Text;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ReservationActionsController implements Initializable {

    @FXML private DatePicker checkOutDate_, checkInDate_;
    @FXML private TextField priceField, capacityField;
    @FXML private ComboBox typeChoice;
    @FXML private Label datesError;
    @FXML private Label noRowsMsg, rowSelectedError, selectDatesMsg;
    @FXML private TableView<RoomsTableView> availableRoomsTable;
    @FXML private TableColumn<RoomsTableView, Object> idCol, roomNumberCol, typeCol, capacityCol, price_dayCol, totalPriceCol;

    List<String> types = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        datesError.setVisible(false);
        noRowsMsg.setVisible(false);
        rowSelectedError.setVisible(false);

        if (VarsManager.actionStarted.equals("add")) {
            List<Object> roomTypes = RoomTypeDao.selectAll();
            System.out.println(roomTypes);
            for (int i=0; i<roomTypes.size(); i++){
                RoomType room = (RoomType)roomTypes.get(i);
                types.add(room.getType());
            }
            typeChoice.getItems().addAll(types);

        }
    }
    //-------------------------------------------------------------------------------------------
    public void filterAvailableRooms(ActionEvent event){
        String type = (String)typeChoice.getValue();
        String priceDay = priceField.getText();
        String capacity = capacityField.getText();
        try { //check if the price is nbr
            double price = Double.parseDouble(priceDay);
            if (price < 0) {
                price = price;
            }
        } catch (NumberFormatException e) {
            priceDay = "";
        }
        try { //check if the capacity is nbr
            double capa = Double.parseDouble(capacity);
            if (capa < 0) {
                capa = capa;
            }
        } catch (NumberFormatException e) {
            capacity = "";
        }
//        loadDataOnAvailableRoomTable(type, priceDay, capacity, checkIn, ChckOut);
    }

    public void loadDataOnAvailableRoomTable(String type, String priceDay, String capacity, LocalDate checkIn, LocalDate checkOut){
        noRowsMsg.setVisible(false);
        selectDatesMsg.setVisible(false);
        List<RoomsTableView> roomsList = new ArrayList<>();
        availableRoomsTable.getItems().clear();
        RoomsTableView.setNBR(1);
        String query = "SELECT r.roomId, r.numRoom, r.type, r.capacity, r.status " +
                "FROM room r " +
                "LEFT JOIN Reservation res " +
                    "ON r.roomId = res.roomId " +
                    "AND res.status IN ('Upcoming', 'In Progress') " +
                    "AND ( " +
                        "("+checkIn+" >= res.check_inDate AND "+checkIn+" < res.check_outDate) " +
                        "OR ("+checkOut+" > res.check_inDate AND "+checkOut+" <= res.check_outDate) " +
                        "OR ("+checkIn+" <= res.check_inDate AND "+checkOut+" >= res.check_outDate) " +
                    " ) " +
                "WHERE r.status = 'available' " +
                "AND res.reservationId IS NULL ";
        List<String> colToSelect =  new ArrayList<String>(List.of("r.roomId","r.numRoom","r.type","r.capacity","r.status"));
        long duration = ChronoUnit.DAYS.between(checkIn, checkOut);

        if(type.isEmpty() && priceDay.isEmpty() && capacity.isEmpty()){
            List<Object[]> roomsdetails = CummonDbFcts.querySelect(query, colToSelect);
            for (Object[] row : roomsdetails) {
                RoomsTableView roomRow = new RoomsTableView(row[0],row[1],row[2],row[3],row[4],null,duration);
                System.out.println(roomRow);
                roomsList.add(roomRow);
            }
        }else{
            if(!type.isEmpty()){
                query += "AND r.type = '" + type +"' ";
            }
            if(!capacity.isEmpty()){
                query += "AND r.capacity = '" + capacity +"' ";
            }
            if(!priceDay.isEmpty()){
                query += "AND " + "(SELECT price_day FROM roomType rT WHERE rT.type = r.type)" +" <= " + priceDay;
            }

            List<Object[]> roomsdetails = CummonDbFcts.querySelect(query, colToSelect);
            for (Object[] row : roomsdetails) {
                RoomsTableView roomRow = new RoomsTableView(row[0],row[1],row[2],row[3],row[4],null,duration);
                System.out.println(roomRow);
                roomsList.add(roomRow);
            }
        }

        idCol.setCellValueFactory(new PropertyValueFactory<>("i"));
        roomNumberCol.setCellValueFactory(new PropertyValueFactory<>("numRoom"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        capacityCol.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        price_dayCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        totalPriceCol.setCellValueFactory(new PropertyValueFactory<>("reservationPrice"));
        availableRoomsTable.getItems().addAll(roomsList);
        if(roomsList.isEmpty()){
            noRowsMsg.setVisible(true);
        }
    }

    public void searchRoom(ActionEvent event){
        datesError.setVisible(true);
        LocalDate checkIn=null;
        LocalDate checkOut=null;
        int i=1;
        try{//to know if the format of dates is valid
             checkIn = checkInDate_.getValue();
             checkOut = checkOutDate_.getValue();

            if (checkIn.isBefore(LocalDate.now())){
                datesError.setText("Check-in date cannot be in the past!");
                datesError.setVisible(true);
            }else if (checkIn.isAfter(checkOut) || checkIn.isEqual(checkOut)){
                datesError.setText("Check-in date must be before Check-out date!");
                datesError.setVisible(true);
            }else {
                datesError.setVisible(false);
                System.out.println(checkIn + "---------"+checkOut);
            }
        }catch (Exception e) {
            datesError.setText("Enter a valid check in and check out date");
            datesError.setVisible(true);
            i=0;
        }
        if(i==1){
                loadDataOnAvailableRoomTable("","","",checkIn, checkOut);

        }

    }
    //-------------------------------------------------------------------------------------------
    public void hideMsg(Label msg,double time){
        Duration duration = Duration.seconds(time);
        Timeline timeline = new Timeline(new KeyFrame(duration, e -> msg.setVisible(false)));
        timeline.setCycleCount(1);
        timeline.play();
    }
        public void closeStage(ActionEvent event){
            CustomerHomePageController.childStage.close();
        }

}
