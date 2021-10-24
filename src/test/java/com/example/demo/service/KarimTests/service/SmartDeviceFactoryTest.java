package com.example.demo.service.KarimTests.service;

import com.example.demo.domain.SmartDevice;
import com.example.demo.domain.SmartPhone;
import com.example.demo.domain.SmartWatch;
import com.example.demo.service.SmartDeviceFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SmartDeviceFactoryTest {

    @Test
    void createByTypePHONETest() {

        SmartPhone smartPhone = (SmartPhone) SmartDeviceFactory.createByType("phone");
    }
    @Test
    void createByTypeWATCHTest() {

        SmartWatch smartWatch = (SmartWatch) SmartDeviceFactory.createByType("watch");
    }
    @Test
    void createByTypeOtherStringTest() {

        assertThrows(IllegalArgumentException.class, () ->SmartDeviceFactory.createByType("wrong_string"));
    }
    @Test
    void createByTypeNullTest() {
        assertThrows(IllegalArgumentException.class, () ->SmartDeviceFactory.createByType(null));
    }
    @Test
    void createByTypeEmptyTest() {
        assertThrows(IllegalArgumentException.class, () ->SmartDeviceFactory.createByType(""));
    }
}