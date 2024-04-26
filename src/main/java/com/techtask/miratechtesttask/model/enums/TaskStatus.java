package com.techtask.miratechtesttask.model.enums;

public enum TaskStatus {
    PENDING("pending"),
    COMPLETED("completed"),
    IN_PROGRESS("in progress"),
    DECLINED("declined"),
    REASSIGNED("reassigned"),
    REVIEW("review"),
    HOLD("hold");

    private final String taskStatus;

    TaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public static TaskStatus fromString(String text) {
        for (TaskStatus status : TaskStatus.values()) {
            if (status.taskStatus.equalsIgnoreCase(text)) {
                return status;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }
}
