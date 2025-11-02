package com.infinite.employee_manager.Controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class EmployeesController {

    @Autowired
    private final EmployeesService employeesService;
    @Autowired
    private final UsersService usersService;

    public EmployeesController(EmployeesService employeesService, UsersService usersService){
        this.employeesService = employeesService;
        this.usersService = usersService;
    }

    @GetMapping("/")
    public String goListPage() {
        return "redirect:/employees";
    }
    

    @GetMapping({"/employees","employees/"})
    public String getAllEmployees(Model model,@AuthenticationPrincipal UserDetails userDetails) {
        List<Employee> employees = employeesService.getEmpsDB();
        model.addAttribute("employees",employees);

        User user;
        if(userDetails != null){
            user = usersService.findByUsername(userDetails.getUsername());
            model.addAttribute("loggedUser",user);
        }

        return "employee-list";
    }
    
    @GetMapping("/employees/{id}")
    public String getEmployeeById(@PathVariable(name="id") Long id,Model model) {
        Employee res = employeesService.getEmployeeById(id);
        
        model.addAttribute("employee",res);
        return "employee-details";
    }

    @PostMapping({"/employees/add"})
    public String goCreateEmployee(Model model) {
        model.addAttribute("add","val");
        return "employee-form";
    }

    @PostMapping({"/employees/add/perform-add"})
    public String performCreateEmployee(@RequestParam(name="name") String name, @RequestParam(name="email") String email, @RequestParam(name="department") String department, @RequestParam(name="salary") Double salary) {
        
        employeesService.createNewEmployee(name, email, department, salary);

        return "redirect:/employees";
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
    public String performEditEmployee(@PathVariable Long id,
        @RequestParam(name="name") String name,
        @RequestParam(name="email") String email,
        @RequestParam(name="salary") Double salary,
        @RequestParam(name="department") String department,
        Model model) {
        
            Employee emp = employeesService.setEmployeeDataById(id, name, email, department, salary);

            model.addAttribute("editEmp",emp);
            
            return "redirect:/employees/" + id;
        }

    @PostMapping("/employees/delete/{id}/perform-del")
    public String performDeleteEmployee(@PathVariable Long id) {

        employeesService.deleteEmployeeById(id);

        return "redirect:/employees";
    }
}
