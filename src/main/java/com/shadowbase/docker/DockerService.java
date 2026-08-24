package com.shadowbase.docker;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class DockerService {

    public String startContainer(String containerName) {

        executeCommand("start", containerName);

        String status = getContainerStatus(containerName);

        if (!"running".equalsIgnoreCase(status)) {
            throw new IllegalStateException(
                    "Docker container '" + containerName
                            + "' failed to start. Current status: " + status
            );
        }

        return status;
    }

    public String stopContainer(String containerName) {

        executeCommand("stop", containerName);

        String status = getContainerStatus(containerName);

        if (!"exited".equalsIgnoreCase(status)) {
            throw new IllegalStateException(
                    "Docker container '" + containerName
                            + "' failed to stop. Current status: " + status
            );
        }

        return status;
    }

    public String getContainerStatus(String containerName) {

        return executeCommand(
                "inspect",
                "--format={{.State.Status}}",
                containerName
        );
    }

    private String executeCommand(String... arguments) {

        try {
            List<String> command = new ArrayList<>();

            command.add("docker");

            for (String argument : arguments) {
                command.add(argument);
            }

            ProcessBuilder processBuilder =
                    new ProcessBuilder(command);

            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();

            StringBuilder output = new StringBuilder();

            try (BufferedReader reader =
                         new BufferedReader(
                                 new InputStreamReader(
                                         process.getInputStream()))) {

                String line;

                while ((line = reader.readLine()) != null) {
                    output.append(line)
                            .append(System.lineSeparator());
                }
            }

            int exitCode = process.waitFor();

            if (exitCode != 0) {
                throw new IllegalStateException(
                        "Docker command failed: "
                                + output.toString().trim()
                );
            }

            return output.toString().trim();

        } catch (InterruptedException exception) {

            Thread.currentThread().interrupt();

            throw new IllegalStateException(
                    "Docker command was interrupted",
                    exception
            );

        } catch (Exception exception) {

            throw new IllegalStateException(
                    "Failed to execute Docker command",
                    exception
            );
        }
    }
}