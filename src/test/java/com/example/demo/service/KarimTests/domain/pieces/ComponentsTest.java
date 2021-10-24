package com.example.demo.service.KarimTests.domain.pieces;

import com.example.demo.domain.pieces.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("SmartDevice Component Test")
public class ComponentsTest {

    @Nested
    public class BatteryTest{

        Battery battery;
        @BeforeEach
        void setup() {battery = new Battery(1L, 4500D);}

        @Test
        void getIdTest() {
            assertDoesNotThrow(() ->battery.getId());
            assertEquals(1L,battery.getId());
        }
        @Test
        void setIdTest() {
            assertDoesNotThrow(() ->battery.setId(2L));
            assertEquals(2L,battery.getId());
        }
        @Test
        void setCapacityTest() {
            assertDoesNotThrow(() ->battery.setCapacity(1500D));
            assertEquals(1500D,battery.getCapacity());
        }
    }

    @Nested
    public class CameraTest{

        Camera camera;
        @BeforeEach
        void setup() {camera = new Camera(1L, "LensPro",8D);}

        @Test
        void setIdTest() {
            assertDoesNotThrow(() ->camera.setId(2L));
            assertEquals(2L,camera.getId());
        }
        @Test
        void setModelTest() {
            assertDoesNotThrow(() ->camera.setModel("Run down camera"));
            assertEquals("Run down camera",camera.getModel());
        }
        @Test
        void setMegapixelsTest() {
            assertDoesNotThrow(() ->camera.setMegapixels(16D));
            assertEquals(16D,camera.getMegapixels());
        }
    }

    @Nested
    public class CPUTest{

            CPU cpu;
            @BeforeEach
            void setup() {cpu = new CPU(1L,4);}

        @Test
        void setIdTest() {
            assertDoesNotThrow(() ->cpu.setId(2L));
            assertEquals(2L,cpu.getId());
        }
        @Test
        void setOnTest() {
            assertDoesNotThrow(() ->cpu.setOn(false));
            assertEquals(false,cpu.getOn());
        }
        @Test
        void setCoresTest() {
            assertDoesNotThrow(() ->cpu.setCores(9));
            assertEquals(9,cpu.getCores());
        }
    }

    @Nested
    public class HealtMonitorTest{

        HealthMonitor healthMonitor;
        @BeforeEach
        void setup() {healthMonitor = new HealthMonitor(1L,9.8,3);}

        @Test
        void setIdTest() {
            assertDoesNotThrow(() ->healthMonitor.setId(2L));
            assertEquals(2L,healthMonitor.getId());
        }
        @Test
        void setOnTest() {
            assertDoesNotThrow(() ->healthMonitor.setBloodPressure(10D));
            assertEquals(10D,healthMonitor.getBloodPressure());
        }
        @Test
        void setCoresTest() {
            assertDoesNotThrow(() ->healthMonitor.setSleepQuality(9));
            assertEquals(9,healthMonitor.getSleepQuality());
        }
        @Test
        void toStringTest() {
            assertDoesNotThrow(() ->healthMonitor.toString());
            assertTrue(healthMonitor.toString() instanceof String);
        }
    }

    @Nested
    public class RAMTest{

        RAM ram;
        @BeforeEach
        void setup() {ram = new RAM(1L,"DDR5",32);}
        @Test
        void setIdTest() {
            assertDoesNotThrow(() ->ram.setId(2L));
            assertEquals(2L,ram.getId());
        }
        @Test
        void setOnTest() {
            assertDoesNotThrow(() ->ram.setType("DDR2"));
            assertEquals("DDR2",ram.getType());
        }
        @Test
        void setGigabytesTest() {
            assertDoesNotThrow(() ->ram.setGigabytes(16));
            assertEquals(16,ram.getGigabytes());
        }
    }
}
