package org.assignments.processing.repository;

import jakarta.transaction.Transactional;
import org.assignments.processing.entity.ProcessingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ProcessingStatusRepository extends JpaRepository<ProcessingStatus, Long> {
}
