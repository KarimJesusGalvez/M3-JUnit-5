package com.example.demo.service.JUnit.Conceptos.Concepto6;

import com.example.demo.service.SmartPhoneServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DisabledTest {
    @Test
    @DisplayName("contar smartphones")
    void countTest() {
        System.out.println("count test");
        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();
        Integer num = service.count();

        //Si un assert falla el resto no se ejecutan

        assertNull(null); // null is null test passed
        assertNull(num); // num is not null test fails
        assertTrue(num > 0);
        assertEquals(3, num);
    }
    @Disabled("Deshabilitado por bug #TODO nº4")
    @Test
    @DisplayName("contar smartphones 2")
    void countTest2() {
        System.out.println("count test2");
        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();
        Integer num = service.count();

        // Se ejecutan todos
        assertAll(
                () -> assertNotNull(null),
                () -> assertNotNull(num),
                () -> assertNull(null), // null is null test passed
                () -> assertNull(num), // num is not null test fails
                () -> assertTrue(num > 0),
                () -> assertEquals(3,num)
        );}
}
