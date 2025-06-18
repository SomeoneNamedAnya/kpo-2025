package org.homework.application.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.homework.application.kafka.KafkaProducerService;
import org.homework.domain.OrderEvent;
import org.homework.domain.OutboxEvent;
import org.homework.infrostructure.OutboxEventRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class OutboxProcessor {
    private final OutboxEventRepository outboxEventRepository;
    private final KafkaProducerService kafkaProducerService;
    private final ObjectMapper objectMapper;

    @Scheduled(fixedDelay = 5000)
    @Transactional
    public void processOutboxEvents() throws JsonProcessingException {

        List<OutboxEvent> events = outboxEventRepository.findAllBySentFalseOrderByCreatedAtAsc();

        for (OutboxEvent event : events) {
            try {
                OrderEvent orderEvent = objectMapper.readValue(
                        event.getPayload(),
                        OrderEvent.class
                );

                kafkaProducerService.sendOrderToPayment(orderEvent);
                event.setSent(true);
                outboxEventRepository.save(event);

            } catch (Exception e) {
                log.error("Failed to process outbox event ID: {}", event.getId(), e);
                throw e;
            }
        }
    }
}