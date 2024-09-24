package com.jsl.order_service.orderline;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.jsl.order_service.orderline.OrderLineMapper.toOrderLine;

@Service
@RequiredArgsConstructor
public class OrderLineServiceImplementation implements OrderLineService{
    private final OrderLineRepository orderLineRepository;
    @Override
    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
        var order = toOrderLine(orderLineRequest);
        return orderLineRepository.save(order).getId();
    }

    @Override
    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
        return orderLineRepository.findAllByOrderId(orderId)
                .stream()
                .map(OrderLineMapper::toOrderLineResponse)
                .toList();
    }
}
