package com.jsl.order_service.kafka;

import com.jsl.order_service.customer.CustomerResponse;
import com.jsl.order_service.order.PaymentMethod;
import com.jsl.order_service.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal price,
        PaymentMethod paymentMethod,
        CustomerResponse customerResponse,
        List<PurchaseResponse> products
) {
}
