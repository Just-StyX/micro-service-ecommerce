package com.jsl.customer_service.util;

import com.jsl.customer_service.customer.Customer;

public class CustomerMapper {
    public static Customer toCustomer(CustomerRequest customerRequest) {
        if (customerRequest == null) return null;

        return Customer.builder()
                .address(customerRequest.address())
                .email(customerRequest.email())
                .lastname(customerRequest.lastname())
                .firstname(customerRequest.firstname())
                .build();
    }

    public static CustomerResponse fromCustomer(Customer customer) {
        if (customer == null) return null;

        return new CustomerResponse(
                customer.getId(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
