package com.jsl.order_service.service;

import com.jsl.order_service.util.OrderRequest;
import com.jsl.order_service.util.OrderResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderService {
    @Transactional
    Integer createOrder(OrderRequest orderRequest);
    List<OrderResponse> findAllOrders();
    OrderResponse findById(Integer id);
}
