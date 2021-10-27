package com.example.demo.service.KarimTests.repository;

import com.example.demo.domain.Employee;

import com.example.demo.domain.SmartPhone;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmployeeRepositoryImpl;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

@DisplayName("Employee Repository Tests")
@TestClassOrder(ClassOrderer.ClassName.class)

public class EmployeeRepositoryImpTest {

    EmployeeRepository employeeRepository;
    // Constructor creates 3 examples

    @BeforeEach
    void resetTest() {
      employeeRepository = new EmployeeRepositoryImpl();
    }


    @Test
    @DisplayName("Object creation check")
    void NullConstructorTest() {assertNotNull(employeeRepository);}

    @Test
    @DisplayName("Check integrity of List")
    void checkEmployeesTest() {}
    // No access to map from outside class

    @Nested
    @DisplayName("Number of registries tests")
    public class COUNT_tests {

        @Test
        @DisplayName("Number updates when List does")
        void AddNewCountTest() {
            // No access to map from outside class
            // cannot check .keyset() or .size()
            int tempint = employeeRepository.count();
            assertEquals(3, tempint);

            employeeRepository.save(new Employee (4L,"Emp 1", 30));
            long templong = employeeRepository.count();
            assertEquals(4, templong);
        }

        @Test
        @DisplayName("Returns valid number of elements")
        void countTest() {
            System.out.println("count test2");
            Integer num = employeeRepository.count();
            assertAll(
                    () -> assertNotNull(num),
                    () -> assertTrue(num > 0),
                    () -> assertEquals(3, num)
            );
        }
    }
    @Nested
    @DisplayName("Find registries")
    public class FIND_tests {

        @Test
        @DisplayName("Display all entries")
        void findAllReturnTest() {

            List<Employee> found = employeeRepository.findAll();
            List<Employee> b = new ArrayList<Employee>();

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
        @DisplayName("Check a single id")
        void findOneReturn1Test() {

            Employee found = employeeRepository.findOne(1L);
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
        @DisplayName("Check the null id")
        void findOneReturnNullTest() {

            try {
                Employee found = employeeRepository.findOne(null);
                assertNull(found); // Unreachable
            } catch (IllegalArgumentException error) {
                error.printStackTrace();
                assertThrows(IllegalArgumentException.class, () -> employeeRepository.findOne(null));
            }
        }
    }

        @Nested
        @DisplayName("Saves in repository")
        public class SAVE_Tests {


            @Test
            @DisplayName("Saves a null object")
            void saveNullObjectTest() {
                // Shouldn't save empty obj
                Employee employee = new Employee();
                //assumeTrue(employee == null);
                int temp = employeeRepository.count();
                Employee employee1 = employeeRepository.save(employee);
                //assertNull(employee1);
                assertTrue(temp < employeeRepository.count());

            }

            @Test
            @DisplayName("Saves a null-Id object")
            void saveIdNullTest() {
                assumeTrue(employeeRepository.count() == 3);

                Employee employee = employeeRepository.findOne(1L);
                employee.setId(null);
                Employee employee1 = employeeRepository.save(employee);
                assertEquals(4, employeeRepository.count());
                assertNotNull(employeeRepository.findOne(4L));
                assertNotNull(employee1.getId());
            }

            @Test
            @DisplayName("Saves a 0-Id Object")
            void saveId0Test() {
                assumeTrue(employeeRepository.count() == 3);

                Employee employee = employeeRepository.findOne(1L);
                employee.setId(0L);
                Employee employee1 = employeeRepository.save(employee);
                assertEquals(4, employeeRepository.count());

                assertNotNull(employeeRepository.findOne(4L));
                assertNotNull(employee1.getId());
            }

            @Test
            @DisplayName("Updates the registry")
            void saveUpdateTest() {
                assumeTrue(employeeRepository.count() == 3);

                employeeRepository.findOne(1L).setAge(50);
                Employee employee1 = employeeRepository.save(employeeRepository.findOne(1L));
                assertEquals(3, employeeRepository.count());

                assertNotNull(employee1.getAge());
                assertEquals(50, employeeRepository.findOne(1L).getAge());
            }

            @Test
            @DisplayName("Saves a negative ID into the Database")
            void saveNegativeID() {
                // Negative Id's are processed Intended usage??
                assumeTrue(employeeRepository.count() == 3);

                Employee employee = employeeRepository.findOne(1L);
                employee.setId(-10L);
                Employee employee1 = employeeRepository.save(employee);
                assertEquals(4, employeeRepository.count());

                assertNotNull(employeeRepository.findOne(-10L));
                assertNotNull(employee1.getId());
            }
        }
        @Nested
        @DisplayName("Changes Id if necessary")
        public class GETIDMAXtests {
        // The method is only accesible from .save()
            // passing a null or 0 as argument

            @Test
            @DisplayName("Change a null Id")
            void GetMaxIdEmptyTest() {

                employeeRepository.deleteAll();
                Employee employee = new Employee();
                employeeRepository.save(employee);
            }
        }

        @Nested
        @DisplayName("Delete methods")
        public class DELETE_tests {

            @Test
            @DisplayName("Doesn't delete a null ID")
            void deleteNullTest() {
                boolean result = employeeRepository.delete(null);
                assertFalse(result);
            }

            @Test
            @DisplayName("Doesn't delete an invalid ID")
            void deleteNotContainsTest() {
                boolean result = employeeRepository.delete(-1L);
                assertFalse(result);
            }

            @Test
            @DisplayName("Deletes one registry")
            void deleteOKTest() {
                //assumeTrue(3 == employeeRepository.count());
                int temp = employeeRepository.count();
                boolean result = employeeRepository.delete(1L);
                assertTrue(result);
                assertEquals((temp - 1), employeeRepository.count());
            }

            @Test
            @DisplayName("Deletes all registries written")
            void deleteAllNotEmptyTest() {
                assertTrue(employeeRepository.count() > 0);
                employeeRepository.deleteAll();
                assertEquals(0, employeeRepository.count());
                //employeeRepository.save(new Employee());
                //assertTrue(employeeRepository.count() > 0);
            }

            @Test
            @DisplayName("Delete Empty Repository")
            void deleteAllEmptyTest() {
                // Does nothing
                EmployeeRepositoryImpl employeeRepository = new EmployeeRepositoryImpl();
                employeeRepository.deleteAll();
                assertEquals(0, employeeRepository.count());
            }

        }
    }

