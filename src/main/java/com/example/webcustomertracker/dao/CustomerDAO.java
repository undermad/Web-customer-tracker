package com.example.webcustomertracker.dao;

import com.example.webcustomertracker.pojo.Customer;

import java.util.List;

public interface CustomerDAO {
    List<Customer> getCustomers(int sort);

    void saveCustomer(Customer customer);

    Customer getCustomer(int id);

    void deleteCustomer(int id);

    List<Customer> searchCustomer(String customerName);
}
