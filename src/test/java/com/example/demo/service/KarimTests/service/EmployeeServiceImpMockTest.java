package com.example.demo.service.KarimTests.service;

import com.example.demo.domain.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmployeeRepositoryImpl;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.EmployeeServiceImpl;
import net.bytebuddy.matcher.NegatingMatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
@DisplayName("Employee Interface and implementation")
public class EmployeeServiceImpMockTest {



    //@Mock
    EmployeeRepository mockEmployeeRepository;
    //@InjectMocks
    EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        mockEmployeeRepository= mock(EmployeeRepository.class);

        when(mockEmployeeRepository.count()).thenReturn(3);

        List<Employee> arrayList = new ArrayList<>();
        arrayList.add(new Employee(1L, "Emp 1", 30));
        arrayList.add( new Employee(2L, "Emp 2", 40));
        arrayList.add( new Employee(3L, "Emp 3", 50));
        when(mockEmployeeRepository.findAll()).thenReturn(arrayList);


        when(mockEmployeeRepository.findOne(1L)).thenReturn( new Employee(1L, "Emp 1", 30));

        when(mockEmployeeRepository.save(any(Employee.class))).thenReturn( new Employee(1L, "Emp 1", 30));

        when(mockEmployeeRepository.delete(anyLong())).thenReturn(true);
        // TODO FIX implementation longThat()

        // To return void no config is needed
        // when(mockEmployeeRepository.deleteAll()).thenReturn(null);

        employeeService = new EmployeeServiceImpl(mockEmployeeRepository);
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
        verify(mockEmployeeRepository,atLeastOnce()).count();
    }

    @Nested
    public class FIND_tests {

        @Test
        @DisplayName("display all entries")
        void findAllReturnTest() {

            List<Employee> found = employeeService.findAll();
            List<Employee> b = new ArrayList<Employee>();

            assertAll(
                    () -> assertNotNull(found),
                    () -> assertTrue(found.size() > 0),
                    () -> assertSame(b.getClass(), found.getClass()));
            int nullcount = 0;
            for (Employee count : found)
                while (nullcount < found.size()) {
                    assertNotNull(count); // if any is null break
                    nullcount += 1;
                }
            // TODO Fix null check for count(see line above)
            verify(mockEmployeeRepository).findAll();

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

            verify(mockEmployeeRepository,atLeastOnce()).findOne(1L);


        }

        @Test
        @DisplayName("check the null id")
        void findOneReturnNullTest() {

            try {
                Employee found = employeeService.findOne(null);
                assertNull(found); // Unreachable
            } catch (IllegalArgumentException error) {
                error.printStackTrace();
                assertThrows(IllegalArgumentException.class, () -> mockEmployeeRepository.findOne(null));
            }
            verify(mockEmployeeRepository,atLeastOnce()).findOne(null);


        }

        @Test
        @DisplayName("Checks with optional OK")
        void findOneOptionalOKTest() {

                Optional<Employee> found = employeeService.findOneOptional(1L);
                assertFalse(found.isEmpty());

        }
        @Test
        @DisplayName("Checks with optional Null")
        void findOneOptionalNullTest() {

            try {
                Employee found = employeeService.findOne(null);
                assertNull(found); // Unreachable
            } catch (IllegalArgumentException error) {
                error.printStackTrace();
                assertThrows(IllegalArgumentException.class, () -> mockEmployeeRepository.findOne(null));
            }
        }

        @Test
        @DisplayName("Checks with optional Null")
        void findOneOptionalExceptionTest() {

            when(mockEmployeeRepository.findOne(anyLong())).thenThrow(new IllegalArgumentException());

            Optional<Employee> employeeOptional = employeeService.findOneOptional(800L);

            assertTrue((employeeOptional.isEmpty()));
            verify(mockEmployeeRepository).findOne(anyLong());
        }
    }

    @Nested
    public class SAVE_Tests {


        @Test
        @DisplayName("Save a null object")
        void saveNullObjectTest() {
            // Shouldn't save empty obj, change behaviour?
            Employee employee = new Employee();
            //assumeTrue(employee == null);
            int temp = employeeService.count();
            Employee employee1 = employeeService.save(employee);
            assertNotNull(employee1);

            verify(mockEmployeeRepository,atLeastOnce()).save(employee);



        }

        @Test
        void saveIdNullTest() {
            assumeTrue(employeeService.count() == 3);

            Employee employee = employeeService.findOne(1L);
            employee.setId(null);
            Employee employee1 = employeeService.save(employee);
            assertNull(employee1.getId());
            verify(mockEmployeeRepository,atLeastOnce()).save(employee);

        }

        @Test
        @DisplayName("If 0 should assign a new value")
        void saveId0Test() {

            // TODO
            assumeTrue(employeeService.count() == 3);

            Employee employee = employeeService.findOne(1L);
            employee.setId(0L);
            Employee employee1 = employeeService.save(employee);
            assertEquals(1L,employee1.getId());
            verify(mockEmployeeRepository,atLeastOnce()).findOne(null);

        }

        @Test
        @DisplayName("Comprobar actualizacion correcta")
        void saveUpdateTest() {

            // TODO
            assumeTrue(employeeService.count() == 3);

            employeeService.findOne(1L).setAge(50);
            Employee employee1 = employeeService.save(employeeService.findOne(1L));
            assertEquals(3, employeeService.count());

            assertNotNull(employee1.getAge());
            assertEquals(50, employeeService.findOne(1L).getAge());
            verify(mockEmployeeRepository,atLeastOnce()).save(employeeService.findOne(1L));

        }

        @Test
        @DisplayName("Saves a negative ID into the Database")
        void saveNegativeID() {
            //TODO
            // Negative Id's are processed Intended usage??
            assumeTrue(employeeService.count() == 3);

            Employee employee = employeeService.findOne(1L);
            employee.setId(-10L);
            Employee employee1 = employeeService.save(employee);
            assertEquals(4, employeeService.count());

            assertNotNull(employeeService.findOne(-10L));
            assertNotNull(employee1.getId());

            verify(mockEmployeeRepository,atLeastOnce()).save(employee);

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
            when(mockEmployeeRepository.delete(anyLong())).thenReturn(false);

            boolean result = employeeService.delete(-1L);
            assertFalse(result);
        }

        @Test
        void deleteOKTest() {

            boolean result = employeeService.delete(1L);
            assertTrue(result);

        }

        @Test
        void deleteAllNotEmptyTest() {
            employeeService.deleteAll();
            verify(mockEmployeeRepository).deleteAll();
        }
        @Test
        void deleteAllEmptyTest() {
            // Does nothing
            employeeService.deleteAll();
            employeeService.deleteAll();
            verify(mockEmployeeRepository,times(2)).deleteAll();
        }
    }
}

