package com.mastery.java.task.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.mastery.java.task.entities.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
