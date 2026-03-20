package org.assignments.processing.enums;

public enum ProcessingStatus {

    PENDING("Pending"),
    RUNNING("Running"),
    SUCCESS("Success"),
    FAILED("Failed"),
    RETRYING("Retrying");

    private String status;

    // Enum constructor must be private
    private ProcessingStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

}
