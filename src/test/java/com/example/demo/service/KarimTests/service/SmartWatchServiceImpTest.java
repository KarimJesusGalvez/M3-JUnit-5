package com.example.demo.service.KarimTests.service;

import com.example.demo.domain.SmartWatch;
import com.example.demo.domain.pieces.*;
import com.example.demo.service.SmartWatchServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@DisplayName("SmartWatch Service tests")
public class SmartWatchServiceImpTest {


    @Nested
    public class Count_tests {

        @Test
        void countTest() {

            SmartWatchServiceImpl service = new SmartWatchServiceImpl();
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
        @DisplayName("Display all entries")
        void findAllReturnTest() {
            SmartWatchServiceImpl service = new SmartWatchServiceImpl();

            List<SmartWatch> found = service.findAll();
            List<SmartWatch> b = new ArrayList<>();

            assertAll(
                    () -> assertNotNull(found),
                    () -> assertTrue(found.size() > 0),
                    () -> assertSame(b.getClass(), found.getClass())
            );
            int nullcount = 0;
            for (SmartWatch count : found)
                while (nullcount < found.size()) {
                    assertNotNull(count); // if any is null break
                    nullcount += 1;
                }
            // TODO Fix null check for count(see line above)
        }

        @Test
        @DisplayName("Check an id")
        void findOneReturn1Test() {

            SmartWatchServiceImpl service = new SmartWatchServiceImpl();

            SmartWatch found = service.findOne(1L);
            assertAll(
                    () -> assertNotNull(found),
                    () -> assertTrue(found.getId() > 0),
                    () -> assertTrue(found.getMonitor().getSleepQuality() == 0
                            && found.getWifi()),
                    () -> assertTrue(found.getId().equals(1L)),
                    () -> assertEquals( "Fitbit sense",found.getName())
            );
        }

        @Test
        @DisplayName("check the null id")
        void findOneReturnNullTest() {

            SmartWatchServiceImpl service = new SmartWatchServiceImpl();

            try {
                SmartWatch found = service.findOne(null);
                assertNull(found); // Unreachable
            } catch (IllegalArgumentException error) {
                error.printStackTrace();
                assertThrows(IllegalArgumentException.class, () -> service.findOne(null));
            }
        }
    }


    @Nested
    public class Save {
        @Test
        @DisplayName("Doesn't save a null object")
        void saveNull() {
            SmartWatchServiceImpl service = new SmartWatchServiceImpl();
            // Fail state FIX
            assertThrows(NullPointerException.class,() ->service.save(null));
        }

        @Test
        @DisplayName("check the null id")
        void saveIdNullTest() {
            SmartWatchServiceImpl service = new SmartWatchServiceImpl();

            SmartWatch watch1 = new SmartWatch(null, "Fitbit sense",
                    new RAM(1L, "DDR4", 2),
                    new Battery(1L, 4500.0),
                    new CPU(1L, 4),
                    true,
                    new HealthMonitor(1L, 0.0, 0));

            assertEquals(3, service.count());
            SmartWatch result = service.save(watch1);
            assertEquals(4, service.count());

            assertNotNull(watch1);
            assertNotNull(result.getId());
            assertEquals(4, result.getId());
        }

        @Test
        @DisplayName("Save 0, should assign a new value")
        void saveIdZeroTest() {
            SmartWatchServiceImpl service = new SmartWatchServiceImpl();

            SmartWatch Watch1 = new SmartWatch(0L, "One plus 9",
                    new RAM(1L, "DDR4", 8),
                    new Battery(1L, 4500.0),
                    new CPU(1L, 4),
                    false,
                    new HealthMonitor(1L, 0.0, 0));

            assertEquals(3, service.count());
            SmartWatch result = service.save(Watch1);
            assertEquals(4, service.count());

            assertNotNull(Watch1);
            assertNotNull(result.getId());
            assertEquals(4, result.getId());
        }

        @Test
        @DisplayName("Updates correctly")
        void saveUpdateTest() {

            SmartWatchServiceImpl service = new SmartWatchServiceImpl();

            SmartWatch Watch1 = new SmartWatch(1L, "One plus 9editado",
                    new RAM(1L, "DDR4", 8),
                    new Battery(1L, 4500.0),
                    new CPU(1L, 4),
                    false,
                    new HealthMonitor(1L, 0.0, 0));

            assertEquals(3, service.count());
            SmartWatch result = service.save(Watch1);
            assertEquals(3, service.count()); // No se agrega ninguno
            assertEquals(1L, result.getId());
            assertEquals("One plus 9editado", Watch1.getName());

        }

        @Test
        @DisplayName("Saving a negative ID")
        void saveNegativeID() {
            SmartWatchServiceImpl service = new SmartWatchServiceImpl();

            SmartWatch Watch1 = new SmartWatch(-4L, "One plus 9editado",
                    new RAM(1L, "DDR4", 8),
                    new Battery(1L, 4500.0),
                    new CPU(1L, 4),
                    false,
                    new HealthMonitor(1L, 0.0, 0));

            assertEquals(3, service.count());
            assertDoesNotThrow(() -> service.save(Watch1));
            assertEquals(4, service.count());

        }

        @Test
        @DisplayName("Returns the max id in DB")
        void getMaxIdNullTest() {

            try {
                SmartWatchServiceImpl service = new SmartWatchServiceImpl();

                service.deleteAll();
                SmartWatch Watch1 = new SmartWatch(0L, "One plus 9editado",
                        new RAM(1L, "DDR4", 8),
                        new Battery(1L, 4500.0),
                        new CPU(1L, 4),
                        false,
                        new HealthMonitor(1L, 0.0, 0));
                service.save(Watch1);

                assertSame(service.findOne(1L), Watch1);
            }
            catch(NoSuchElementException error){
                error.printStackTrace();
                System.out.println("Error in SmartWatchServiceImpl.getMaxSmartWatchId:76");
            }
        }
    }

    @Nested
    @DisplayName("Delete test")
    public class delete {

        @Test
        @DisplayName("check the null id")
        void deleteNullTest() {
            SmartWatchServiceImpl service = new SmartWatchServiceImpl();
            boolean result = service.delete(null);
            assertFalse(result);
        }

        @Test
        @DisplayName("Delete non existing registries")
        void deleteNotContainsTest() {
            SmartWatchServiceImpl service = new SmartWatchServiceImpl();
            boolean result = service.delete(-1L);
            assertFalse(result);
        }

        @Test
        @DisplayName("Deletes the id provided")
        void deleteOKTest() {
            SmartWatchServiceImpl service = new SmartWatchServiceImpl();
            boolean result = service.delete(1L);
            assertTrue(result);

        }

        @Test
        @DisplayName("Deletes all the registries")
        void deleteAllTest() {
            SmartWatchServiceImpl service = new SmartWatchServiceImpl();
            assumeTrue(service.count() > 0);
            service.deleteAll();
            assertEquals(0, service.count());
        }
    }
}
