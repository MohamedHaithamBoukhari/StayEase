package com.example.hotelmanagement.config;

public class SwitchedPageManager {
    private static SwitchedPageManager instance;
    private String switchedPage = "";

    private SwitchedPageManager() {
        // Private constructor to prevent direct instantiation
    }

    public static SwitchedPageManager getInstance() {
        if (instance == null) {
            instance = new SwitchedPageManager();
        }
        return instance;
    }

    public String getSwitchedPage() {
        return switchedPage;
    }

    public void setSwitchedPage(String page) {
        switchedPage = page;
    }
}
