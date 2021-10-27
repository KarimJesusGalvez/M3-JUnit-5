package com.example.demo.service.KarimTests.service;

import com.example.demo.domain.SmartPhone;
import com.example.demo.domain.SmartWatch;
import com.example.demo.service.SmartDeviceFacade;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("SmartDevice Facade tests")
@TestClassOrder(ClassOrderer.ClassName.class)
class SmartDeviceFacadeTest {


    @Test
    @DisplayName("Cannot create a Phone from a Watch")
    void createSmartPhoneWrongTest() {
            assertThrows(ClassCastException.class, () -> SmartDeviceWrongPhoneCast());
    }
    private void SmartDeviceWrongPhoneCast() {SmartWatch smartWatch = (SmartWatch) SmartDeviceFacade.createSmartPhone();}

    @Test
    @DisplayName("Creates a Smartphone")
    void createSmartPhoneOKTest() {

            SmartPhone smartPhone = (SmartPhone) SmartDeviceFacade.createSmartPhone();

            assertEquals("DDR4",smartPhone.getRam().getType());
            assertEquals(12.5,smartPhone.getCamera().getMegapixels());
            assertTrue(smartPhone.getWifi());
    }

    @Test
    @DisplayName("Cannot create a Watch from a Phone")
    void createSmartWatchWrongTest() {

        assertThrows(ClassCastException.class, () -> SmartDeviceWrongWatchCast());
    }
    private void SmartDeviceWrongWatchCast() {
        SmartPhone smartPhone = (SmartPhone) SmartDeviceFacade.createSmartWatch();
    }

    @Test
    @DisplayName("Creates an SmartWatch")
    void createSmartWatchOKTest() {

            SmartWatch smartWatch = (SmartWatch) SmartDeviceFacade.createSmartWatch();

            assertEquals("DDR4",smartWatch.getRam().getType());
            assertEquals(0,smartWatch.getMonitor().getBloodPressure());
    }
}