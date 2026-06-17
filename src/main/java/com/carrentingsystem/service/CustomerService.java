package com.carrentingsystem.service;

import com.carrentingsystem.entity.Customer;
import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
    Customer saveCustomer(Customer customer);
    void deleteCustomer(Long id);
    Customer findByEmail(String email);
}
