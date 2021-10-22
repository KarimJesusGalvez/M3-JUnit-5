package com.example.demo.service.Mockito;

import com.example.demo.domain.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmployeeServiceImpMockTest {

    EmployeeRepository repository;
    EmployeeService service;

    @BeforeEach
    void setUp() {

        repository = mock(EmployeeRepository.class);
        service = new EmployeeServiceImpl(repository);
    }

    @Test
    void count(){

        // given (Prepare enviroment) configure mock
        when(repository.count()).thenReturn(3);
        //if someone calls .count() the return will be 3 // check type in original count()

        // when (execute code)
        Integer result = service.count();

        // then(Assert and verify)
        assertNotNull(result);
        assertEquals(3,result);
    }
    @Test
    void findOneTest(){

        // given (Prepare enviroment) configure mock
        Employee employee = new Employee(1L,"Empleado1",45);
        when(repository.findOne(1L)).thenReturn(employee);
        //if someone calls findOne() the return will be employee // check return type in original method

        // when (execute code)
        Employee result = service.findOne(1L);

        // then(Assert and verify)
        assertNotNull(result);

    }
}
