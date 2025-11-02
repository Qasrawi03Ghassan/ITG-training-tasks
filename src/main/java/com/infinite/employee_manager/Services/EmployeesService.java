package com.infinite.employee_manager.Services;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infinite.employee_manager.Models.Employee;
import com.infinite.employee_manager.Repositories.EmployeesRepository;

@Service
public class EmployeesService {

    @Autowired
    private final EmployeesRepository employeesRepository;
    public EmployeesService(EmployeesRepository employeesRepository){
        this.employeesRepository = employeesRepository;
    }

    public List<Employee> getEmpsDB() {
        return employeesRepository.findAll();
    }   

    public Employee getEmployeeById(Long id){
        return employeesRepository.findById(id).orElse(null);
    }

    public Employee createNewEmployee(String name, String email, String department, Double salary){
        Employee newEmp = new Employee(name,email,department,salary);
        //empsDB.add(newEmp);

        employeesRepository.save(newEmp);

        return newEmp;
    }

    public void deleteEmployeeById(Long id){
        //empsDB.remove(getEmployeeById(id));

        Employee toDel = getEmployeeById(id);
        if(toDel != null){
            employeesRepository.delete(toDel);
        }
    }

    public Employee setEmployeeDataById(Long id, String name, String email, String department, Double salary){
        Employee res = getEmployeeById(id);

        if(res != null){
            res.setName(name);
            res.setEmail(email);
            res.setDepartment(department);
            res.setSalary(salary);

            employeesRepository.save(res);
        }

        return res;
    }
}
