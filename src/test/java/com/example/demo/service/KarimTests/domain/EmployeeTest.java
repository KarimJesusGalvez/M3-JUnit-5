package com.example.demo.service.KarimTests.domain;

import com.example.demo.domain.Employee;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Employee set Test")
@TestClassOrder(ClassOrderer.ClassName.class)
public class EmployeeTest {

    @Test
    @DisplayName("Change Name")
    void setName() {
        Employee employee = new Employee();
        assertDoesNotThrow(() -> employee.setName("Nombre1"));
        assertEquals("Nombre1",employee.getName());

    }

}
