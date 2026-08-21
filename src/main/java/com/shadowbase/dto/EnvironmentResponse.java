package com.shadowbase.dto;

import com.shadowbase.entity.EnvironmentStatus;

import java.time.Instant;

public class EnvironmentResponse {

    private Long id;

    private String name;

    private String databaseName;

    private String containerId;

    private EnvironmentStatus status;

    private Instant createdAt;

    private Instant updatedAt;

    public EnvironmentResponse() {
    }

    public EnvironmentResponse(
            Long id,
            String name,
            String databaseName,
            String containerId,
            EnvironmentStatus status,
            Instant createdAt,
            Instant updatedAt) {

        this.id = id;
        this.name = name;
        this.databaseName = databaseName;
        this.containerId = containerId;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getContainerId() {
        return containerId;
    }

    public EnvironmentStatus getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}