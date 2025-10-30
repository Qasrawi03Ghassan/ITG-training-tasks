package com.infinite.employee_manager.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.infinite.employee_manager.Models.Employee;

@Service
public class EmployeesService {

    private static Long id = 8L; 

    private List<Employee> empsDB = new ArrayList<>();

    public void setUpDB(){
        empsDB.add(new Employee(1L, "Ahmad", "Ahmad@example.com", "Back-End", 6000.0));
        empsDB.add(new Employee(2L, "Adel", "Adel@example.com", "Front-End", 4000.0));
        empsDB.add(new Employee(3L, "Omar", "Omar@example.com", "QA", 2000.0));
        empsDB.add(new Employee(4L, "Hasan", "Hasan@example.com", "Back-End", 6000.0));
        empsDB.add(new Employee(5L, "Tuqa", "Tuqa@example.com", "Front-End", 4000.0));
        empsDB.add(new Employee(6L, "Maram", "Maram@example.com", "QA", 2000.0));
        empsDB.add(new Employee(7L, "Hiba", "Hiba@example.com", "Finance", 3000.0));
    }

    public EmployeesService(){
        setUpDB();
    }

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

    public Employee createNewEmployee(String name, String email, String department, Double salary){
        Employee newEmp = new Employee(id++,name,email,department,salary);

        empsDB.add(newEmp);

        return newEmp;
    }

    public void deleteEmployeeById(Long id){
        empsDB.remove(getEmployeeById(id));
    }

    public Employee setEmployeeDataById(Long id, String name, String email, String department, Double salary){
        Employee res = getEmployeeById(id);

        res.setName(name);
        res.setEmail(email);
        res.setDepartment(department);
        res.setSalary(salary);

        return res;
    }
}
