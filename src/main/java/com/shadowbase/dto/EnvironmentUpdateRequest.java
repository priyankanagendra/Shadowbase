package com.shadowbase.dto;

import com.shadowbase.entity.EnvironmentStatus;

public class EnvironmentUpdateRequest {

    private String name;

    private String databaseName;

    private String containerId;

    private EnvironmentStatus status;

    public EnvironmentUpdateRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }

    public EnvironmentStatus getStatus() {
        return status;
    }

    public void setStatus(EnvironmentStatus status) {
        this.status = status;
    }
}