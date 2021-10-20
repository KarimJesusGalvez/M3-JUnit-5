package com.example.demo.service.Mockito;

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

        // given (Prepare enviroment)
        when(repository.count()).thenReturn(3);

        // when (execute code)
        Integer result = service.count();

        // then(Assert and verify)
        assertNotNull(result);
        assertEquals(3,result);
    }
}
