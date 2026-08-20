package com.shadowbase.controller;

import com.shadowbase.entity.Environment;
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
    public Environment createEnvironment(@RequestBody Environment environment) {
        return environmentService.createEnvironment(environment);
    }

    @GetMapping
    public List<Environment> getAllEnvironments() {
        return environmentService.getAllEnvironments();
    }

    @GetMapping("/{id}")
    public Environment getEnvironmentById(@PathVariable Long id) {
        return environmentService.getEnvironmentById(id);
    }

    @PutMapping("/{id}")
    public Environment updateEnvironment(
            @PathVariable Long id,
            @RequestBody Environment environment) {

        return environmentService.updateEnvironment(id, environment);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEnvironment(@PathVariable Long id) {
        environmentService.deleteEnvironment(id);
    }
}