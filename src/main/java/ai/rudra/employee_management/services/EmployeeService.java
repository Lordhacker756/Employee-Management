package ai.rudra.employee_management.services;

import ai.rudra.employee_management.entities.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> getAllEmployees();

    public Employee getEmployeeById(Integer id);

    public Employee createEmployee(Employee employee);

    public Employee updateEmployee(Integer id, Employee employee);

    public void deleteEmployee(Integer id);
}
