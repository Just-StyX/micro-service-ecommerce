package com.jsl.order_service.customer;

public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email
) {
}