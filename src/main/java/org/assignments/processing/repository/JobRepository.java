package org.assignments.processing.repository;

import jakarta.transaction.Transactional;
import org.assignments.processing.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface JobRepository extends JpaRepository<Job, Long> {
    boolean existsByOrderId(Long orderId);
}
