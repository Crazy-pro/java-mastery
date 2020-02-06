package com.mastery.java.task.controllers;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.mastery.java.task.services.EmployeeService;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastery.java.task.entities.Employee;
import org.springframework.http.MediaType;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static com.mastery.java.task.entities.Gender.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeServiceMock;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void findAllTest() throws Exception {
        List<Employee> employeesFromMock = getEmployees();
        Mockito.when(employeeServiceMock.findAll()).thenReturn(employeesFromMock);
        mockMvc.perform(get("/employees")
                .content(mapper.writeValueAsString(employeesFromMock))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findByIdTest() throws Exception {
        Employee employeeFromMock = getEmployees().get(0);
        Mockito.when(employeeServiceMock.findById(1L)).thenReturn(employeeFromMock);
        mockMvc.perform(get("/employees/1")
                .content(mapper.writeValueAsString(employeeFromMock))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void createTest() throws Exception {
        Employee employeeFromMock = new Employee();
        employeeFromMock.setFirstName("EmployeeForTest");
        Employee employee = new Employee();
        employee.setFirstName(employeeFromMock.getFirstName());
        Mockito.when(employeeServiceMock.create(any(Employee.class))).thenReturn(employee);
        mockMvc.perform(post("/employees")
                .content(mapper.writeValueAsString(employeeFromMock))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(employeeFromMock.getFirstName()));
    }

    @Test
    public void updateTest() throws Exception {
        Employee employeeFromMock = getEmployees().get(0);
        Mockito.when(employeeServiceMock.update(any(Employee.class), any(Long.class))).thenReturn(employeeFromMock);
        mockMvc.perform(put("/employees/1")
                .content(mapper.writeValueAsString(employeeFromMock))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.firstName").value(employeeFromMock.getFirstName()));
    }

    private List<Employee> getEmployees() {
        return Arrays.asList(
                new Employee(1L, "Alex", "Block", 1L, "Java Developer", MALE, "20.03.2000"),
                new Employee(2L, "Kate", "Block", 2L, "Front-End Developer", FEMALE, "20.03.2000"),
                new Employee(3L, "Alex", "Block", 3L, "Back-End Developer", MALE, "20.03.2000"),
                new Employee(4L, "Alex", "Block", 4L, "Full-Stack Developer", FEMALE, "20.03.2000")
        );
    }

}
