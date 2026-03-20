package org.assignments.processing.dto;

import lombok.Data;

@Data
public class OrderEvent {

    private String eventType;
    private Long orderId;
    private String correlationId;
    private String status;
}