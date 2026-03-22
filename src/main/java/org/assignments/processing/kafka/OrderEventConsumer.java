package org.assignments.processing.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.assignments.processing.dto.OrderEvent;
import org.assignments.processing.entity.OutboxEvent;
import org.assignments.processing.service.ProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderEventConsumer {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    @Autowired
    ProcessingService processingService;

    @KafkaListener(topics = "order_topic", groupId = "processing-group", containerFactory= "kafkaListenerContainerFactory")
    public void consume(String eventMessage) {
        try{
        log.info("payload - {}", eventMessage);
        OrderEvent event = MAPPER.readValue(eventMessage, OrderEvent.class);
        log.info("Received order event {} {}", event.getStatus(), event.getOrderId());


        if ("orderCreated".equalsIgnoreCase(event.getStatus())) {
            //OrderEvent orderEvent = MAPPER.readValue(event.getPayload(), OrderEvent.class);
            processingService.startProcessing(event);
        }}
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
