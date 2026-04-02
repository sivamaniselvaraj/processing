package org.assignments.processing.enums;

public enum ProcessingStatusEnum {

    PENDING("Pending"),
    RUNNING("Running"),
    SUCCESS("Success"),
    FAILED("Failed"),
    RETRYING("Retrying");

    private String status;

    // Enum constructor must be private
    private ProcessingStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

}
