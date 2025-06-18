package org.homework.application.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.homework.application.exeption.NotExistException;
import org.homework.domain.InboxEvent;
import org.homework.infrostruccture.repository.InboxEventRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class KafkaConsumeService {


    private final ObjectMapper objectMapper;
    private final InboxEventRepository inboxEventRepository;
    @KafkaListener(topics = "orders", groupId = "kpo2")
    public void handleStatusUpdate(@Payload String event) throws NotExistException {
        System.out.println(event);
        InboxEvent inboxEvent = new InboxEvent();
        inboxEvent.setAggregateType("Order");
        inboxEvent.setEventType("NewOrder");
        inboxEvent.setCreatedAt(LocalDateTime.now());


        inboxEvent.setPayload(event);

        inboxEventRepository.save(inboxEvent);
        System.out.println("Complited");
    }
}