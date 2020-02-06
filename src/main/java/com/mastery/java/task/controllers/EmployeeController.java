package com.mastery.java.task.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import com.mastery.java.task.services.EmployeeService;
import org.springframework.web.bind.annotation.*;
import com.mastery.java.task.entities.Employee;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    Employee findById(@PathVariable Long employeeId) {
        return employeeService.findById(employeeId);
    }

    @PostMapping("/employees")
    Employee create(@RequestBody @Valid Employee newEmployee) {
        return employeeService.create(newEmployee);
    }

    @PutMapping("/employees/{employeeId}")
    Employee update(@RequestBody @Valid Employee newEmployee, @PathVariable Long employeeId) {
        return employeeService.update(newEmployee, employeeId);
    }

    @DeleteMapping("/employees/{employeeId}")
    void deleteById(@PathVariable Long employeeId) {
        employeeService.deleteById(employeeId);
    }

    @DeleteMapping("/employees")
    void deleteAll() {
        employeeService.deleteAll();
    }

}
