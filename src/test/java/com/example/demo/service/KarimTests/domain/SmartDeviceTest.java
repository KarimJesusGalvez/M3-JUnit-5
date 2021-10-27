package com.example.demo.service.KarimTests.domain;

import com.example.demo.domain.SmartDevice;
import com.example.demo.domain.SmartPhone;
import com.example.demo.domain.pieces.Battery;
import com.example.demo.domain.pieces.CPU;
import com.example.demo.domain.pieces.RAM;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("SmartDevice Common Methods Test")
@TestClassOrder(ClassOrderer.ClassName.class)

class SmartDeviceTest {


   SmartPhone smartDevice;
    @BeforeEach
    void setup(){
        smartDevice = new SmartPhone() {};
            //abstract classes implemmentation  NOT recommended but possible
            // SmartDevice smartdevice = new SmartDevice(){}
    }

    @Test
    @DisplayName("set new name")
    void setName() {
        assertDoesNotThrow(() ->smartDevice.setName("Nombre1"));
        assertEquals("Nombre1",smartDevice.getName());
    }

    @Test
    @DisplayName("set new RAM")
    void setRam() {
        assertDoesNotThrow(() ->smartDevice.setRam(new RAM(3L, "DDR5", 32)));
        assertEquals(32,smartDevice.getRam().getGigabytes());
        assertTrue(smartDevice.getRam() instanceof RAM);
    }

    @Test
    @DisplayName("set new Battery")
    void setBattery() {
        assertDoesNotThrow(() ->smartDevice.setBattery(new Battery(3L, 9500.0)));
        assertTrue(smartDevice.getBattery() instanceof Battery);
    }

    @Test
    @DisplayName("set new CPU")
    void setCpu() {
        assertDoesNotThrow(() ->smartDevice.setCpu(new CPU(1L,4)));
        assertEquals(4,smartDevice.getCpu().getCores());
        assertTrue(smartDevice.getCpu() instanceof CPU);
    }

    @Test
    @DisplayName("set new wifi")
    void setWifi() {
        assertDoesNotThrow(() -> smartDevice.setWifi(false));
        assertTrue(!smartDevice.getWifi());
    }

    @Test
    @DisplayName("print attributes")
    void testToString() {
        SmartDevice smartDevice = new SmartDevice() {};
        assertDoesNotThrow(() -> smartDevice.toString());
    }
}