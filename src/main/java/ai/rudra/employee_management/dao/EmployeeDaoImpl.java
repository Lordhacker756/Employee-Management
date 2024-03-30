package ai.rudra.employee_management.dao;

import ai.rudra.employee_management.entities.Employee;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao{

//    This persistant context is going to automatically inject Entity manager into the variable
    @PersistenceContext
    EntityManager entityManager;

    public EmployeeDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> getAllEmployees() {
        TypedQuery<Employee> query = entityManager.createQuery("SELECT e from Employee e", Employee.class);
        return query.getResultList();
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    @Transactional
    public Employee createEmployee(Employee employee) {
        entityManager.persist(employee);
        return entityManager.find(Employee.class, employee.getId());
    }

    @Override
    @Transactional
    public Employee updateEmployee(Integer id, Employee employee) {
        Employee retrievedEmployee = getEmployeeById(id);
        retrievedEmployee.setFirstName(employee.getFirstName());
        retrievedEmployee.setLastName(employee.getLastName());
        retrievedEmployee.setEmail(employee.getEmail());

        return entityManager.merge(retrievedEmployee);
    }

    @Override
    @Transactional
    public void deleteEmployee(Integer id) {
        Employee employee = getEmployeeById(id);
        entityManager.remove(employee);
    }
}
