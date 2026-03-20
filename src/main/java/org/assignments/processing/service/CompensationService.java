package org.assignments.processing.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.assignments.processing.dto.OrderEvent;
import org.assignments.processing.entity.Job;
import org.assignments.processing.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CompensationService {

    @Autowired
    JobRepository jobRepository;

    @Autowired
    OutboxService outboxService;

    void compensate(Job job, OrderEvent event) throws JsonProcessingException {

        job.setJobStatus("FAILED");
        jobRepository.save(job);

        outboxService.createOutbox("orderFailed",event);

    }
}
