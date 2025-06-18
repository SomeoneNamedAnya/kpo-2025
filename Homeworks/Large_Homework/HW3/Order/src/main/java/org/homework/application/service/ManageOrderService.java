package org.homework.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.homework.application.exeption.OrderException;
import org.homework.application.kafka.KafkaProducerService;
import org.homework.domain.OutboxEvent;
import org.homework.domain.OrderEvent;
import org.homework.infrostructure.OutboxEventRepository;
import org.homework.domain.Order;
import org.homework.infrostructure.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ManageOrderService {
    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private final OutboxEventRepository outboxEventRepository;

    @Autowired
    private final ObjectMapper objectMapper;


    @Transactional
    public void addOrder(Order order) throws OrderException {
        var savedOrder = ordersRepository.save(order);
        saveToOutbox(savedOrder);
    }
    public List<Order> getAllById(int id) {
        return ordersRepository.findAllByUserId(id);
    }

    private void saveToOutbox(Order order) throws OrderException {
        OrderEvent event = createAddedEvent(order);

        OutboxEvent outboxEvent = new OutboxEvent();
        outboxEvent.setAggregateType("Order");
        outboxEvent.setEventType("NewOrder");
        outboxEvent.setCreatedAt(LocalDateTime.now());

        try {
            outboxEvent.setPayload(objectMapper.writeValueAsString(event));
        } catch (JsonProcessingException e) {
            throw new OrderException("Failed to serialize customer event");
        }

        outboxEventRepository.save(outboxEvent);
    }

    private OrderEvent createAddedEvent(Order order) {
        return new OrderEvent(
                order.getId(),
                order.getUserId(),
                order.getAmount(),
                order.getDescription(),
                order.getStatus()
        );
    }


    public Optional<Order> findById(int id) {
        return ordersRepository.findById(id);
    }

    public Order save(Order customer) {
        return ordersRepository.save(customer);
    }
}
