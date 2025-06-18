package org.homework.application.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.homework.application.service.ManageOrderService;
import org.homework.domain.OrderCompletedEvent;
import org.homework.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Optional;
@RequiredArgsConstructor
@Component
public class OrderConsumerService {
    @Autowired
    private ManageOrderService manageOrderService;
    @Autowired
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "order-updates", groupId = "kpo")
    public void handleStatusUpdate(@Payload String event) throws JsonProcessingException {
        OrderCompletedEvent orderCompletedEvent = objectMapper.readValue(
                event,
                OrderCompletedEvent.class
        );
        Optional<Order> orderOptional = manageOrderService.findById(orderCompletedEvent.orderId());

        if (orderOptional.isEmpty()) {
            return;
        }

        var order = orderOptional.get();
        order.setStatus(orderCompletedEvent.updatedParameter());
        manageOrderService.save(order);
    }
}
