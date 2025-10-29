package com.infinite.employee_manager.Controllers;


import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.infinite.employee_manager.Models.Employee;
import com.infinite.employee_manager.Models.User;
import com.infinite.employee_manager.Services.EmployeesService;
import com.infinite.employee_manager.Services.UsersService;



@Controller
public class EmployeesController {

    private final EmployeesService employeesService;
    private final UsersService usersService;

    public EmployeesController(EmployeesService employeesService, UsersService usersService){
        this.employeesService = employeesService;
        this.usersService = usersService;
    }

    @GetMapping("/employees")
    public String getAllEmployees(Model model,@AuthenticationPrincipal UserDetails userDetails) {
        List<Employee> employees = employeesService.getEmpsDB();
        model.addAttribute("employees",employees);

        User user = null;
        if(userDetails != null){
            user = usersService.findByUsername(userDetails.getUsername());
            model.addAttribute("loggedUser",user);
        }

        return "employee-list";
    }
    
    @GetMapping("/employees/{id}")
    public String getEmployeeById(@PathVariable(name="id") Long id,Model model) {
        model.addAttribute("id",id);
        return "employee-details";
    }

    @PostMapping({"/employees/add"})
    public String createEmployee(@PathVariable(value="name") String empName) {
        return "employee-form";
    }

    @PostMapping("/employees/edit/{id}")
    public String goEditEmployee(@PathVariable Long id,Model model) {

        Employee emp = employeesService.getEmployeeById(id);
        model.addAttribute("editEmp",emp);

        return "employee-form";
    }

    @PostMapping("/employees/delete/{id}")
    public String goDeleteEmployee(@PathVariable Long id, Model model) {
        Employee emp = employeesService.getEmployeeById(id);
        model.addAttribute("delEmp",emp);
        return "employee-form";
    }

    @PostMapping("/employees/edit/{id}/perform-edit")
    public String performEditEmployee(@PathVariable Long id,Model model) {
        Employee emp = employeesService.getEmployeeById(id);
        //todo: implement employee edit here



        model.addAttribute("editEmp",emp);
        return "employee-form";
    }

    @PostMapping("/employees/delete/{id}/perform-del")
    public String performDeleteEmployee(@PathVariable Long id) {
        //todo:implement employee deletion here


        
        return "employee-list";
    }
    
    
}
