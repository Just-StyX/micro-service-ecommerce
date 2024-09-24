package com.jsl.order_service.payment;

import com.jsl.order_service.customer.CustomerResponse;
import com.jsl.order_service.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customerResponse
) {
}
