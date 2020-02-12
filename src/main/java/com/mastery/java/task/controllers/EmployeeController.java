package com.mastery.java.task.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import com.mastery.java.task.services.EmployeeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.mastery.java.task.entities.Employee;
import org.springframework.http.HttpStatus;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> findAll() {
        final List<Employee> employees = employeeService.findAll();
        return (employees != null)
                ? new ResponseEntity<>(employees, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> findById(@PathVariable Long employeeId) {
        final Employee employee = employeeService.findById(employeeId);
        return (employee != null)
                ? new ResponseEntity<>(employee, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody @Valid Employee newEmployee) {
        employeeService.create(newEmployee);
        return (newEmployee != null)
                ? new ResponseEntity<>(newEmployee, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<Employee> update(@RequestBody @Valid Employee newEmployee, @PathVariable Long employeeId) {
        employeeService.update(newEmployee, employeeId);
        return (newEmployee != null)
                ? new ResponseEntity<>(newEmployee, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<?> deleteById(@PathVariable Long employeeId) {
        employeeService.deleteById(employeeId);
        return (employeeId != null)
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll() {
        employeeService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
