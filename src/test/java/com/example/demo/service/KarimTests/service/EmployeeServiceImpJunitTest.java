package com.example.demo.service.KarimTests.service;

import com.example.demo.domain.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmployeeRepositoryImpl;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.EmployeeServiceImpl;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@DisplayName("Employee Service Tests")
@TestClassOrder(ClassOrderer.ClassName.class)
public class EmployeeServiceImpJunitTest {

    EmployeeService employeeService;
    EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();


    @BeforeEach
    void setUp() {
        employeeService = new EmployeeServiceImpl(employeeRepository);

    }

    @Test
    void countTest() {
        // see EmployeeRepositoryImpTest.countTest()

        Integer num = employeeService.count();
        assertAll(
                () -> assertNotNull(num),
                () -> assertTrue(num > 0),
                () -> assertEquals(3, num)
        );
    }

    @Nested
    public class FIND_tests {


        @Test
        @DisplayName("display all entries")
        void findAllReturnTest() {

            List<Employee> found = employeeService.findAll();
            List<Employee> b = new ArrayList<>();

            assertAll(
                    () -> assertNotNull(found),
                    () -> assertTrue(found.size() > 0),
                    () -> assertSame(b.getClass(), found.getClass())
            );
            int nullcount = 0;
            for (Employee count : found)
                while (nullcount < found.size()) {
                    assertNotNull(count); // if any is null break
                    nullcount += 1;
                }
            // TODO Fix null check for count(see line above)
        }

        @Test
        @DisplayName("check an id")
        void findOneReturn1Test() {

            Employee found = employeeService.findOne(1L);
            assertAll(
                    () -> assertNotNull(found),
                    () -> assertTrue(found.getId() > 0),
                    () -> assertTrue(found.getAge() > 0
                            && found.getAge() < 200),
                    () -> assertTrue(found.getId().equals(1L)),
                    () -> assertFalse(found.getName().isEmpty())
            );
        }

        @Test
        @DisplayName("check the null id")
        void findOneReturnNullTest() {

            try {
                Employee found = employeeService.findOne(null);
                assertNull(found); // Unreachable
            } catch (IllegalArgumentException error) {
                error.printStackTrace();
                assertThrows(IllegalArgumentException.class, () -> employeeRepository.findOne(null));
            }
        }
    }

    @Nested
    public class SAVE_Tests {


        @Test
        void saveNullObjectTest() {
            // Shouldn't save empty obj
            Employee employee = new Employee();
            //assumeTrue(employee == null);
            int temp = employeeService.count();
            employeeService.save(employee);
            assertTrue(temp < employeeService.count());

        }

        @Test
        void saveIdNullTest() {
            assumeTrue(employeeService.count() == 3);

            Employee employee = employeeService.findOne(1L);
            employee.setId(null);
            Employee employee1 = employeeService.save(employee);
            assertEquals(4, employeeService.count());
            assertNotNull(employeeService.findOne(4L));
            assertNotNull(employee1.getId());
        }

        @Test
        @DisplayName("If 0 should assign a new value")
        void saveId0Test() {
            assumeTrue(employeeService.count() == 3);

            Employee employee = employeeService.findOne(1L);
            employee.setId(0L);
            Employee employee1 = employeeService.save(employee);
            assertEquals(4, employeeService.count());

            assertNotNull(employeeService.findOne(4L));
            assertNotNull(employee1.getId());
        }

        @Test
        @DisplayName("Comprobar actualizacion correcta")
        void saveUpdateTest() {
            assumeTrue(employeeService.count() == 3);

            employeeService.findOne(1L).setAge(50);
            Employee employee1 = employeeService.save(employeeService.findOne(1L));
            assertEquals(3, employeeService.count());

            assertNotNull(employee1.getAge());
            assertEquals(50, employeeService.findOne(1L).getAge());
        }

        @Test
        @DisplayName("Saves a negative ID into the Database")
        void saveNegativeID() {
            // Negative Id's are processed Intended usage??
            assumeTrue(employeeService.count() == 3);

            Employee employee = employeeService.findOne(1L);
            employee.setId(-10L);
            Employee employee1 = employeeService.save(employee);
            assertEquals(4, employeeService.count());

            assertNotNull(employeeService.findOne(-10L));
            assertNotNull(employee1.getId());
        }
    }

    @Nested
    public class GETIDMAXtests {

        @Test
        @DisplayName("check empty map")
        void GetMaxIdEmptyTest() {
            employeeService.deleteAll();
            Employee employee = new Employee();
            employeeService.save(employee);

        }

        @Test
        @DisplayName("check valid map")
        void GetMaxIdNotEmptyTest() {
            // No access checked through save
            // returns max id value stored
        }
    }

    @Nested
    public class DELETE_tests {


        @Test
        void deleteNullTest() {
            boolean result = employeeService.delete(null);
            assertFalse(result);
        }

        @Test
        void deleteNotContainsTest() {
            boolean result = employeeService.delete(-1L);
            assertFalse(result);
        }

        @Test
        void deleteOKTest() {
            //assumeTrue(3 == employeeService.count());
            int temp = employeeService.count();
            boolean result = employeeService.delete(1L);
            assertTrue(result);
            assertEquals((temp - 1), employeeService.count());
        }

        @Test
        void deleteAllNotEmptyTest() {
            assertTrue(employeeService.count() > 0);
            employeeService.deleteAll();
            assertEquals(0, employeeService.count());
            //employeeService.save(new Employee());
            //assertTrue(employeeService.count() > 0);
        }

        @Test
        void deleteAllEmptyTest() {
            // Does nothing
            employeeService.deleteAll();
            assertEquals(0, employeeService.count());

        }
    }
}

