package com.example.employees.controller;

import com.example.employees.model.EmployeeResponse;
import com.example.employees.service.EmployeeService;
import com.example.employees.model.EmployeeRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public List<EmployeeResponse> all() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public EmployeeResponse byId(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> create(@Valid @RequestBody EmployeeRequest req) {
        EmployeeResponse saved = service.create(EmployeeResponse.builder()
                .name(req.name())
                .email(req.email())
                .role(req.role())
                .build());
        return ResponseEntity.created(URI.create("/api/employees/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public EmployeeResponse update(@PathVariable Long id, @Valid @RequestBody EmployeeRequest req) {
        return service.update(id, EmployeeResponse.builder()
                .name(req.name())
                .email(req.email())
                .role(req.role())
                .build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
