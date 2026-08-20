package com.shadowbase.repository;

import com.shadowbase.entity.Environment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnvironmentRepository extends JpaRepository<Environment, Long> {

    boolean existsByName(String name);
}