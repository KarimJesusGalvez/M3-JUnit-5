package com.example.demo.service.KarimTests.service;

import com.example.demo.domain.SmartPhone;
import com.example.demo.domain.pieces.Battery;
import com.example.demo.domain.pieces.CPU;
import com.example.demo.domain.pieces.Camera;
import com.example.demo.domain.pieces.RAM;
import com.example.demo.service.SmartPhoneServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@DisplayName("SmartPhone Service tests")
public class SmartPhoneServiceImpTest {


    @Nested
    public class Count_tests {

    @Test
    void countTest() {

        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();
//        creates 3 obj
        Integer num = service.count();

        assertAll(
                () -> assertNotNull(num),
                () -> assertTrue(num > 0),
                () -> assertEquals(3, num)
        );
    }
}
    @Nested
    public class FIND_tests {


        @Test
        @DisplayName("display all entries")
        void findAllReturnTest() {
            SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();

            List<SmartPhone> found = service.findAll();
            List<SmartPhone> b = new ArrayList<>();

            assertAll(
                    () -> assertNotNull(found),
                    () -> assertTrue(found.size() > 0),
                    () -> assertSame(b.getClass(), found.getClass())
            );
            int nullcount = 0;
            for (SmartPhone count : found)
                while (nullcount < found.size()) {
                    assertNotNull(count); // if any is null break
                    nullcount += 1;
                }
            // TODO Fix null check for count(see line above)
        }

        @Test
        @DisplayName("check an id")
        void findOneReturn1Test() {

            SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();

            SmartPhone found = service.findOne(1L);
            assertAll(
                    () -> assertNotNull(found),
                    () -> assertTrue(found.getId() > 0),
                    () -> assertTrue(found.getCamera().getMegapixels() == 12.5
                            && found.getWifi() == false),
                    () -> assertTrue(found.getId().equals(1L)),
                    () -> assertEquals("One plus 9",found.getName())
            );
        }

        @Test
        @DisplayName("check the null id")
        void findOneReturnNullTest() {

            SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();

            try {
                SmartPhone found = service.findOne(null);
                assertNull(found); // Unreachable
            } catch (IllegalArgumentException error) {
                error.printStackTrace();
                assertThrows(IllegalArgumentException.class, () -> service.findOne(null));
            }
        }


        @Test
        @DisplayName("Find by wifi")
        void findbyWifiTest() {

            SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();

            List<SmartPhone> found = service.findByWifi(true);
            assertAll(
                    () -> assertNotNull(found),
                    () -> assertTrue(found.get(0).getId() > 0),
                    () -> assertTrue(found.get(0).getCamera().getMegapixels() == 8.5
                            && found.get(0).getWifi() == true),
                    () -> assertTrue(found.get(1).getId().equals(3L)),
                    () -> assertFalse(found.get(0).getName().equals( "One plus 9"))
            );
        }

        @Test
        @DisplayName("Find by wifi in null")
        void findbyWifiNullTest() {

            SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();
            service.deleteAll();
            List<SmartPhone> found = service.findByWifi(true);
            assertTrue(found.isEmpty());

        }
    }

    @Nested
    public class Save {
        @Test
        @DisplayName("Doesn't save a null object")
        void saveNull() {
            SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();
            assertThrows(NullPointerException.class,() ->service.save(null));
        }

        @Test
        @DisplayName("saves with null id")
        void saveIdNullTest() {
            SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();

            SmartPhone phone1 = new SmartPhone(null, "One plus 9",
                    new RAM(1L, "DDR4", 8),
                    new Battery(1L, 4500.0),
                    new CPU(1L, 4),
                    false,
                    new Camera(1L, "front camera", 12.5));

            assertEquals(3, service.count());
            SmartPhone result = service.save(phone1);
            assertEquals(4, service.count());

            assertNotNull(phone1);
            assertNotNull(result.getId());
            assertEquals(4, result.getId());
        }

        @Test
        @DisplayName("If 0 should assign a new value")
        void saveIdZeroTest() {
            SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();

            SmartPhone phone1 = new SmartPhone(0L, "One plus 9",
                    new RAM(1L, "DDR4", 8),
                    new Battery(1L, 4500.0),
                    new CPU(1L, 4),
                    false,
                    new Camera(1L, "front camera", 12.5));

            assertEquals(3, service.count());
            SmartPhone result = service.save(phone1);
            assertEquals(4, service.count());

            assertNotNull(phone1);
            assertNotNull(result.getId());
            assertEquals(4, result.getId());
        }

        @Test
        @DisplayName("Updates correctly")
        void saveUpdateTest() {

            SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();

            SmartPhone phone1 = new SmartPhone(1L, "One plus 9editado",
                    new RAM(1L, "DDR4", 8),
                    new Battery(1L, 4500.0),
                    new CPU(1L, 4),
                    false,
                    new Camera(1L, "front camera", 12.5));

            assertEquals(3, service.count());
            SmartPhone result = service.save(phone1);
            assertEquals(3, service.count()); // No se agrega ninguno

            assertEquals(1L, result.getId());

            assertEquals("One plus 9editado", phone1.getName());

        }

        @Test
        @DisplayName("Saving a negative ID")
        void saveNegativeID() {
            SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();

            SmartPhone phone1 = new SmartPhone(-4L, "One plus 9editado",
                    new RAM(1L, "DDR4", 8),
                    new Battery(1L, 4500.0),
                    new CPU(1L, 4),
                    false,
                    new Camera(1L, "front camera", 12.5));

            assertDoesNotThrow(() -> service.save(phone1));
            System.out.println("Saves a negative id");

        }

        @Test
        @DisplayName("returns last id used")
        void getMaxIdNullTest() {
            SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();

            service.deleteAll();
            SmartPhone phone1 = new SmartPhone(0L, "One plus 9editado",
                    new RAM(1L, "DDR4", 8),
                    new Battery(1L, 4500.0),
                    new CPU(1L, 4),
                    false,
                    new Camera(1L, "front camera", 12.5));

            service.save(phone1);

            assertTrue(service.findOne(1L) == phone1);
        }
    }

    @Nested
    @DisplayName("Delete test")
    public class delete {

        @Test
        @DisplayName("check the null id")
        void deleteNullTest() {
            SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();
            boolean result = service.delete(null);
            assertFalse(result);
        }

        @Test
        @DisplayName("Delete non existing registries")
        void deleteNotContainsTest() {
            SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();
            boolean result = service.delete(-1L);
            assertFalse(result);
        }

        @Test
        @DisplayName("Deletes the id provided")
        void deleteOKTest() {
            SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();
            boolean result = service.delete(1L);
            assertTrue(result);
        }


        @Test
        @DisplayName("Deletes all the registries")
        void deleteAllTest() {
            SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();
            assumeTrue(service.count() > 0);
            service.deleteAll();
            assertEquals(0, service.count());
        }
    }
}
