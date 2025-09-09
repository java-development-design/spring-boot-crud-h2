package com.example.employees.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record EmployeeRequest(
        @NotBlank(message = "Name is required") String name,
        @Email(message = "Email must be valid") String email,
        @NotBlank(message = "Role is required") String role
) { }
