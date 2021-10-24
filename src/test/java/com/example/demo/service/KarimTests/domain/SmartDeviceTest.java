package com.example.demo.service.KarimTests.domain;

import com.example.demo.domain.SmartDevice;
import com.example.demo.domain.pieces.Battery;
import com.example.demo.domain.pieces.CPU;
import com.example.demo.domain.pieces.RAM;
import com.example.demo.service.SmartPhoneServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SmartDeviceTest {

   SmartDevice smartDevice;
    @BeforeEach
    void setup(){
        smartDevice = new SmartDevice() {
            // TODO Make sense of this code !!
        };
    }

    @Test
    void setName() {
        assertDoesNotThrow(() ->smartDevice.setName("Nombre1"));
        assertEquals("Nombre1",smartDevice.getName());

    }

    @Test
    void setRam() {
        assertDoesNotThrow(() ->smartDevice.setRam(new RAM(3L, "DDR5", 32)));
        assertEquals(32,smartDevice.getRam().getGigabytes());
        assertTrue(smartDevice.getRam() instanceof RAM);
    }

    @Test
    void setBattery() {
        assertDoesNotThrow(() ->smartDevice.setBattery(new Battery(3L, 9500.0)));
        assertTrue(smartDevice.getBattery() instanceof Battery);
    }

    @Test
    void setCpu() {
        assertDoesNotThrow(() ->smartDevice.setCpu(new CPU(1L,4)));
        assertEquals(4,smartDevice.getCpu().getCores());
        assertTrue(smartDevice.getCpu() instanceof CPU);
    }


    @Test
    void setWifi() {
        assertDoesNotThrow(() -> smartDevice.setWifi(false));
        assertTrue(false == smartDevice.getWifi());
    }

    @Test
    void testToString() {
        assertDoesNotThrow(() -> smartDevice.toString());

    }
}