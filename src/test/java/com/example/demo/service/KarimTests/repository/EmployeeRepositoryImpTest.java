package com.example.demo.service.KarimTests.repository;

import com.example.demo.domain.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmployeeRepositoryImpl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeRepositoryImpTest {

    EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
    // Constructor creates 3 examples

    @AfterEach
    void resettest() {
        EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
    }

    @Test
    @DisplayName("Check integrity of List")
    void checkEmployees() {
        // No access to map from outside class
        assertTrue(false);
    }

    @Test
    void checkConstructNull() {
        assertNotNull(employeeRepository);
    }
    @Test
    void checkCountType() {
        // No access to map from outside class
        // cannot check .keyset() or .size()
        int tempint= employeeRepository.count();
        assertEquals(3,tempint);
        long templong= employeeRepository.count();
        assertEquals(3,templong);
    }
    @Test
    @DisplayName("Number of elements")
    void countTest() {
        System.out.println("count test2");
        Integer num = employeeRepository.count();
        assertAll(
                () -> assertNotNull(num),
                () -> assertTrue(num > 0),
                () -> assertEquals(3,num)
        );}
    @Test
    @DisplayName("display all entries")
    void findAllReturn() {

        List<Employee> found = employeeRepository.findAll();
        List<Employee> b = new ArrayList<Employee>();

        assertAll(
                () -> assertNotNull(found),
                () -> assertTrue(found.size() > 0),
                () -> assertSame(b.getClass(), found.getClass())
        );

        for (Employee count : found)
            assertNotNull(count); // if any is null break
        // TODO Fix null check for count(see line above)
    }
    @Test
    @DisplayName("check an id")
    void findOneReturn1() {

        Employee found = employeeRepository.findOne(1L);
        assertAll(
                () -> assertNotNull(found),
                () -> assertTrue(found.getId() > 0),
                () -> assertTrue(found.getAge() > 0
                && found.getAge() < 999),
        () -> assertTrue(found.getId().equals(1L)),
        () -> assertFalse(found.getName().isEmpty())
        );
    }
    @Test
    @DisplayName("check the null id")
    void findOneReturnnull() {

        try {
            Employee found = employeeRepository.findOne(null);
            assertNull(found); // Unreachable
        }catch(IllegalArgumentException error){
            error.printStackTrace();
            assertThrows(IllegalArgumentException.class, ()->employeeRepository.findOne(null));
        }
    }
    @Test
    @DisplayName("check the null id")
    void deleteAllNull() {


    }




}
