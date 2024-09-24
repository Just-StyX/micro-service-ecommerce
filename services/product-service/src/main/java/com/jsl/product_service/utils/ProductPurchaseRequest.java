package com.jsl.product_service.utils;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductPurchaseRequest(
        @NotNull(message = "Product is required")
        Integer productId,
        @Positive(message = "How much quantity you want to purchase ?")
        double quantity
) {
}
