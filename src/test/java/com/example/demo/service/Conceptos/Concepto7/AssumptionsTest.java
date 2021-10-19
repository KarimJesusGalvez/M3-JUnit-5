package com.example.demo.service.Conceptos.Concepto7;

import com.example.demo.service.SmartPhoneServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class AssumptionsTest {

    @Test
    void test1() {
        String username = System.getenv("USERNAME");
        assumeTrue(username.equals(("pc_testing")));

        // if assume is false the test is disabled
        System.out.println("count test");
        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();
        Integer num = service.count();
        assertNull(null); // null is null test passed
        assertNull(num); // num is not null test fails
        assertTrue(num > 0);
        assertEquals(3, num);
    }

}
