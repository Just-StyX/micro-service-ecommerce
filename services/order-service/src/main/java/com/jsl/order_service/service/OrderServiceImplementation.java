package com.jsl.order_service.service;

import com.jsl.order_service.customer.CustomerClient;
import com.jsl.order_service.customer.CustomerResponse;
import com.jsl.order_service.kafka.KafkaOrderProducer;
import com.jsl.order_service.kafka.OrderConfirmation;
import com.jsl.order_service.order.Order;
import com.jsl.order_service.orderline.OrderLineRequest;
import com.jsl.order_service.orderline.OrderLineService;
import com.jsl.order_service.payment.PaymentClient;
import com.jsl.order_service.payment.PaymentRequest;
import com.jsl.order_service.product.ProductClient;
import com.jsl.order_service.product.PurchaseRequest;
import com.jsl.order_service.product.PurchaseResponse;
import com.jsl.order_service.util.OrderMapper;
import com.jsl.order_service.util.OrderRequest;
import com.jsl.order_service.util.OrderResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.jsl.order_service.util.OrderMapper.toOrder;

@Service
@RequiredArgsConstructor
public class OrderServiceImplementation implements OrderService{
    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final PaymentClient paymentClient;
    private final ProductClient productClient;
    private final KafkaOrderProducer kafkaOrderProducer;
    private final OrderLineService orderLineService;
    @Override
    @CircuitBreaker(name = "createOrder", fallbackMethod = "circuitBreaker")
    public Integer createOrder(OrderRequest orderRequest) {
        CustomerResponse customer = customerClient.findCustomerById(orderRequest.customerId()).orElseThrow();
        List<PurchaseResponse> purchaseResponses = productClient.purchaseProducts(orderRequest.products());
        Order order = orderRepository.save(toOrder(orderRequest));

        for (PurchaseRequest purchaseRequest: orderRequest.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            order.getId(), purchaseRequest.productId(), purchaseRequest.quantity()
                    )
            );
        }
        PaymentRequest paymentRequest = new PaymentRequest(
                orderRequest.amount(), orderRequest.paymentMethod(), order.getId(), order.getReference(), customer
        );

        paymentClient.requestOrderPayment(paymentRequest);
        kafkaOrderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        order.getReference(), order.getTotalAmount(), order.getPaymentMethod(), customer, purchaseResponses
                )
        );
        return order.getId();
    }

    @Override
    public List<OrderResponse> findAllOrders() {
        return orderRepository.findAll().stream()
                .map(OrderMapper::toOrderResponse).toList();
    }

    @Override
    public OrderResponse findById(Integer id) {
        return orderRepository.findById(id).map(OrderMapper::toOrderResponse).orElseThrow();
    }

    private Integer circuitBreaker(OrderRequest orderRequest, Throwable throwable) {
        return -1;
    }
}
