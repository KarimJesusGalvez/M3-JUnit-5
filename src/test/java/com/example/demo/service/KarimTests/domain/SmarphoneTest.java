package com.example.demo.service.KarimTests.domain;

import com.example.demo.domain.SmartDevice;
import com.example.demo.domain.SmartPhone;
import com.example.demo.domain.pieces.Battery;
import com.example.demo.domain.pieces.CPU;
import com.example.demo.domain.pieces.Camera;
import com.example.demo.domain.pieces.RAM;
import com.example.demo.service.SmartPhoneServiceImpl;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("SmartPhone Class Test")
@TestClassOrder(ClassOrderer.ClassName.class)
public class SmarphoneTest {

    SmartDevice smartPhone;
    SmartDevice smartPhoneempty;
    SmartPhone smartPhoneProper;

    @BeforeEach
    void setup() {
        smartPhoneempty = new SmartPhone();
        smartPhone = new SmartPhone(4L,
                "One plus 9editado",
                new RAM(1L, "DDR4", 8),
                new Battery(1L, 4500.0),
                new CPU(1L, 4),
                false,
                new Camera(1L,
                        "front camera", 12.5));
        smartPhoneProper = new SmartPhone(4L, "One plus 9editado",
                new RAM(1L, "DDR4", 8),
                new Battery(1L, 4500.0),
                new CPU(1L, 4), false,
                new Camera(1L, "front camera", 12.5));
    }

    @Nested
    @Order(1)
    public class contructorTest {

        @Test
        @DisplayName("Construct empty object")
        void emptyConstructorTest() {
            SmartDevice smartPhone = new SmartPhone();
            assertThat(smartPhone, instanceOf(SmartDevice.class));
            assertThat(smartPhone, instanceOf(SmartPhone.class));
        }

        @Test
        @DisplayName("Construct filled object")
        void fullConstructorTest() {


            assertThat(smartPhone, instanceOf(SmartDevice.class));
            assertThat(smartPhone, instanceOf(SmartPhone.class));

            assertEquals(4500, smartPhone.getBattery().getCapacity());
            assertEquals(false, smartPhone.getWifi());
            assertEquals(4, smartPhone.getCpu().getCores());

        }
    }
    @Nested
    @DisplayName("Getter Setters and Print")
    public class get_set_toStringTest {

        @Test
        void getCameraTest() {
            Camera temp = new Camera(1L, "front camera", 12.5);
            assertEquals(smartPhoneProper.getCamera().getMegapixels(), temp.getMegapixels());
        }

        @Test
        void setCameraTest() {
            smartPhoneProper.setCamera(new Camera(2L, "High Contrast camera", 0.5));
        }

        @Test
        void toStringTest() {
            String temp = smartPhoneempty.toString();
            assertEquals(temp, "SmartPhone [camera=null, getId()=null, getName()=null, getRam()=null, getBattery()=null, getCpu()=null, getWifi()=null]");
        }
    }
}
