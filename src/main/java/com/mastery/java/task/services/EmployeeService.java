package com.mastery.java.task.services;

import com.mastery.java.task.exceptions.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import com.mastery.java.task.repositories.EmployeeRepository;
import com.mastery.java.task.entities.Employee;

import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.List;

import static com.mastery.java.task.Application.LOGGER;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee create(Employee newEmployee) {
        Employee createdEmployee = employeeRepository.save(newEmployee);
        LOGGER.info("A new employee is created in the database");
        return createdEmployee;
    }

    public Employee update(Employee newEmployee, Long employeeId) {
        return employeeRepository.findById(employeeId)
                .map(employee -> {
                    employee.setFirstName(newEmployee.getFirstName());
                    employee.setLastName(newEmployee.getLastName());
                    employee.setDepartmentId(newEmployee.getDepartmentId());
                    employee.setJobTitle(newEmployee.getJobTitle());
                    employee.setGender(newEmployee.getGender());
                    employee.setDateOfBirth(newEmployee.getDateOfBirth());
                    Employee updatedEmployee = employeeRepository.save(employee);
                    LOGGER.info("Employee with id " + employeeId + " was updated");
                    return updatedEmployee;
                })
                .orElseThrow(() -> {
                    LOGGER.log(Level.WARNING, "Employee with id " + employeeId + " doesn't exist!");
                    return new EmployeeNotFoundException("Employee with id " + employeeId + " doesn't exist!");
                });
    }

    public List<Employee> findAll() {
        List<Employee> employees = (List<Employee>) employeeRepository.findAll();
        LOGGER.info("Employees were returned from the database");
        return employees;
    }

    public Employee findById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> {
                    LOGGER.log(Level.WARNING, "Employee with id " + employeeId + " doesn't exist!");
                    return new EmployeeNotFoundException("Employee with id " + employeeId + " doesn't exist!");
                });
        LOGGER.info("Employee with id " + employeeId + " was returned from the database");
        return employee;
    }

    public void deleteById(Long employeeId) {
        employeeRepository.findById(employeeId).ifPresentOrElse(
                ignored -> {
                    employeeRepository.deleteById(employeeId);
                    LOGGER.info("Employee with id " + employeeId + " has been deleted");
                },
                () -> {
                    LOGGER.log(Level.WARNING, "Employee with id " + employeeId + " doesn't exist!");
                    throw new EmployeeNotFoundException("Employee with id " + employeeId + " doesn't exist!");
                }
        );

    }

    public void deleteAll() {
        employeeRepository.deleteAll();
        LOGGER.info("Employees have been removed from the database");
    }

}
