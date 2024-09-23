package com.jsl.customer_service.service;

import com.jsl.customer_service.customer.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
