package com.jsl.order_service.orderline;

import com.jsl.order_service.util.OrderResponse;

import java.util.List;

public interface OrderLineService {
    Integer saveOrderLine(OrderLineRequest orderLineRequest);
    List<OrderLineResponse> findAllByOrderId(Integer orderId);
}
