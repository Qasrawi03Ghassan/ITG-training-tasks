package com.infinite.employee_manager.Services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.infinite.employee_manager.Models.Employee;

@Service
public class EmployeesService {
    private List<Employee> empsDB = Arrays.asList(
            new Employee(1L, "Alice", "alice@example.com", "HR", 50000.0),
            new Employee(2L, "Bob", "bob@example.com", "IT", 65000.0),
            new Employee(3L, "Charlie", "charlie@example.com", "Finance", 60000.0)
    );

    public List<Employee> getEmpsDB() {
        return empsDB;
    }

    public void setEmpsDB(List<Employee> empDB) {
        this.empsDB = empDB;
    }    
}
