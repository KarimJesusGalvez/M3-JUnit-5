package com.example.demo.service.JUnit.Conceptos.concepto5;

import com.example.demo.domain.SmartPhone;
import com.example.demo.service.SmartPhoneServiceImpl;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LifeCycleClassTest {

    SmartPhoneServiceImpl service;
    @BeforeAll //Executes before the test  class call
    static void setUp(){
        System.out.println("before each setup");

    }
    @AfterAll
    static void tearDown(){
        System.out.println("tear down after method");

    }
    @Test
    @DisplayName("contar smartphones")
    void countTest() {
        System.out.println("count test");
        Integer num = service.count();

        //Si un assert falla el resto no se ejecutan

        assertNull(null); // null is null test passed
        assertNotNull(num); // num is not null test fails
        assertTrue(num > 0);
        assertEquals(3, num);
    }
    @Test
    @DisplayName("Buscar todos los Smartphones")
    void findAllTest(){
        List<SmartPhone> smartphones = service.findAll();
        assertNotNull(smartphones);
        assertEquals(3,smartphones.size());

    }
}
