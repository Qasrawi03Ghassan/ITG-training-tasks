package com.infinite.employee_manager.Services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.infinite.employee_manager.Models.Employee;

@Service
public class EmployeesService {
    private List<Employee> empsDB = Arrays.asList(
            new Employee(1L, "Ahmad", "Ahmad@example.com", "Back-End", 60000.0),
            new Employee(2L, "Adel", "Adel@example.com", "Front-End", 4000.0),
            new Employee(3L, "Omar", "Omar@example.com", "QA", 2000.0),
            new Employee(4L, "Hasan", "Hasan@example.com", "Back-End", 60000.0),
            new Employee(5L, "Tuqa", "Tuqa@example.com", "Front-End", 4000.0),
            new Employee(6L, "Maram", "Maram@example.com", "QA", 2000.0),
            new Employee(7L, "Hiba", "Hiba@example.com", "HR", 15000.0)
    );

    public List<Employee> getEmpsDB() {
        return empsDB;
    }

    public void setEmpsDB(List<Employee> empDB) {
        this.empsDB = empDB;
    }    

    public Employee getEmployeeById(Long id){
        Employee res = null;
        for (Employee employee : empsDB) {
            if(employee.getId().equals(id)){
                res = employee;
            }
        }
        return res;
    }
}
