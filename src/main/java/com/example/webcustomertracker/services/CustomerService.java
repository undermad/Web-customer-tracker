package com.example.webcustomertracker.services;

import com.example.webcustomertracker.pojo.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getCustomers(int sort);

    void saveCustomer(Customer customer);

    Customer getCustomer(int id);

    void deleteCustomer(int id);

    List<Customer> searchCustomer(String customerName);
}
