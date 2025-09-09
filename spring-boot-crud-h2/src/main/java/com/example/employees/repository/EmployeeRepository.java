package com.example.employees.repository;

import com.example.employees.model.EmployeeResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeResponse, Long> {
    Optional<EmployeeResponse> findByEmail(String email);
}
