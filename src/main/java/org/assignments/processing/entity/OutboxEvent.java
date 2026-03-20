package org.assignments.processing.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name="OutboxEvents")
@Data
public class OutboxEvent {

    @Id
    private Long eventId;

    private String aggregateType;
    private Long aggregateId;
    private String eventType;

    @Column(columnDefinition="json")
    private String payload;

    private String status;

    private LocalDateTime createdAt;
    private LocalDateTime processedAt;
}