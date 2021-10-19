package com.example.demo.service.KarimTests.repository;

import com.example.demo.domain.Employee;
import com.example.demo.domain.SmartPhone;
import com.example.demo.domain.pieces.Battery;
import com.example.demo.domain.pieces.CPU;
import com.example.demo.domain.pieces.Camera;
import com.example.demo.domain.pieces.RAM;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmployeeRepositoryImpl;

import com.example.demo.service.SmartPhoneServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;


public class EmployeeRepositoryImpTest {

    EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
    // Constructor creates 3 examples

    @AfterEach
    void resetTest() {
        EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
    }

    @Test
    @DisplayName("Check integrity of List")
    void checkEmployeesTest() {
        // No access to map from outside class
        assertTrue(false);
    }

    @Test
    void checkConstructNullTest() {
        assertNotNull(employeeRepository);
    }

    // COUNT TESTS

    @Test
    void checkCountTypeTest() {
        // No access to map from outside class
        // cannot check .keyset() or .size()
        int tempint= employeeRepository.count();
        assertEquals(3,tempint);
        long templong= employeeRepository.count();
        assertEquals(3,templong);
    }
    @Test
    @DisplayName("Number of elements")
    void countTest() {
        System.out.println("count test2");
        Integer num = employeeRepository.count();
        assertAll(
                () -> assertNotNull(num),
                () -> assertTrue(num > 0),
                () -> assertEquals(3,num)
        );}

    // FIND TESTS
    @Test
    @DisplayName("display all entries")
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
            while(nullcount < found.size() ){
                assertNotNull(count); // if any is null break
                nullcount +=1;
            }
        // TODO Fix null check for count(see line above)
    }
    @Test
    @DisplayName("check an id")
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
    @DisplayName("check the null id")
    void findOneReturnnullTest() {

        try {
            Employee found = employeeRepository.findOne(null);
            assertNull(found); // Unreachable
        }catch(IllegalArgumentException error){
            error.printStackTrace();
            assertThrows(IllegalArgumentException.class, ()->employeeRepository.findOne(null));
        }
    }

    // SAVE TESTS

    @Test
    void saveNullObjectTest() {
        // Shouldn't save empty obj
        Employee employee = new Employee();
        assumeTrue(employee == null);
        int temp = employeeRepository.count();
        Employee employee1 = employeeRepository.save(employee);
        assertNull(employee1);
        assertTrue(temp < employeeRepository.count());

    }

    @Test
    void saveIdNullTest() {
        assumeTrue(employeeRepository.count() == 3);

        Employee employee = employeeRepository.findOne(1L);
        employee.setId(null);
        Employee employee1 = employeeRepository.save(employee);
        assertEquals(4,employeeRepository.count());
        assertNotNull(employeeRepository.findOne(4L));
        assertNotNull(employee1.getId());
    }
    @Test
    @DisplayName("If 0 should assign a new value")
    void saveId0Test() {
        assumeTrue(employeeRepository.count() == 3);

        employeeRepository.findOne(1L).setId(0L);
        Employee employee1 = employeeRepository.save(employeeRepository.findOne(0L));
        assertEquals(4,employeeRepository.count());

        assertNotNull(employeeRepository.findOne(4L));
        assertNotNull(employee1.getId());
    }

    @Test
    @DisplayName("Comprobar actualizacion correcta")
    void saveUpdateTest() {
        assumeTrue(employeeRepository.count() == 3);

        employeeRepository.findOne(1L).setAge(50);
        Employee employee1 = employeeRepository.save(employeeRepository.findOne(0L));
        assertEquals(3, employeeRepository.count());

        assertNotNull(employee1.getAge());
        assertEquals(50, employeeRepository.findOne(1L).getAge());
    }
    @Test
    @DisplayName("Saves a negative ID into the Database")
    void saveNegativeID(){
        // Negative Id's are processed Intended usage??
        assumeTrue(employeeRepository.count() == 3);

        employeeRepository.findOne(1L).setId(-10L);
        Employee employee1 = employeeRepository.save(employeeRepository.findOne(0L));
        assertEquals(4,employeeRepository.count());

        assertNotNull(employeeRepository.findOne(-10L));
        assertNotNull(employee1.getId());
    }

// GET ID MAX TESTS

@Test
@DisplayName("check the null id")
void GetMaxIdEmptyTest() {
        // No access checked through save
    // returns OL
}
    @Test
    @DisplayName("check the null id")
    void GetMaxIdNOTEmptyTest() {
        // No access checked through save
        // returns max id value stored
    }
    // DELETE TESTS

    @Test
    @DisplayName("check the null id")
    void deleteAllNullTest() {
       // ?? cannot check if, no access to employees
        employeeRepository.deleteAll();
    }

    @Test
    void deleteNullTest(){
        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();
        boolean result = service.delete(null);
        assertFalse(result);
    }
    @Test
    void deleteNotContainsTest(){
        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();
        boolean result = service.delete(-1L);
        assertFalse(result);
    }
    @Test
    void deleteOKTest(){
        //assumeTrue(3 == employeeRepository.count());
        int temp = employeeRepository.count();
        boolean result =  employeeRepository.delete(1L);
        assertTrue(result);
        assertEquals((temp-1),employeeRepository.count());
    }

    @Test
    void deleteAllNotEmptyTest(){
        assertTrue(employeeRepository.count()>0);
        employeeRepository.deleteAll();
        assertEquals(0,employeeRepository.count());
        //employeeRepository.save(new Employee());
        //assertTrue(employeeRepository.count() > 0);
    }
    @Test
    void deleteAllEmptyTest(){
        // Does nothing
        EmployeeRepositoryImpl employeeRepository = new EmployeeRepositoryImpl();
        employeeRepository.deleteAll();
        assertEquals(0,employeeRepository.count());

    }




}
