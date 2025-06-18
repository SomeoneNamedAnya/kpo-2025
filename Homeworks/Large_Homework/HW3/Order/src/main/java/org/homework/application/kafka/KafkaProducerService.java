package org.homework.application.kafka;

import org.homework.domain.OrderEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public void sendOrderToPayment(OrderEvent event) {
        kafkaTemplate.send(
                "orders",
                String.valueOf(event.userId()),
                event
        );
    }
}