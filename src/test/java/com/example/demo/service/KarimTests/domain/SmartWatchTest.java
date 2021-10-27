package com.example.demo.service.KarimTests.domain;

import com.example.demo.domain.SmartDevice;
import com.example.demo.domain.SmartWatch;
import com.example.demo.domain.pieces.*;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("SmartWatch Class Test")
@TestClassOrder(ClassOrderer.ClassName.class)
public class SmartWatchTest {

    SmartDevice smartWatch;
    SmartDevice smartWatchEmpty;
    SmartWatch smartWatchProper;

    @BeforeEach
    void setup() {
        smartWatchEmpty = new SmartWatch();
        smartWatch = new SmartWatch(4L,
                "One plus 9editado",
                new RAM(1L, "DDR4", 8),
                new Battery(1L, 4500.0),
                new CPU(1L, 4),
                false,
                new HealthMonitor(1L, 12D, 5));
        smartWatchProper =  new SmartWatch(4L,
                "One plus 9editado",
                new RAM(1L, "DDR4", 8),
                new Battery(1L, 4500.0),
                new CPU(1L, 4),
                false, new HealthMonitor(1L, 12D, 5));
    }
    @Nested
    @DisplayName("Constructor tests")
    public class contructorTest {

        @Test
        @DisplayName("Create empty object")
        void emptyConstructorTest() {
            SmartDevice smartwatch = new SmartWatch();
            assertThat(smartwatch, instanceOf(SmartDevice.class));
            assertThat(smartWatchEmpty, instanceOf(SmartWatch.class));
        }

        @Test
        @DisplayName("Create Filled Object")
        void fullConstructorTest() {

            assertThat(smartWatch, instanceOf(SmartDevice.class));
            assertThat(smartWatchProper, instanceOf(SmartWatch.class));

            assertEquals(8, smartWatch.getRam().getGigabytes());
            assertEquals("DDR4", smartWatch.getRam().getType());
            assertEquals(12D, smartWatchProper.getMonitor().getBloodPressure());
        }
    }

    @Nested
    @DisplayName("Getter Setter Print tests")
    public class get_set_toStringTest {

        @Test
        @DisplayName("Change Monitor")
        void getMonitorTest() {
            assertEquals(5,smartWatchProper.getMonitor().getSleepQuality());
        }

        @Test
        @DisplayName("Change Camera")
        void setCameraTest() {
            HealthMonitor temp = new HealthMonitor(4L, 50D, 100);
            smartWatchProper.setMonitor(temp);
            assertEquals(100,smartWatchProper.getMonitor().getSleepQuality());
        }

        @Test
        @DisplayName("Print values")
        void toStringTest() {
            String temp = smartWatchEmpty.toString();
            assertEquals("SmartWatch [monitor=null, getId()=null, getName()=null, getRam()=null, getBattery()=null, getCpu()=null, getWifi()=null]", temp);
        }
    }
}
