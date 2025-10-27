package com.infinite.employee_manager.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.infinite.employee_manager.Services.EmployeesService;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    private EmployeesService employeesService;
    private EmployeesController(EmployeesService employeesService){
        this.employeesService = employeesService;
    }

    @GetMapping
    public String getAllEmployees() {
        return"List all employees";
    }
    
    @GetMapping("/{id}")
    public String getEmployeeById(@PathVariable Long id) {
        return String.format("Get employee based on ID: %d",id);
    }

    @PostMapping("/add")
    public String createEmployee(@PathVariable(value="name") String empName) {
        return String.format("Create new Employee with name: %s",empName);
    }
    
}
