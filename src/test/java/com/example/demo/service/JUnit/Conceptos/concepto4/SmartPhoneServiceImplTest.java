package com.example.demo.service.JUnit.Conceptos.concepto4;

import com.example.demo.domain.SmartPhone;
import com.example.demo.service.SmartPhoneServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SmartPhoneServiceImplTest {

    @Test
    void count() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findOne() {
        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();

        SmartPhone phone1 = service.findOne(1L);
        assertNotNull(phone1);
        assertEquals(1L,phone1.getId());

    }

    @Test
    void findOnePhone999Test() {
        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();

        SmartPhone phone1 = service.findOne(999L);
        assertNull(phone1);
        //assertEquals(999L,phone1.getId());
        System.out.println(phone1);

    }
    @Test
    @DisplayName("Throw Illegal argument exception")
    void findOneEXceptionTest(){
        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();
        // Checks if the exception is raised
        assertThrows(IllegalArgumentException.class,() ->service.findOne(null));

    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteAll() {
    }

    @Test
    void findByWifi() {
    }
}