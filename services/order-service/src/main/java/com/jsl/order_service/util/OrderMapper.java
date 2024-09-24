package com.jsl.order_service.util;

import com.jsl.order_service.order.Order;

public class OrderMapper {
    public static Order toOrder(OrderRequest orderRequest) {
        if (orderRequest == null) return null;
        return Order.builder()
                .reference(orderRequest.reference())
                .paymentMethod(orderRequest.paymentMethod())
                .customerId(orderRequest.customerId())
                .build();
    }

    public static OrderResponse toOrderResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId()
        );
    }
}
