package com.example.demo.service.KarimTests.domain;

import com.example.demo.domain.Employee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeTest {

    @Test
    void setName() {
        Employee employee = new Employee();
        assertDoesNotThrow(() -> employee.setName("Nombre1"));
        assertEquals("Nombre1",employee.getName());

    }

}
