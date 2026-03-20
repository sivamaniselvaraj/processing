package org.assignments.processing.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.assignments.processing.dto.OrderEvent;
import org.assignments.processing.service.ProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderEventConsumer {

    @Autowired
    ProcessingService processingService;

    @KafkaListener(topics = "order_topic", groupId = "processing-group")
    public void consume(OrderEvent event) throws JsonProcessingException {

        log.info("Received order event {}", event);

        if("orderCreated".equalsIgnoreCase(event.getEventType())){
            processingService.startProcessing(event);
        }

    }
}
