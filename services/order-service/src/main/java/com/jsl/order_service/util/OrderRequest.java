package com.jsl.order_service.util;

import com.jsl.order_service.order.PaymentMethod;
import com.jsl.order_service.product.PurchaseRequest;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        String reference,
        @Positive(message = "order amount is greater than zero")
        BigDecimal amount,
        @NotNull(message = "Precise payment method")
        PaymentMethod paymentMethod,
        @NotNull(message = "Customer is required")
        String customerId,
        @NotEmpty(message = "You should at least purchase one product")
        List<PurchaseRequest> products
) {
}
