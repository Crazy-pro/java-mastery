package com.mastery.java.task.repositories;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.mastery.java.task.entities.Employee;

import java.util.Optional;
import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    Optional<List<Employee>> findByDepartmentId(@Param("departmentId") Long departmentId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE employees e SET e.job_title = :jobTitle WHERE e.department_id = :departmentId", nativeQuery = true)
    void updateJobTitleByDepartmentId(@Param("departmentId") Long departmentId, @Param("jobTitle") String jobTitle);

}
