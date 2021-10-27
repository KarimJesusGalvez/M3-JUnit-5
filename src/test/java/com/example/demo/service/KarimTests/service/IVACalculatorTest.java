package com.example.demo.service.KarimTests.service;

import com.example.demo.service.IVACalculator;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("IVA Calculator tests")
@TestClassOrder(ClassOrderer.ClassName.class)
public class IVACalculatorTest {

    IVACalculator ivaCalculator = new IVACalculator();



    @Test
    void calculatePositive() {
        double a = ivaCalculator.calculateIVA(1);
        assertEquals(0.21,a);
    }
    @Test
    void calculate0() {
        double a = ivaCalculator.calculateIVA(0);
        assertEquals(0,a);
    }
    @Test
    void calculateNegative() {
        double a = ivaCalculator.calculateIVA(-1);
        assertEquals(-0.21,a);
    }
    @Test
    void calculateNumchar() {
        double a = ivaCalculator.calculateIVA('1');
        assertTrue(a > 0);
    }
    @Test
    void calculateLetterchar() {
        double a = ivaCalculator.calculateIVA('a');
        System.out.println(a);
        // Char can be converted to double
        // Not intended usage??
        assertEquals(20.37,a);
    }
    @Test
    void calculateSymbolchar() {
        double a = ivaCalculator.calculateIVA('\'');
        System.out.println(a);
        // Char can be converted to double
        // Not intended usage??
        assertEquals(8.19,a);
    }
    @Test
    void calculateDoubleclass() {
        double a = ivaCalculator.calculateIVA(2D);
        assertTrue(a > 0);
    }
}

