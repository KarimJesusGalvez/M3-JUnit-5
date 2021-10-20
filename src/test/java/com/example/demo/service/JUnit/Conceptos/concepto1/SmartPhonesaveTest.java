package com.example.demo.service.JUnit.Conceptos.concepto1;
import com.example.demo.domain.SmartPhone;
import com.example.demo.domain.pieces.Battery;
import com.example.demo.domain.pieces.CPU;
import com.example.demo.domain.pieces.Camera;
import com.example.demo.domain.pieces.RAM;
import com.example.demo.service.SmartPhoneServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartPhonesaveTest {

    @Test
    void saveNull() {
        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();
        // Fail state FIX
        SmartPhone result = service.save(null);
    }

    @Test
    void saveIdNullTest() {
        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();

        SmartPhone phone1 = new SmartPhone(null, "One plus 9",
                new RAM(1L, "DDR4", 8),
                new Battery(1L, 4500.0),
                new CPU(1L, 4),
                false,
                new Camera(1L, "front camera", 12.5));

        assertEquals(3,service.count());
        SmartPhone result = service.save(phone1);
        assertEquals(4,service.count());

        assertNotNull(phone1);
        assertNotNull(result.getId());
        assertEquals(4, result.getId());
    }
    @Test
    @DisplayName("If 0 should assign a new value")
    void saveIdZeroTest() {
        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();

        SmartPhone phone1 = new SmartPhone(0L, "One plus 9",
                new RAM(1L, "DDR4", 8),
                new Battery(1L, 4500.0),
                new CPU(1L, 4),
                false,
                new Camera(1L, "front camera", 12.5));

        assertEquals(3,service.count());
        SmartPhone result = service.save(phone1);
        assertEquals(4,service.count());

        assertNotNull(phone1);
        assertNotNull(result.getId());
        assertEquals(4, result.getId());
    }

    @Test
    @DisplayName("Comprobar actualizacion correcta")
    void saveUpdateTest() {

        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();

        SmartPhone phone1 = new SmartPhone(1L, "One plus 9editado",
                new RAM(1L, "DDR4", 8),
                new Battery(1L, 4500.0),
                new CPU(1L, 4),
                false,
                new Camera(1L, "front camera", 12.5));

        assertEquals(3,service.count());
        SmartPhone result = service.save(phone1);
        assertEquals(3,service.count()); // No se agrega ninguno

        assertEquals(1L,result.getId());

        assertEquals("One plus 9editado", phone1.getName());

    }

    @Test
    void saveNegativeID(){
        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();

        SmartPhone phone1 = new SmartPhone(-4L, "One plus 9editado",
                new RAM(1L, "DDR4", 8),
                new Battery(1L, 4500.0),
                new CPU(1L, 4),
                false,
                new Camera(1L, "front camera", 12.5));

        assertEquals(3,service.count());
       assertThrows(IllegalArgumentException.class, ()->service.save(phone1));
        assertEquals(3,service.count()); // No se deberia agregar el negativo

    }

    @Test
    void deleteNullTest(){
        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();
       boolean result = service.delete(null);
       assertFalse(result);
    }
    @Test
    void deleteNotContainsTest(){
        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();
        boolean result = service.delete(-1L);
        assertFalse(result);
    }
    @Test
    void deleteOKTest(){
        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();
        boolean result = service.delete(1L);
        assertTrue(result);
    }


    @Test
    void deleteAllTest(){
        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();
        assertTrue(service.count()>0);
        service.deleteAll();
        assertEquals(0,service.count());
    }
}
