package com.jsl.product_service.utils;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
        @NotNull(message = "Product name is required")
        String name,
        @NotNull(message = "Product description is required")
        String description,
        @NotNull(message = "Product category is required")
        Integer categoryId,
        @Positive(message = "Available quantity must be positive")
        double availableQuantity,
        @Positive(message = "Price must be positive")
        BigDecimal price
) {
}
