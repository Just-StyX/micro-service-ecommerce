package com.jsl.order_service.orderline;

import com.jsl.order_service.order.Order;

public class OrderLineMapper {
    public static OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
                .id(request.orderId())
                .productId(request.productId())
                .order(
                        Order.builder()
                                .id(request.orderId())
                                .build()
                )
                .quantity(request.quantity())
                .build();
    }

    public static OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(
                orderLine.getId(),
                orderLine.getQuantity()
        );
    }
}
