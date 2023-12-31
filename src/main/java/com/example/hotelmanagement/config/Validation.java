package com.example.hotelmanagement.config;

import com.example.hotelmanagement.dao.RoomDao;
import com.example.hotelmanagement.localStorage.VarsManager;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Validation {
    public static boolean isValidFullName(String fullName) {
        return fullName.matches("^[a-zA-Z\\s'-]+$");
    }

    public static boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }

    public static boolean isValidCIN(String cin) {
        return cin.matches("^[A-Za-z][A-Za-z0-9]*$");
    }

    public static boolean isValidPassword(String password) {
        return password.length() >= 8;
    }

    public static boolean isValidPhone(String phone) {
        return phone.matches("(\\+212\\s\\d{9}|\\d{10})");
    }

    public static boolean isValidAddress(String address) {
        return address.matches("^[a-zA-Z0-9,\\s-]+$");
    }

    public static boolean isValidSalary(String salary) {
        return salary.matches("[0-9]+");
    }
    public static boolean isValidPosition(String position) {
        return !position.isEmpty();
    }
    public static boolean isValidWorkingHours(String workingHours) {
        return !workingHours.isEmpty();
    }
    public static boolean isValidWorkingDays(List workingDays) {
        return !workingDays.isEmpty();
    }

    public static boolean isValidRoomNbr(int roomNbr, String action){
        Map<String, Object> map = new HashMap<>();
        map.put("numRoom", roomNbr);
        List<Object> rooms = RoomDao.select(map,"*");

        if(action.equals("add")){
            if(rooms.size() == 1){
                return false; //room nbr exists
            }
            return true;
        } else if (action.equals("update")) {
            if(roomNbr == VarsManager.selectedRoomId || rooms.size() == 0){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
    public static boolean isValidCapacity(int capacity){
        return true;
    }
    public static boolean isValidRoomType(String type){
        return type != null;
    }
    public static boolean isValidRoomStatus(String status){
        return status != null;
    }

    public static boolean isValidCheckInDate(LocalDate checkInDate){
        LocalDate currentDate = LocalDate.now();
        return !checkInDate.isBefore(currentDate);
    }
    public static boolean isValidCheckOutDate(LocalDate checkInDate, LocalDate checkOutDate){
        return !checkOutDate.isBefore(checkInDate);
    }
}
