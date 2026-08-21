package com.shadowbase.service;

import com.shadowbase.dto.EnvironmentCreateRequest;
import com.shadowbase.dto.EnvironmentResponse;
import com.shadowbase.dto.EnvironmentUpdateRequest;
import com.shadowbase.entity.Environment;
import com.shadowbase.entity.EnvironmentStatus;
import com.shadowbase.repository.EnvironmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnvironmentService {

    private final EnvironmentRepository environmentRepository;

    public EnvironmentService(EnvironmentRepository environmentRepository) {
        this.environmentRepository = environmentRepository;
    }

    public EnvironmentResponse createEnvironment(EnvironmentCreateRequest request) {

        if (environmentRepository.existsByName(request.getName())) {
            throw new IllegalArgumentException(
                    "Environment with name '" + request.getName() + "' already exists"
            );
        }

        Environment environment = new Environment();

        environment.setName(request.getName());
        environment.setDatabaseName(request.getDatabaseName());
        environment.setContainerId(request.getContainerId());
        environment.setStatus(EnvironmentStatus.ACTIVE);

        Environment savedEnvironment = environmentRepository.save(environment);

        return toResponse(savedEnvironment);
    }

    public List<EnvironmentResponse> getAllEnvironments() {

        return environmentRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public EnvironmentResponse getEnvironmentById(Long id) {

        Environment environment = environmentRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Environment with id " + id + " not found"
                        )
                );

        return toResponse(environment);
    }

    public EnvironmentResponse updateEnvironment(
            Long id,
            EnvironmentUpdateRequest request) {

        Environment existingEnvironment = environmentRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Environment with id " + id + " not found"
                        )
                );

        if (!existingEnvironment.getName().equals(request.getName())
                && environmentRepository.existsByName(request.getName())) {

            throw new IllegalArgumentException(
                    "Environment with name '" + request.getName() + "' already exists"
            );
        }

        existingEnvironment.setName(request.getName());
        existingEnvironment.setDatabaseName(request.getDatabaseName());
        existingEnvironment.setContainerId(request.getContainerId());

        if (request.getStatus() != null) {
            existingEnvironment.setStatus(request.getStatus());
        }

        Environment updatedEnvironment =
                environmentRepository.save(existingEnvironment);

        return toResponse(updatedEnvironment);
    }

    public void deleteEnvironment(Long id) {

        Environment environment = environmentRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Environment with id " + id + " not found"
                        )
                );

        environmentRepository.delete(environment);
    }

    private EnvironmentResponse toResponse(Environment environment) {

        return new EnvironmentResponse(
                environment.getId(),
                environment.getName(),
                environment.getDatabaseName(),
                environment.getContainerId(),
                environment.getStatus(),
                environment.getCreatedAt(),
                environment.getUpdatedAt()
        );
    }
}