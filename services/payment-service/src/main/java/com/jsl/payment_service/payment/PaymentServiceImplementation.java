package com.jsl.payment_service.payment;

import com.jsl.payment_service.kafka.KafkaNotificationProducer;
import com.jsl.payment_service.kafka.PaymentNotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.jsl.payment_service.payment.PaymentMapper.toPayment;

@Service
@RequiredArgsConstructor
public class PaymentServiceImplementation implements PaymentService{
    private final PaymentRepository paymentRepository;
    private final KafkaNotificationProducer kafkaNotificationProducer;
    @Override
    public Integer createPayment(PaymentRequest request) {
        var payment = paymentRepository.save(toPayment(request));

        kafkaNotificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        request.orderReference(),
                        request.amount(),
                        request.paymentMethod(),
                        request.customer().firstname(),
                        request.customer().lastname(),
                        request.customer().email()
                )
        );
        return payment.getId();
    }
}
