package com.shadowbase;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShadowbaseBackendApplication {

    public static void main(String[] args) {

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        SpringApplication.run(
                ShadowbaseBackendApplication.class,
                args
        );
    }
}