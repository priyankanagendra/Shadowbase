package com.shadowbase.service;

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

    public Environment createEnvironment(Environment environment) {

        if (environmentRepository.existsByName(environment.getName())) {
            throw new IllegalArgumentException(
                    "Environment with name '" + environment.getName() + "' already exists"
            );
        }

        if (environment.getStatus() == null) {
            environment.setStatus(EnvironmentStatus.ACTIVE);
        }

        return environmentRepository.save(environment);
    }

    public List<Environment> getAllEnvironments() {
        return environmentRepository.findAll();
    }

    public Environment getEnvironmentById(Long id) {
        return environmentRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Environment with id " + id + " not found"
                        )
                );
    }

    public Environment updateEnvironment(Long id, Environment updatedEnvironment) {

        Environment existingEnvironment = getEnvironmentById(id);

        if (!existingEnvironment.getName().equals(updatedEnvironment.getName())
                && environmentRepository.existsByName(updatedEnvironment.getName())) {
            throw new IllegalArgumentException(
                    "Environment with name '" + updatedEnvironment.getName() + "' already exists"
            );
        }

        existingEnvironment.setName(updatedEnvironment.getName());
        existingEnvironment.setDatabaseName(updatedEnvironment.getDatabaseName());
        existingEnvironment.setContainerId(updatedEnvironment.getContainerId());

        if (updatedEnvironment.getStatus() != null) {
            existingEnvironment.setStatus(updatedEnvironment.getStatus());
        }

        return environmentRepository.save(existingEnvironment);
    }

    public void deleteEnvironment(Long id) {
        Environment environment = getEnvironmentById(id);
        environmentRepository.delete(environment);
    }
}