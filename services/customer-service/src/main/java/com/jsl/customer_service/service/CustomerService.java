package com.jsl.customer_service.service;

import com.jsl.customer_service.util.CustomerRequest;
import com.jsl.customer_service.util.CustomerResponse;

import java.util.List;

public interface CustomerService {
    String createCustomer(CustomerRequest customerRequest);
    void updateCustomer(CustomerRequest customerRequest, String id);
    List<CustomerResponse> findAllCustomers();
    CustomerResponse findById(String id);
    boolean existsById(String id);
    void deleteCustomer(String id);
}
