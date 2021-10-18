package com.example.demo.service.KarimTests.service;

import com.example.demo.service.IRPFCalculator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IRPFCalculatorTest {

    IRPFCalculator irpfCalculator = new IRPFCalculator();

    // TODO test access level

    @Test
    void calculatePositive() {
        double a = irpfCalculator.calculateIRPF(1);
        assertEquals(0.15,a);
    }
    @Test
    void calculate0() {
        double a = irpfCalculator.calculateIRPF(0);
        assertEquals(0,a);
    }
    @Test
    void calculateNegative() {
        double a = irpfCalculator.calculateIRPF(-1);
        assertEquals(-0.15,a);
    }
    @Test
    void calculateNumchar() {
        double a = irpfCalculator.calculateIRPF('1');
        assertTrue(a > 0);
    }
    @Test
    void calculateLetterchar() {
        double a = irpfCalculator.calculateIRPF('a');
        System.out.println(a);
        // Char can be converted to double
        // Not intended usage??
        assertTrue(false);
    }
    @Test
    void calculateSymbolchar() {
        double a = irpfCalculator.calculateIRPF('\'');
        System.out.println(a);
        // Char can be converted to double
        // Not intended usage??
        assertTrue(false);
    }
    @Test
    void calculateDoubleclass() {
        Double b = 2D;
        double a = irpfCalculator.calculateIRPF(2D);
        assertTrue(a > 0);
    }
}

