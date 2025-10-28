package com.infinite.employee_manager.Controllers;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.infinite.employee_manager.Models.Employee;
import com.infinite.employee_manager.Services.EmployeesService;

@Controller
@RequestMapping("/employees")
public class EmployeesController {

    private EmployeesService employeesService;
    private EmployeesController(EmployeesService employeesService){
        this.employeesService = employeesService;
    }

    @GetMapping
    public String getAllEmployees(Model model) {
        List<Employee> employees = employeesService.getEmpsDB();
        model.addAttribute("employees",employees);
        return "employee-list";
    }
    
    @GetMapping("/{id}")
    public String getEmployeeById(@PathVariable(name="id") Long id,Model model) {
        model.addAttribute("id",id);
        return "employee-details";
    }

    @PostMapping({"/add"})
    public String createEmployee(@PathVariable(value="name") String empName) {
        return "employee-form";
    }
    
}
