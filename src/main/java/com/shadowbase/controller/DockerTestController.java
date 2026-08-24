package com.shadowbase.controller;

import com.shadowbase.docker.DockerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/docker")
public class DockerTestController {

    private final DockerService dockerService;

    public DockerTestController(DockerService dockerService) {
        this.dockerService = dockerService;
    }

    @GetMapping("/status")
    public String getContainerStatus(
            @RequestParam String containerName) {

        return dockerService.getContainerStatus(containerName);
    }
}