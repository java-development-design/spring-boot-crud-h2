package com.example.employees.controller;

import com.example.employees.entity.Employee;
import com.example.employees.mapper.EmployeeMapper;
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
    public List<EmployeeResponse> getEmployeeList() {
        return service.findAllEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeResponse getEmployeeById(@PathVariable Long id) {
        return service.findByEmployeeId(id);
    }

    @PostMapping("/save")
    public ResponseEntity<EmployeeResponse> saveEmployeeData(@Valid @RequestBody EmployeeRequest req) {
        Employee saved = service.saveEmployees(Employee.builder()
                .name(req.getName())
                .email(req.getEmail())
                .role(req.getRole())
                .build());
        return ResponseEntity.created(URI.create("/api/employees/" + saved.getId())).body(EmployeeMapper.toResponse(saved));
    }

    @PutMapping("/{id}")
    public EmployeeResponse updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeRequest req) {
        return service.updateEmployeeById(id,req);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployeeById(@PathVariable Long id) {
        service.deleteEmployeeById(id);
    }
}
