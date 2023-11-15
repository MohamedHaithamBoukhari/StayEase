package com.example.hotelmanagement.config;

import com.example.hotelmanagement.beans.Customer;

public class CustomerManager {
    private static CustomerManager instance;
    private Customer customer = new Customer();

    private CustomerManager() {
        // Private constructor to prevent direct instantiation
    }

    public static CustomerManager getInstance() {
        if (instance == null) {
            instance = new CustomerManager();
        }
        return instance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer.setCustomerId(customer.getCustomerId());
        this.customer.setFullName(customer.getFullName());
        this.customer.setCin(customer.getCin());
        this.customer.setEmail(customer.getEmail());
        this.customer.setPassword(customer.getPassword());
        this.customer.setPhone(customer.getPhone());
        this.customer.setAddress(customer.getAddress());
    }
}
