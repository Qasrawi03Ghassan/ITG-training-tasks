package com.infinite.employee_manager.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    @GetMapping("/")
    public String getAllEmployees() {
        return"List all employees";
    }

    @GetMapping("/{id}")
    public String getEmployeeById(@PathVariable Long id) {
        return String.format("Get employee based on ID: %d",id);
    }
    
    
    
}
