package com.mastery.java.task.services;

import com.mastery.java.task.exceptions.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import com.mastery.java.task.repositories.EmployeeRepository;
import com.mastery.java.task.entities.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee create(Employee newEmployee) {
        return employeeRepository.save(newEmployee);
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
                    return employeeRepository.save(employee);
                })
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id \"" + employeeId + "\" doesn't exist! "));
    }

    public List<Employee> findAll() {
        return (List<Employee>) employeeRepository.findAll();
    }

    public Employee findById(Long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id \"" + employeeId + "\" doesn't exist! "));
    }

    public void deleteById(Long employeeId) {
        employeeRepository.findById(employeeId).ifPresentOrElse(
                ignored -> employeeRepository.deleteById(employeeId),
                () -> new EmployeeNotFoundException("Employee with id \"" + employeeId + "\" doesn't exist! ")
        );
    }

    public void deleteAll() {
        employeeRepository.deleteAll();
    }

}
