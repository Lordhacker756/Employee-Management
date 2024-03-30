package ai.rudra.employee_management.controllers;

import ai.rudra.employee_management.dao.EmployeeDao;
import ai.rudra.employee_management.entities.Employee;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {
//    Load the data of all the employees for easy response

    List<Employee> employeeList;
    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeController(EmployeeDao employeeDao){
        this.employeeDao = employeeDao;
    }

    @PostConstruct
    public void getAllEmployees(){
        employeeList = employeeDao.getAllEmployees();
    }

//    Get all the employees
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> allEmployees(){
        return new ResponseEntity<>(employeeList, HttpStatus.FOUND);
    }

//    Get a single employee
    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int employeeId){
        return new ResponseEntity<>(employeeDao.getEmployeeById(employeeId), HttpStatus.FOUND);
    }

//    Create a new employee
    @PostMapping("/employees")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        Employee emp = employeeDao.createEmployee(employee);
        return new ResponseEntity<>(emp,HttpStatus.CREATED);
    }

//    Update an employee
    @PutMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int employeeId, @RequestBody Employee employee){
        Employee updateEmployee = employeeDao.updateEmployee(employeeId, employee);
        return new ResponseEntity<>(updateEmployee, HttpStatus.CREATED);
    }

//    Delete an employee
    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int employeeId){
        employeeDao.deleteEmployee(employeeId);
        return new ResponseEntity<>("Employee Deleted Successfully", HttpStatus.OK);
    }

}
