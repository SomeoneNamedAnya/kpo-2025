package org.homework.application.kafka;

import org.homework.domain.OrderCompletedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    @Autowired
    private KafkaTemplate<String, OrderCompletedEvent> kafkaTemplate;

    public void sendPaymentToOrder(OrderCompletedEvent event) {
        kafkaTemplate.send(
                "order-updates",
                "kpo",
                event
        );
    }
}