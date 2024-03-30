package ai.rudra.employee_management.controllers;

import ai.rudra.employee_management.dao.EmployeeDao;
import ai.rudra.employee_management.entities.Employee;
import ai.rudra.employee_management.services.EmployeeService;
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

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
//    Get all the employees
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> allEmployees(){
        employeeList = employeeService.getAllEmployees();
        return new ResponseEntity<>(employeeList, HttpStatus.FOUND);
    }

//    Get a single employee
    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int employeeId){
        return new ResponseEntity<>(employeeService.getEmployeeById(employeeId), HttpStatus.FOUND);
    }

//    Create a new employee
    @PostMapping("/employees")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        Employee emp = employeeService.createEmployee(employee);
        return new ResponseEntity<>(emp,HttpStatus.CREATED);
    }

//    Update an employee
    @PutMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int employeeId, @RequestBody Employee employee){
        Employee updateEmployee = employeeService.updateEmployee(employeeId, employee);
        return new ResponseEntity<>(updateEmployee, HttpStatus.CREATED);
    }

//    Delete an employee
    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int employeeId){
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>("Employee Deleted Successfully", HttpStatus.OK);
    }

}
