package com.example.demo.service.JUnit.Conceptos.concepto3;

import com.example.demo.service.SmartPhoneServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AssertAllTest {
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
            @Test
            @DisplayName("try catch test no es valido")
            void countTest3() {
                System.out.println("count test3");
                SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();
                Integer num = service.count();

                // Se ejecutan todos
            try {
                assertNull(null); // null is null test passed



            }
            catch (AssertionError error){
            }
            try {
                assertNull(num); // num is not null test fails
                }
            catch (AssertionError error){}
                try {
                    assertEquals(3, num);                }
                catch (AssertionError error){}
                try {
                    assertTrue(num > 0);
                }
                catch (AssertionError error){}

            }
    }


