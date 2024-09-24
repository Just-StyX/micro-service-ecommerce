package com.jsl.customer_service.service;

import com.jsl.customer_service.util.CustomerRequest;
import com.jsl.customer_service.util.CustomerResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> createCustomer(
            @RequestBody @Valid CustomerRequest customerRequest
            ) {
        return ResponseEntity.ok(customerService.createCustomer(customerRequest));
    }

    @PutMapping("/{customer-id}")
    public ResponseEntity<Void> updateCustomer(
            @RequestBody @Valid CustomerRequest customerRequest,
            @PathVariable("customer-id") String id
    ) {
        customerService.updateCustomer(customerRequest, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll() {
        return ResponseEntity.ok(customerService.findAllCustomers());
    }

    @GetMapping("/exists/{customer-id}")
    public ResponseEntity<Boolean> existsById(
            @PathVariable("customer-id") String id
    ) {
        return ResponseEntity.ok(customerService.existsById(id));
    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> findById(
            @PathVariable("customer-id") String id
    ) {
        return ResponseEntity.ok(customerService.findById(id));
    }

    @DeleteMapping("/{customer-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("customer-id") String id
    ) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }
}
