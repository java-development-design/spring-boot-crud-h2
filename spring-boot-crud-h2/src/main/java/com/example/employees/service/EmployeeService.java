package com.example.employees.service;

import com.example.employees.model.EmployeeResponse;
import com.example.employees.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<EmployeeResponse> findAll() {
        return repository.findAll();
    }

    public EmployeeResponse findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
    }

    public EmployeeResponse create(EmployeeResponse employee) {
        repository.findByEmail(employee.getEmail()).ifPresent(e -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        });
        return repository.save(employee);
    }

    public EmployeeResponse update(Long id, EmployeeResponse data) {
        EmployeeResponse existing = findById(id);
        existing.setName(data.getName());
        existing.setEmail(data.getEmail());
        existing.setRole(data.getRole());
        return repository.save(existing);
    }

    public void delete(Long id) {
        EmployeeResponse existing = findById(id);
        repository.delete(existing);
    }
}
