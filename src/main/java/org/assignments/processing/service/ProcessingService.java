package org.assignments.processing.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.assignments.processing.dto.OrderEvent;
import org.assignments.processing.entity.Job;
import org.assignments.processing.entity.ProcessingStatus;
import org.assignments.processing.enums.JobStatus;
import org.assignments.processing.enums.ProcessingStatusEnum;
import org.assignments.processing.repository.JobRepository;
import org.assignments.processing.repository.ProcessingStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
public class ProcessingService {

    @Autowired
    JobRepository jobRepository;

    @Autowired
    ProcessingStatusRepository statusRepository;

    @Autowired
    CompensationService compensationService;

    @Autowired
    OutboxService outboxService;

    @Transactional
    public void startProcessing(OrderEvent event) throws JsonProcessingException {

        if (jobRepository.existsByOrderId(event.getOrderId())) {
            log.info(" {} Idempotent check: Order# {} already exists", event.getCorrelationId(), event.getOrderId());
            return;
        }

        Job job = new Job();
        job.setJobId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
        job.setOrderId(event.getOrderId());
        job.setJobType("Order_Processing");
        job.setJobStatus(JobStatus.PENDING.name());
        job.setCreatedAt(LocalDateTime.now());

        jobRepository.save(job);

        ProcessingStatus status = new ProcessingStatus();
        status.setStatusId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
        status.setJobId(job.getJobId());
        status.setStatus(JobStatus.PENDING.name());
        status.setMessage("Processing started");
        status.setCreatedAt(LocalDateTime.now());

        statusRepository.save(status);

        try {

            processOrder(job, event);

        } catch (Exception e) {

            compensationService.compensate(job, event);

        }

    }

    private void processOrder(Job job, OrderEvent event) throws JsonProcessingException {

        job.setJobStatus("RUNNING");
        jobRepository.save(job);

        // business logic here

//        job.setJobStatus("SUCCESS");
//        job.setCompletedAt(LocalDateTime.now());

        //jobRepository.save(job);

        outboxService.createOutbox(JobStatus.PENDING.name(), event);
    }

    public void updateStatus(Long orderId, String status) {
        Job job = new Job();
        job.setOrderId(orderId);
        job.setJobStatus(status);
        jobRepository.save(job);
    }
}