package com.example.demo.service.Mockito;

import com.example.demo.domain.Employee;
import com.example.demo.repository.EmployeeRepositoryImpl;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("Service operations with Employees.class")
class EmployeeServiceImplTest {

    EmployeeService service;

    @BeforeEach
    void setup(){
        service = new EmployeeServiceImpl(new EmployeeRepositoryImpl());
    }
    @Test
    void countTest() {
        Integer count = service.count();
        assertNotNull(count);
        assertEquals(3,count);
    }

    @Test
    void findAllTest() {
        List<Employee> employees = service.findAll();
        assertNotNull(employees);
        assertNotNull(employees.get(0).getName());
    }

    @Test
    void findOne1Test() {
        Employee employee = service.findOne((1L));
        assertNotNull(employee);
        assertEquals(1L,employee.getId());
        assertNotNull(employee.getName());
    }

    @Test
    void findOneNotExistsTest() {
        Employee employee = service.findOne(999L);
        assertNotNull(employee);
    }
    @Test
    void findOneExceptionTest() {
        assertThrows(IllegalArgumentException.class,
                ()->service.findOne(null));
    }
    @Test
    void findOne1OptionalTest(){
        Optional<Employee> employeeOpt = service.findOneOptional(1L);
        assertTrue(employeeOpt.isPresent());
        assertEquals(1L,employeeOpt.get().getId());
    }

    @Test
    void findOnenullOptionalTest(){
        Optional<Employee> employeeOpt = service.findOneOptional(null);
        assertTrue(employeeOpt.isEmpty());
    }
    @Test
    void findOneNotExistsOptionalTest(){
        Optional<Employee> employeeOpt = service.findOneOptional(999L);
        assertTrue(employeeOpt.isEmpty());
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteAll() {
    }
}