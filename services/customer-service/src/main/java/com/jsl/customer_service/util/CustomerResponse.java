package com.jsl.customer_service.util;

import com.jsl.customer_service.customer.Address;

public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email,
        Address address
) {
}
