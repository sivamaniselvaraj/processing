package org.assignments.processing.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.assignments.processing.dto.OrderEvent;
import org.assignments.processing.entity.OutboxEvent;
import org.assignments.processing.repository.OutboxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class OutboxService {

    @Autowired
    OutboxRepository outboxRepository;

    public void createOutbox(String status, OrderEvent event) throws JsonProcessingException {

        Map<String,Object> payload = Map.of(
                "orderId", event.getOrderId(),
                "correlationId", event.getCorrelationId(),
                "status", status
        );

        OutboxEvent outbox = new OutboxEvent();

        outbox.setEventId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
        outbox.setAggregateType("Processing");
        outbox.setAggregateId(event.getOrderId());
        outbox.setEventType(status);
        outbox.setPayload(new ObjectMapper().writeValueAsString(payload));
        outbox.setStatus("PENDING");
        outbox.setCreatedAt(LocalDateTime.now());

        outboxRepository.save(outbox);

    }

}
