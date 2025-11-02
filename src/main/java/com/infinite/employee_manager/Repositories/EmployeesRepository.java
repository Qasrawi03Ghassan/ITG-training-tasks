package com.infinite.employee_manager.Repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.infinite.employee_manager.Models.Employee;


public interface EmployeesRepository extends JpaRepository<Employee, Long>{
    public Optional<Employee> findById(Long id);
}
