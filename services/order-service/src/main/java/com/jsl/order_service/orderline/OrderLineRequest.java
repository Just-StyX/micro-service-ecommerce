package com.jsl.order_service.orderline;

public record OrderLineRequest(
        Integer orderId,
        Integer productId,
        double quantity
) {
}
