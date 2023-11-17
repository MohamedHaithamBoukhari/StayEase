package com.example.hotelmanagement.config;

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
        return false;
    }
    public static boolean isValidWorkingDays(String workingDays) {
        return false;
    }
}
