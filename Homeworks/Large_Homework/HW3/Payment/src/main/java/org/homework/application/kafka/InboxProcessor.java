package org.homework.application.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.homework.application.service.OrderEvent;
import org.homework.application.service.ManageAccountService;
import org.homework.domain.InboxEvent;
import org.homework.domain.OrderCompletedEvent;
import org.homework.domain.OutboxEvent;
import org.homework.infrostruccture.repository.InboxEventRepository;
import org.homework.infrostruccture.repository.OutboxEventRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class InboxProcessor {
    private final InboxEventRepository inboxEventRepository;
    private final ManageAccountService manageAccountService;
    private final ObjectMapper objectMapper;
    private final OutboxEventRepository outboxEventRepository;

    @Scheduled(fixedDelay = 5000)
    @Transactional
    public void processInboxEvents() {

        List<InboxEvent> events = inboxEventRepository.findAllBySentFalseOrderByCreatedAtAsc();

        for (InboxEvent event : events) {
            try {

                OrderEvent orderEvent = objectMapper.readValue(
                        event.getPayload(),
                        OrderEvent.class
                );

                OutboxEvent outboxEvent = new OutboxEvent();
                outboxEvent.setAggregateType("CompleteOrder");
                outboxEvent.setEventType("ProcessedOrder");
                outboxEvent.setCreatedAt(LocalDateTime.now());

                try {
                    manageAccountService.withdrawAccount(orderEvent.userId(), orderEvent.amount());
                    outboxEvent.setPayload(objectMapper.writeValueAsString(
                            new OrderCompletedEvent(
                                    orderEvent.id(),
                                    "FINISHED")));
                } catch (Exception e) {
                    outboxEvent.setPayload(objectMapper.writeValueAsString(
                            new OrderCompletedEvent(
                                    orderEvent.id(),
                                    "CANCELLED")));
                }

                outboxEventRepository.save(outboxEvent);

                event.setSent(true);

                inboxEventRepository.save(event);


            } catch (Exception e) {

                log.error("Failed to process outbox event ID: {}", event.getId(), e);
            }
        }
    }
}