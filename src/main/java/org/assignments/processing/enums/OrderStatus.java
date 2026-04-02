package org.assignments.processing.enums;

public enum OrderStatus {
    CREATED("Order Created"),
    CANCELLED("Order Cancelled"),
    COMPLETED("Order Completed"),
    FAILED("Order Failed");

    private String status;

    // Enum constructor must be private
    private OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }
}
