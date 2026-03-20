package org.assignments.processing.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "Jobs")
@Data
public class Job {

    @Id
    private Long jobId;

    private Long orderId;
    private String jobType;
    private String jobStatus;

    private LocalDateTime createdAt;
    private LocalDateTime startedAt;
    private LocalDateTime completedAt;
}