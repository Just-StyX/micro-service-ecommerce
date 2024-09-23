package com.jsl.customer_service.service;

import com.jsl.customer_service.customer.Customer;
import com.jsl.customer_service.util.CustomerMapper;
import com.jsl.customer_service.util.CustomerRequest;
import com.jsl.customer_service.util.CustomerResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.jsl.customer_service.util.CustomerMapper.toCustomer;

@Service
@RequiredArgsConstructor
public class CustomerServiceImplementation implements CustomerService{
    private final CustomerRepository customerRepository;
    @Override
    public String createCustomer(CustomerRequest customerRequest) {
        return customerRepository.save(toCustomer(customerRequest)).getId();
    }

    @Override
    public void updateCustomer(CustomerRequest customerRequest, String id) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        mergeCustomer(customer, customerRequest);
        customerRepository.save(customer);
    }

    @Override
    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll().stream().map(CustomerMapper::fromCustomer).toList();
    }

    @Override
    public CustomerResponse findById(String id) {
        return customerRepository.findById(id).map(CustomerMapper::fromCustomer).orElseThrow();
    }

    @Override
    public boolean existsById(String id) {
        return customerRepository.findById(id).isPresent();
    }

    @Override
    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }

    private void mergeCustomer(Customer customer, CustomerRequest customerRequest) {
        if (StringUtils.isNotBlank(customerRequest.firstname())) customer.setFirstname(customerRequest.firstname());
        if (StringUtils.isNotBlank(customerRequest.email())) customer.setEmail(customerRequest.email());
        if (customerRequest.address() != null) customer.setAddress(customerRequest.address());
    }
}
