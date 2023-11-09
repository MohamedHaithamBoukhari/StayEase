package com.example.hotelmanagement.beans;

public class Customer {
    private int clientId;
    private String fullName;
    private String cin;
    private String phone;
    private String email;
    private String password;
    private String address;

    public Customer(int clientId, String fullName, String cin, String phone, String email, String password, String address) {
        this.clientId = clientId;
        this.fullName = fullName;
        this.cin = cin;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
