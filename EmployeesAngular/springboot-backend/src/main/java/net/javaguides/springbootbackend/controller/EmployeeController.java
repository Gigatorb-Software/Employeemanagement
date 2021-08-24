package net.javaguides.springbootbackend.controller;

import net.javaguides.springbootbackend.model.Employee;
import net.javaguides.springbootbackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController<EmployeeRepository> {
@Autowired
    private EmployeeRepository employeeRepository;

    //get all employee
    @GetMapping("/Employees")
    public List<Employee> getAllEmployees(){
        return employeeRepository .findAll();
    }
    //create employee rest api
    @PostMapping("/Employees")
    public Employee createEmployee(@RequestBody Employee employee){
        return  employeeRepository.save(employee);
    }
}
