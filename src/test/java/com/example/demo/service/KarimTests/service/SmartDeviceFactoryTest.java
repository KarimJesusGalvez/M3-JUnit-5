package com.example.demo.service.KarimTests.service;

import com.example.demo.domain.SmartPhone;
import com.example.demo.domain.SmartWatch;
import com.example.demo.service.SmartDeviceFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("SmartDevice Factory tests")
class SmartDeviceFactoryTest {

    @Test
    @DisplayName("Creates phone")
    void createByTypePHONETest() {
        SmartPhone smartPhone = (SmartPhone) SmartDeviceFactory.createByType("phone");
    }
    @Test
    @DisplayName("Creates Smartwatch")
    void createByTypeWATCHTest() {

        SmartWatch smartWatch = (SmartWatch) SmartDeviceFactory.createByType("watch");
    }
    @Test
    @DisplayName("Cannot create if non-standard string")
    void createByTypeOtherStringTest() {

        assertThrows(IllegalArgumentException.class, () ->SmartDeviceFactory.createByType("wrong_string"));
    }
    @Test
    @DisplayName("Cannot create if type null")
    void createByTypeNullTest() {
        assertThrows(NullPointerException.class, () ->SmartDeviceFactory.createByType(null));
    }
    @Test
    @DisplayName("Cannot create if type empty")
    void createByTypeEmptyTest() {
        assertThrows(IllegalArgumentException.class, () ->SmartDeviceFactory.createByType(""));
    }
}