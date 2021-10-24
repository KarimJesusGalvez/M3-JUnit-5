package com.example.demo.service.KarimTests.service;

import com.example.demo.domain.SmartPhone;
import com.example.demo.domain.SmartWatch;
import com.example.demo.service.SmartDeviceFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SmartDeviceFacadeTest {



    @Test
    void createSmartPhoneWrongTest() {

            assertThrows(ClassCastException.class, () -> SmartDeviceWrongPhoneCast());

    }

    private void SmartDeviceWrongPhoneCast() {
        SmartWatch smartWatch = (SmartWatch) SmartDeviceFacade.createSmartPhone();
    }

    @Test
    void createSmartPhoneOKTest() {

            SmartPhone smartPhone = (SmartPhone) SmartDeviceFacade.createSmartPhone();

            assertEquals("DDR4",smartPhone.getRam().getType());
            assertEquals(12.5,smartPhone.getCamera().getMegapixels());
            assertTrue(smartPhone.getWifi());
    }

    private void SmartDeviceWrongWatchCast() {
        SmartPhone smartPhone = (SmartPhone) SmartDeviceFacade.createSmartWatch();
    }
    @Test
    void createSmartWatchWrongTest() {

        assertThrows(ClassCastException.class, () -> SmartDeviceWrongWatchCast());

        }

    @Test
    void createSmartWatchOKTest() {

            SmartWatch smartWatch = (SmartWatch) SmartDeviceFacade.createSmartWatch();

            assertEquals("DDR4",smartWatch.getRam().getType());
            assertEquals(0,smartWatch.getMonitor().getBloodPressure());
    }
}