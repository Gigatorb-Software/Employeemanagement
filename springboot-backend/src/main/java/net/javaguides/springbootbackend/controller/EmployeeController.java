package net.javaguides.springbootbackend.controller;


import net.bytebuddy.asm.Advice;
import net.javaguides.springbootbackend.exception.ResourceNotFoundException;
import net.javaguides.springbootbackend.model.Employee;
import net.javaguides.springbootbackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    //get all employee
    @GetMapping("/Employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    //create employee rest api
    @PostMapping("/Employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }
//get employee by id rest api
    @GetMapping("/Employees/{id}")
    public ResponseEntity <Employee> getEmployeeById(@PathVariable Long id){
        Employee employee=employeeRepository.findById(id).orElseThrow(()-> new
                ResourceNotFoundException("Employee not exits with id:" +id));
        return ResponseEntity.ok(employee);
    }

//update employee rest api
    @PutMapping("/Employees/{id}")
  public  ResponseEntity<Employee> UpdateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails ){
        Employee employee=employeeRepository.findById(id).orElseThrow(()-> new
                ResourceNotFoundException("Employee not exits with id:" + id));
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmailId(employeeDetails.getEmailId());
         Employee updatedEmployee = employeeRepository.save(employee);
         return  ResponseEntity.ok(updatedEmployee);
    }
//delete employee rest api
    @DeleteMapping("/Employees/{id}")
    public ResponseEntity <Map<String,Boolean>> deleteEmployee(@PathVariable  Long id){
        Employee employee=employeeRepository.findById(id).orElseThrow(()-> new
                ResourceNotFoundException("Employee not exits with id:" + id));
        employeeRepository.delete(employee);
        Map<String ,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }






}