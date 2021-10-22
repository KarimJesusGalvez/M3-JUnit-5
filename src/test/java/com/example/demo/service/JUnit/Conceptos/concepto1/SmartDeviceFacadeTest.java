package com.example.demo.service.JUnit.Conceptos.concepto1;

import com.example.demo.domain.SmartDevice;
import com.example.demo.domain.SmartPhone;
import com.example.demo.domain.SmartWatch;
import com.example.demo.service.SmartDeviceFacade;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDeviceFacadeTest {

    @Test
    void createSmartPhone(){
    SmartDevice result = SmartDeviceFacade.createSmartPhone();
    assertNotNull(result);
    assertNotNull(result.getRam());
    assertNotNull(result.getBattery());

    assertTrue(result instanceof SmartPhone);
    assertFalse(result instanceof SmartWatch);
    assertNotNull(((SmartPhone) result).getCamera());
    }
    @Test
    void createSmartWatch(){
        SmartDevice result = SmartDeviceFacade.createSmartWatch();
        assertNotNull(result);
        assertNotNull(result.getCpu().getCores());
        assertNotNull(result.getRam().getGigabytes());
        assertNotNull(result.getBattery().getId());

        assertFalse(result instanceof SmartPhone);
        assertTrue(result instanceof SmartWatch);
        assertNotNull(((SmartWatch) result).getMonitor());
    }
}
