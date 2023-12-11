package com.example.hotelmanagement.controllers.customer;

import com.example.hotelmanagement.HelloApplication;
import com.example.hotelmanagement.beans.*;
import com.example.hotelmanagement.config.PathConfig;
import com.example.hotelmanagement.dao.InvoiceDao;
import com.example.hotelmanagement.dao.ReservationDao;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class ReservationActionsController implements Initializable {

    public static Stage childStage;
    private Scene scene;


    @FXML private DatePicker checkOutDate_, checkInDate_;
    @FXML private TextField priceField, capacityField;
    @FXML private ComboBox typeChoice;
    @FXML private Label datesError;
    @FXML private Label noRowsMsg, rowSelectedError, selectDatesMsg;
    @FXML private TableView<RoomsTableView> availableRoomsTable;
    @FXML private TableColumn<RoomsTableView, Object> idCol, roomNumberCol, typeCol, capacityCol, price_dayCol, totalPriceCol;
    @FXML private Label CurrentDateField, ReservationIdField, EmailField, RoomNbrField, RoomTypeField, RoomCapacityField, CheckInDateField, CheckOutDateField, DurationField, AmountField;
    @FXML private Label reservInfoTodelete;
    Map<String, Object> confirmationValMap = new HashMap<>();
    private boolean validDates = false;
    List<String> types = new ArrayList<>();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if (VarsManager.actionStarted.equals("add")) {
            noRowsMsg.setVisible(false);
            rowSelectedError.setVisible(false);
            datesError.setVisible(false);
            List<Object> roomTypes = RoomTypeDao.selectAll();
            System.out.println(roomTypes);
            for (int i=0; i<roomTypes.size(); i++){
                RoomType room = (RoomType)roomTypes.get(i);
                types.add(room.getType());
            }
            typeChoice.getItems().addAll(types);
        }else if (VarsManager.actionStarted.equals("confirmReservation")){
            String customerEmail = CustomerManager.getInstance().getCustomer().getEmail();
            int availableRoomId = VarsManager.selectedAvailableRoomId;

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String currentDate = LocalDate.now().format(formatter);
            String checkInDate = VarsManager.confirmCheckIn.format(formatter);
            String checkOutDate = VarsManager.confirmCheckOut.format(formatter);

            int duration = (int) ChronoUnit.DAYS.between(VarsManager.confirmCheckIn, VarsManager.confirmCheckOut);
            Map map = new HashMap<>();
            map.put("roomId", availableRoomId);
            Room selectedRoom = (Room) (RoomDao.select(map, "*").get(0));

            Map priceMap = new HashMap<>();
            priceMap.put("type",selectedRoom.getType());
            int typePrice = ((RoomType)(RoomTypeDao.select(priceMap,"*").get(0))).getPrice_day();
            int amount = (typePrice*(1+selectedRoom.getCapacity()) - (selectedRoom.getCapacity()*40)) * duration;

            CurrentDateField.setText("-- "+currentDate+" --");
            ReservationIdField.setText(String.valueOf(CummonDbFcts.selectMaxVal("reservation",  "reservationId")+1));
            EmailField.setText(customerEmail);
            RoomNbrField.setText(String.valueOf(selectedRoom.getNumRoom()));
            RoomTypeField.setText(selectedRoom.getType());
            RoomCapacityField.setText(String.valueOf(selectedRoom.getCapacity()));
            CheckInDateField.setText(checkInDate);
            CheckOutDateField.setText(checkOutDate);
            DurationField.setText(String.valueOf(duration + " Day"));
            AmountField.setText(String.valueOf(amount + " Dh"));
        } else if(VarsManager.actionStarted.equals("delete")){
            Map map = new HashMap<>();
            map.put("reservationId", VarsManager.selectedResId);
            Reservation reservation = ((Reservation)(ReservationDao.select(map,"*").get(0)));
            reservInfoTodelete.setText("from " + reservation.getCheck_inDate() + " to " + reservation.getCheck_outDate() + " ?");
        }
    }
    //-------------------------------------------------------------------------------------------
    public void loadDataOnAvailableRoomTable(String type, String priceDay, String capacity, LocalDate checkIn, LocalDate checkOut){
        noRowsMsg.setVisible(false);
        selectDatesMsg.setVisible(false);
        if(validDates){
            List<RoomsTableView> roomsList = new ArrayList<>();
            availableRoomsTable.getItems().clear();
            RoomsTableView.setNBR(1);
            String query = "SELECT r.roomId, r.numRoom, r.type, r.capacity, r.status " +
                    "FROM room r " +
                    "LEFT JOIN Reservation res " +
                    "ON r.roomId = res.roomId " +
                    "AND res.status IN ('Upcoming', 'In Progress', 'Cancelled') " +
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
                    System.out.println("from if loadData...  roomrow = "+ roomRow);
                    roomsList.add(roomRow);
                }
            }else{
                if(!type.isEmpty()){
                    query += "AND r.type = '" + type +"' ";
                }
                if(!capacity.isEmpty()){
                    query += "AND r.capacity <= '" + capacity +"' ";
                }
                if(!priceDay.isEmpty()){
                    query += "AND " + "(SELECT price_day FROM roomType rT WHERE rT.type = r.type)" +" <= " + priceDay;
                }

                List<Object[]> roomsdetails = CummonDbFcts.querySelect(query, colToSelect);
                for (Object[] row : roomsdetails) {
                    RoomsTableView roomRow = new RoomsTableView(row[0],row[1],row[2],row[3],row[4],null,duration);
                    System.out.println("from else loadData... with filters  roomrow = "+ roomRow);
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
    }
    public void filterAvailableRooms(ActionEvent event){
        Object type = typeChoice.getValue();
        String priceDay = priceField.getText();
        String capacity = capacityField.getText();
        if(type == null){
            type = "";
        }
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
        loadDataOnAvailableRoomTable((String)type, priceDay, capacity, VarsManager.confirmCheckIn, VarsManager.confirmCheckOut);
    }
    public void searchRoom(ActionEvent event){
        datesError.setVisible(false);
        LocalDate checkIn=null;
        LocalDate checkOut=null;
        int i=1;
        try{//to know if the format of dates is valid
            checkIn = checkInDate_.getValue();
            checkOut = checkOutDate_.getValue();
            VarsManager.confirmCheckIn =  checkIn;
            VarsManager.confirmCheckOut =  checkOut;

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
            validDates = true;
            loadDataOnAvailableRoomTable("","","",checkIn, checkOut);
        }else {
            validDates = false;
        }
    }
    public void bookRoomWindow(ActionEvent event) throws IOException {

        rowSelectedError.setVisible(false);
        if(availableRoomsTable.getSelectionModel().getSelectedItem() == null){
            rowSelectedError.setVisible(true);
            return;
        }

        VarsManager.actionStarted = "confirmReservation";
        VarsManager.selectedAvailableRoomId = (int) availableRoomsTable.getSelectionModel().getSelectedItem().getRoomId();

        FXMLLoader loader = new FXMLLoader(new URL(PathConfig.RESSOURCES_ABS_PATH + "views/customer/confirmReservation-view.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);
        childStage = new Stage();
        childStage.setScene(scene);

        childStage.initStyle(StageStyle.TRANSPARENT);
        childStage.setScene(scene);

        Stage parentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        parentStage.setResizable(false);
        childStage.initOwner(parentStage);
        childStage.initModality(Modality.WINDOW_MODAL);

        childStage.showAndWait();
    }
    public void confirmReservation(ActionEvent event){
        int customerId = CustomerManager.getInstance().getCustomer().getCustomerId();
        int availableRoomId = VarsManager.selectedAvailableRoomId;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentDate = LocalDate.now().format(formatter);
        String checkInDate = VarsManager.confirmCheckIn.format(formatter);
        String checkOutDate = VarsManager.confirmCheckOut.format(formatter);

        int duration = (int) ChronoUnit.DAYS.between(VarsManager.confirmCheckIn, VarsManager.confirmCheckOut);
        Map map = new HashMap<>();
        map.put("roomId", availableRoomId);
        Room selectedRoom = (Room) (RoomDao.select(map, "*").get(0));


        //insert reservation
        Reservation reservation = new Reservation(currentDate,customerId,selectedRoom.getRoomId(),checkInDate,checkOutDate,"Upcoming");
        ReservationDao.insert(reservation);
        //update room status
        String[] updatedColumns = {"status"};
        Object[] newColumnsValue = {"Occupied"};
        String testColumn = "roomId";
        Object testColumnValue = VarsManager.selectedAvailableRoomId;
        RoomDao.updateColumns(updatedColumns, newColumnsValue, testColumn, testColumnValue);
        //generate invoice
        Map priceMap = new HashMap<>();
        priceMap.put("type",selectedRoom.getType());
        int typePrice = ((RoomType)(RoomTypeDao.select(priceMap,"*").get(0))).getPrice_day();
        int amount = (typePrice*(1+selectedRoom.getCapacity()) - (selectedRoom.getCapacity()*40)) * duration;
        Invoice invoice = new Invoice(customerId,CummonDbFcts.selectMaxVal("reservation","reservationId"),"Unpaid",currentDate,amount);
        InvoiceDao.insert(invoice);

        VarsManager.actionCompleted = "confirmReservation";
        ReservationActionsController.childStage.close();
        CustomerHomePageController.childStage.close();
    }
    public void cancelReservation(ActionEvent event){
        ReservationActionsController.childStage.close();
    }
    public void deleteReservation(ActionEvent event){
        String[] updatedColumns = {"status"};
        Object[] newColumnsValue = {"Cancelled"};
        String testColumn = "reservationId";
        Object testColumnValue = VarsManager.selectedResId;
        ReservationDao.updateColumns(updatedColumns,newColumnsValue,testColumn,testColumnValue);
        VarsManager.actionCompleted = "delete";
        CustomerHomePageController.childStage.close();
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
