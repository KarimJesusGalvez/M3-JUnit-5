package com.example.demo.service.JUnit.Conceptos.concepto1;

import com.example.demo.domain.SmartPhone;
import com.example.demo.service.SmartPhoneServiceImpl;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
@DisplayName("Tests operaciones CRUD con smartphones")
public class AssertionTest {

    //testing unitario == funcion
    @Test
    @DisplayName("contar smartphones")
    void countTest() {
        System.out.println("count test");
        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();
    Integer num = service.count();
        assertNull(null); // null is null test passed
        assertNull(num); // num is not null test fails
        assertTrue(num > 0);
        assertEquals(3,num);
    }

    @Test
    @DisplayName("Buscar todos los Smartphones")
    void findAllTest(){
        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();
    List<SmartPhone> smartphones = service.findAll();
    assertNotNull(smartphones);
    assertEquals(3,smartphones.size());

    }
    @Test
    void holamundotest(){}
}
