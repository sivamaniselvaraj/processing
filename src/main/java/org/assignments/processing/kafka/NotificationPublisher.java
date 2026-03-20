package org.assignments.processing.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assignments.processing.entity.OutboxEvent;
import org.assignments.processing.repository.OutboxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationPublisher {

    @Autowired
    OutboxRepository outboxRepository;

    @Autowired
    KafkaTemplate<String,String>  kafkaTemplate;

    @Scheduled(fixedDelay = 5000)
    public void publishOutboxEvents(){

        List<OutboxEvent> events = outboxRepository.findByStatus("PENDING");

        for(OutboxEvent event : events){

            kafkaTemplate.send("notification_topic",event.getPayload());

            event.setStatus("SENT");
            event.setProcessedAt(LocalDateTime.now());

            outboxRepository.save(event);
        }
    }
}
