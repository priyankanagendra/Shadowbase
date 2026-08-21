package com.shadowbase.controller;

import com.shadowbase.dto.EnvironmentCreateRequest;
import com.shadowbase.dto.EnvironmentResponse;
import com.shadowbase.dto.EnvironmentUpdateRequest;
import com.shadowbase.service.EnvironmentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/environments")
public class EnvironmentController {

    private final EnvironmentService environmentService;

    public EnvironmentController(EnvironmentService environmentService) {
        this.environmentService = environmentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EnvironmentResponse createEnvironment(
            @RequestBody EnvironmentCreateRequest request) {

        return environmentService.createEnvironment(request);
    }

    @GetMapping
    public List<EnvironmentResponse> getAllEnvironments() {

        return environmentService.getAllEnvironments();
    }

    @GetMapping("/{id}")
    public EnvironmentResponse getEnvironmentById(
            @PathVariable Long id) {

        return environmentService.getEnvironmentById(id);
    }

    @PutMapping("/{id}")
    public EnvironmentResponse updateEnvironment(
            @PathVariable Long id,
            @RequestBody EnvironmentUpdateRequest request) {

        return environmentService.updateEnvironment(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEnvironment(@PathVariable Long id) {

        environmentService.deleteEnvironment(id);
    }
}