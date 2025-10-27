package com.infinite.employee_manager.Services;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.infinite.employee_manager.Models.Employee;
import com.infinite.employee_manager.Repositories.EmployeesRepository;

@Service
public class EmployeesService {
    private Map<Long,Employee> empsDB = Map.of(1L, new Employee(1L,"TestEmp","test@emp.com","Back-end",3000.0));

    
}
