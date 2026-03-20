package org.assignments.processing.enums;

public enum JobStatus {

    PENDING("Pending"),
    RUNNING("Running"),
    VALIDATING_ORDER("Validating Order"),
    PROCESSING_PAYMENT("Processing Payment"),
    SUCCESS("Success");

    private String status;

    // Enum constructor must be private
    private JobStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

}
