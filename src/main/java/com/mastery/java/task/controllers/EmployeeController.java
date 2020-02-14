package com.mastery.java.task.controllers;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.mastery.java.task.entities.TransferableEntity;
import com.mastery.java.task.jms.consumer.ConsumerJMS;
import com.mastery.java.task.jms.producer.ProducerJMS;
import com.mastery.java.task.services.EmployeeService;
import org.springframework.web.bind.annotation.*;
import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;
import org.springframework.http.ResponseEntity;
import com.mastery.java.task.entities.Employee;
import org.springframework.http.HttpStatus;

import javax.validation.Valid;
import java.util.List;
import java.net.URI;

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

    @PutMapping
    public ResponseEntity<?> updateJobTitleByDepartmentId(@RequestBody @Valid TransferableEntity entity) throws Exception {
        BrokerService broker = BrokerFactory.createBroker(new URI("broker:(tcp://localhost:61616)"));
        broker.start();
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml")) {
            ProducerJMS producerJMS = (ProducerJMS) context.getBean("producerJMS");
            producerJMS.sendMessage(entity);
            ConsumerJMS consumerJMS = (ConsumerJMS) context.getBean("consumerJMS");
            entity = consumerJMS.receiveMessage();
            System.out.println("Consumer receives:\n" + entity);
        } finally {
            broker.stop();
        }
        Long departmentId = entity.getDepartmentId();
        String jobTitle = entity.getJobTitle();
        employeeService.updateJobTitleByDepartmentId(departmentId, jobTitle);
        return (entity != null)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
