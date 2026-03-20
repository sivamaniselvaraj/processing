package org.assignments.processing.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name="ProcessingStatus")
@Data
public class ProcessingStatus {

    @Id
    private Long statusId;

    private Long jobId;
    private String status;
    private String message;

    private LocalDateTime createdAt;
}
