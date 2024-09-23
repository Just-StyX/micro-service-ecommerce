package com.jsl.customer_service.customer;

import lombok.*;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Builder
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String state;
    private String city;
    private String street;
    private String zipcode;
}
