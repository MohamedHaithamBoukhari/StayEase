package com.example.hotelmanagement.controllers.customer;

import com.example.hotelmanagement.beans.Feedback;
import com.example.hotelmanagement.beans.Room;
import com.example.hotelmanagement.beans.RoomType;
import com.example.hotelmanagement.config.Validation;
import com.example.hotelmanagement.controllers.admin.HomePageController;
import com.example.hotelmanagement.dao.FeedbackDao;
import com.example.hotelmanagement.dao.RoomDao;
import com.example.hotelmanagement.dao.RoomTypeDao;
import com.example.hotelmanagement.daoFactory.CummonDbFcts;
import com.example.hotelmanagement.localStorage.CustomerManager;
import com.example.hotelmanagement.localStorage.VarsManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class FeedbackActionsController implements Initializable {
    @FXML private Label rateError = new Label();
    @FXML private Spinner<Integer> customerService_rateSpinner_, cleanliness_rateSpinner_, roomComfort_rateSpinner_, location_rateSpinner_, safety_rateSpinner_, environnement_rateSpinner_, view_rateSpinner_, serviceVSprice_rateSpinner_;
    @FXML private TextField commentField_;
    int rate1, rate2, rate3, rate4, rate5, rate6, rate7, rate8;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if (VarsManager.actionStarted.equals("delete")) {
        }else {
            rateError.setVisible(false);

            customerService_rateSpinner_.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10,0));
            cleanliness_rateSpinner_.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10,0));
            roomComfort_rateSpinner_.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10,0));
            location_rateSpinner_.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10,0));
            safety_rateSpinner_.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10,0));
            environnement_rateSpinner_.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10,0));
            view_rateSpinner_.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10,0));
            serviceVSprice_rateSpinner_.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10,0));


            if(VarsManager.actionStarted.equals("update") || VarsManager.actionStarted.equals("details")){
                Map<String, Object> map = new HashMap<>();
                map.put("feedbackId", VarsManager.selectedFeedbackId);

                Object feedback = FeedbackDao.select(map, "*").get(0);
                System.out.println(feedback);

                customerService_rateSpinner_.getValueFactory().setValue(((Feedback)feedback).getServiceVSprice_rate());
                cleanliness_rateSpinner_.getValueFactory().setValue(((Feedback)feedback).getCleanliness_rate());
                roomComfort_rateSpinner_.getValueFactory().setValue(((Feedback)feedback).getRoomComfort_rate());
                location_rateSpinner_.getValueFactory().setValue(((Feedback)feedback).getLocation_rate());
                safety_rateSpinner_.getValueFactory().setValue(((Feedback)feedback).getSafety_rate());
                environnement_rateSpinner_.getValueFactory().setValue(((Feedback)feedback).getEnvironnement_rate());
                view_rateSpinner_.getValueFactory().setValue(((Feedback)feedback).getView_rate());
                serviceVSprice_rateSpinner_.getValueFactory().setValue(((Feedback)feedback).getServiceVSprice_rate());
                commentField_.setText(((Feedback)feedback).getReview_rate());

            }
        }
    }
    //----------------- Actions-------------------------------------
    public void addFeedback(ActionEvent event){
        rate1 = customerService_rateSpinner_.getValue();
        rate2 = cleanliness_rateSpinner_.getValue();
        rate3 = roomComfort_rateSpinner_.getValue();
        rate4 = location_rateSpinner_.getValue();
        rate5 = safety_rateSpinner_.getValue();
        rate6 = environnement_rateSpinner_.getValue();
        rate7 = view_rateSpinner_.getValue();
        rate8 = serviceVSprice_rateSpinner_.getValue();
        List<Integer> ratesList =  new ArrayList<Integer>(List.of (rate1, rate2, rate3, rate4, rate5, rate6, rate7, rate8));
        String comment = commentField_.getText();

        System.out.println("boolean verify fields" +verifyFields(event, ratesList));
        System.out.println("list of rats" +ratesList);
        if(verifyFields(event, ratesList)){
            int customerId = CustomerManager.getInstance().getCustomer().getCustomerId();
            int totalRate = (rate1+ rate2+ rate3+ rate4+ rate5+ rate6+ rate7+ rate8) / 8;
            LocalDate currentDate = LocalDate.now();

            Feedback feedback = new Feedback(customerId, "Invisible", 100, rate1, rate2, rate3, rate4, rate5, rate6, rate7, rate8, comment,totalRate,currentDate);
            System.out.println("inserted feedback : " +feedback);
            FeedbackDao.insert(feedback);
            VarsManager.actionCompleted = "add";
            closeStage(event);
        }else {
            rateError.setVisible(true);
        }
    }
    public void editFeedback(ActionEvent event){
        rate1 = customerService_rateSpinner_.getValue();
        rate2 = cleanliness_rateSpinner_.getValue();
        rate3 = roomComfort_rateSpinner_.getValue();
        rate4 = location_rateSpinner_.getValue();
        rate5 = safety_rateSpinner_.getValue();
        rate6 = environnement_rateSpinner_.getValue();
        rate7 = view_rateSpinner_.getValue();
        rate8 = serviceVSprice_rateSpinner_.getValue();
        List<Integer> ratesList =  new ArrayList<Integer>(List.of (rate1, rate2, rate3, rate4, rate5, rate6, rate7, rate8));
        String comment = commentField_.getText();

        if(verifyFields(event, ratesList)){
            int totalRate = (rate1+ rate2+ rate3+ rate4+ rate5+ rate6+ rate7+ rate8) / 8;

            String[] updatedColumns = {"customerService_rate", "cleanliness_rate", "roomComfort_rate", "location_rate",  "safety_rate", "environnement_rate","view_rate","serviceVSprice_rate", "totalRate", "review_rate"};
            Object[] newColumnsValue = {rate1, rate2, rate3, rate4, rate5, rate6, rate7, rate8, totalRate, comment};
            String testColumn = "feedbackId";
            Object testColumnValue = VarsManager.selectedFeedbackId;

            int i = FeedbackDao.updateColumns(updatedColumns, newColumnsValue, testColumn, testColumnValue);
            if(i == 1){
                VarsManager.actionCompleted = "update";
                closeStage(event);
            }
        }else {
            rateError.setVisible(true);
        }

    }
    public void deleteFeedback(ActionEvent event){
        String testColumn = "feedbackId";
        Object testColumnValue = VarsManager.selectedFeedbackId;
        int i = FeedbackDao.delete(testColumn, testColumnValue);
        if (i == 1) {
            VarsManager.actionCompleted = "delete";
            closeStage(event);
        }
    }
    //----------------- verification-------------------------------------
    public boolean verifyFields(ActionEvent event, List<Integer> ratesList){
        rateError.setVisible(false);
        for (int rate: ratesList){
            if (rate<1 || rate>10) return false;
        }
        return true;
    }
    //--------------------------------------------------------------------------------
    public void closeStage(ActionEvent event){
        CustomerHomePageController.childStage.close();
        VarsManager.actionStarted = "";
    }
    public void closeAdminChildStage(ActionEvent event){
        HomePageController.childStage.close();
        VarsManager.actionStarted = "";
    }
}
