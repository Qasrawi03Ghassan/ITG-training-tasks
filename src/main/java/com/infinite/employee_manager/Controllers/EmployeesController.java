package com.infinite.employee_manager.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.infinite.employee_manager.Services.EmployeesService;

@Controller
@RequestMapping("/employees")
public class EmployeesController {

    private EmployeesService employeesService;
    private EmployeesController(EmployeesService employeesService){
        this.employeesService = employeesService;
    }

    @GetMapping
    public String getAllEmployees() {
        return "employee-list";
    }
    
    @GetMapping("/{id}")
    public String getEmployeeById(@PathVariable Long id) {
        return "employee-details";
    }

    @PostMapping({"/add"})
    public String createEmployee(@PathVariable(value="name") String empName) {
        return "employee-form";
    }
    
}
